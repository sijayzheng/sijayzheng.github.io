package cn.sijay.suap.core.utils;

import cn.sijay.suap.core.base.BaseException;
import cn.sijay.suap.core.constant.ExceptionConstant;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <strong>FileUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {

    public static final String DATE_FILE_NAME_FORMAT = "yyyyMMdd";
    public static final String DATE_TIME_FILE_NAME_FORMAT = "yyyyMMddHHmmss";
    public static final String CODE_UTF8 = "UTF-8";
    public static final String CODE_UTF8_BOM = "UTF-8_BOM";
    public static final String CODE_GBK = "GBK";
    private static final int BYTE_SIZE = 8;

    public static String concatPath(String... path) {
        return String.join(File.separator, path);
    }

    public static void mkDirOrTouch(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if (file.isDirectory()) {
                if (file.mkdirs()) {
                    log.info("创建文件夹：{}", file.getAbsolutePath());
                }
            } else {
                if (file.getParentFile().mkdirs()) {
                    log.info("创建父文件夹：{}", file.getParentFile().getAbsolutePath());
                    try {
                        if (file.createNewFile()) {
                            log.info("创建文件：{}", file.getName());
                        }
                    } catch (IOException e) {
                        throw new BaseException(ExceptionConstant.IO_ERROR);
                    }
                }
            }
        }
    }

    /**
     * 获取一个文件夹中的所有文件
     *
     * @param path    path
     * @param suffix  suffix
     * @param isdepth isdepth
     * @return ** 1
     */
    public static List<String> listFiles(String path, String suffix, boolean isdepth) {
        List<String> fileNameList = new ArrayList<>();
        File file = new File(path);
        return listFile(fileNameList, file, suffix, isdepth);
    }

    /**
     * 递归遍历子目录
     *
     * @param fileNameList fileNameList
     * @param file         file
     * @param suffix       suffix
     * @param isdepth      isdepth
     * @return **
     */
    private static List<String> listFile(List<String> fileNameList, File file, String suffix, boolean isdepth) {
        if (file.isDirectory()) {
            File[] t = file.listFiles();
            assert t != null;
            for (File element : t) {
                if (isdepth || element.isFile()) {
                    listFile(fileNameList, element, suffix, isdepth);
                }
            }
        } else {
            String filePath = file.getAbsolutePath();
            if (!"".equals(suffix)) {
                int begIndex = filePath.lastIndexOf("."); // 最后一个.(即后缀名前面的.)的索引
                String tempsuffix;
                if (begIndex != -1) {
                    tempsuffix = filePath.substring(begIndex + 1);
                    if (tempsuffix.equals(suffix)) {
                        fileNameList.add(filePath);
                    }
                }
            } else {
                fileNameList.add(filePath);
            }
        }
        return fileNameList;
    }

    /**
     * 获取文件大小
     *
     * @param file file
     * @return **
     */
    public static long getFileSize(File file) {
        long size = 0;
        if (file.exists() && file.isFile()) {
            String fileName = file.getName();
            try (FileInputStream fis = new FileInputStream(file); FileChannel fc = fis.getChannel()) {
                size = fc.size();
                System.out.println("文件" + fileName + "的大小是：" + fc.size() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return size;
    }

    public static String getFileSizes(File file) {
        long size = getFileSize(file);
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString;
        if (size < 1024) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1048576) {
            fileSizeString = df.format((double) size / 1024) + "K";
        } else if (size < 1073741824) {
            fileSizeString = df.format((double) size / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) size / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 复制文件
     *
     * @param path    path
     * @param newPath newpath
     */
    public static void copyFile(String path, String newPath) {
        copyFile(new File(path), newPath);
    }

    public static void copyFile(File file, String newPath) {
        try {
            if (file.exists()) {
                FileInputStream inStream = new FileInputStream(file);
                File newFile = new File(newPath);
                FileOutputStream fileOutputStream;
                if (newFile.isDirectory()) {
                    newFile.mkdirs();
                    fileOutputStream = new FileOutputStream(newPath + File.separator + file.getName());
                } else {
                    newFile.getParentFile().mkdirs();
                    fileOutputStream = new FileOutputStream(newPath);
                }
                fileOutputStream.write(inStream.readAllBytes());
                inStream.close();
                fileOutputStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            log.error(e.getMessage());
        }
    }

    // --------------------//// --------------------//// --------------------////

    /**
     * 获取文件编码
     *
     * @param bis       bis
     * @param ignoreBom ignoreBom
     * @return **
     */
    public static String getEncode(BufferedInputStream bis, boolean ignoreBom) throws Exception {
        bis.mark(0);
        String encodeType;
        byte[] head = new byte[3];
        bis.read(head);
        if (head[0] == -1 && head[1] == -2) {
            encodeType = "UTF-16";
        } else if (head[0] == -2 && head[1] == -1) {
            encodeType = "Unicode";
        } else if (head[0] == -17 && head[1] == -69 && head[2] == -65) { // ��BOM
            if (ignoreBom) {
                encodeType = CODE_UTF8;
            } else {
                encodeType = CODE_UTF8_BOM;
            }
        } else if (isUTF8(bis)) {
            encodeType = CODE_UTF8;
        } else {
            encodeType = CODE_GBK;
        }
        return encodeType;
    }

    /**
     * 更改文件编码
     *
     * @param path    path
     * @param newPath newPath
     * @param charset charset
     */
    public static void changeFileEncode(String path, String newPath, String charset) throws Exception {
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(fileInputStream, getEncode(path, true));
        StringBuilder stringBuffer = new StringBuilder();
        while (reader.ready()) {
            stringBuffer.append((char) reader.read());
        }
        writeFile((newPath + path.substring(path.lastIndexOf("/")).replace("\\", "/")), stringBuffer.toString(), charset);
        stringBuffer.setLength(0);
        reader.close();
        fileInputStream.close();
    }

    /**
     * @param filename filename
     * @return **
     */
    public static String fileNameLegal(String filename) {
        filename = filename.replace("/", "");
        filename = filename.replace("\\", "");
        filename = filename.replace(":", "");
        filename = filename.replace("*", "");
        filename = filename.replace("?", "");
        filename = filename.replace("<", "");
        filename = filename.replace(">", "");
        filename = filename.replace("|", "");
        filename = filename.replace("\"", "");
        return filename;
    }

    /**
     * @param filename filename
     * @param value    value
     * @return **
     */
    public static boolean getContainsFilename(String filename, String value) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(filename);
        InputStreamReader reader = new InputStreamReader(fileInputStream, getEncode(filename, true));
        StringBuilder stringBuffer = new StringBuilder();
        while (reader.ready()) {
            stringBuffer.append((char) reader.read());
        }
        return stringBuffer.toString().contains(value);
    }

    /**
     * 获取文件编码
     *
     * @param fullFileName fullFileName
     * @param ignoreBom    ignoreBom
     * @return **
     */
    public static String getEncode(String fullFileName, boolean ignoreBom) throws Exception {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fullFileName));
        return getEncode(bis, ignoreBom);
    }

    /**
     * �Ƿ�����BOM��UTF8��ʽ�����жϳ��泡����ֻ������BOM UTF8��GBK
     *
     * @param bis bis
     * @return **
     */
    private static boolean isUTF8(BufferedInputStream bis) throws Exception {
        bis.reset();
        // ��ȡ��һ���ֽ�
        int code = bis.read();
        do {
            BitSet bitSet = convert2BitSet(code);
            // �ж��Ƿ�Ϊ���ֽ�
            if (bitSet.get(0)) {// ���ֽ�ʱ���ٶ�ȡN���ֽ�
                if (!checkMultiByte(bis, bitSet)) {// δ���ͨ��,ֱ�ӷ���
                    return false;
                }
            }  // ���ֽ�ʱʲô�����������ٴζ�ȡ�ֽ�
            //            code = bis.read();
        } while (code != -1);
        return true;
    }

    /**
     * �����ֽڣ��ж��Ƿ�Ϊutf8���Ѿ���ȡ��һ���ֽ�
     *
     * @param bis    bis
     * @param bitSet bitSet
     * @return **
     */
    private static boolean checkMultiByte(BufferedInputStream bis, BitSet bitSet) throws Exception {
        int count = getCountOfSequential(bitSet);
        byte[] bytes = new byte[count - 1];// �Ѿ���ȡ��һ���ֽڣ������ٶ�ȡ
        bis.read(bytes);
        for (byte b : bytes) {
            if (!checkUtf8Byte(b)) {
                return false;
            }
        }
        return true;
    }

    /**
     * ��ⵥ�ֽڣ��ж��Ƿ�Ϊutf8
     *
     * @param b b
     * @return **
     */
    private static boolean checkUtf8Byte(byte b) {
        BitSet bitSet = convert2BitSet(b);
        return bitSet.get(0) && !bitSet.get(1);
    }

    /**
     * ���bitSet�дӿ�ʼ�ж��ٸ�������1
     *
     * @param bitSet bitSet
     * @return **
     */
    private static int getCountOfSequential(BitSet bitSet) {
        int count = 0;
        for (int i = 0; i < BYTE_SIZE; i++) {
            if (bitSet.get(i)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    /**
     * ������תΪBitSet
     *
     * @param code code
     * @return **
     */
    private static BitSet convert2BitSet(int code) {
        BitSet bitSet = new BitSet(BYTE_SIZE);
        for (int i = 0; i < BYTE_SIZE; i++) {
            int tmp3 = code >> (BYTE_SIZE - i - 1);
            int tmp2 = 0x1 & tmp3;
            if (tmp2 == 1) {
                bitSet.set(i);
            }
        }
        return bitSet;
    }

    /**
     * 百分号编码工具方法
     *
     * @param s 需要百分号编码的字符串
     * @return 百分号编码后的字符串
     */
    public static String percentEncode(String s) {
        String encode = URLEncoder.encode(s, StandardCharsets.UTF_8);
        return encode.replaceAll("\\+", "%20");
    }

    /**
     * 下载文件名重新编码
     *
     * @param response     响应对象
     * @param realFileName 真实文件名
     */
    public static void setAttachmentResponseHeader(HttpServletResponse response, String realFileName) {
        String percentEncodedFileName = FileUtil.percentEncode(realFileName);
        String contentDispositionValue = "attachment; filename=" + percentEncodedFileName + ";" + "filename*=" + "utf-8''" + percentEncodedFileName;
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition,filename");
        response.setHeader("Content-disposition", contentDispositionValue);
        response.setHeader("filename", percentEncodedFileName);
    }

    public static BufferedReader getBufferedReader(File file) {
        try {
            return new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            log.error("文件不存在");
        }
        return null;
    }

    /**
     * 创建文件夹
     *
     * @param path path
     * @return **
     */
    public static boolean createFolder(String path) {
        File myFolderPath = new File(path);
        try {
            if (!myFolderPath.exists()) {
                myFolderPath.mkdirs();
                return true;
            }
        } catch (Exception e) {
            System.out.println("新建目录操作出错");
            log.error(e.getMessage());
        }
        return false;
    }

    /**
     * 读取文件属性
     *
     * @param path path
     * @return **
     */
    public static HashMap<String, String> fileAttribute(String path) {
        HashMap<String, String> map = new HashMap<>();
        File f = new File(path);
        if (f.exists()) {
            map.put("name", f.getName());
            map.put("length", String.valueOf(f.length()));
            map.put("isFile", String.valueOf(f.isFile()));
            map.put("isFolder", String.valueOf(f.isDirectory()));
            map.put("isReadable", String.valueOf(f.canRead()));
            map.put("writeable", String.valueOf(f.canWrite()));
            map.put("lastUpdateDate", String.valueOf(new Date(f.lastModified())));
        }
        return map;
    }

    /**
     * 设置文件为只读
     *
     * @param path path
     * @return **
     */
    public static boolean setFileReadOnly(String path) {
        try {
            return new File(path).setReadOnly();
        } catch (Exception e) {
            log.error("拒绝写访问,{}", e.getMessage());
            return false;
        }
    }

    /**
     * 获取文件扩展名
     *
     * @param path path
     * @return **
     */
    public static String getFileSuffix(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }

    public static File zipFolder(String sourcePath, String targetPath, String zipName) {
        return zipFolder(new File(sourcePath), targetPath, zipName);
    }

    public static File zipFolder(File source, String targetPath, String zipName) {
        try {
            if (!source.exists()) {
                throw new BaseException(ExceptionConstant.FILE_NOT_FOUND, source.getName());
            }
            if (!source.isDirectory()) {
                throw new BaseException(ExceptionConstant.FILE_NOT_FOLDER, source.getName());
            }
            File zipFile = new File(targetPath + File.separator + zipName + ".zip");
            if (zipFile.getParentFile().exists() || zipFile.getParentFile().mkdirs()) {
                try (FileOutputStream fos = new FileOutputStream(zipFile); ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos))) {
                    zipFile(zos, source, "");
                }
            }
            return zipFile;
        } catch (Exception e) {
            throw new BaseException(ExceptionConstant.ZIP_FILE_FAILED, e);
        }
    }

    private static void zipFile(ZipOutputStream zipOutputStream, File sourceFile, String path) throws Exception {
        if (sourceFile.isDirectory()) {
            path = path + sourceFile.getName() + File.separator;
            zipOutputStream.putNextEntry(new ZipEntry(path));
            for (File file : Objects.requireNonNull(sourceFile.listFiles())) {
                zipFile(zipOutputStream, file, path);
            }
        } else {
            zipOutputStream.putNextEntry(new ZipEntry(path + sourceFile.getName()));
            try (FileInputStream fis = new FileInputStream(sourceFile); BufferedInputStream bis = new BufferedInputStream(fis)) {
                zipOutputStream.write(bis.readAllBytes());
            }
        }
    }

    public static void replaceTxtByStr(String filePath, String oldStr, String replaceStr) {
        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(file))
        ) {
            List<String> lines = reader.lines().map(line -> {
                if (line.contains(oldStr)) {
                    return line.replace(oldStr, replaceStr);
                } else {
                    return line;
                }
            }).toList();
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 递归取得文件夹（包括子目录）中所有文件的大小
     *
     * @param file file
     * @return **
     */
    public static long getFolderSize(File file) {
        // 取得文件夹大小
        long size = 0;
        File[] flist = file.listFiles();
        assert flist != null;
        for (File element : flist) {
            if (element.isDirectory()) {
                size = size + getFolderSize(element);
            } else {
                size = size + element.length();
            }
        }
        return size;
    }

    /**
     * 递归删除空文件夹
     *
     * @param dir dir
     */
    public static void clear(File dir) {
        File[] dirs = dir.listFiles();
        assert dirs != null;
        for (File file : dirs) {
            if (file.isDirectory()) {
                clear(file);
            }
        }
        if (dir.isDirectory() && dir.delete()) {
            System.out.println("clear success");
        }
    }

    /**
     * 写入文件
     *
     * @param path    path
     * @param data    data
     * @param charset charset
     */
    public static void writeFile(String path, String data, String charset) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, charset);
        outputStreamWriter.append(data);
        outputStreamWriter.close();
        fileOutputStream.close();
    }

    /**
     * 合并文件
     *
     * @param outFile outFile
     * @param files   files
     */
    public static void mergeFiles(String outFile, String... files) {
        System.out.println("Merge  into " + outFile);
        try (FileOutputStream fos = new FileOutputStream(outFile)) {
            for (String f : files) {
                try (FileInputStream fis = new FileInputStream(f)) {
                    fos.write(fis.readAllBytes());
                }
            }
            System.out.println("Merged!! ");
        } catch (IOException ioe) {
            throw new BaseException(ExceptionConstant.FILE_MERGE_ERROR);
        }
    }

    public static void mergeFiles(String outFile, File... files) {
        mergeFiles(outFile, List.of(files));
    }

    public static void mergeFiles(String outFile, List<File> files) {
        System.out.println("Merge  into " + outFile);
        try (FileOutputStream fos = new FileOutputStream(outFile)) {
            for (File f : files) {
                try (FileInputStream fis = new FileInputStream(f)) {
                    fos.write(fis.readAllBytes());
                }
            }
            System.out.println("Merged!! ");
        } catch (IOException ioe) {
            throw new BaseException(ExceptionConstant.FILE_MERGE_ERROR);
        }
    }

    public static boolean isDirectoryEmpty(File file) {
        File[] files = file.listFiles();
        return files == null || files.length == 0;
    }

    /**
     * ��һָ��������ļ�ת��Ϊ��һ������ļ�
     *
     * @param oldFullFileName oldFullFileName
     * @param oldCharsetName  oldCharsetName
     * @param newFullFileName newFullFileName
     * @param newCharsetName  newCharsetName
     */
    public static void convert(String oldFullFileName, String oldCharsetName, String newFullFileName, String newCharsetName) throws Exception {
        StringBuilder content = new StringBuilder();
        BufferedReader bin = new BufferedReader(new InputStreamReader(new FileInputStream(oldFullFileName), oldCharsetName));
        String line;
        while ((line = bin.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }
        newFullFileName = newFullFileName.replace("\\", "/");
        File dir = new File(newFullFileName.substring(0, newFullFileName.lastIndexOf("/")));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Writer out = new OutputStreamWriter(new FileOutputStream(newFullFileName), newCharsetName);
        out.write(content.toString());
    }

    /**
     * �ļ��Ƿ����
     *
     * @param path path
     * @return **
     */
    public static boolean isExists(String path) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        } else {
            file.mkdir();
            return isExists(path);
        }
    }

    public static void collectByModifDate(String path) {
        for (File file : listFile(new File(path))) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneOffset.of("+8"));
            String s = localDateTime.toLocalDate().toString();
            String newPath = path + "\\" + s;
            File dir = new File(newPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 FileOutputStream fileOutputStream = new FileOutputStream(newPath + "\\" + file.getName())) {
                fileOutputStream.write(fileInputStream.readAllBytes());
                file.deleteOnExit();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * @param oldPath oldPath
     * @param newPath newPath
     */
    public static void moveFile(String oldPath, String newPath) {
        try (FileInputStream in = new FileInputStream(oldPath);
             FileOutputStream out = new FileOutputStream(newPath)) {
            out.write(in.readAllBytes());
            deleteFile(oldPath);
            System.out.println("�ļ��ƶ��ɹ�");
        } catch (IOException e) {

        }
    }

    /**
     * 删除文件
     *
     * @param path path
     */
    public static void deleteFile(String path) {
        deleteFile(new File(path));
    }

    public static void deleteFile(File file) {
        try {
            File[] list = file.listFiles();
            if (list != null) {
                for (File f : list) {
                    if (f.isDirectory()) {
                        deleteFile(f);
                    } else {
                        Files.deleteIfExists(Path.of(f.toURI()));
                    }
                }
            }
            Files.deleteIfExists(Path.of(file.toURI()));
        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            log.error(e.getMessage());
        }
    }

    public static List<File> listFile(String path) {
        return listFile(new File(path));
    }

    public static List<File> listFile(File file) {
        List<File> files = new ArrayList<>();
        Optional.ofNullable(file.listFiles()).ifPresent(list -> {
            for (File f : list) {
                if (f.isDirectory()) {
                    files.addAll(listFile(f));
                } else {
                    files.add(f);
                }
            }
        });
        return files;
    }
}
