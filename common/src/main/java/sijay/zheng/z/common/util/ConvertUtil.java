/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.common.util;

import lombok.NoArgsConstructor;
import sijay.zheng.z.common.constant.CommConstant;
import sijay.zheng.z.common.enums.CaseEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author sijay
 * @date 2022/4/26 21:25
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ConvertUtil {
    public static final List<Character> A_Z = List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

    public static String caseConvert(String name) {
        return caseConvert(name, CaseEnum.LOWER_CAMEL_CASE);
    }

    public static String caseConvert(String name, CaseEnum caseEnum) {
        String res;
        switch (caseEnum) {
            case LOWER_CAMEL_CASE -> res = lowerCamelCase(name);
            case UPPER_CAMEL_CASE -> res = upperCamelCase(name);
            case LOWER_SANKE_CASE -> res = snakeCase(name, false);
            case UPPER_SNAKE_CASE -> res = snakeCase(name, true);
            default -> res = caseConvert(name);
        }
        return res;
    }

    private static String upperCamelCase(String name) {
        name = name.replace(CommConstant.SPACE, CommConstant.UNDERLINE);
        return Arrays.stream(name.split(CommConstant.UNDERLINE)).map(e -> {
            e = e.toLowerCase(Locale.ROOT);
            e = (char) (e.charAt(0) ^ 32) + e.substring(1);
            return e;
        }).collect(Collectors.joining());
    }

    private static String lowerCamelCase(String name) {
        name = upperCamelCase(name);
        return (char) (name.charAt(0) ^ 32) + name.substring(1);
    }

    private static String snakeCase(String name, boolean upperCase) {
        StringBuilder stringBuffer = new StringBuilder();
        name = name.replaceAll("[. ]+", "_");
        for (char c : name.toCharArray()) {
            if (A_Z.contains(c)) {
                stringBuffer.append('_');
            }
            stringBuffer.append(c);
        }
        return upperCase ? stringBuffer.toString().toUpperCase(Locale.ROOT) : stringBuffer.toString().toLowerCase(Locale.ROOT);
    }
}
