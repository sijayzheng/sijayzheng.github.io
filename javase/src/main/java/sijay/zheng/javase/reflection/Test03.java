package sijay.zheng.javase.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Sijay
 * @date 2022/3/9 0:45
 */
public class Test03 {
    public static void main(String[] args) throws Exception {
        Method method = Test03.class.getDeclaredMethod("test", HashMap.class, List.class);
        for (Class<?> type : method.getParameterTypes()) {
            System.out.println("type====" + type.getTypeName());
        }
        for (Class<?> exceptionType : method.getExceptionTypes()) {
            System.out.println("exceptionType----" + exceptionType.getTypeName());
        }

        System.out.println("returnType++" + method.getReturnType()
                .getTypeName());

        Type[] genericParameterTypes = method.getGenericParameterTypes();
        Type[] genericExceptionTypes = method.getGenericExceptionTypes();
        Type genericReturnType = method.getGenericReturnType();
        for (Type genericParameterType : genericParameterTypes) {
            System.out.println("genericParameterType====" + genericParameterType);
            if (genericParameterType instanceof ParameterizedType parameterizedType) {
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println("actualTypeArgument===" + actualTypeArgument);
                }
            }
        }
        for (Type genericExceptionType : genericExceptionTypes) {
            System.out.println("genericExceptionType==" + genericExceptionType);
        }
        System.out.println("genericReturnType=" + genericReturnType);

        ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println("xxxxx===" + actualTypeArgument);
        }
    }

    public static LinkedHashMap<Integer, String> test(HashMap<String, User> map, List<User> list) {
        return null;
    }
}
