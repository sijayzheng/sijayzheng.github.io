package cn.sijay.suap.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <strong>TransIgnore</strong>
 * <p>
 * 用于controller中的方法上，禁用翻译
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface TranslateIgnore {
}
