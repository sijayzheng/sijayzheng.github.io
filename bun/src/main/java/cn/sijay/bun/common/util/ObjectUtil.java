package cn.sijay.bun.common.util;

import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * <strong>ObjectUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-12-12
 */
@NoArgsConstructor
public class ObjectUtil {
    public static <T> T orDefault(T t, T defaultValue) {
        return Optional.ofNullable(t).orElse(defaultValue);
    }

}
