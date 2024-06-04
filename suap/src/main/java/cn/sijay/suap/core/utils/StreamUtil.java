package cn.sijay.suap.core.utils;

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
 * @since 2024-06-01
 */
@Slf4j
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class StreamUtil {
    public static <T, K, U> Map<K, U> toMap(List<T> list, Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper) {
        return list.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }
}
