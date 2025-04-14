package cn.sijay.biu.core.util;

import cn.sijay.biu.core.constant.SymbolConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.util.AntPathMatcher;

import java.util.*;
import java.util.stream.Collectors;

/**
 * StringUtil
 *
 * @author Sijay
 * @since 2025-02-20
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {
    public static final String EMPTY = "";

    public static String format(String msg, Object... args) {
        return MessageFormatter.arrayFormat(msg, args).getMessage();
    }

    /**
     * 获取参数不为空值
     *
     * @param str defaultValue 要判断的value
     * @return value 返回值
     */
    public static String blankToDefault(String str, String defaultValue) {
        return StringUtils.defaultIfBlank(str, defaultValue);
    }

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 去空格
     */
    public static String trim(String str) {
        return StringUtils.trim(str);
    }

    /**
     * 截取字符串
     *
     * @param str   字符串
     * @param start 开始
     * @return 结果
     */
    public static String substring(final String str, int start) {
        return substring(str, start, str.length());
    }

    /**
     * 截取字符串
     *
     * @param str   字符串
     * @param start 开始
     * @param end   结束
     * @return 结果
     */
    public static String substring(final String str, int start, int end) {
        return StringUtils.substring(str, start, end);
    }

    /**
     * 是否为http(s)://开头
     *
     * @param link 链接
     * @return 结果
     */
    public static boolean isHttp(String link) {
        if (isBlank(link)) {
            return false;
        } else {
            return link.toLowerCase().startsWith("http://") || link.toLowerCase().startsWith("https://");
        }
    }

    public static boolean isBlank(CharSequence cs) {
        return StringUtils.isBlank(cs);
    }

    public static boolean isAnyBlank(CharSequence... cs) {
        return StringUtils.isAnyBlank(cs);
    }

    /**
     * 字符串转set
     *
     * @param str 字符串
     * @param sep 分隔符
     * @return set集合
     */
    public static Set<String> str2Set(String str, String sep) {
        return new HashSet<>(str2List(str, sep, true, false));
    }

    /**
     * 字符串转list
     *
     * @param str         字符串
     * @param sep         分隔符
     * @param filterBlank 过滤纯空白
     * @param trim        去掉首尾空白
     * @return list集合
     */
    public static List<String> str2List(String str, String sep, boolean filterBlank, boolean trim) {
        List<String> list = new ArrayList<>();
        if (isEmpty(str)) {
            return list;
        }

        // 过滤空白字符串
        if (filterBlank && isBlank(str)) {
            return list;
        }
        String[] split = str.split(sep);
        for (String string : split) {
            if (filterBlank && isBlank(string)) {
                continue;
            }
            if (trim) {
                string = trim(string);
            }
            list.add(string);
        }

        return list;
    }

    /**
     * 查找指定字符串是否包含指定字符串列表中的任意一个字符串同时串忽略大小写
     *
     * @param cs                  指定字符串
     * @param searchCharSequences 需要检查的字符串数组
     * @return 是否包含任意一个字符串
     */
    public static boolean containsAnyIgnoreCase(CharSequence cs, CharSequence... searchCharSequences) {
        return StringUtils.containsAnyIgnoreCase(cs, searchCharSequences);
    }

    /**
     * 是否包含字符串
     *
     * @param str     验证字符串
     * @param strings 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strings) {
        return StringUtils.equalsAnyIgnoreCase(str, strings);
    }

    public static String join(String delimiter, String... strings) {
        return String.join(delimiter, strings);
    }

    /**
     * 查找指定字符串是否匹配指定字符串列表中的任意一个字符串
     *
     * @param str      指定字符串
     * @param patterns 需要检查的字符串数组
     * @return 是否匹配
     */
    public static boolean matches(String str, List<String> patterns) {
        if (isEmpty(str) || CollectionUtil.isEmpty(patterns)) {
            return false;
        }
        for (String pattern : patterns) {
            if (isMatch(pattern, str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断url是否与规则配置:
     * ? 表示单个字符;
     * * 表示一层路径内的任意字符串，不可跨层级;
     * ** 表示任意层路径;
     *
     * @param pattern 匹配规则
     * @param url     需要匹配的url
     */
    public static boolean isMatch(String pattern, String url) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, url);
    }

    /**
     * 数字左边补齐0，使之达到指定长度。注意，如果数字转换为字符串后，长度大于size，则只保留 最后size个字符。
     *
     * @param num  数字对象
     * @param size 字符串指定长度
     * @return 返回数字的字符串格式，该字符串为指定长度。
     */
    public static String padl(final Number num, final int size) {
        return padl(num.toString(), size, '0');
    }

    /**
     * 字符串左补齐。如果原始字符串s长度大于size，则只保留最后size个字符。
     *
     * @param s    原始字符串
     * @param size 字符串指定长度
     * @param c    用于补齐的字符
     * @return 返回指定长度的字符串，由原字符串左补齐或截取得到。
     */
    public static String padl(final String s, final int size, final char c) {
        final StringBuilder sb = new StringBuilder(size);
        if (s != null) {
            final int len = s.length();
            if (s.length() <= size) {
                sb.append(String.valueOf(c).repeat(size - len));
                sb.append(s);
            } else {
                return s.substring(len - size, len);
            }
        } else {
            sb.append(String.valueOf(c).repeat(Math.max(0, size)));
        }
        return sb.toString();
    }

    /**
     * 切分字符串(分隔符默认逗号)
     *
     * @param str 被切分的字符串
     * @return 分割后的数据列表
     */
    public static List<String> splitList(String str) {
        return splitTo(str);
    }

    /**
     * 切分字符串
     *
     * @param str       被切分的字符串
     * @param separator 分隔符
     * @return 分割后的数据列表
     */
    public static List<String> splitList(String str, String separator) {
        return splitTo(str, separator);
    }

    /**
     * 切分字符串自定义转换(分隔符默认逗号)
     *
     * @param str 被切分的字符串
     * @return 分割后的数据列表
     */
    public static List<String> splitTo(String str) {
        return splitTo(str, SymbolConstants.COMMA);
    }

    /**
     * 切分字符串自定义转换
     *
     * @param str       被切分的字符串
     * @param separator 分隔符
     * @return 分割后的数据列表
     */
    public static List<String> splitTo(String str, String separator) {
        if (isBlank(str)) {
            return new ArrayList<>(0);
        }
        return Arrays.stream(split(str, separator))
                     .filter(Objects::nonNull)
                     .collect(Collectors.toList());
    }

    public static String hide(String str, int startInclude, int endExclude) {
        return replaceByCodePoint(str, startInclude, endExclude, '*');
    }

    public static String replaceByCodePoint(String str, int startInclude, int endExclude, char replacedChar) {
        if (isEmpty(str)) {
            return str;
        } else {
            int[] strCodePoints = str.codePoints().toArray();
            int strLength = strCodePoints.length;
            if (startInclude > strLength) {
                return str;
            } else {
                if (endExclude > strLength) {
                    endExclude = strLength;
                }

                if (startInclude > endExclude) {
                    return str;
                } else {
                    StringBuilder stringBuilder = new StringBuilder();

                    for (int i = 0; i < strLength; ++i) {
                        if (i >= startInclude && i < endExclude) {
                            stringBuilder.append(replacedChar);
                        } else {
                            stringBuilder.append(new String(strCodePoints, i, 1));
                        }
                    }

                    return stringBuilder.toString();
                }
            }
        }
    }

    public static String subBefore(String string, char separator, boolean isLastSeparator) {
        if (isBlank(string)) {
            return "";
        } else {
            int pos = isLastSeparator ? string.lastIndexOf(separator) : string.indexOf(separator);
            if (-1 == pos) {
                return string;
            } else {
                return 0 == pos ? "" : string.substring(0, pos);
            }
        }
    }

    public static String cleanHtmlTag(String content) {
        return content.replaceAll("(<[^<]*?>)|(<\\s*?/[^<]*?>)|(<[^<]*?/\\s*?>)", "");
    }

    public static String[] split(String str, String separator) {
        return StringUtils.splitByWholeSeparator(str, separator);
    }

    public static List<String> splitToList(String str, String separator) {
        return Arrays.stream(split(str, separator))
                     .filter(Objects::nonNull)
                     .collect(Collectors.toList());
    }

    public static boolean isNotBlank(String value) {
        return StringUtils.isNotBlank(value);
    }

    public static boolean equals(String s1, String s2) {
        return StringUtils.equals(s1, s2);
    }

    public static void print(String... strings) {
        System.out.println(String.join("  |  ", strings));
    }

    public static String defaultString(String value) {
        return defaultString(value, "");
    }

    public static String defaultString(String value, String defaultValue) {
        return isBlank(value) ? defaultValue : value;
    }
}
