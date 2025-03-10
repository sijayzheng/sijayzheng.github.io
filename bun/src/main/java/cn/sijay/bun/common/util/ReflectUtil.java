package cn.sijay.bun.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * <strong>ReflectUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-11-20
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectUtil {
    private static final String SETTER_PREFIX = "set";

    private static final String GETTER_PREFIX = "get";

    /**
     * 调用Getter方法
     */
    @SuppressWarnings("unchecked")
    public static <E> E invokeGetter(Class<?> clazz, Object obj, String propertyName) {
        String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(propertyName);
        Method method = ReflectionUtils.findMethod(clazz, getterMethodName);
        if (method != null) {
            return (E) ReflectionUtils.invokeMethod(method, obj);
        }
        return (E) obj;
    }

    /**
     * 调用Setter方法, 仅匹配方法名
     */
    public static <E> void invokeSetter(Class<?> clazz, Object obj, String propertyName, E value) {
        String setterMethodName = SETTER_PREFIX + StringUtils.capitalize(propertyName);
        Method method = ReflectionUtils.findMethod(clazz, setterMethodName);
        if (method != null) {
            ReflectionUtils.invokeMethod(method, obj, value);
        }
    }

}
