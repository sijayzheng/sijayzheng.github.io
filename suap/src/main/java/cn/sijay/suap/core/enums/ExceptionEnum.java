package cn.sijay.suap.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <em>ExceptionEnum 异常信息</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/10 15:24
 */
@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    UNKNOWN_ERROR("未知错误"),

    BUSINESS_ERROR("业务错误"),
    UPDATE_ERROR("更新错误"),
    DELETE_ERROR("删除错误"),
    INSERT_ERROR("插入错误"),
    SELECT_ERROR("查询错误"),

    LOGIN_ERROR("登录错误"),
    LOGOUT_ERROR("登出错误"),
    REGISTER_ERROR("注册错误"),

    VALIDATE_UNIQUE_ERROR("唯一性校验错误,字段[{}]的值[{}]已存在"),

    DATA_NOT_FOUND("数据{}不存在"),

    REQUEST_PARAM_ERROR("请求参数错误"),

    EXCEL_READ_ERROR("Excel文件读取失败"),

    REFLECT_ERROR("反射错误"),
    REFLECT_CLASS_NOT_FOUND_ERROR("反射错误，类{}找不到"),
    REFLECT_METHOD_NOT_FOUND_ERROR("反射错误，类{}方法{}找不到"),
    REFLECT_METHOD_INVOKE_ERROR("反射错误，类{}方法{}调用错误"),

    FILE_NOT_FOUND("文件{}不存在"),
    FILE_NOT_FOLDER("{}不是文件夹"),

    ZIP_FILE_FAILED("文件压缩失败"),

    IO_ERROR("IO异常"),

    JSON_SERIAL_ERROR("JSON序列化错误"),
    JSON_PARSE_ERROR("JSON反序列化错误"),

    ;
    private final String message;
}
