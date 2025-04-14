package cn.sijay.suap.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>NoticeType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Getter
@AllArgsConstructor
public enum NoticeType {
    A("公告"),
    B("通知"),
    ;

    @JsonValue
    private final String desc;
}
