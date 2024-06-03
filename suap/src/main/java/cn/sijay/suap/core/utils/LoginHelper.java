package cn.sijay.suap.core.utils;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import cn.sijay.suap.core.constant.Constants;

/**
 * <strong>LoginHelper</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
public class LoginHelper {
    public static final String LOGIN_USER_KEY = "loginUser";
    public static final String TENANT_KEY = "tenantId";
    public static final String USER_KEY = "userId";
    public static final String DEPT_KEY = "deptId";
    public static final String CLIENT_KEY = "clientid";
    public static final String TENANT_ADMIN_KEY = "isTenantAdmin";

    private LoginHelper() {
    }

    /**
     * 登录系统 基于 设备类型
     * 针对相同用户体系不同设备
     *
     * @param loginUser 登录用户信息
     * @param model     配置参数
     */
//    public static void login(LoginUser loginUser, SaLoginModel model) {
//        SaStorage storage = SaHolder.getStorage();
//        storage.set(LOGIN_USER_KEY, loginUser);
//        storage.set(TENANT_KEY, loginUser.getTenantId());
//        storage.set(USER_KEY, loginUser.getUserId());
//        storage.set(DEPT_KEY, loginUser.getDeptId());
//        model = ObjectUtil.defaultIfNull(model, new SaLoginModel());
//        StpUtil.login(loginUser.getLoginId(),
//                model.setExtra(TENANT_KEY, loginUser.getTenantId())
//                     .setExtra(USER_KEY, loginUser.getUserId())
//                     .setExtra(DEPT_KEY, loginUser.getDeptId()));
//        SaSession tokenSession = StpUtil.getTokenSession();
//        tokenSession.updateTimeout(model.getTimeout());
//        tokenSession.set(LOGIN_USER_KEY, loginUser);
//    }

    /**
     * 获取用户(多级缓存)
     */
//    public static LoginUser getLoginUser() {
//        return (LoginUser) getStorageIfAbsentSet(LOGIN_USER_KEY, () -> {
//            SaSession session = StpUtil.getTokenSession();
//            if (ObjectUtil.isNull(session)) {
//                return null;
//            }
//            return session.get(LOGIN_USER_KEY);
//        });
//    }

    /**
     * 获取用户基于token
     */
//    public static LoginUser getLoginUser(String token) {
//        SaSession session = StpUtil.getTokenSessionByToken(token);
//        if (ObjectUtil.isNull(session)) {
//            return null;
//        }
//        return (LoginUser) session.get(LOGIN_USER_KEY);
//    }

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        //return Convert.toLong(getExtra(USER_KEY));
        return 1L;
    }

    /**
     * 获取部门ID
     */
    public static Long getDeptId() {
        //return Convert.toLong(getExtra(DEPT_KEY));
        return 1L;
    }

    private static Object getExtra(String key) {
        return getStorageIfAbsentSet(key, () -> StpUtil.getExtra(key));
    }

    /**
     * 获取用户账户
     */
//    public static String getUsername() {
//        return getLoginUser().getUsername();
//    }

    /**
     * 获取用户类型
     */
//    public static UserType getUserType() {
//        return UserType.getUserType(StpUtil.getLoginIdAsString());
//    }

    /**
     * 是否为超级管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isSuperAdmin(Long userId) {
        return Constants.SUPER_ADMIN_ID.equals(userId);
    }

//    public static boolean isSuperAdmin() {
//        return isSuperAdmin(getUserId());
//    }

//    public static boolean isLogin() {
//        return getLoginUser() != null;
//    }

    public static Object getStorageIfAbsentSet(String key, Supplier<Object> handle) {
        try {
            Object obj = SaHolder.getStorage().get(key);
            if (ObjectUtil.isNull(obj)) {
                obj = handle.get();
                SaHolder.getStorage().set(key, obj);
            }
            return obj;
        } catch (Exception e) {
            return null;
        }
    }
}
