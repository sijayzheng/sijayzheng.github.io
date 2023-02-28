package sijay.zheng.experience.common.util;

import org.apache.commons.lang3.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

/**
 * @author sijay
 */
public class StringUtil extends StringUtils {

    public static final int LEFT_SPACE = 0;
    public static final int RIGHT_SPACE = 1;
    public static final int TRUNC_LEFT = 0;
    public static final int TRUNC_RIGHT = 1;
    private static final String[] CHARSET_ARR = {"UTF-8", "GB2312", "GBK", "Windows-1252", "ISO8859-1"};

    private StringUtil() {
    }

    public static void testAllCharset(String text) throws UnsupportedEncodingException {
        if (text == null || text.length() == 0) {
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
     * 字符串转换成为16进制(无需Unicode编码)
     *
     * @param str
     * @return
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            // sb.append(' ');
        }
        return sb.toString().trim();
    }

    public static boolean startsWithIgnoreCase(String base, String start) {
        if (base.length() < start.length()) {
            return false;
        } else {
            return base.regionMatches(true, 0, start, 0, start.length());
        }
    }

    public static boolean endsWithIgnoreCase(String base, String end) {
        if (base.length() < end.length()) {
            return false;
        } else {
            return base.regionMatches(true, base.length() - end.length(), end, 0, end.length());
        }
    }

    public static int parseInt(String value, int defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static boolean parseBoolean(String attribute, boolean defaultValue) {
        if (attribute == null) {
            return defaultValue;
        }
        return attribute.equals("true");
    }

    public static String trim(String string) {
        return string != null ? string.trim() : "";
    }

    public static String leftTrim(String str) {
        if (str == null) {
            return "";
        }
        byte[] bytes = str.getBytes();
        int index = 0;
        do {
            byte ch = bytes[index];
            if (ch == 32) {
                index++;
            } else {
                return str.substring(index);
            }
        } while (true);
    }

    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        byte[] bytes = str.getBytes();
        int index = length(str);
        if (index == 0) {
            return "";
        }
        index--;
        byte ch;
        do {
            ch = bytes[index];
        }
        while (ch == 32 && --index >= 0);
        return new String(str.getBytes(), 0, index + 1);
    }

    public static String allTrim(String str) {
        if (str == null) {
            return "";
        }
        String tmp = str.trim();
        if (tmp.equals("")) {
            return "";
        }
        int idx;
        int len;
        len = tmp.length();
        for (idx = tmp.indexOf(" "); idx > 0; ) {
            tmp = tmp.substring(0, idx) + tmp.substring(idx + 1, len);
            idx = tmp.indexOf(" ");
            len = tmp.length();
        }

        return tmp;
    }

    public static String alignStr(String str, int len, int direct, int truncWay) {
        return alignStr(str, len, direct, truncWay, ' ');
    }

    public static String alignStr(String str, int len, int direct, int truncWay, char fixStr) {
        if (str == null) {
            return "";
        }
        byte[] b = getBytes(str);
        int l = length(str);
        if (l >= len) {
            if (truncWay == 0) {
                return new String(b, l - len, len);
            } else {
                return new String(b, 0, len);
            }
        }
        StringBuffer sb = new StringBuffer(str);
        for (int i = l; i < len; i++) {
            if (direct == 0) {
                sb = sb.insert(0, fixStr);
            } else {
                sb = sb.append(fixStr);
            }
        }

        return sb.substring(0);
    }

    public static String toUTF8String(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c <= '\377') {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes(StandardCharsets.UTF_8);
                } catch (Exception ex) {
                    b = new byte[0];
                }
                for (int value : b) {
                    int k = value;
                    if (k < 0) {
                        k += 256;
                    }
                    sb.append("%").append(Integer.toHexString(k).toUpperCase());
                }

            }
        }

        return sb.toString();
    }

    public static int length(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return (new String(str.getBytes("GBK"), StandardCharsets.ISO_8859_1)).length();
        } catch (UnsupportedEncodingException e) {
            return -1;
        }
    }

    public static byte[] getBytes(String str) {
        try {
            return str.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "".getBytes();
    }

    public static String stringNumberAdd(String numberStr, int increaseValue, int minLength) {
        if (numberStr == null || numberStr.trim().equals("")) {
            numberStr = "0";
        }
        int stringLength = numberStr.length();
        if (stringLength < minLength) {
        }
        numberStr = Long.toString(Long.parseLong(numberStr) + (long) increaseValue);
        int temp = numberStr.length();//
        if (minLength >= temp) {
            minLength -= temp;
        } else {
            minLength = 0;
        }
        String fillIn = "";
        StringBuilder numberStrBuilder = new StringBuilder(numberStr);
        for (int i = 0; i < minLength; i++) {
            numberStrBuilder.insert(0, "0");
        }
        numberStr = numberStrBuilder.toString();

        return numberStr;
    }

    public static String trimStringNumberRightZero(String numberStr) {
        if (numberStr == null || numberStr.trim().equals("")) {
            return "";
        }
        int i;
        for (i = numberStr.length(); i > 0 && numberStr.charAt(i - 1) == '0'; i--) {
            ;
        }
        return numberStr.substring(0, i);
    }

    public static ArrayList splitString(String targetString, String seperator) {
        if (targetString == null || targetString.trim().equals("")) {
            return new ArrayList();
        }
        ArrayList resultStrs = new ArrayList();
        String singleStatement;
        int fromIndex = 0;
        int endIndex;
        boolean breakFor = false;
        while (fromIndex < targetString.length()) {
            endIndex = targetString.indexOf(seperator, fromIndex);
            if (endIndex == -1) {
                endIndex = targetString.length();
                breakFor = true;
            }
            singleStatement = targetString.substring(fromIndex, endIndex);
            if (!singleStatement.trim().equals("")) {
                resultStrs.add(singleStatement);
            }
            fromIndex = endIndex + 1;
            if (breakFor) {
                break;
            }
        }
        return resultStrs;
    }

    public static List<Integer> getIntArray(String str) {
        List<Integer> list = new ArrayList<>();
        if (str == null) {
            return list;
        }
        try {
            String[] strarray = str.split(",");
            for (String astr : strarray) {
                list.add(Integer.valueOf(astr));
            }
        } catch (NumberFormatException e) {
            return list;
        }
        return list;
    }

    public static String getArrayString(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder("" + list.get(0));
        for (int i = 1; i < list.size(); i++) {
            result.append(",").append(list.get(i));
        }
        return result.toString();
    }

    public static String xmlParse(String str) {
        str = str.replaceAll("&", "&amp;");
        int positionOfLt = 0;
        while (positionOfLt >= 0) {
            String Substr = str.substring(positionOfLt + 1);
            if (Substr.indexOf("formula") != 0
                    && Substr.indexOf("global") != 0
                    && Substr.indexOf("normal") != 0
                    && Substr.indexOf("alarm") != 0
                    && Substr.indexOf("unnormal") != 0
                    && Substr.indexOf("line") != 0
                    && Substr.indexOf("filter") != 0
                    && Substr.indexOf("/") != 0
                    && Substr.indexOf("show") != 0
            ) {
                str = str.subSequence(0, positionOfLt) + "&lt;" + Substr;
            }
            positionOfLt = str.indexOf("<", positionOfLt + 1);
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(stringNumberAdd("009900123", 1, 20));
    }
}
