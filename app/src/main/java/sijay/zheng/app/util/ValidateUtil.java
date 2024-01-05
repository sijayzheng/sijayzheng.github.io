package cn.zheng.sijay.j.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

/**
 * @author sijay
 * @desc ValidateUtil
 * @date 2023/12/19 14:30
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidateUtil {
    static final Pattern URL_BEGIN = Pattern.compile("^(https?)://");
    static final Pattern URL_PATTERN = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    public static boolean isUrl(String url) {
        return URL_BEGIN.matcher(url).find();
    }

}
