package cn.sijay.suap.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * <em>BeanUtil</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/11 9:21
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanUtil {
    private final static List<String> ENTITY_COMMON_FIELDS = List.of("creator", "createTime", "updater", "updateTime", "version", "creatorName", "updaterName");

    public static void copyProperties(Object source, Object target) {
        log.info("source: {}, target: {}", source, target);
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        Map<String, Field> sourceFieldMap = Arrays.stream(sourceClass.getDeclaredFields()).collect(Collectors.toMap(Field::getName, field -> field));
        Map<String, Field> targetFieldMap = Arrays.stream(targetClass.getDeclaredFields()).collect(Collectors.toMap(Field::getName, field -> field));
        System.out.println(JsonUtil.toJsonString(sourceFieldMap));
        System.out.println(JsonUtil.toJsonString(targetFieldMap));
        log.info("source: {}, target: {}", source, target);
        log.info("copyProperties end");
    }

    public static void copyEntityProperties(Object source, Object target) {
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        for (Field field : sourceClass.getDeclaredFields()) {
            if (!ENTITY_COMMON_FIELDS.contains(field.getName())) {
                field.setAccessible(true);
                try {
                    if (ObjectUtils.isNotEmpty(field.get(source))) {
                        Field targetClassDeclaredField = targetClass.getDeclaredField(field.getName());
                        targetClassDeclaredField.setAccessible(true);
                        targetClassDeclaredField.set(target, field.get(source));
                    }
                } catch (IllegalAccessException | NoSuchFieldException ignored) {
                }
            }
        }
    }

}
