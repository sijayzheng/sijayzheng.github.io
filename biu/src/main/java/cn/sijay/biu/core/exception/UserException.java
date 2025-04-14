package cn.sijay.biu.core.exception;

import java.io.Serial;

/**
 * 用户信息异常类
 *
 * @author Sijay
 */
public class UserException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object... args) {
        super("user", code, args);
    }
}
