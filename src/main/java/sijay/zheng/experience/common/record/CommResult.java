package sijay.zheng.experience.common.record;

import sijay.zheng.experience.common.enums.*;

/**
 * 通用返回
 *
 * @author sijay
 * @date 2022/9/3 17:20
 */
public record CommResult<T>(int code, String msg, T data) {
    /**
     * @param <T> 数据
     * @return 通用返回结果
     */
    public static <T> CommResult<T> ofSuccess() {
        return new CommResult<>(CommResultTypeEnum.SUCCESS.getCode(), CommResultTypeEnum.SUCCESS.getMsg(), null);
    }

    /**
     * @param msg 信息
     * @param <T> 数据
     * @return 通用返回结果
     */
    public static <T> CommResult<T> ofVoidSuccess(String msg) {
        return new CommResult<>(CommResultTypeEnum.SUCCESS.getCode(), msg, null);
    }

    /**
     * @param data 数据
     * @param <T>  数据类型
     * @return 通用返回结果
     */
    public static <T> CommResult<T> ofSuccess(T data) {
        return new CommResult<>(CommResultTypeEnum.SUCCESS.getCode(), CommResultTypeEnum.SUCCESS.getMsg(), data);
    }

    /**
     * @param msg  信息
     * @param data 数据
     * @param <T>  数据类型
     * @return 通用返回结果
     */
    public static <T> CommResult<T> ofSuccess(String msg, T data) {
        return new CommResult<>(CommResultTypeEnum.SUCCESS.getCode(), msg, data);
    }

    /**
     * @param code 返回代码
     * @param msg  信息
     * @param data 数据
     * @param <T>  数据类型
     * @return 通用返回结果
     */
    public static <T> CommResult<T> of(int code, String msg, T data) {
        return new CommResult<>(code, msg, data);
    }

    /**
     * @param code 返回代码
     * @param msg  信息
     * @param <T>  数据类型
     * @return 通用返回结果
     */
    public static <T> CommResult<T> of(int code, String msg) {
        return new CommResult<>(code, msg, null);
    }

    /**
     * @param <T> 数据
     * @return 通用返回结果
     */
    public static <T> CommResult<T> ofError() {
        return new CommResult<>(CommResultTypeEnum.ERROR.getCode(), CommResultTypeEnum.ERROR.getMsg(), null);
    }

    /**
     * @param msg 信息
     * @param <T> 数据
     * @return 通用返回结果
     */
    public static <T> CommResult<T> ofVoidError(String msg) {
        return new CommResult<>(CommResultTypeEnum.ERROR.getCode(), msg, null);
    }

    /**
     * @param data 数据
     * @param <T>  数据类型
     * @return 通用返回结果
     */
    public static <T> CommResult<T> ofError(T data) {
        return new CommResult<>(CommResultTypeEnum.ERROR.getCode(), CommResultTypeEnum.ERROR.getMsg(), data);
    }

    /**
     * @param msg  信息
     * @param data 数据
     * @param <T>  数据类型
     * @return 通用返回结果
     */
    public static <T> CommResult<T> ofError(String msg, T data) {
        return new CommResult<>(CommResultTypeEnum.ERROR.getCode(), msg, data);
    }
}