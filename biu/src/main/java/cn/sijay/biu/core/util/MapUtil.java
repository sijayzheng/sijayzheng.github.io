package cn.sijay.biu.core.util;

import java.util.Map;

/**
 * MapUtil
 *
 * @author Sijay
 * @since 2025-03-04
 */
public class MapUtil {
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map.isEmpty();
    }

    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

    @SafeVarargs
    public static <K, V> void removeAny(Map<K, V> map, K... keys) {
        for (K key : keys) {
            map.remove(key);
        }
    }
}
