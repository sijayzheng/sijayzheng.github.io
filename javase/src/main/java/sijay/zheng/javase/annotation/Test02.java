package sijay.zheng.javase.annotation;

import java.lang.annotation.*;

/**
 * @author Sijay
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@interface MyAnn {
    String value() default "233";
}

/**
 * @author Sijay
 */
@MyAnn
public class Test02 {
    @MyAnn
    public void test() {
    }
}
