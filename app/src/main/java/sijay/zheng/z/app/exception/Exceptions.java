package sijay.zheng.z.app.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.slf4j.helpers.MessageFormatter;

import java.io.Serial;

/**
 * @author sijay
 * @description BaseException
 * @date 2023/8/4 9:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Exceptions extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private String msg;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    public Exceptions(String msg) {
        this.msg = msg;
    }

    public static void throwExceptions(String msg, Object... args) {
        throw new Exceptions(MessageFormatter.arrayFormat(msg, args).getMessage());
    }

    public static void throwExceptions(String msg) {
        throw new Exceptions(msg);
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
