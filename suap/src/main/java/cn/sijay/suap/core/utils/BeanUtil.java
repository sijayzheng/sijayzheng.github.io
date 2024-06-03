package cn.sijay.suap.core.utils;

import cn.sijay.suap.core.exception.UtilException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <strong>BeanUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanUtil {
    private final static List<String> ENTITY_COMMON_FIELDS = List.of("creator", "createTime", "updater", "updateTime", "version", "creatorName", "updaterName");

//    public static void copyProperties(Object source, Object target) {
//        log.info("source: {}, target: {}", source, target);
//        Class<?> sourceClass = source.getClass();
//        Class<?> targetClass = target.getClass();
//        Map<String, Field> sourceFieldMap = Arrays.stream(sourceClass.getDeclaredFields()).collect(Collectors.toMap(Field::getName, field -> field));
//        Map<String, Field> targetFieldMap = Arrays.stream(targetClass.getDeclaredFields()).collect(Collectors.toMap(Field::getName, field -> field));
//        System.out.println(cn.sijay.suap.core.util.JsonUtil.toJsonString(sourceFieldMap));
//        System.out.println(JsonUtil.toJsonString(targetFieldMap));
//        log.info("source: {}, target: {}", source, target);
//        log.info("copyProperties end");
//    }

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

    public static <S, T> void copyProperties(S source, T target) {
        BeanUtils.copyProperties(source, target);
//        copyProperties(source.getClass(), target.getClass(), true, false);
    }

    public static <S, T> T copyProperties(S source, Class<T> targetClass) {
        T target;
        try {
            target = targetClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new UtilException("调用{}的无参构造方法失败", targetClass);
        }
        BeanUtils.copyProperties(source, target);
//        copyProperties(source.getClass(), targetClass, true, false);
        return target;
    }

    public static <S, T> void copyProperties(Class<S> sourceClass, Class<T> targetClass, boolean ignoreEmpty, boolean includeSupperClassFields) {

//        Map<String, Field> sourceFieldMap = StreamUtil.toMap(getFields(sourceClass, includeSupperClassFields), Field::getName, field -> field);
//        Map<String, Field> targetFieldMap = StreamUtil.toMap(getFields(targetClass, includeSupperClassFields), Field::getName, field -> field);
//        sourceFieldMap.forEach((key, value) -> {
//            if (targetFieldMap.containsKey(key)) {
//                Field sourceField = sourceFieldMap.get(key);
//                Field targetField = targetFieldMap.get(key);
//                sourceField.setAccessible(true);
//                try {
//                    Object sourceFieldValue = sourceField.get(sourceClass);
//                    if (ignoreEmpty) {
//                        if (ObjectUtils.isNotEmpty(sourceFieldValue)) {
//                            targetField.setAccessible(true);
//                            targetField.set(targetClass, sourceFieldValue);
//                        }
//                    } else {
//                        targetField.setAccessible(true);
//                        targetField.set(targetClass, sourceFieldValue);
//                    }
//                } catch (IllegalAccessException e) {
//                    throw new UtilException("");
//                }
//            }
//
//        });
//        System.out.println("--------------------------------------------");
//        targetFieldMap.forEach((key, value) -> {
//            System.out.println(key);
//        });
    }

    public static <T> List<Field> getFields(Class<T> clazz) {
        return getFields(clazz, false);
    }

    public static <T> List<Field> getFields(String clazzName) throws ClassNotFoundException {
        return getFields(Class.forName(clazzName), false);
    }

    public static <T> List<Field> getFields(String clazzName, boolean includeSupperClassFields) throws ClassNotFoundException {
        return getFields(Class.forName(clazzName), includeSupperClassFields);
    }

    public static <T> List<Field> getFields(Class<T> clazz, boolean includeSupperClassFields) {
        List<Field> list = new ArrayList<>(Arrays.stream(clazz.getDeclaredFields()).filter(field -> !field.getName().equals("serialVersionUID"))
                                                 .toList());
        if (includeSupperClassFields && !Object.class.equals(clazz.getSuperclass())) {
            list.addAll(getFields(clazz.getSuperclass()));
        }
        return list;
    }

    public static <T> T cast(Object t, Class<T> clazz) {
        return clazz.cast(t);
    }
}
