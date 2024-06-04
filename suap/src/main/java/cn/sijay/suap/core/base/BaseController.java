package cn.sijay.suap.core.base;

import cn.sijay.suap.core.entity.PageResult;
import cn.sijay.suap.core.entity.Res;
import cn.sijay.suap.core.enums.OperateType;
import cn.sijay.suap.core.enums.ResultCodeEnum;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * <strong>BaseController</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
public class BaseController {

    /**
     * @return 通用返回结果
     */
    protected Res<Void> success() {
        return Res.success();
    }

    /**
     * @param msg 信息
     * @return 通用返回结果
     */
    protected Res<Void> success(String msg) {
        return Res.success(msg);
    }

    /**
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Res<T> success(T data) {
        return Res.success(data);
    }

    /**
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Res<T> success(String msg, T data) {
        return Res.success(msg, data);
    }

    /**
     * @return 通用返回结果
     */
    protected Res<Void> error() {
        return Res.failure();
    }

    /**
     * @param msg 信息
     * @return 通用返回结果
     */
    protected Res<Void> error(String msg) {
        return Res.failure(msg);
    }

    /**
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Res<T> error(T data) {
        return Res.failure(data);
    }

    /**
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Res<T> error(String msg, T data) {
        return Res.failure(msg, data);
    }

    /**
     * @return 通用返回结果
     */
    protected Res<Void> convert(OperateType operateType) {
        return success(operateType.getDesc() + ResultCodeEnum.SUCCESS.getMsg());
    }

    protected <T> PageResult<T> convert(List<T> list) {
        return PageResult.of(list);
    }

    protected <T> PageResult<T> convert(Page<T> page) {
        return PageResult.of(page);
    }
}
