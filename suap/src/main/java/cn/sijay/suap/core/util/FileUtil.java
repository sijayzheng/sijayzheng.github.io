package cn.sijay.suap.core.util;

import cn.sijay.suap.core.enums.ExceptionEnum;
import cn.sijay.suap.core.exception.BaseException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * <em>FileUtil</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/12 15:30
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {

    public static final String DATE_FILE_NAME_FORMAT = "yyyyMMdd";
    public static final String DATE_TIME_FILE_NAME_FORMAT = "yyyyMMddHHmmss";

    public static String concatPath(String... path) {
        return String.join(File.separator, path);
    }

    public static String getFileSuffix() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FILE_NAME_FORMAT));
    }

    public static String getShotFileSuffix() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FILE_NAME_FORMAT));
    }

    public static void mkDirOrTouch(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if (file.isDirectory()) {
                file.mkdirs();
                log.info("创建文件夹：{}", file.getAbsolutePath());
            } else {
                file.getParentFile().mkdirs();
                log.info("创建文件夹：{}", file.getParentFile().getAbsolutePath());
                try {
                    file.createNewFile();
                    log.info("创建文件夹：{}", file.getName());
                } catch (IOException e) {
                    throw new BaseException(ExceptionEnum.IO_ERROR);
                }
            }
        }
    }

    public static File zipFolder(String sourcePath, String targetPath, String zipName) {
        return zipFolder(new File(sourcePath), targetPath, zipName);
    }

    public static File zipFolder(File source, String targetPath, String zipName) {
        try {
            if (!source.exists()) {
                throw new BaseException(ExceptionEnum.FILE_NOT_FOUND, source.getName());
            }
            if (!source.isDirectory()) {
                throw new BaseException(ExceptionEnum.FILE_NOT_FOLDER, source.getName());
            }
            File zipFile = new File(targetPath + File.separator + zipName + ".zip");
            zipFile.getParentFile().mkdirs();
            try (FileOutputStream fos = new FileOutputStream(zipFile); ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos))) {
                zipFile(zos, source, "");
            }
            return zipFile;
        } catch (Exception e) {
            throw new BaseException(ExceptionEnum.ZIP_FILE_FAILED, e);
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
}
