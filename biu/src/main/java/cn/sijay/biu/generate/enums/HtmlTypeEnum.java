package cn.sijay.biu.generate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * HtmlEnum
 *
 * @author Sijay
 * @since 2024-11-08
 */
@Getter
@AllArgsConstructor
public enum HtmlTypeEnum {
    INPUT("输入框"),
    TEXTAREA("文本输入框"),
    INPUT_NUMBER("数字输入框"),
    RADIO("单选框"),
    CHECKBOX("复选框"),
    SELECT("选择器"),
    TREE_SELECT("树形选择"),
    DATE_PICKER("日期选择器"),
    DATETIME_PICKER("日期时间选择器"),
    TIME_PICKER("时间选择"),
    IMAGE_UPLOAD("图片上传"),
    FILE_UPLOAD("文件上传"),
    EDITOR("富文本"),
    ;
    private final String value;
}
