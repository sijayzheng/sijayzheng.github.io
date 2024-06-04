package cn.sijay.suap.core.exception;

import cn.sijay.suap.core.enums.ExceptionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <strong>UtilException</strong>
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
public class UtilException extends BaseException {
    public UtilException(String msg, Object... args) {
        super(msg, args);
    }

    public UtilException(String msg) {
        super(msg);
    }

    public UtilException(ExceptionEnum e) {
        this(e.getMessage());
    }
}
