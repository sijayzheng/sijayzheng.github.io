package cn.sijay.suap.core.utils;

import cn.sijay.suap.core.entity.Option;
import cn.sijay.suap.core.enums.ExceptionEnum;
import cn.sijay.suap.core.enums.JavaType;
import cn.sijay.suap.core.exception.BaseException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * <strong>ReflectUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
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

    public static List<Option<String>> enumToOption(String className) {
        List<Option<String>> optionMap = new ArrayList<>();
        Method labelMethod = null;
        try {
            Class<?> clazz = Class.forName(JavaType.getByCode(className).getPkg());
            String labelField = "";
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getAnnotation(JsonValue.class) != null) {
                    labelField = field.getName();
                }
            }
            for (Method method : clazz.getMethods()) {
                if (method.getName().startsWith("get")) {
                    if (method.getName().equalsIgnoreCase("get" + labelField)) {
                        labelMethod = method;
                    }
                }
            }
            if (labelMethod != null) {
                for (Object o : clazz.getEnumConstants()) {
                    String value = labelMethod.invoke(o).toString();
                    optionMap.add(new Option<>(value, value));
                }
            }
        } catch (ClassNotFoundException e) {
            throw new BaseException(ExceptionEnum.REFLECT_CLASS_NOT_FOUND_ERROR, className);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new BaseException(ExceptionEnum.REFLECT_METHOD_INVOKE_ERROR, className, labelMethod);
        }
        return optionMap;
    }

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
    // public static List<SelectOption<String>> enumToOption(String className) {
    //     List<SelectOption<String>> optionMap = new ArrayList<>();
    //     Method labelMethod = null;
    //     try {
    //         Class<?> clazz = Class.forName(JavaType.getByCode(className).getPkg());
    //         String labelField = "";
    //         for (Field field : clazz.getDeclaredFields()) {
    //             if (field.getAnnotation(JsonValue.class) != null) {
    //                 labelField = field.getName();
    //             }
    //         }
    //         for (Method method : clazz.getMethods()) {
    //             if (method.getName().startsWith("get")) {
    //                 if (method.getName().equalsIgnoreCase("get" + labelField)) {
    //                     labelMethod = method;
    //                 }
    //             }
    //         }
    //         if (labelMethod != null) {
    //             for (Object o : clazz.getEnumConstants()) {
    //                 String value = labelMethod.invoke(o).toString();
    //                 optionMap.add(new SelectOption<>(value, value));
    //             }
    //         }
    //     } catch (ClassNotFoundException e) {
    //         throw new BaseException(ExceptionEnum.REFLECT_CLASS_NOT_FOUND_ERROR, className);
    //     } catch (InvocationTargetException | IllegalAccessException e) {
    //         throw new BaseException(ExceptionEnum.REFLECT_METHOD_INVOKE_ERROR, className, labelMethod);
    //     }
    //     return optionMap;
    // }

}
