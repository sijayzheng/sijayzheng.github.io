/*
 * Ownership belongs to Sijay Zheng
 */

/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app.common.enums;

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

    ;

    //错误代码
    private final String code;
    //错误信息
    private final String msg;
}
