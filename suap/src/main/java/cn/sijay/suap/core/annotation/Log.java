package cn.sijay.suap.core.annotation;

import cn.sijay.suap.core.enums.OperateType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * <em>Log</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/22 16:13
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";

    OperateType operateType() default OperateType.OTHER;
}
