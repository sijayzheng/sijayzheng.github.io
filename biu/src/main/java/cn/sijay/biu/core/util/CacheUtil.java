package cn.sijay.biu.core.util;

import lombok.NoArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * CacheUtil
 *
 * @author Sijay
 * @since 2025-03-05
 */
@NoArgsConstructor
public class CacheUtil {

    private static final CacheManager CACHE_MANAGER = SpringUtil.getBean(CacheManager.class);

    /**
     * 获取缓存值
     *
     * @param cacheNames 缓存组名称
     * @param key        缓存key
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T get(String cacheNames, Object key) {
        Cache.ValueWrapper wrapper = getCache(cacheNames).get(key);
        return wrapper != null ? (T) wrapper.get() : null;
    }

    private static Cache getCache(String cacheNames) {
        return CACHE_MANAGER.getCache(cacheNames);
    }

    /**
     * 保存缓存值
     *
     * @param cacheNames 缓存组名称
     * @param key        缓存key
     * @param value      缓存值
     */
    public static void put(String cacheNames, Object key, Object value) {
        getCache(cacheNames).put(key, value);
    }

    /**
     * 删除缓存值
     *
     * @param cacheNames 缓存组名称
     * @param key        缓存key
     */
    public static void evict(String cacheNames, Object key) {
        getCache(cacheNames).evict(key);
    }

    /**
     * 清空缓存值
     *
     * @param cacheNames 缓存组名称
     */
    public static void clear(String cacheNames) {
        getCache(cacheNames).clear();
    }

}
