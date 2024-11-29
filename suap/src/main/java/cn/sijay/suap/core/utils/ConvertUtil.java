package cn.sijay.suap.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * <strong>ConvertUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConvertUtil {

    public static <T> T convert(Object o, Class<T> targetType) throws ClassCastException {
        if (o == null) {
            return null;
        }
        Class<?> type = o.getClass();
        if (targetType.isAssignableFrom(type)) {
            return (T) o;
        }

        String s = o.toString();
        if (targetType == Byte.class) {
            return (T) Byte.valueOf(s);
        }
        if (targetType == Short.class) {
            return (T) Short.valueOf(s);
        }
        if (targetType == Integer.class) {
            return (T) Integer.valueOf(s);
        }
        if (targetType == Long.class) {
            return (T) Long.valueOf(s);
        }
        if (targetType == Float.class) {
            return (T) Float.valueOf(s);
        }
        if (targetType == Double.class) {
            return (T) Double.valueOf(s);
        }
        if (targetType == Boolean.class) {
            return (T) Boolean.valueOf(s);
        }
        if (targetType == BigDecimal.class) {
            return (T) new BigDecimal(s);
        }
        if (targetType == String.class) {
            return (T) s;
        }
        // 这里会抛出ClassCastException
        return (T) o;
    }

}
