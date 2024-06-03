package cn.sijay.suap.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>DisplayType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Getter
@AllArgsConstructor
public enum DisplayType {
    none("无"),
    primary("主要"),
    success("成功"),
    info("信息"),
    warning("警告"),
    danger("危险"),
    ;
    @JsonValue
    private final String desc;
}
