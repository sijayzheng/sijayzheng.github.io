package cn.sijay.biu.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ObjectUtil
 *
 * @author Sijay
 * @since 2025-03-03
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectUtil {
    public static <T> T defaultIfNull(final T object, final T defaultValue) {
        return (object != null) ? object : defaultValue;
    }
}
