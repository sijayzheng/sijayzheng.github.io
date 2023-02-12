package sijay.zheng.experience.javase.reflection;

import java.lang.annotation.*;
import java.lang.reflect.*;

/**
 * @author Sijay
 */
public class Test04 {
    public static void main(String[] args) {
        try {
            Class<?> cl = Class.forName("zheng.sijay.reflection.Student");
            //获取所有注解
            Annotation[] annotations = cl.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }
            //获取指定注解
            STable table = cl.getAnnotation(STable.class);
            System.out.println(table.value());
            for (STable sTable : cl.getAnnotationsByType(STable.class)) {
                System.out.println("___" + sTable);
            }
            //
            Field name = cl.getDeclaredField("name");
            SField field = name.getAnnotation(SField.class);
            System.out.println(field);
            System.out.println(field.column());
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}

@STable("t_student")
class Student {
    @SField(column = "id", type = "int", length = 11)
    private int id;
    @SField(column = "name", type = "varchar", length = 50)
    private String name;
    @SField(column = "age", type = "int", length = 3)
    private int age;

    public Student() {
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface STable {
    String value();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SField {
    String column();

    String type();

    int length();
}