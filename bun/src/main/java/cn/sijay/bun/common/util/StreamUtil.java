package cn.sijay.bun.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <strong>StreamUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-21
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StreamUtil {
    public static <T, K, U> Map<K, U> toMap(List<T> list, Function<T, K> keyMapper, Function<T, U> valueMapper) {
        return list.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <T, K> Map<K, T> toMap(List<T> list, Function<T, K> keyMapper) {
        return list.stream().collect(Collectors.toMap(keyMapper, v -> v));
    }
}
