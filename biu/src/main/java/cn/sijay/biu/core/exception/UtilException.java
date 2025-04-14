package cn.sijay.biu.core.exception;

import java.io.Serial;

/**
 * UtilException
 *
 * @author Sijay
 * @since 2025-03-07
 */
public class UtilException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UtilException(String code, Object... args) {
        super("util", code, args);
    }

}