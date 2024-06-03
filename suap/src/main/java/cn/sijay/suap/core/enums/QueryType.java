package cn.sijay.suap.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>QueryType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Getter
@AllArgsConstructor
public enum QueryType {
    LIKE("包含"),
    EQUAL("等于"),
    GREATER_THAN("大于"),
    GREATER_OR_EQUAL("大于等于"),
    LESS_THAN("小于"),
    LESS_OR_EQUAL("小于等于"),
    BETWEEN("在范围内"),
    IN("在列表内"),
    ;

    @JsonValue
    private final String desc;

}
