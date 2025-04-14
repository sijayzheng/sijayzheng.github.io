package cn.sijay.biu.core.util;

import cn.sijay.biu.core.exception.UtilException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 反射工具类. 提供调用getter/setter方法, 访问私有变量, 调用私有方法, 获取泛型类型Class, 被AOP过的真实类等工具函数.
 *
 * @author Sijay
 */
@SuppressWarnings("rawtypes")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectUtil {

    private static final String GETTER_PREFIX = "get";

    /**
     * 调用Getter方法.
     * 支持多级，如：对象名.对象名.方法
     */
    @SuppressWarnings("unchecked")
    public static <E> E invokeGetter(Object obj, String propertyName) {
        Object object = obj;
        for (String name : StringUtil.split(propertyName, ".")) {
            String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(name);
            object = invoke(object, getterMethodName);
        }
        return (E) object;
    }

    private static Object invoke(Object object, String methodName) {
        try {
            return object.getClass().getMethod(methodName).invoke(object);
        } catch (Exception e) {
            throw new UtilException(e.getMessage());
        }
    }

    public static List<Field> getFields(Class<?> aClass, Predicate<Field> predicate) {
        //get all fields from aClass
        return Arrays.stream(aClass.getDeclaredFields()).filter(predicate).toList();
    }
}
