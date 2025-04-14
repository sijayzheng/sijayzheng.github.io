package cn.sijay.bun.auth.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <strong>AuthHelper</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-12-04
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthHelper {
    private final static String LOGIN_USER = "loginUser";
    private final static String USER_ID = "userId";
    private final static String USER_NAME = "userName";
    private final static String DEPT_ID = "deptId";

//    public static void login(SystemUserDTO user) {
//        SaLoginModel model = new SaLoginModel();
//        model.setExtra(USER_ID, user.getId());
//        model.setExtra(USER_NAME, user.getUserName());
//        model.setExtra(DEPT_ID, user.getDeptId());
//        StpUtil.login(user.getId(), model);
//        StpUtil.getTokenSession().set(LOGIN_USER, user);
//    }
}
