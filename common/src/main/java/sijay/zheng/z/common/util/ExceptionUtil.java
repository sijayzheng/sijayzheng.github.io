/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.common.util;

/**
 * @author sijay
 * @date 2022/11/11 20:55
 */
public class ExceptionUtil {

    public static void throwException(String errorMsg) {
        throw new RuntimeException(errorMsg);
    }
}
