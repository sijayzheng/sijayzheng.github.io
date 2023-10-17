/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.SortedMap;

/**
 * TODO
 *
 * @author zhengshijie
 * @date 2023/7/10 9:48
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EncodingUtil {
    public static void main(String[] args) {
        String s = "锘挎槬鐪犱笉瑙夋檽锛屽\uE629澶勯椈鍟奸笩";
        new EncodingUtil().GarbledRecovery(s);
    }

    public void GarbledRecovery(String garbled) {
        System.out.printf("%18s|%18s|%s %n", "现编码", "原编码", "解码后字符串");
        final SortedMap<String, Charset> charsetMap = Charset.availableCharsets();
        charsetMap.forEach((k, v) -> {
            charsetMap.forEach((name, charset) -> {
                if (!name.equals(k)) {
                    String str = new String(garbled.getBytes(v), charset);
                    System.out.printf("%20s|%20s|%s %n", k, charset, str);
                }
            });
        });
    }

}
