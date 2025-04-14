package cn.sijay.biu.core.exception;

import java.io.Serial;

/**
 * 验证码错误异常类
 *
 * @author Sijay
 */
public class CaptchaException extends UserException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("验证码错误");
    }
}
