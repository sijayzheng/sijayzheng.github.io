package cn.sijay.biu.core.helper;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import cn.sijay.biu.auth.entity.LoginUser;
import cn.sijay.biu.core.constant.UserConstants;
import cn.sijay.biu.core.util.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * LoginHelper
 *
 * @author Sijay
 * @since 2025-02-18
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginHelper {

    public static final String LOGIN_USER = "loginUser";
    public static final String USER_NAME = "username";
    public static final String DEPT_ID = "deptId";
    public static final String DEPT_NAME = "deptName";

    /**
     * @param loginUser 登录用户信息
     */
    public static void login(LoginUser loginUser) {
        SaLoginParameter parameter = new SaLoginParameter()
                .setExtra(USER_NAME, loginUser.getUsername())
                .setExtra(DEPT_ID, loginUser.getDeptId())
                .setExtra(DEPT_NAME, loginUser.getDeptName());
        StpUtil.login(loginUser.getId(), parameter);
        StpUtil.getTokenSession().set(LOGIN_USER, loginUser);
    }

    /**
     * 获取用户(多级缓存)
     */
    public static LoginUser getLoginUser() {
        SaSession session = StpUtil.getTokenSession();
        if (ObjectUtils.isEmpty(session)) {
            return new LoginUser();
        }
        return (LoginUser) session.get(LOGIN_USER);
    }

    /**
     * 获取用户基于token
     */
    public static LoginUser getLoginUser(String token) {
        SaSession session = StpUtil.getTokenSessionByToken(token);
        if (ObjectUtils.isEmpty(session)) {
            return new LoginUser();
        }
        return (LoginUser) session.get(LOGIN_USER);
    }

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        return StpUtil.getLoginIdAsLong();
    }

    /**
     * 获取用户账户
     */
    public static String getUsername() {
        return getExtra(USER_NAME);
    }

    /**
     * 获取部门ID
     */
    public static Long getDeptId() {
        String deptId = getExtra(DEPT_ID);
        return Long.valueOf(StringUtil.isBlank(deptId) ? "0" : deptId);
    }

    /**
     * 获取部门名
     */
    public static String getDeptName() {
        return getExtra(DEPT_NAME);
    }

    /**
     * 获取当前 Token 的扩展信息
     *
     * @param key 键值
     * @return 对应的扩展数据
     */
    private static String getExtra(String key) {
        return Optional.ofNullable(String.valueOf(StpUtil.getExtra(key))).orElse("");
    }

    /**
     * 是否为超级管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isSuperAdmin(Long userId) {
        return UserConstants.ADMIN_ROLE_ID.equals(userId);
    }

    /**
     * 是否为超级管理员
     *
     * @return 结果
     */
    public static boolean isSuperAdmin() {
        return isSuperAdmin(getUserId());
    }

    /**
     * 检查当前用户是否已登录
     *
     * @return 结果
     */
    public static boolean isLogin() {
        try {
            return getLoginUser().getLoginId() != null;
        } catch (Exception e) {
            return false;
        }
    }

}
