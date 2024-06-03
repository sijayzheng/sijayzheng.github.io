package cn.sijay.suap.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

/**
 * <strong>ValidateUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidateUtil {
    static final Pattern URL_BEGIN = Pattern.compile("^(https?)://");
    static final Pattern URL_PATTERN = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    public static boolean isUrl(String url) {
        return URL_BEGIN.matcher(url).find();
    }

    /**
     * 是否为http(s)://开头
     *
     * @param link 链接
     * @return 结果
     */
    public static boolean isHttp(String link) {
        return isUrl(link);
    }
}

