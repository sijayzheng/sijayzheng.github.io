/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app.common.annotation;

import java.lang.annotation.*;

/**
 * @author Sijay
 * @date 2023/2/12 22:17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogDog {
    String value() default "";
}
