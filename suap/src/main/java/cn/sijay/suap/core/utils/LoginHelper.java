package cn.sijay.suap.core.utils;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.sijay.suap.core.constant.Constants;
import cn.sijay.suap.sys.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * <strong>LoginHelper</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
public class LoginHelper {

    private static final Logger log = LoggerFactory.getLogger(LoginHelper.class);

    private LoginHelper() {
    }

    /**
     * @param user 登录用户信息
     */
    public static void login(SysUser user, SaLoginModel model) {
        SaStorage storage = SaHolder.getStorage();
        storage.set(Constants.LOGIN_USER_KEY, user);
        storage.set(Constants.USER_ID, user.getId());
        storage.set(Constants.DEPT_ID, user.getDeptId());

        StpUtil.login(user.getId(), model.setExtra(Constants.USER_ID, user.getId())
                                         .setExtra(Constants.DEPT_ID, user.getDeptId()));
        SaSession tokenSession = StpUtil.getTokenSession();
        tokenSession.updateTimeout(model.getTimeout());
        tokenSession.set(Constants.LOGIN_USER_KEY, user);
    }

    /**
     * 获取用户(多级缓存)
     */
    public static SysUser getLoginUser() {
        return (SysUser) getStorageIfAbsentSet(Constants.LOGIN_USER_KEY, () -> {
            SaSession session = StpUtil.getTokenSession();
            if (ObjectUtil.isNull(session)) {
                return null;
            }
            return session.get(Constants.LOGIN_USER_KEY);
        });
    }

    /**
     * 获取用户基于token
     */
    public static SysUser getLoginUser(String token) {
        SaSession session = StpUtil.getTokenSessionByToken(token);
        if (ObjectUtil.isNull(session)) {
            return null;
        }
        return (SysUser) session.get(Constants.LOGIN_USER_KEY);
    }

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        Object userId = getExtra(Constants.USER_ID);
        if (Objects.isNull(userId)) {
            throw new NotLoginException("用户未登录", "", "");
        }
        return Long.valueOf(String.valueOf(userId));
    }

    /**
     * 获取部门ID
     */
    public static Long getDeptId() {
        return Long.valueOf(String.valueOf(getExtra(Constants.DEPT_ID)));
    }

    /**
     * 获取系统模块ID
     */
    public static Long getModuleId() {
        return Long.valueOf(String.valueOf(getExtra(Constants.MODULE_ID)));
    }

    private static Object getExtra(String key) {
        return getStorageIfAbsentSet(key, () -> StpUtil.getExtra(key));
    }

    /**
     * 获取用户账户
     */
    public static String getUsername() {
        return getLoginUser().getUsername();
    }

    /**
     * 是否为超级管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isSuperAdmin(Long userId) {
        log.info("login as super admin");
        return Constants.SUPER_ADMIN_ID.equals(userId);
    }

    public static boolean isSuperAdmin() {
        return isSuperAdmin(getUserId());
    }

    public static boolean isLogin() {
        return getLoginUser() != null;
    }

    public static Object getStorageIfAbsentSet(String key, Supplier<Object> handle) {
        try {
            Object obj = SaHolder.getStorage()
                                 .get(key);
            if (ObjectUtil.isNull(obj)) {
                obj = handle.get();
                SaHolder.getStorage()
                        .set(key, obj);
            }
            return obj;
        } catch (Exception e) {
            return null;
        }
    }
}
