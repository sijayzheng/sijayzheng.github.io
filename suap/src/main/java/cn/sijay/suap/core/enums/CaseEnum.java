package cn.sijay.suap.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>CaseEnum</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@AllArgsConstructor
@Getter
public enum CaseEnum {
    /**
     * 小驼峰命名
     */
    LOWER_CAMEL_CASE,
    /**
     * 大驼峰命名
     */
    UPPER_CAMEL_CASE,
    /**
     * 小写蛇形命名
     */
    LOWER_SANKE_CASE,
    /**
     * 大写蛇形命名
     */
    UPPER_SNAKE_CASE,
}
