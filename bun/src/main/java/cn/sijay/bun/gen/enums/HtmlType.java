package cn.sijay.bun.gen.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>HtmlType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-08
 */
@Getter
@AllArgsConstructor
public enum HtmlType {
    INPUT("输入框"),
    INPUT_NUMBER("数字输入框"),
    RADIO("单选框"),
    SWITCH("开关"),
    SELECT("选择器"),
    TREE_SELECT("树形选择"),
    DATE_PICKER("日期选择器"),
    DATETIME_PICKER("日期时间选择器"),
    TIME_PICKER("时间选择"),
    ;
    private final String value;
}
