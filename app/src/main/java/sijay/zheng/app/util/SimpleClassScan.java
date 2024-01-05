package cn.zheng.sijay.j.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author sijay
 * @desc SimpleClassScan
 * @date 2023/12/19 14:30
 */
@Slf4j
public class SimpleClassScan {

    private final Set<Class<?>> classSet;
    private final Map<String, ProtocolHandler> handlerMap;

    public SimpleClassScan() {
        classSet = new HashSet<>();
        handlerMap = new HashMap<>();
//注册一个文件扫描器
        FileProtocolHandler fileProtocolHandler = new FileProtocolHandler();
//注册一个jar包扫描器
        JarProtocolHandler jarProtocolHandler = new JarProtocolHandler();
        handlerMap.put(fileProtocolHandler.handleProtocol(), fileProtocolHandler);
        handlerMap.put(jarProtocolHandler.handleProtocol(), jarProtocolHandler);
    }

    public Set<Class<?>> scan(String... basePackages) {
        ClassLoader classLoader = this.getClass()
                                      .getClassLoader();
        for (String basePackage : basePackages) {
//将com.aa.bb 替换成 com/aa/bb
            String resourceName = basePackage.replace('.', '/') + "/";
            Enumeration<URL> resources = null;
            try {
//通过classLoader获取所有的resources
                resources = classLoader.getResources(resourceName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (resources == null) {
                continue;
            }
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                String protocol = url.getProtocol();
//根据url中protocol类型查找适用的解析器
                ProtocolHandler protocolHandler = handlerMap.get(protocol);
                if (protocolHandler == null) {
                    throw new RuntimeException("need support protocol [" + protocol + "]");
                }
                protocolHandler.handle(basePackage, url);
            }
        }
        return classSet;
    }

    /**
     * 将class添加到结果中
     *
     * @param classFullName 形如com.aa.bb.cc.Test.class的字符串
     */
    private void addResult(String classFullName) {
        Class<?> aClass = null;
        try {
            aClass = Class.forName(classFullName.substring(0, classFullName.length() - 6));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (aClass != null) {
            classSet.add(aClass);
        }
    }

    /**
     * 检查一个文件名是否是class文件名
     *
     * @param fileName 文件名
     * @return
     */
    private boolean checkIsNotClass(String fileName) {
//只要class类型的文件
        boolean isClass = fileName.endsWith(".class");
        if (!isClass) {
            return true;
        }
//排除内部类
        return fileName.indexOf('$') != -1;
    }

    public Set<Class<?>> getClassSet() {
        return classSet;
    }

    /**
     * 协议处理器
     */
    private interface ProtocolHandler {
        /**
         * 适配的协议
         *
         * @return
         */
        String handleProtocol();

        /**
         * 处理url，最后需要调用{@link #addResult(String)}将结果存储到result中
         *
         * @param url
         */
        void handle(String basePackage, URL url);
    }

    /**
     * jar包解析器
     */
    private class JarProtocolHandler implements ProtocolHandler {

        @Override
        public String handleProtocol() {
            return "jar";
        }

        @Override
        public void handle(String basePackage, URL url) {
            try {
                String resourceName = basePackage.replace('.', '/') + "/";
                JarURLConnection conn = (JarURLConnection) url.openConnection();
                JarFile jarFile = conn.getJarFile();
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
//遍历jar包中的所有项
                    JarEntry jarEntry = entries.nextElement();
                    String entryName = jarEntry.getName();
                    if (!entryName.startsWith(resourceName)) {
                        continue;
                    }
                    if (checkIsNotClass(entryName)) {
                        continue;
                    }
                    String classNameFullName = entryName.replace('/', '.');
                    addResult(classNameFullName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件解析器
     */
    private class FileProtocolHandler implements ProtocolHandler {

        @Override
        public String handleProtocol() {
            return "file";
        }

        @Override
        public void handle(String basePackage, URL url) {
            File rootFile = new File(url.getFile());
            findClass(rootFile, File.separator + basePackage.replace('.', File.separatorChar) + File.separator);
        }

        /**
         * 递归的方式查找class文件
         *
         * @param rootFile    当前文件
         * @param subFilePath 子路径
         */
        private void findClass(File rootFile, String subFilePath) {
            if (rootFile == null) {
                return;
            }
//如果是文件夹
            if (rootFile.isDirectory()) {
                File[] files = rootFile.listFiles();
                if (files == null) {
                    return;
                }
                for (File file : files) {
                    findClass(file, subFilePath);
                }
            }
            String fileName = rootFile.getName();
            if (checkIsNotClass(fileName)) {
                return;
            }
            String path = rootFile.getPath();
            int i = path.indexOf(subFilePath);
            String subPath = path.substring(i + 1);
            String fullClassPath = subPath.replace(File.separatorChar, '.');
            addResult(fullClassPath);
        }
    }
}

