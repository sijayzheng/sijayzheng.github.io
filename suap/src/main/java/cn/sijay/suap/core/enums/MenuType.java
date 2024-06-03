package cn.sijay.suap.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>MenuType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Getter
@AllArgsConstructor
public enum MenuType {
    DIRECTORY("目录"),
    MENU("菜单"),
    BUTTON("按钮"),
    ;

    @JsonValue
    private final String desc;
}
