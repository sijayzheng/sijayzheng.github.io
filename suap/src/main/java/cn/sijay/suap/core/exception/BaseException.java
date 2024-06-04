package cn.sijay.suap.core.exception;

import cn.sijay.suap.core.enums.ExceptionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.slf4j.helpers.MessageFormatter;

import java.io.Serial;
import java.util.Optional;

/**
 * <strong>BaseException</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 错误信息
     */
    private String msg;

    public BaseException(String msg, Object... args) {
        this.msg = MessageFormatter.arrayFormat(msg, args).getMessage();
    }

    public BaseException(String msg) {
        this.msg = msg;
    }

    public BaseException(ExceptionEnum e) {
        this.msg = e.getMessage();
    }

    public BaseException(ExceptionEnum e, Object... args) {
        this.msg = MessageFormatter.arrayFormat(e.getMessage(), args).getMessage();
    }

    @Override
    public String getMessage() {
        return Optional.ofNullable(getMsg()).orElse("系统异常");
    }

}

