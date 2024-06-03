package cn.sijay.suap.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>GenderType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Getter
@AllArgsConstructor
public enum GenderType {
    MALE("男"),
    FEMALE("女"),
    UNKNOWN("未知"),
    ;
    @JsonValue
    private final String desc;

}
