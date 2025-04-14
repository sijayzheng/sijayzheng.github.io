package cn.sijay.biu.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * CollectionUtil
 *
 * @author Sijay
 * @since 2025-03-03
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CollectionUtil {
    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }
}
