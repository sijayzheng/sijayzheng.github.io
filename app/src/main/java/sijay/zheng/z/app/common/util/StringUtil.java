/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author sijay
 */
public class StringUtil extends StringUtils {
    private static final String[] CHARSET_ARR = {"UTF-8", "GB2312", "GBK", "Windows-1252", "ISO8859-1"};

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

}
