package cn.sijay.biu.core.exception;

import java.io.Serial;

/**
 * 文件名大小限制异常类
 *
 * @author Sijay
 */
public class FileSizeLimitExceededException extends FileException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize) {
        super("上传的文件大小超出限制的文件大小！<br/>允许的文件最大大小是：{}MB！", new Object[]{defaultMaxSize});
    }
}
