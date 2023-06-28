package sijay.zheng.z.javase.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Sijay
 * @date 2022/3/9 0:30
 */
public class Test02 {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        test1();
        test2();
        test3();
    }

    public static void test1() {
        User user = new User();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            user.getName();
        }
        long end = System.currentTimeMillis();
        System.out.println("普通耗时：" + (end - start) + "ms");
    }

    public static void test2() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        User user = new User();
        Method getName = user.getClass().getDeclaredMethod("getName");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user);
        }
        long end = System.currentTimeMillis();
        System.out.println("反射耗时：" + (end - start) + "ms");
    }

    public static void test3() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        User user = new User();
        Method getName = user.getClass().getDeclaredMethod("getName");
        getName.setAccessible(true);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user);
        }
        long end = System.currentTimeMillis();
        System.out.println("反射关闭检查耗时：" + (end - start) + "ms");
    }
}
