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

/**
 * TODO
 *
 * @author zhengshijie
 * @date 2023/6/14 15:41
 */
public class BaseController {

    /**
     * @return 通用返回结果
     */
    protected Returns<Void> success() {
        return Returns.success();
    }

    /**
     * @param msg 信息
     * @return 通用返回结果
     */
    protected Returns<Void> success(String msg) {
        return Returns.success(msg);
    }

    /**
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Returns<T> success(T data) {
        return Returns.success(data);
    }

    /**
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Returns<T> success(String msg, T data) {
        return Returns.success(msg, data);
    }

    /**
     * @return 通用返回结果
     */
    protected Returns<Void> error() {
        return Returns.error();
    }

    /**
     * @param msg 信息
     * @return 通用返回结果
     */
    protected Returns<Void> error(String msg) {
        return Returns.error(msg);
    }

    /**
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Returns<T> error(T data) {
        return Returns.error(data);
    }

    /**
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Returns<T> error(String msg, T data) {
        return Returns.error(msg, data);
    }

}
