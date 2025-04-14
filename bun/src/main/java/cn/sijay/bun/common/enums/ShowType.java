package cn.sijay.bun.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>ShowType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-14
 */
@Getter
@AllArgsConstructor
public enum ShowType {
    PRIMARY("主要"),
    SUCCESS("成功"),
    INFO("提示"),
    WARNING("警告"),
    DANGER("危险"),
    NONE("无"),
    ;
    private final String value;
}
