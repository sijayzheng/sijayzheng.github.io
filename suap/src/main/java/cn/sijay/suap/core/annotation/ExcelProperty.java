package cn.sijay.suap.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * <em>ExcelProperty</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/19 9:39
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelProperty {
    String value();

    int sort();
}
