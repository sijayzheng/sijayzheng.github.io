package cn.sijay.biu.core.annotation;

import java.lang.annotation.*;

/**
 * ApiEncrypt
 *
 * @author Sijay
 * @since 2025-02-14
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiEncrypt {

    /**
     * 响应加密忽略，默认不加密，为 true 时加密
     */
    boolean response() default false;

}
