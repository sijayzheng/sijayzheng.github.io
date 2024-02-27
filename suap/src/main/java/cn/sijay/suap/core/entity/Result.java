package cn.sijay.suap.core.entity;

import cn.sijay.suap.core.enums.ResultCodeEnum;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * <em>Result</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/8 17:01
 */
@Data
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String code;
    private String msg;
    private T data;

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return success(ResultCodeEnum.SUCCESS.getMsg());
    }

    public static <T> Result<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> Result<T> success(String msg, T data) {
        return of(ResultCodeEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> Result<T> of(String code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    public static <T> Result<T> success(T data) {
        return success(ResultCodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> Result<T> error() {
        return error(ResultCodeEnum.FAILURE.getMsg());
    }

    public static <T> Result<T> error(String msg) {
        return error(msg, null);
    }

    public static <T> Result<T> error(String msg, T data) {
        return of(ResultCodeEnum.FAILURE.getCode(), msg, data);
    }

    public static <T> Result<T> error(T data) {
        return error(ResultCodeEnum.FAILURE.getMsg(), data);
    }

    public static <T> Result<T> error(ResultCodeEnum resultCodeEnum) {
        return of(resultCodeEnum.getCode(), resultCodeEnum.getMsg(), null);
    }
}


