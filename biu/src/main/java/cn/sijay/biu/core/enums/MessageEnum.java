package cn.sijay.biu.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * MessageEnum
 *
 * @author Sijay
 * @since 2024-11-14
 */
@Getter
@AllArgsConstructor
public enum MessageEnum {
    NOTICE("通知"),
    ANNOUNCEMENT("公告"),
    MESSAGE("站内信"),
    ;
    private final String value;
}
