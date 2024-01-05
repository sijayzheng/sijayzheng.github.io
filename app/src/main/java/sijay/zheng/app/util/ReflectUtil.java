package cn.zheng.sijay.j.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author sijay
 * @desc ReflectUtil
 * @date 2023/12/19 14:30
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectUtil {
    public static void setFieldValue(Object obj, String source, String target) {
        try {
            Class<?> clazz = obj.getClass();
            Field sourceField = clazz.getDeclaredField(source);
            sourceField.setAccessible(true);
            Field targetField = clazz.getDeclaredField(target);
            targetField.setAccessible(true);
            targetField.set(obj, sourceField.get(obj));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
