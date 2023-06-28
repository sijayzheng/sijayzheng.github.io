/*
 * Ownership belongs to Sijay Zheng
 */

/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app.common.core;

import cn.dev33.satoken.stp.StpUtil;

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
    public static Returns success() {
        return Returns.success();
    }

    /**
     * @param msg 信息
     * @return 通用返回结果
     */
    public static Returns successMsg(String msg) {
        return Returns.successMsg(msg);
    }

    /**
     * @param data 数据
     * @return 通用返回结果
     */
    public static Returns success(Object data) {
        return Returns.success(data);
    }

    /**
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    public static Returns success(String msg, Object data) {
        return Returns.success(msg, data);
    }

    /**
     * @return 通用返回结果
     */
    public static Returns error() {
        return Returns.error();
    }

    /**
     * @param msg 信息
     * @return 通用返回结果
     */
    public static Returns errorMsg(String msg) {
        return Returns.errorMsg(msg);
    }

    /**
     * @param data 数据
     * @return 通用返回结果
     */
    public static Returns error(Object data) {
        return Returns.error(data);
    }

    /**
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    public static Returns error(String msg, Object data) {
        return Returns.error(msg, data);
    }

//    /**
//     * 获取用户缓存信息
//     */
//    public LoginUser getLoginUser() {
//        return SecurityUtils.getLoginUser();
//    }

    /**
     * 获取登录用户id
     */
    public Long getUserId() {
        // 获取当前会话账号id, 如果未登录，则抛出异常：`NotLoginException`
        return StpUtil.getLoginIdAsLong();      // 获取当前会话账号id, 并转化为`long`类型
    }

}
