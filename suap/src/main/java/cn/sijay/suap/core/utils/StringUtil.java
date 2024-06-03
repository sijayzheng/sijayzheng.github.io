package cn.sijay.suap.core.utils;

import cn.sijay.suap.core.constant.Constants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <strong>StringUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil extends StringUtils {
    public static final String SEPARATOR = ",";
    private static final String[] CHARSET_ARR = {"UTF-8", "GB2312", "GBK", "Windows-1252", "ISO8859-1"};

    public static void testAllCharset(String text) throws UnsupportedEncodingException {
        if (text == null || text.isEmpty()) {
            System.out.println("文本不能为空");
            return;
        }
        System.out.println("假设当前编码       假设原始编码          编码后的内容");
        for (String curCharset : CHARSET_ARR) {
            byte[] btArr = text.getBytes(curCharset);
            for (String originCharset : CHARSET_ARR) {
                if (originCharset.equals(curCharset)) {
                    continue;
                }
                String encodeText = new String(btArr, originCharset);
                printTable(curCharset, originCharset, encodeText);
            }
        }
    }

    private static void printTable(String curCharset, String originCharset, String encodeText) {
        System.out.print(curCharset);
        for (int i = 0; i < 15 - curCharset.length(); i++) {
            System.out.print(" ");
        }
        System.out.print("|   " + originCharset);
        for (int i = 0; i < 16 - originCharset.length(); i++) {
            System.out.print(" ");
        }
        System.out.println("|     " + encodeText);
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     * 转义{}： format("this is \{} for {}", "a", "b") -> this is {} for a<br>
     * 转义\： format("this is \\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param params   参数值
     * @return 格式化后的文本
     */
    public static String format(String template, Object... params) {
        return MessageFormatter.basicArrayFormat(template, params);
    }

    public static String[] toArray(String str) {
        return toArray(str, Constants.COMMA);
    }

    /**
     * 字符串转set
     *
     * @param str       字符串
     * @param separator 分隔符
     * @return set集合
     */
    public static String[] toArray(String str, String separator) {
        return str.split(separator);
    }

    /**
     * 字符串转set
     *
     * @param str       字符串
     * @param separator 分隔符
     * @return set集合
     */
    public static Set<String> toSet(String str, String separator) {
        return new HashSet<>(toList(str, separator, true, false));
    }

    /**
     * 字符串转list
     *
     * @param str         字符串
     * @param separator   分隔符
     * @param filterBlank 过滤纯空白
     * @param trim        去掉首尾空白
     * @return list集合
     */
    public static List<String> toList(String str, String separator, boolean filterBlank, boolean trim) {
        List<String> list = new ArrayList<>();
        if (isBlank(str)) {
            return list;
        }
        // 过滤空白字符串
        if (filterBlank && isBlank(str)) {
            return list;
        }
        String[] split = str.split(separator);
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

    public static List<String> toList(String str) {
        return toList(str, Constants.COMMA, false, false);
    }

    /**
     * 查找指定字符串是否包含指定字符串列表中的任意一个字符串同时串忽略大小写
     *
     * @param cs                  指定字符串
     * @param searchCharSequences 需要检查的字符串数组
     * @return 是否包含任意一个字符串
     */
    public static boolean containsAnyIgnoreCase(CharSequence cs, CharSequence... searchCharSequences) {
        return containsAnyIgnoreCase(cs, searchCharSequences);
    }

    /**
     * 转蛇形命名 user_name
     */
    public static String toLowerSnakeCase(String str) {
        //string to underline_case
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        // 前置字符是否大写
        boolean preCharIsUpperCase;
        // 当前字符是否大写
        boolean currentCharIsUpperCase;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i > 0) {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            } else {
                preCharIsUpperCase = false;
            }
            currentCharIsUpperCase = Character.isUpperCase(c);
            if (preCharIsUpperCase && currentCharIsUpperCase) {
                // 两个大写字母在一起，那么连他们一起处理
                sb.append(Constants.UNDERLINE);
            } else if ((i != 0 && !preCharIsUpperCase) && currentCharIsUpperCase) {
                // 前面一个字母为小写，当前字母大写，则在前面添加下划线
                sb.append(Constants.UNDERLINE);
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    public static String toUpperSnakeCase(String str) {
        return toRootUpperCase(toLowerSnakeCase(str));
    }

    /**
     * 转烤串命名 user-name
     */
    public static String toLowerKebabCase(String str) {
        return toLowerSnakeCase(str).replace(Constants.UNDERLINE, Constants.HYPHEN);
    }

    public static String toUpperKebabCase(String str) {
        return toRootUpperCase(toLowerKebabCase(str));
    }

    /**
     * 驼峰式命名法 例如：user_name->userName
     */
    public static String toLowerCamelCase(String s) {
        String upperCamelCase = toUpperCamelCase(s);
        return upperCamelCase.substring(0, 1)
                             .toLowerCase(Locale.ROOT)
                             .concat(upperCamelCase.substring(1));
    }

    /**
     * 驼峰式命名法 例如：user_name->userName
     */
    public static String toUpperCamelCase(String s) {
        return Arrays.stream(s.split("_"))
                     .map(StringUtils::capitalize)
                     .collect(Collectors.joining());
    }

    /**
     * 是否包含字符串
     *
     * @param str     验证字符串
     * @param strings 字符串组
     * @return 包含返回true
     */
    public static boolean anyMatchIgnoreCase(String str, String... strings) {
        return equalsAnyIgnoreCase(str, strings);
    }

    public static boolean anyMatch(String str, String... strings) {
        return equalsAny(str, strings);
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
                sb.append(String.valueOf(c)
                                .repeat(size - len));
                sb.append(s);
            } else {
                return s.substring(len - size, len);
            }
        } else {
            sb.append(String.valueOf(c)
                            .repeat(Math.max(0, size)));
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
        return splitList(str, Constants.COMMA);
    }

    /**
     * 切分字符串
     *
     * @param str       被切分的字符串
     * @param separator 分隔符
     * @return 分割后的数据列表
     */
    public static List<String> splitList(String str, String separator) {
        return Arrays.stream(str.split(separator))
                     .toList();
    }

    /**
     * 切分字符串自定义转换(分隔符默认逗号)
     *
     * @param str    被切分的字符串
     * @param mapper 自定义转换
     * @return 分割后的数据列表
     */
    public static <T> List<T> splitTo(String str, Function<? super Object, T> mapper) {
        return splitTo(str, SEPARATOR, mapper);
    }

    /**
     * 切分字符串自定义转换
     *
     * @param str       被切分的字符串
     * @param separator 分隔符
     * @param mapper    自定义转换
     * @return 分割后的数据列表
     */
    public static <T> List<T> splitTo(String str, String separator, Function<? super Object, T> mapper) {
        if (isBlank(str)) {
            return new ArrayList<>(0);
        }
        return splitList(str, separator)
                .stream()
                .filter(Objects::nonNull)
                .map(mapper)
                .collect(Collectors.toList());
    }

    public static String toLike(String string) {
        return "%" + string + "%";
    }

    public static String toLeftLike(String string) {
        return "%" + string;
    }

    public static String toRightLike(String string) {
        return string + "%";
    }
}

