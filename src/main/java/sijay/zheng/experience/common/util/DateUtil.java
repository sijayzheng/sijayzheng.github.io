package sijay.zheng.experience.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    private static final int[] dayArray = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
            30, 31
    };

    public DateUtil() {
    }

    public static void main(String[] args) {
        System.out.println(getFullFormatedDateString(System.currentTimeMillis()));
    }

    public static String[] getCloseYearList(String baseYear, int before, int after, boolean inc) {
        int year = Integer.parseInt(baseYear);
        if (before < 0 || after < 0) {
            throw new IllegalArgumentException("before or after year must be great than zero!");
        }
        String[] yearList = new String[before + after + 1];
        if (inc) {
            for (int i = 0; i <= before + after; i++) {
                yearList[i] = String.valueOf((year - before) + i);
            }

        } else {
            for (int i = 0; i <= before + after; i++) {
                yearList[i] = String.valueOf((year + after) - i);
            }

        }
        return yearList;
    }

    public static String[] getCurrentCloseYearList(int before, int after, boolean inc) {
        String year = getCurrentYear();
        return getCloseYearList(year, before, after, inc);
    }

    public static Timestamp getSQLCurTimestamp() {
        return Timestamp.valueOf(getFormatedCurDateString("-"));
    }

    public static String getCurrentYear() {
        Calendar calendar = new GregorianCalendar();
        return "" + calendar.get(Calendar.YEAR);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     * <p>
     * delimeter 为日期分隔符 - or /
     */
    public static String getFormatedDateString(long time, String delimeter) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(time);
        return getFormatedDateString(calendar, delimeter);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String getFullFormatedDateString(long time) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(time);
        return getFormatedDateString(calendar, "-");
    }

    public static String getFormatedDateString(Calendar calendar, String delimeter) {
        String year = "" + calendar.get(Calendar.YEAR);
        String month = "" + (calendar.get(Calendar.MONTH) + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = "" + calendar.get(Calendar.DATE);
        if (day.length() == 1) {
            day = "0" + day;
        }
        String hour = "" + calendar.get(Calendar.HOUR_OF_DAY);
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        String minute = "" + calendar.get(Calendar.MINUTE);
        if (minute.length() == 1) {
            minute = "0" + minute;
        }
        String second = "" + calendar.get(Calendar.SECOND);
        if (second.length() == 1) {
            second = "0" + second;
        }
        String str;
        str = year + delimeter + month + delimeter + day + " " + hour + ":" + minute + ":" + second;
        return str;
    }

    public static String getFormatedCurDateString(String delimeter) {
        Calendar calendar = new GregorianCalendar();
        return getFormatedDateString(calendar, delimeter);
    }

    /**
     * yyyy-MM-dd
     */
    public static String getFormatedDateString(Date date) {
        return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String getFullFormatedDateString(Date date) {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
    }

    /**
     * yyyy-MM-dd
     */
    public static String getFormatedDateString() {
        return (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
    }

    /**
     * yyyyMMdd
     */
    public static String getFormatedDateString1() {
        return (new SimpleDateFormat("yyyyMMdd")).format(new Date());
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String getFullFormatedDateString() {

        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
    }

    /**
     * 传入格式和date对象
     */
    public static String getFormatedDateString(Date date, String formate) {
        return (new SimpleDateFormat(formate)).format(date);
    }

    public static int getCurrentHour() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static Date parseDate(String strPattern, String strDate)
            throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(strPattern);
        Date date;
        date = df.parse(strDate);
        return date;
    }

    public static Date parseDate(String strDate)
            throws ParseException {
        return parseDate("yyyy-MM-dd", strDate);
    }

    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        }
        if (year % 4 == 0) {
            return year % 100 != 0;
        } else {
            return false;
        }
    }

    public static boolean isLeapYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return isLeapYear(year);
    }

    public static synchronized boolean isLeapYear(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        int year = gc.get(Calendar.YEAR);
        return isLeapYear(year);
    }

    public static synchronized Date getFirstDayOfMonth(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.set(Calendar.DATE, 1);
        return gc.getTime();
    }

    public static int getLastDayOfMonth(int month) {
        if (month < 1 || month > 12) {
            return -1;
        }
        int retn;
        if (month == 2) {
            if (isLeapYear()) {
                retn = 29;
            } else {
                retn = dayArray[month - 1];
            }
        } else {
            retn = dayArray[month - 1];
        }
        return retn;
    }

    public static Date getLastDayOfMonth(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        switch (gc.get(Calendar.MONTH)) {
            case 0 -> // '\0'
                    gc.set(5, 31);
            case 1 -> // '\001'
                    gc.set(5, 28);
            case 2 -> // '\002'
                    gc.set(5, 31);
            case 3 -> // '\003'
                    gc.set(5, 30);
            case 4 -> // '\004'
                    gc.set(5, 31);
            case 5 -> // '\005'
                    gc.set(5, 30);
            case 6 -> // '\006'
                    gc.set(5, 31);
            case 7 -> // '\007'
                    gc.set(5, 31);
            case 8 -> // '\b'
                    gc.set(5, 30);
            case 9 -> // '\t'
                    gc.set(5, 31);
            case 10 -> // '\n'
                    gc.set(5, 30);
            case 11 -> // '\013'
                    gc.set(5, 31);
        }
        if (gc.get(Calendar.MONTH) == Calendar.FEBRUARY && isLeapYear(gc.get(Calendar.YEAR))) {
            gc.set(Calendar.DATE, 29);
        }
        return gc.getTime();
    }

    public static Date getNextMonth(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.MONTH, 1);
        return gc.getTime();
    }

    public static Date getPreviousMonth(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.MONTH, -1);
        return gc.getTime();
    }

    public static Date getPreviousMonths(Date date, int months) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.MONTH, -months);
        return gc.getTime();
    }

    public static Date getFirstDayOfPreviousMonth(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.setTime(getPreviousMonth(gc.getTime()));
        gc.setTime(getFirstDayOfMonth(gc.getTime()));
        return gc.getTime();
    }

    public static Date getLastDayOfPreviousMonth(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.setTime(getPreviousMonth(gc.getTime()));
        gc.setTime(getLastDayOfMonth(gc.getTime()));
        return gc.getTime();
    }

    public static Date getFirstDayOfNextMonth(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.setTime(getNextMonth(gc.getTime()));
        gc.setTime(getFirstDayOfMonth(gc.getTime()));
        return gc.getTime();
    }

    public static Date getLastDayOfNextMonth(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.setTime(getNextMonth(gc.getTime()));
        gc.setTime(getLastDayOfMonth(gc.getTime()));
        return gc.getTime();
    }

    public static Date getNextDay(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.DATE, 1);
        return gc.getTime();
    }

    public static Date getPreviousDay(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.DATE, -1);
        return gc.getTime();
    }

    public static Date getNextDays(Date date, int days) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.DATE, days);
        return gc.getTime();
    }

    public static Date getPreviousDays(Date date, int days) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.DATE, -days);
        return gc.getTime();
    }

    /**
     * 该该方法用于获取下一天的时间
     */
    public static String nextday(String date) {

        int year = Integer.parseInt(date.substring(0, 4));
        int mon = Integer.parseInt(date.substring(5, 7)) - 1;
        int day = Integer.parseInt(date.substring(8, 10));
        Calendar ca = new GregorianCalendar(year, mon, day);
        ca.roll(Calendar.DAY_OF_YEAR, true); //滚动一天后
        int nextday = ca.get(Calendar.DAY_OF_MONTH);
        int nextmonth = ca.getTime().getMonth();
        int nextyear = ca.getTime().getYear() + 1900;
        if (nextmonth == 0 && nextday == 1) {
            nextyear++;
        }
        String nowmonth;
        String nowday;
        if (nextmonth < 9) {
            nowmonth = "0" + (nextmonth + 1);
        } else {
            nowmonth = String.valueOf(nextmonth + 1);
        }
        if (nextday < 10) {
            nowday = "0" + nextday;
        } else {
            nowday = String.valueOf(nextday);
        }

        return nextyear + "-" + nowmonth + "-" + nowday;
    }

    /**
     * 该该方法用于计算两个时间的时间差（单位：秒）
     */
    public static Integer getDiffSeconds(String startdate, String enddate) throws ParseException {

        Date d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startdate);
        Date d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(enddate);
        long diff = d2.getTime() - d1.getTime();

        return (int) (diff / 1000);
    }

    /**
     * 该该方法用于计算当天结束还有多少秒（单位：秒）
     */
    public static Integer getSecondsEndToday() {

        Calendar c = Calendar.getInstance();
        long now = c.getTimeInMillis();

        c.add(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        long millis = c.getTimeInMillis() - now;

        return (int) (millis / 1000);
    }

}
