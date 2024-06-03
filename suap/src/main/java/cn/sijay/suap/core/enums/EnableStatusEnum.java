package cn.sijay.suap.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>EnableStatusEnum</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@AllArgsConstructor
public enum EnableStatusEnum {
    ENABLE("0", "正常"),
    DISABLE("1", "停用"),
    DELETED("2", "删除"),
    ;

    @Getter
    private final String code;
    private final String desc;

    public static EnableStatusEnum getByCode(String code) {
        if (code == null) {
            return EnableStatusEnum.ENABLE;
        }
        for (EnableStatusEnum enableStatusEnum : EnableStatusEnum.values()) {
            if (enableStatusEnum.getCode().equals(code)) {
                return enableStatusEnum;
            }
        }
        return EnableStatusEnum.ENABLE;
    }

    public String getDesc() {
        return desc;
    }
}
