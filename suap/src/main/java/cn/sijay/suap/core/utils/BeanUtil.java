package cn.sijay.suap.core.utils;

import cn.sijay.suap.core.base.BaseException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * <strong>BeanUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanUtil {
    private final static List<String> ENTITY_COMMON_FIELDS = List.of("creator", "createTime", "updater", "updateTime", "version", "creatorName", "updaterName");

    public static void copyNotBlankEntityProperties(Object source, Object target) {
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

    public static <S, T> void copyProperties(S source, T target) {
        BeanUtils.copyProperties(source, target);
    }

    public static <S, T> T copyProperties(S source, Class<T> targetClass) {
        T target;
        try {
            target = targetClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new BaseException("调用{}的无参构造方法失败", targetClass);
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static <T> T cast(Object t, Class<T> clazz) {
        return clazz.cast(t);
    }
}
