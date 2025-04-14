package cn.sijay.biu.core.exception;

import cn.sijay.biu.core.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * 基础异常
 *
 * @author Sijay
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String msg;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    public BaseException(String msg) {
        this(null, msg, null);
    }

    public BaseException(String msg, Object... args) {
        this(null, msg, args);
    }

    @Override
    public String getMessage() {
        return StringUtil.isEmpty(msg) ? "系统错误" : StringUtil.format(msg, args);
    }

}
