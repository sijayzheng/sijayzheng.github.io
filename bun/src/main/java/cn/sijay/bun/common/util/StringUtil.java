package cn.sijay.bun.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <strong>StringUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-11-07
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {
    public static String toUpperCamelCase(String tableName) {
        return Arrays.stream(tableName.split("_")).map(StringUtils::capitalize).collect(Collectors.joining());
    }

    public static String toLowerCamelCase(String tableName) {
        return StringUtils.contains(tableName, "_") ? StringUtils.uncapitalize(toUpperCamelCase(tableName)) : StringUtils.uncapitalize(tableName);
    }

    public static String join(String delimiter, String... strings) {
        return String.join(delimiter, strings);
    }
}
