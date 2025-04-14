package cn.sijay.biu.core.entity;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * LoginInfoEvent
 *
 * @author Sijay
 * @since 2025-02-24
 */
@Data
public class LoginInfoEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 登录状态 0成功 1失败
     */
    private String status;

    /**
     * 提示消息
     */
    private String message;

    /**
     * 请求体
     */
    private HttpServletRequest request;

    /**
     * 其他参数
     */
    private Object[] args;

}
