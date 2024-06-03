package cn.sijay.suap.core.entity;

import cn.sijay.suap.core.enums.ResultCodeEnum;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * <strong>Res</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Data
public class Res<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    private T data;

    public Res(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Res<T> success() {
        return success(ResultCodeEnum.SUCCESS.getMsg());
    }

    public static <T> Res<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> Res<T> success(String msg, T data) {
        return of(ResultCodeEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> Res<T> of(int code, String msg, T data) {
        return new Res<>(code, msg, data);
    }

    public static <T> Res<T> success(T data) {
        return success(ResultCodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> Res<T> failure() {
        return failure(ResultCodeEnum.FAILURE.getMsg());
    }

    public static <T> Res<T> failure(String msg) {
        return failure(msg, null);
    }

    public static <T> Res<T> failure(String msg, T data) {
        return of(ResultCodeEnum.FAILURE.getCode(), msg, data);
    }

    public static <T> Res<T> failure(T data) {
        return failure(ResultCodeEnum.FAILURE.getMsg(), data);
    }

    public static <T> Res<T> failure(ResultCodeEnum resultCodeEnum) {
        return of(resultCodeEnum.getCode(), resultCodeEnum.getMsg(), null);
    }
}


