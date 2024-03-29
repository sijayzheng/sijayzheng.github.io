package cn.zheng.sijay.j.util;

import cn.zheng.sijay.j.domain.common.Pair;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sijay
 * @desc TranslateUtil
 * @date 2023/12/19 14:30
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TranslateUtil {
    public static <T> List<T> transListFromSelf(List<T> menus, Pair<String, String>[] pair) {
        log.info(menus.toString());
        return menus.stream()
                    .map(item -> TranslateUtil.transSingle(item, pair))
                    .collect(Collectors.toList());
    }

    public static <T> T transSingle(T t, Pair<String, String>... pair) {
        log.info("transSingle----: {}", t);
        for (Pair<String, String> p : pair) {
            log.info(p.key() + "---" + p.value());
            ReflectUtil.setFieldValue(t, p.key(), p.value());
        }
        log.info("transSingle: {}", t);
        return t;
    }
}
