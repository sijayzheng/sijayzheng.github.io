package cn.sijay.biu.core.util;

import cn.sijay.biu.core.exception.UtilException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件处理工具类
 *
 * @author Sijay
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {

    public static void writeUtf8String(String string, String path) {
        try {
            Path parent = Paths.get(path).getParent();
            if (!Files.exists(parent)) {
                Files.createDirectories(parent);
            }
            Files.writeString(Paths.get(path), string);
            log.info("{}写入成功", path);
        } catch (IOException e) {
            throw new UtilException("{}写入失败", path);
        }
    }

    public static String concatPath(String... dirs) {
        return String.join(File.separator, dirs);
    }

    public static List<File> listFiles(String path) {
        return listFiles(new File(path));
    }

    public static List<File> listFiles(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return List.of();
            } else {
                List<File> list = new ArrayList<>();
                for (File f : files) {
                    if (f.isDirectory()) {
                        list.addAll(listFiles(f));
                    } else {
                        list.add(f);
                    }
                }
                return list;
            }
        } else {
            return List.of(file);
        }
    }

    public static List<String> readLines(File file) {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new UtilException("读取文件失败");
        }
    }
}
