package sijay.zheng.z.javase.annotation;

import java.lang.annotation.*;

/**
 * @author Sijay
 */
@MyAnn
public class Test02 {
    @MyAnn
    public void test() {
    }
}

/**
 * @author sijay
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@interface MyAnn {
    String value() default "233";
}
