package cn.sijay.suap.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>CommResultTypeEnum</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@AllArgsConstructor
@Getter
public enum CommResultTypeEnum {
    /**
     * 成功
     */
    SUCCESS(0, "操作成功"),
    /**
     * 错误
     */
    ERROR(1, "操作失败"),

    ;
    private final int code;
    private final String msg;

}
