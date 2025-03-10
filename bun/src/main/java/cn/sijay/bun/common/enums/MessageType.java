package cn.sijay.bun.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>MessageType</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-11-14
 */
@Getter
@AllArgsConstructor
public enum MessageType {
    NOTICE("通知"),
    ANNOUNCEMENT("公告"),
    MESSAGE("站内信"),
    ;
    private final String value;
}
