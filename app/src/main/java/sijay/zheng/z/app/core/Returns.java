/*
 * Ownership belongs to Sijay Zheng
 */

/*
 * Ownership belongs to Sijay Zheng
 */

/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app.core;

import lombok.Data;
import sijay.zheng.z.common.enums.ErrorCodeEnum;

import java.io.Serial;
import java.io.Serializable;

/**
 * @param <T>
 */
@Data
public class Returns<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;
    private T data;

    public Returns(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Returns<T> of(String code, String msg, T data) {
        return new Returns<>(code, msg, data);
    }

    public static Returns<Void> success() {
        return success(ErrorCodeEnum.SUCCESS.getMsg());
    }

    public static Returns<Void> success(String msg) {
        return success(msg, null);
    }

    public static <T> Returns<T> success(T data) {
        return success(ErrorCodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> Returns<T> success(String msg, T data) {
        return of(ErrorCodeEnum.SUCCESS.getCode(), msg, data);
    }

    public static Returns<Void> error() {
        return error(ErrorCodeEnum.ERROR.getMsg());
    }

    public static Returns<Void> error(String msg) {
        return error(msg, null);
    }

    public static <T> Returns<T> error(T data) {
        return error(ErrorCodeEnum.ERROR.getMsg(), data);
    }

    public static <T> Returns<T> error(String msg, T data) {
        return of(ErrorCodeEnum.ERROR.getCode(), msg, data);
    }
}


