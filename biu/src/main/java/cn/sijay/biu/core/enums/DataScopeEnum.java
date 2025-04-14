package cn.sijay.biu.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DataScopeEnum
 *
 * @author Sijay
 * @since 2024-11-14
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum {
    ALL("所有"),
    DEPT("本部门"),
    DEPT_UNDER("本部门及以下"),
    SELF("本人"),
    ;
    private final String value;
}
