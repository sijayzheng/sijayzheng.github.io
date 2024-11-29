package cn.sijay.suap.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * <strong>ReflectUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectUtil {

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object newInstance(String className) {
        try {
            return Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 调用obj的setField 方法设置value
     *
     * @param obj       obj
     * @param fieldName 字段
     * @param value     值
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void setValue(Object obj, String fieldName, Object value) {
        Field field;
        try {
            if (obj instanceof Map) {
                ((Map) obj).put(fieldName, value);
                return;
            }
            field = getDeclaredField(obj.getClass(), fieldName);
            if (field == null) {
                return;
            }

            Class<?> type = field.getType();
            if (value != null && !type.isAssignableFrom(value.getClass())) {
                // 对于类型不同的字段进行转换
                value = ConvertUtil.convert(value, type);
            }
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            log.error("给{}的字段{}设置值{}错误", obj, fieldName, value, e);
        }
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredField
     *
     * @param clazz     : 子类对象
     * @param fieldName : 父类中的属性名
     * @return 父类中的属性对象
     */
    public static Field getDeclaredField(Class<?> clazz, String fieldName) {
        Field field;

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了

            }
        }

        return null;
    }

    /**
     * 获取对象的所有field
     *
     * @param object object
     * @return 所有字段
     */
    public static List<Field> getAllField(Object object) {
        Class<?> clazz = object.getClass();
        return getAllField(clazz);
    }

    /**
     * 获取一个class的所有的字段
     *
     * @param clazz class
     * @return 所有字段
     */
    public static List<Field> getAllField(Class<?> clazz) {
        Field[] fields;
        List<Field> result = new ArrayList<>();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                fields = clazz.getDeclaredFields();
                result.addAll(Arrays.asList(fields));
            } catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }
        return result;
    }

    /**
     * 反射获取一个值
     *
     * @param obj       obj
     * @param fieldName 字段名称
     * @return obj.字段 的值
     */
    @SuppressWarnings("rawtypes")
    public static Object getValue(Object obj, String fieldName) {
        Field field;
        try {
            if (obj instanceof Map) {
                return ((Map) obj).get(fieldName);
            }

            List<Field> fieldList = getAllField(obj.getClass());
            for (Field declaredfield : fieldList) {
                if (declaredfield.getName().equals(fieldName)) {
                    field = declaredfield;
                    field.setAccessible(true);
                    return field.get(obj);
                }
            }
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
            log.error("", e);
        }
        return "";
    }

    public static String getStringValue(Object obj, String fieldName) {
        return String.valueOf(Optional.ofNullable(getValue(obj, fieldName)).orElse(""));
    }

    /**
     * 根据一个class和注解获取字段集合
     *
     * @param clazz           class
     * @param annotationClass 注解
     * @return 字段集合
     */
    public static <T extends Annotation> List<Field> getAnnotationField(Class<?> clazz, Class<T> annotationClass) {
        List<Field> result = new ArrayList<>();
        List<Field> fields = getAllField(clazz);
        for (Field field : fields) {
            if (field.getAnnotation(annotationClass) != null) {
                result.add(field);
            }
        }
        return result;
    }

}
