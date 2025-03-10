package cn.sijay.bun.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>GenderType</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-11-14
 */
@Getter
@AllArgsConstructor
public enum GenderType {
    MALE("男"),
    FEMALE("女"),
    UNKNOWN("未知"),
    ;
    private final String value;
}
