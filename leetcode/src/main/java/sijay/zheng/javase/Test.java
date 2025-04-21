package sijay.zheng.javase;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sijay
 */
public class Test {
    public static void main(String[] args) throws Exception {
        String url = "https://bkimg.cdn.bcebos.com/pic/37d12f2eb9389b503a80d4b38b35e5dde6116ed7?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto";
        URLConnection urlConnection = new URL(url).openConnection();
        urlConnection.connect();
        File file1 = new File("D:/test.jpg");
        try (BufferedInputStream bis = new BufferedInputStream(urlConnection.getInputStream()); FileOutputStream fileOutputStream = new FileOutputStream(file1)) {
            int len = 0;
            while ((len = bis.read()) != -1) {
                fileOutputStream.write(len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mains(String[] args) {
        List<User> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                list.add(new User("user" + (Math.round(Math.random() * 100)), "2022-0" + (Math.round(Math.random() * 7) + 1) + "-0" + (Math.round(Math.random() * 7) + 1)));
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        list.stream()
                .sorted((o1, o2) -> {
                    int i = LocalDate.parse(o2.transDate(), formatter).compareTo(LocalDate.parse(o1.transDate(), formatter));
                    if (i == 0) {
                        return o2.channelSysRef().compareTo(o1.channelSysRef());
                    } else {
                        return i;
                    }
                })
                .collect(Collectors.toList());
    }

}

record User(String channelSysRef, String transDate) {
}
