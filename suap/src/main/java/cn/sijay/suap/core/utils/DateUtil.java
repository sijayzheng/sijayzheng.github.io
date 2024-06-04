package cn.sijay.suap.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <strong>DateUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

    static final String FULL = "yyyy-MM-dd HH:mm:ss";
    static final String DATE = "yyyyMMdd";

    public static int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String getFullFormatedDateString(long time) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(System.currentTimeMillis() / 1000, Math.toIntExact(System.currentTimeMillis() % 1000), OffsetDateTime.now()
                                                                                                                                                                       .getOffset());
        return localDateTime.format(DateTimeFormatter.ofPattern(FULL));
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String getFullFormatedDateString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(FULL));
    }

    /**
     * yyyyMMdd
     */
    public static String getFormatedDateString() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE));
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String getFullFormatedDateString() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(FULL));
    }

    public static String getFormatedDateString(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getFormatedDateString(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static int getCurrentHour() {
        return LocalDateTime.now().getHour();
    }

    public static LocalDateTime parseDate(String date, String pattern) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parseDate(String date) {
        return parseDate(date, FULL);
    }

    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || year % 4 == 0 && year % 100 != 0;
    }

    public static boolean isLeapYear() {
        return isLeapYear(LocalDate.now().getYear());
    }

    public static LocalDate getFirstDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(1);
    }

    public static int getLastDayOfMonth(int month) {
        int[] dayArray = {
                31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
                30, 31
        };
        if (month < 1 || month > 12) {
            return -1;
        }
        if (month == 2) {
            if (isLeapYear()) {
                return 29;
            } else {
                return dayArray[month - 1];
            }
        } else {
            return dayArray[month - 1];
        }
    }

    public static LocalDate getLastDayOfMonth(LocalDate date) {
        return date.plusMonths(1).withDayOfMonth(1).minusDays(1);
    }

    public static LocalDate getNextMonth(LocalDate date) {
        return date.plusMonths(1);
    }

    public static LocalDate getPreviousMonth(LocalDate date) {
        return date.minusMonths(1);
    }

    public static LocalDate getPreviousMonths(LocalDate date, int months) {
        return date.minusMonths(months);
    }

    public static LocalDate getFirstDayOfPreviousMonth(LocalDate date) {
        return getFirstDayOfMonth(getPreviousMonth(date));
    }

    public static LocalDate getLastDayOfPreviousMonth(LocalDate date) {
        return getLastDayOfMonth(getPreviousMonth(date));
    }

    public static LocalDate getFirstDayOfNextMonth(LocalDate date) {
        return getFirstDayOfMonth(getNextMonth(date));
    }

    public static LocalDate getLastDayOfNextMonth(LocalDate date) {
        return getLastDayOfMonth(getNextMonth(date));
    }

    public static LocalDate getNextDay(LocalDate date) {
        return date.plusDays(1);
    }

    public static LocalDate getPreviousDay(LocalDate date) {
        return date.minusDays(1);
    }

    public static LocalDate getNextDays(LocalDate date, int days) {
        return date.plusDays(days);
    }

    public static LocalDate getPreviousDays(LocalDate date, int days) {
        return date.minusDays(days);
    }

    /**
     * 该该方法用于获取下一天的时间
     */
    public static String nextDay(String date) {
        LocalDateTime localDateTime = parseDate(date);
        return localDateTime.plusDays(1).toLocalDate().toString();
    }

    /**
     * 该该方法用于计算两个时间的时间差（单位：秒）
     */
    public static long getDiffSeconds(String startDate, String endDate) {
        Duration duration = getDiff(startDate, endDate);
        return duration.toDays() * 24 * 60 * 60 + duration.toHours() * 60 * 60 + duration.toMinutes() * 60 + duration.toSeconds();
    }

    public static Duration getDiff(String startDate, String endDate) {
        return Duration.between(parseDate(startDate), parseDate(endDate));
    }

    public static Integer getSecondsEndToday() {
        LocalDateTime localDateTime = LocalDate.now().plusDays(1).atStartOfDay();
        return Math.toIntExact(localDateTime.toEpochSecond(OffsetDateTime.now().getOffset()) - System.currentTimeMillis() / 1000);
    }

    public static LocalDateTime toLocalDateTime(LocalDate date) {
        return date.atStartOfDay();
    }

}
