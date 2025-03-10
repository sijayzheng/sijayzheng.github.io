package cn.sijay.bun.common.exception;

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
 * @author sijay
 * @since 2024-11-07
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
    private String message;

    public BaseException(String message, Object... args) {
        this.message = MessageFormatter.basicArrayFormat(message, args);
    }

    public BaseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return Optional.ofNullable(this.message).orElse("系统异常");
    }

}


