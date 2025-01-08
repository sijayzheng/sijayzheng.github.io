package cn.sijay.demos.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

/**
 * <strong>Main</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-07-23
 */
public class Main {
    public static void main(String[] args) {
        HashSet<String> strings = new HashSet<>();
        strings.add("rybigscreenbusinessinfo.java");
        strings.add("rybigscreendashboardinfo.java");
        strings.add("rybigscreenorginfo.java");
        strings.add("rybigscreenstaticinfo.java");
        strings.add("rybigscreencar.java");
        strings.add("rybigscreenmain.java");
        strings.add("rybigscreenpeople.java");
        strings.add("rybigscreentask.java");
        strings.add("rydefect.java");
        strings.add("rydefectattr.java");
        strings.add("rydefectlog.java");
        strings.add("ryfactory.java");
        strings.add("ryfix.java");
        strings.add("ryfixattr.java");
        strings.add("ryindicator.java");
        strings.add("ryindicatorstandard.java");
        strings.add("rytestjob.java");
        strings.add("rytestlog.java");
        strings.add("ryworker.java");
        Collection<File> files = FileUtils.listFiles(new File("D:\\code\\corpCode\\grd1300\\code\\snowy-master\\snowy-plugin\\snowy-plugin-biz\\src\\main\\java\\vip\\xiaonuo\\biz\\modular"), new String[]{"java"}, true);
        files.parallelStream().filter(file -> strings.contains(file.getName().toLowerCase())).forEach(file -> {
            try (FileInputStream stream = new FileInputStream(file)) {
                System.out.println(new String(stream.readAllBytes()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
