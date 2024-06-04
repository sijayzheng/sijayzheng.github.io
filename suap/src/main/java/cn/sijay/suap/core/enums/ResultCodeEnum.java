package cn.sijay.suap.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>ResultCodeEnum</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@AllArgsConstructor
@Getter
public enum ResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS(200, "成功"),
    /**
     * 错误
     */
    FAILURE(500, "失败"),
    WARNING(777, "系统警告消息"),
    /**
     * HTTP错误
     */
    HTTP301(301, "资源已被移除"),
    HTTP303(303, "重定向"),
    HTTP304(304, "资源没有被修改"),
    HTTP400(400, "参数列表错误（缺少，格式不匹配）"),
    HTTP401(401, "认证失败，无法访问系统资源"),
    HTTP403(403, "访问受限，没有访问权限"),
    HTTP404(404, "资源，服务未找到"),
    HTTP405(405, "不允许的http方法"),
    HTTP409(409, "资源冲突，或者资源被锁"),
    HTTP415(415, "不支持的数据，媒体类型"),
    HTTP500(500, "系统内部错误"),
    HTTP501(501, "接口未实现"),
    /**
     * 其他错误码
     */
    ER001(9001, "用户名或密码错误"),
    ER002(9002, "用户输入密码错误次数超限"),
    ER003(9003, "用户账户不存在"),
    ER004(9004, "用户账户被冻结"),
    ER005(9005, "用户账户已作废"),
    ER006(9006, "该用户名已注册"),
    ER007(9007, "该手机号已注册"),
    ER008(9008, "请求必填参数为空"),
    ER009(9009, "用户输入内容非法"),
    ER010(9010, "用户上传文件类型不匹配"),
    ER011(9011, "用户上传文件太大"),

    ;
    //代码
    private final int code;
    //信息
    private final String msg;
}
