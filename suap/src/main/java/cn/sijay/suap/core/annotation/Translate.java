package cn.sijay.suap.core.annotation;

import cn.sijay.suap.core.base.ITrans;
import cn.sijay.suap.core.enums.TranslateType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <strong>Trans</strong>
 * <p>
 * 需要翻译的字段注解
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Translate {

    /**
     * 翻译类型
     */
    TranslateType type();

    /**
     * 字典类型
     */
    String dictCode() default "";

    /**
     * 目标class
     */
    Class<? extends ITrans> target() default ITrans.class;

    /**
     * 目标字段
     */
    String field() default "";

}
