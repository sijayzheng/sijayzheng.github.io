package cn.sijay.biu.core.exception;

import java.io.Serial;

public class CaptchaExpireException extends UserException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CaptchaExpireException() {
        super("验证码已失效");
    }
}