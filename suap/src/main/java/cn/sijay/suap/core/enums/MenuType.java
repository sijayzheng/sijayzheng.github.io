package cn.sijay.suap.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <em>MenuType</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/2/1 11:41
 */
@Getter
@AllArgsConstructor
public enum MenuType {

    A("A", "应用"),
    D("D", "目录"),
    M("M", "菜单"),
    B("B", "按钮"),
    ;
    @EnumValue
    private final String code;
    @JsonValue
    private final String desc;

    public static MenuType getByCode(String code) {
        if (code == null) {
            return MenuType.D;
        }
        for (MenuType menuType : MenuType.values()) {
            if (menuType.getCode().equals(code)) {
                return menuType;
            }
        }
        return MenuType.D;
    }
}
