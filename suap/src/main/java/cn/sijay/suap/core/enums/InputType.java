package cn.sijay.suap.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>InputType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Getter
@AllArgsConstructor
public enum InputType {
    INPUT("文本输入框"),
    NUMBER_INPUT("数值输入框"),
    TEXTAREA("文本块"),
    SELECT("下拉框"),
    TREE_SELECT("树形下拉框"),
    CHECKBOX("复选框"),
    RADIO("单选框"),
    DATETIME_PICK("日期时间选择器"),
    DATE_PICK("日期选择器"),
    TIME_PICK("时间选择器"),
    SWITCH("开关"),
    ;

    @JsonValue
    private final String desc;

}
