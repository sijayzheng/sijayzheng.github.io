package cn.sijay.suap.core.base;

import cn.sijay.suap.core.entity.PageResult;
import cn.sijay.suap.core.entity.Res;
import cn.sijay.suap.core.entity.TreeNode;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

/**
 * <strong>BaseController</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
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
        return Res.error();
    }

    /**
     * @param msg 信息
     * @return 通用返回结果
     */
    protected Res<Void> error(String msg) {
        return Res.error(msg);
    }

    /**
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Res<T> error(T data) {
        return Res.error(data);
    }

    /**
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Res<T> error(String msg, T data) {
        return Res.error(msg, data);
    }

    /**
     * 转分页数据
     *
     * @return 通用返回结果
     */
    protected <T> PageResult<T> toPageResult(Page<T> page) {
        return PageResult.of(page);
    }

    /**
     * 转下拉框数据
     *
     * @return 通用返回结果
     */
    protected <T, K> Res<List<TreeNode<K>>> toOptions(List<T> list, Function<? super T, TreeNode<K>> mapper) {
        return success(list.stream().map(mapper).toList());
    }
}
