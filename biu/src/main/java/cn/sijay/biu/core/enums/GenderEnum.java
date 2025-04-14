package cn.sijay.biu.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GenderType
 *
 * @author Sijay
 * @since 2024-11-14
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {
    MALE("男"),
    FEMALE("女"),
    UNKNOWN("未知"),
    ;
    private final String value;
}
