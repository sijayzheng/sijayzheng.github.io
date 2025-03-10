package cn.sijay.bun.core.annotation;

import java.lang.annotation.*;

/**
 * <strong>CellMerge</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-11-20
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CellMerge {

    /**
     * col index
     */
    int index() default -1;

    boolean main() default false;

}
