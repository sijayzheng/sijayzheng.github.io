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
 * @since 2024-07-18
 */
@Getter
@AllArgsConstructor
public enum QueryType {
    like("包含"),
    equal("等于"),
    greater_than("大于"),
    greater_or_equal("大于等于"),
    less_than("小于"),
    less_or_equal("小于等于"),
    between("在范围内"),
    in("在列表内"),
    ;

    @JsonValue
    private final String desc;

}
