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
}
