package sijay.zheng.experience.javase;

import lombok.*;

import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.stream.*;

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
                    int i = LocalDate.parse(o2.getTransDate(), formatter)
                            .compareTo(LocalDate.parse(o1.getTransDate(), formatter));
                    if (i == 0) {
                        return o2.getChannelSysRef()
                                .compareTo(o1.getChannelSysRef());
                    } else {
                        return i;
                    }
                })
                .collect(Collectors.toList());
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    private String channelSysRef;
    private String transDate;
}