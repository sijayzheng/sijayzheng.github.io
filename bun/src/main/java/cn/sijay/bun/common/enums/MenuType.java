package cn.sijay.bun.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>MenuType</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-11-14
 */
@Getter
@AllArgsConstructor
public enum MenuType {
    DIRECTORY("目录"),
    MENU("菜单"),
    BUTTON("按钮"),
    ;
    private final String value;

}
