package cn.sijay.suap.core.annotation;

import cn.sijay.suap.core.enums.QueryType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <strong>QueryColumn</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryColumn {
    QueryType value() default QueryType.EQUAL;

}
