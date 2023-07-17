/*
 * Ownership belongs to Sijay Zheng
 */

/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app.common.core;

import lombok.Data;
import sijay.zheng.z.app.common.enums.ErrorCodeEnum;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * 返回信息
 *
 * @author zhengshijie
 * @date 2023/6/15 11:14
 */
@Data
public class Returns<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 状态码
     */
    private String code;
    /**
     * 返回内容
     */
    private String msg;
    /**
     * 数据对象
     */
    private T data;

    /**
     * 初始化一个新创建的 ReturnResult 对象，使其表示一个空消息。
     */
    public Returns() {
    }

    /**
     * 初始化一个新创建的 ReturnResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public Returns(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 初始化一个新创建的 ReturnResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public Returns(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        if (!Objects.isNull(data)) {
            this.data = data;
        }
    }

    /**
     * @param code 返回代码
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    public static <T> Returns<T> of(String code, String msg, T data) {
        return new Returns<>(code, msg, data);
    }

    /**
     * @return 通用返回结果
     */
    public static <T> Returns<T> success() {
        return successMsg(ErrorCodeEnum.SUCCESS.getMsg());
    }

    /**
     * @param msg 信息
     * @return 通用返回结果
     */
    public static <T> Returns<T> successMsg(String msg) {
        return success(msg, null);
    }

    /**
     * @param data 数据
     * @return 通用返回结果
     */
    public static <T> Returns<T> success(T data) {
        return success(ErrorCodeEnum.SUCCESS.getMsg(), data);
    }

    /**
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    public static <T> Returns<T> success(String msg, T data) {
        return of(ErrorCodeEnum.SUCCESS.getCode(), msg, data);
    }

    /**
     * @return 通用返回结果
     */
    public static <T> Returns<T> error() {
        return errorMsg(ErrorCodeEnum.ERROR.getMsg());
    }

    /**
     * @param msg 信息
     * @return 通用返回结果
     */
    public static <T> Returns<T> errorMsg(String msg) {
        return error(msg, null);
    }

    /**
     * @param data 数据
     * @return 通用返回结果
     */
    public static <T> Returns<T> error(T data) {
        return error(ErrorCodeEnum.ERROR.getMsg(), data);
    }

    /**
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    public static <T> Returns<T> error(String msg, T data) {
        return of(ErrorCodeEnum.ERROR.getCode(), msg, data);
    }
}


