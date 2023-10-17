package sijay.zheng.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sijay
 * @description EnableStatusEnum
 * @date 2023/9/28 9:50
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

    public String getDesc() {
        return desc;
    }

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
}
