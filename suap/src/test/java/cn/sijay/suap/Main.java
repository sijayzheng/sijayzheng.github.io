package cn.sijay.suap;

import cn.sijay.suap.core.utils.FileUtil;
import org.apache.commons.text.StringEscapeUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <p>
 * <em>Main</em>
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
public class Main {
    public static void main(String[] args) {

    }

    public static void main11(String[] args) throws IOException {
        try (FileOutputStream fos = new FileOutputStream("d:\\sql.sql")) {
            for (File file : FileUtil.listFile(new File("D:\\data"))) {
//                if (file.getName().equalsIgnoreCase("flow_form_control_structure.txt")) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    String[] split = new String(fis.readAllBytes()).split("\r\n");
                    String stringBuilder = "replace into " +
                        file.getName()
                            .replace(".txt", "") + "(" +
                        String.join(",", split[0].split("\t")) +
                        ")values\n" +
                        Arrays.stream(split)
                              .skip(1)
                              .map(s -> s.split("\t", -1))
                              .map(item ->
                                  Arrays.stream(item)
                                        .map(el -> StringEscapeUtils.unescapeJava(StringEscapeUtils.unescapeJava(el)))
                                        .map(el -> el.replace("'", "\\'"))
                                        .map(el -> el.replace("[object Object]", "`[object Object]`"))
                                        .map(el -> el.replace("0000-00-00 00:00:00", "2020-01-01 00:00:00"))
                                        .map(el -> !"null".equalsIgnoreCase(el) ? "'" + el + "'" : "null")
                                        .collect(Collectors.joining(",", "(", ")")
                                        ))
                              .collect(Collectors.joining(",\n")) +
                        ";\n";
                    System.out.println(stringBuilder);
                    fos.write(stringBuilder.getBytes(StandardCharsets.UTF_8));
//                    }

                }
            }
        }
    }
}
