package cn.sijay.suap.core.enums;

import cn.sijay.suap.core.base.BaseDataEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <em>yon</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/8 17:35
 */
@Getter
@AllArgsConstructor
public enum YesOrNo implements BaseDataEnum {
    Y("Y", "是"),
    N("N", "否"),
    ;

    @EnumValue
    private final String code;
    @JsonValue
    private final String desc;

    public static YesOrNo getByCode(String code) {
        if (code == null) {
            return YesOrNo.N;
        }
        for (YesOrNo yesOrNo : YesOrNo.values()) {
            if (yesOrNo.getCode()
                       .equals(code)) {
                return yesOrNo;
            }
        }
        return YesOrNo.N;
    }

    public static boolean isYes(YesOrNo yesOrNo) {
        return Y.equals(yesOrNo);
    }

    public static boolean isNo(YesOrNo yesOrNo) {
        return N.equals(yesOrNo);
    }

}

