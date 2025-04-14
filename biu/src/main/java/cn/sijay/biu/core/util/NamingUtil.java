package cn.sijay.biu.core.util;

import org.apache.commons.lang3.StringUtils;

/**
 * NamingUtil
 *
 * @author Sijay
 * @since 2025-03-04
 */
public class NamingUtil {

    /**
     * 蛇形命名法转小驼峰命名法
     *
     * @param string 蛇形命名的字符串
     * @return 小驼峰命名的字符串
     */
    public static String snakeToCamel(String string) {
        return StringUtils.uncapitalize(snakeToUpperCamel(string));
    }

    /**
     * 蛇形命名法转大驼峰命名法
     *
     * @param string 蛇形命名的字符串
     * @return 大驼峰命名的字符串
     */
    public static String snakeToUpperCamel(String string) {
        if (string.contains("_")) {
            StringBuilder builder = new StringBuilder();
            for (String s : string.split("_")) {
                builder.append(StringUtils.capitalize(s));
            }
            return builder.toString();
        } else {
            return StringUtils.capitalize(string);
        }
    }

    /**
     * 驼峰命名法转蛇形命名法
     *
     * @param camelCase 驼峰命名的字符串
     * @return 蛇形命名的字符串
     */
    public static String camelToSnake(String camelCase) {
        StringBuilder snakeCase = new StringBuilder();
        for (int i = 0; i < camelCase.length(); i++) {
            char c = camelCase.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    snakeCase.append('_');
                }
                snakeCase.append(Character.toLowerCase(c));
            } else {
                snakeCase.append(c);
            }
        }
        return snakeCase.toString();
    }

}
