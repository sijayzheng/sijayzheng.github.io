package cn.sijay.bun.common.util;

import cn.sijay.bun.common.exception.BaseException;
import cn.sijay.bun.common.exception.ExceptionConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * <strong>FileUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-15
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {
    public static boolean writeFile(String filePath, String content) {
        return writeFile(filePath, content, false, StandardCharsets.UTF_8);
    }

    public static boolean writeFile(String filePath, String content, boolean append) {
        return writeFile(filePath, content, append, StandardCharsets.UTF_8);
    }

    public static boolean writeFile(String filePath, String content, boolean append, Charset charset) {
        boolean flag = true;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                flag = file.getParentFile().mkdirs();
                flag = flag && file.createNewFile();
            }
            try (FileWriter fw = new FileWriter(file, charset, append)) {
                fw.write(content);
            }
        } catch (IOException e) {
            throw new BaseException(ExceptionConstant.FILE_WRITE_ERROR);
        }
        return flag;
    }

    public static List<File> listAllFile(String path) {
        List<File> fileList = new ArrayList<>();
        File f = new File(path);
        if (f.isFile()) {
            fileList.add(f);
            return fileList;
        }
        File[] files = f.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file);
                } else if (file.isDirectory()) {
                    fileList.addAll(listAllFile(file.getAbsolutePath()));
                }
            }
        }
        return fileList;
    }
}
