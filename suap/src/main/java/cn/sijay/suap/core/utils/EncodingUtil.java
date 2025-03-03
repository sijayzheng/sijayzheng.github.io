package cn.sijay.suap.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.SortedMap;

/**
 * <strong>EncodingUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EncodingUtil {

    public void GarbledRecovery(String garbled) {
        System.out.printf("%18s|%18s|%s %n", "现编码", "原编码", "解码后字符串");
        final SortedMap<String, Charset> charsetMap = Charset.availableCharsets();
        charsetMap.forEach((k, v) -> charsetMap.forEach((name, charset) -> {
            if (!name.equals(k)) {
                String str = new String(garbled.getBytes(v), charset);
                System.out.printf("%20s|%20s|%s %n", k, charset, str);
            }
        }));
    }

}
