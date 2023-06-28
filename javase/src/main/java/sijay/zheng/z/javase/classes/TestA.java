package sijay.zheng.z.javase.classes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author Sijay
 */
public class TestA {
    public static void main(String[] args) throws Exception {
        Class<User> clazz = User.class;
        // 获取构造器
        clazz.getConstructors();
        clazz.getConstructor(int.class, String.class);
        clazz.getDeclaredConstructors();
        Constructor<User> constructor = clazz.getDeclaredConstructor(int.class, String.class);

        //获取字段
        clazz.getFields();
        clazz.getField("publicName");
        clazz.getDeclaredFields();
        clazz.getDeclaredField("name");

        //获取方法
        clazz.getMethods();
        clazz.getMethod("getId");
        clazz.getDeclaredMethods();
        clazz.getDeclaredMethod("getName");

        User user = constructor.newInstance(100, "username");

        Method print = clazz.getDeclaredMethod("print");
        print.setAccessible(false);
        print.invoke(user);
        Method prints = clazz.getDeclaredMethod("prints");
        prints.setAccessible(false);
        prints.invoke(user);
    }
}

class User {
    private int id;
    private String name;
    public String publicName;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void print() {
        System.out.println("haahaaa");
    }

    private static void prints() {
        System.out.println("23333333333333333");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }
}
