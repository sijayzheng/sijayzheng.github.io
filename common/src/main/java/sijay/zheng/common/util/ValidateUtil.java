package sijay.zheng.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

/**
 * Validator 校验框架工具
 *
 * @author sijay
 * @date 2022/10/4 12:05
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
