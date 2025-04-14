package cn.sijay.biu.core.annotation;

import cn.sijay.biu.core.strategy.CellMergeStrategy;

import java.lang.annotation.*;

/**
 * excel 列单元格合并(合并列相同项)
 * <p>
 * 需搭配 {@link CellMergeStrategy} 策略使用
 *
 * @author Sijay
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CellMerge {

    /**
     * col index
     */
    int index() default -1;

    /**
     * 合并需要依赖的其他字段名称
     */
    String[] mergeBy() default {};

}
