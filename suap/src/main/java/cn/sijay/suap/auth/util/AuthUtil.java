package cn.sijay.suap.auth.util;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.stp.StpUtil;
import cn.sijay.suap.sys.entity.SysUser;

/**
 * <p>
 * <em>AuthUtil</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/2/6 11:58
 */
public class AuthUtil {
    public static final String LOGIN_USER_KEY = "loginUser";
    public static final String USER_KEY = "userId";

    /**
     * 登录系统 基于 设备类型
     * 针对相同用户体系不同设备
     *
     * @param user 登录用户信息
     */
    public static void login(SysUser user) {
        SaStorage storage = SaHolder.getStorage();
        storage.set(LOGIN_USER_KEY, user);
        storage.set(USER_KEY, user.getId());
        StpUtil.login(user.getId());
        StpUtil.getTokenSession().set(LOGIN_USER_KEY, user);
    }

    public static SysUser getLoginUser() {
        return SaHolder.getStorage().get(LOGIN_USER_KEY, new SysUser());
    }

    public static Long getUserId() {
        return SaHolder.getStorage().get(USER_KEY, 0L);
    }
}
