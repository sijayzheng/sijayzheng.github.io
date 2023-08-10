package sijay.zheng.z.app.util;

import cn.dev33.satoken.stp.StpUtil;

/**
 * @author sijay
 * @description AuthUtils
 * @date 2023/8/9 9:53
 */
public class AuthUtils {
    /**
     * 获取登录用户id
     */
    public Long getUserId() {
        // 获取当前会话账号id, 如果未登录，则抛出异常：`NotLoginException`
        return StpUtil.getLoginIdAsLong();      // 获取当前会话账号id, 并转化为`long`类型
    }
}
