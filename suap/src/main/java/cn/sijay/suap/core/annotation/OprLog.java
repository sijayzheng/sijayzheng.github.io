package cn.sijay.suap.core.annotation;

import cn.sijay.suap.core.enums.OperateType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <strong>OprLog</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OprLog {
    String value() default "";

    OperateType operateType() default OperateType.OTHER;
}
