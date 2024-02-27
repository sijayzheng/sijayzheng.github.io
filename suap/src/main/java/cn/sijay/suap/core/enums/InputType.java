package cn.sijay.suap.core.enums;

import cn.sijay.suap.core.base.BaseDataEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <em>InputType</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/16 10:49
 */
@Getter
@AllArgsConstructor
public enum InputType implements BaseDataEnum {
    TEXT_INPUT("TI", "文本输入框"),
    NUMBER_INPUT("NI", "数值输入框"),
    TEXTAREA("TA", "文本块"),
    SELECTOR("SE", "下拉框"),
    TREE_SELECTOR("TS", "树形下拉框"),
    CHECKBOX("CK", "复选框"),
    RADIO("RA", "单选框"),
    DATETIME_PICK("DT", "日期时间选择器"),
    DATE_PICK("DP", "日期选择器"),
    TIME_PICK("TP", "时间选择器"),
    SWITCH("SW", "开关"),
    ;

    @EnumValue
    private final String code;
    @JsonValue
    private final String desc;

    public static InputType getByCode(String code) {
        if (code == null) {
            return InputType.TEXT_INPUT;
        }
        for (InputType inputType : InputType.values()) {
            if (inputType.getCode().equals(code)) {
                return inputType;
            }
        }
        return InputType.TEXT_INPUT;
    }

}
