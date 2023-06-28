/*
 * Ownership belongs to Sijay Zheng
 */

/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app.common.core;

import sijay.zheng.z.app.common.enums.ErrorCodeEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

/**
 * 返回信息
 *
 * @author zhengshijie
 * @date 2023/6/15 11:14
 */
public class Returns extends HashMap<String, Object> implements Serializable {
    /**
     * 状态码
     */
    public static final String CODE = "code";
    /**
     * 返回内容
     */
    public static final String MSG = "msg";
    /**
     * 数据对象
     */
    public static final String DATA = "data";
    private static final long serialVersionUID = 1L;

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
        super.put(CODE, code);
        super.put(MSG, msg);
    }

    /**
     * 初始化一个新创建的 ReturnResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public Returns(String code, String msg, Object data) {
        super.put(CODE, code);
        super.put(MSG, msg);
        if (!Objects.isNull(data)) {
            super.put(DATA, data);
        }
    }

    /**
     * @param code 返回代码
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    public static Returns of(String code, String msg, Object data) {
        return new Returns(code, msg, data);
    }

    /**
     * @return 通用返回结果
     */
    public static Returns success() {
        return successMsg(ErrorCodeEnum.SUCCESS.getMsg());
    }

    /**
     * @param msg 信息
     * @return 通用返回结果
     */
    public static Returns successMsg(String msg) {
        return success(msg, null);
    }

    /**
     * @param data 数据
     * @return 通用返回结果
     */
    public static Returns success(Object data) {
        return success(ErrorCodeEnum.SUCCESS.getMsg(), data);
    }

    /**
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    public static Returns success(String msg, Object data) {
        return of(ErrorCodeEnum.SUCCESS.getCode(), msg, data);
    }

    /**
     * @return 通用返回结果
     */
    public static Returns error() {
        return errorMsg(ErrorCodeEnum.ERROR.getMsg());
    }

    /**
     * @param msg 信息
     * @return 通用返回结果
     */
    public static Returns errorMsg(String msg) {
        return error(msg, null);
    }

    /**
     * @param data 数据
     * @return 通用返回结果
     */
    public static Returns error(Object data) {
        return error(ErrorCodeEnum.ERROR.getMsg(), data);
    }

    /**
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    public static Returns error(String msg, Object data) {
        return of(ErrorCodeEnum.ERROR.getCode(), msg, data);
    }

    /**
     * 链式调用
     *
     * @param key   键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public Returns put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}


