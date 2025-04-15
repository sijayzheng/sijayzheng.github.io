package cn.sijay.biu;

import cn.sijay.biu.core.util.FileUtil;

import java.io.File;
import java.util.List;

/**
 * Main
 *
 * @author sijay
 * @since 2025-04-15
 */
public class Main {
    public static void main(String[] args) {
        List<File> files = FileUtil.listFiles("C:\\Users\\sijay\\code\\vscode\\z\\biu\\sql");
        for (File file : files) {
            // read file and print
            List<String> lines = FileUtil.readLines(file);
            for (String line : lines) {
                System.out.println(line);
            }
        }
    }
}
