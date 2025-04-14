package cn.sijay.biu.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * MenuEnum
 *
 * @author Sijay
 * @since 2024-11-14
 */
@Getter
@AllArgsConstructor
public enum MenuEnum {
    DIRECTORY("目录"),
    MENU("菜单"),
    BUTTON("按钮"),
    ;
    private final String value;

}
