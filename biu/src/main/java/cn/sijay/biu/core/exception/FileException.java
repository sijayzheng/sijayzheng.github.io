package cn.sijay.biu.core.exception;

import java.io.Serial;

/**
 * 文件信息异常类
 *
 * @author Sijay
 */
public class FileException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object... args) {
        super("file", code, args);
    }

}
