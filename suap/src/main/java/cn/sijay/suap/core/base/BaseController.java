package cn.sijay.suap.core.base;

import cn.sijay.suap.core.entity.PageResult;
import cn.sijay.suap.core.entity.Result;
import cn.sijay.suap.core.enums.OperateType;
import cn.sijay.suap.core.enums.ResultCodeEnum;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 * <em>BaseController</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/8 16:36
 */
public class BaseController {

    /**
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Result<T> error(String msg, T data) {
        return Result.error(msg, data);
    }

    /**
     * @return 通用返回结果
     */
    protected Result<Boolean> toBoolean(boolean flag) {
        return success(flag ? ResultCodeEnum.SUCCESS.getMsg() : ResultCodeEnum.FAILURE.getMsg(), flag);
    }

    /**
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Result<T> success(String msg, T data) {
        return Result.success(msg, data);
    }

    /**
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Result<T> success(T data) {
        return Result.success(data);
    }

    /**
     * @return 通用返回结果
     */
    protected Result<Void> success() {
        return Result.success();
    }

    /**
     * @return 通用返回结果
     */
    protected Result<Void> error() {
        return Result.error();
    }

    /**
     * @return 通用返回结果
     */
    protected Result<Boolean> toBoolean(boolean flag, String msg) {
        msg += flag ? ResultCodeEnum.SUCCESS.getMsg() : ResultCodeEnum.FAILURE.getMsg();
        return success(msg + ResultCodeEnum.SUCCESS.getMsg(), flag);
    }

    /**
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Result<T> error(T data) {
        return Result.error(data);
    }

    /**
     * @return 通用返回结果
     */
    protected Result<Boolean> toBoolean(boolean flag, OperateType operateType) {
        String msg = flag ? ResultCodeEnum.SUCCESS.getMsg() : ResultCodeEnum.FAILURE.getMsg();
        return success(operateType.getDesc() + msg, flag);
    }

    /**
     * @param msg 信息
     * @return 通用返回结果
     */
    protected Result<Void> success(String msg) {
        return Result.success(msg);
    }

    /**
     * @param msg 信息
     * @return 通用返回结果
     */
    protected Result<Void> error(String msg) {
        return Result.error(msg);
    }

    protected <T> PageResult<T> toPageResult(List<T> list) {
        return PageResult.build(list);
    }

    protected <T> PageResult<T> toPageResult() {
        return PageResult.build();
    }

    protected <T> PageResult<T> toPageResult(Page<T> page) {
        return PageResult.build(page);
    }
}
