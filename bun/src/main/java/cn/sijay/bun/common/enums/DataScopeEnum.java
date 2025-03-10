package cn.sijay.bun.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>DataScopeEnum</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-11-14
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum {
    ALL("所有"),
    DEPT("本部门"),
    DEPT_UNDER("本部门及以下"),
    SELF("本人"),
    CUSTOM("自定义"),
    ;
    private final String value;
}
