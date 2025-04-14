package cn.sijay.suap.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * <strong>ObjectUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectUtil {
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isNull(Object obj) {
        if (obj instanceof String str) {
            return str.isBlank();
        }
        return Objects.isNull(obj);
    }
}
