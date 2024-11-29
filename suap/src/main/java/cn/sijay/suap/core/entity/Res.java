package cn.sijay.suap.core.entity;

import cn.sijay.suap.core.enums.ResultCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * @since 2024-07-18
 */
@Data
public class Res<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "code", title = "消息状态码")
    private int code;

    @Schema(name = "message", title = "消息内容")
    private String message;

    @Schema(name = "data", title = "数据")
    private T data;

    public Res(int code, String message, T data) {
        this.code = code;
        this.message = message;
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

    public static <T> Res<T> of(int code, String msg) {
        return new Res<>(code, msg, null);
    }

    public static <T> Res<T> success(T data) {
        return success(ResultCodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> Res<T> error() {
        return error(ResultCodeEnum.FAILURE.getMsg());
    }

    public static <T> Res<T> error(String msg) {
        return error(msg, null);
    }

    public static <T> Res<T> error(String msg, T data) {
        return of(ResultCodeEnum.FAILURE.getCode(), msg, data);
    }

    public static <T> Res<T> error(T data) {
        return error(ResultCodeEnum.FAILURE.getMsg(), data);
    }

}


