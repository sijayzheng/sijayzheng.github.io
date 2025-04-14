package cn.sijay.biu.core.util;

/**
 * ArrayUtil
 *
 * @author Sijay
 * @since 2025-03-04
 */
public class ArrayUtil {
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }
}
