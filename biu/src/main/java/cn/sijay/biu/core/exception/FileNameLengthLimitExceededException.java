package cn.sijay.biu.core.exception;

import java.io.Serial;

/**
 * 文件名称超长限制异常类
 *
 * @author Sijay
 */
public class FileNameLengthLimitExceededException extends FileException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength) {
        super("上传的文件名最长{}个字符", new Object[]{defaultFileNameLength});
    }
}
