package cn.sijay.suap.core.enums;

import cn.sijay.suap.core.base.BaseDataEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <em>GenderType</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/16 11:32
 */
@Getter
@AllArgsConstructor
public enum GenderType implements BaseDataEnum {
    MALE("M", "男"),
    FEMALE("F", "女"),
    UNKNOWN("U", "未知"),
    ;

    @EnumValue
    private final String code;
    @JsonValue
    private final String desc;

    public static GenderType getByCode(String code) {
        if (code == null) {
            return GenderType.UNKNOWN;
        }
        for (GenderType sex : GenderType.values()) {
            if (sex.getCode()
                   .equals(code)) {
                return sex; // 获取到当前GenderEnum信息
            }
        }
        return GenderType.UNKNOWN;
    }

}
