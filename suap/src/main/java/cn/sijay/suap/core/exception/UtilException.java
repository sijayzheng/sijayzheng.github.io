package cn.sijay.suap.core.exception;

import cn.sijay.suap.core.enums.ExceptionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.slf4j.helpers.MessageFormatter;

/**
 * <p>
 * <em>UtilException</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/2/5 17:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UtilException extends BaseException {
    public UtilException(String msg, Object... args) {
        this(MessageFormatter.arrayFormat(msg, args).getMessage());
    }

    public UtilException(String msg) {
        super(msg);
    }

    public UtilException(ExceptionEnum e) {
        this(e.getMessage());
    }
}
