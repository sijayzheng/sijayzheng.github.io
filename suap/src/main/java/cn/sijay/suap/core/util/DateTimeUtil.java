package cn.sijay.suap.core.util;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * <p>
 * <em>DateTimeUtil</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/12 9:19
 */
@Slf4j
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class DateTimeUtil {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";

    public static String now() {
        return now(DATE_TIME_FORMAT);
    }

    public static String now(String format) {
        return now(format, Locale.CHINA);
    }

    public static String now(String format, Locale locale) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format, locale));
    }

    public static String nowTime() {
        return nowTime(TIME_FORMAT);
    }

    public static String nowTime(String format) {
        return nowTime(format, Locale.CHINA);
    }

    public static String nowTime(String format, Locale locale) {
        return now(format, locale);
    }

    public static String nowDate() {
        return nowDate(DATE_FORMAT);
    }

    public static String nowDate(String format) {
        return nowDate(format, Locale.CHINA);
    }

    public static String nowDate(String format, Locale locale) {
        return now(format, locale);
    }

}
