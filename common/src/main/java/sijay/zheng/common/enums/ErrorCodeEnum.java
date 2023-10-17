/*
 * Ownership belongs to Sijay Zheng
 */

/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态类型
 *
 * @author zhengshijie
 * @date 2023/6/8 17:07
 */
@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {
    /**
     * 成功
     */
    SUCCESS("0000", "成功"),
    /**
     * 错误
     */
    ERROR("9999", "失败"),
    /**
     * 其他错误码
     */
    ER001("ER001", "用户名或密码错误"),
    ER002("ER002", "用户输入密码错误次数超限"),
    ER003("ER003", "用户账户不存在"),
    ER004("ER004", "用户账户被冻结"),
    ER005("ER005", "用户账户已作废"),
    ER006("ER006", "该用户名已注册"),
    ER007("ER007", "该手机号已注册"),
    ER008("ER008", "请求必填参数为空"),
    ER009("ER009", "用户输入内容非法"),
    ER010("ER010", "用户上传文件类型不匹配"),
    ER011("ER011", "用户上传文件太大"),

    ER301("ER301", "资源已被移除"),
    ER303("ER303", "重定向"),
    ER304("ER304", "资源没有被修改"),
    ER400("ER400", "参数列表错误（缺少，格式不匹配）"),
    ER401("ER401", "认证失败，无法访问系统资源"),
    ER403("ER403", "访问受限，没有访问权限"),
    ER404("ER404", "资源，服务未找到"),
    ER405("ER405", "不允许的http方法"),
    ER409("ER409", "资源冲突，或者资源被锁"),
    ER415("ER415", "不支持的数据，媒体类型"),
    ER500("ER500", "系统内部错误"),
    ER501("ER501", "接口未实现"),
    ER601("ER600", "系统警告消息"),
    ;

    //错误代码
    private final String code;
    //错误信息
    private final String msg;
}
