/*
 * Ownership belongs to Sijay Zheng
 */

/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app.service;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sijay.zheng.z.app.dto.auth.LoginReq;
import sijay.zheng.z.app.entity.sys.SysUser;
import sijay.zheng.z.app.exception.Exceptions;
import sijay.zheng.z.app.repository.sys.UserRepository;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * TODO
 *
 * @author zhengshijie
 * @date 2023/6/15 14:10
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    static final String LOGIN_USER_KEY = "loginUser";
    static final String USER_KEY = "userId";
    private final UserRepository userRepository;

    public SysUser login(LoginReq loginReq) {
        String username = loginReq.username();
        String password = loginReq.password();
        SysUser user = userRepository.getSysUserByUserName(username);
        if (Objects.isNull(user) || !BCrypt.checkpw(password, user.getPassword())) {
            Exceptions.throwExceptions("用户名或密码错误");
        }
        SaLoginModel model = new SaLoginModel();
        // 生成token
        SaStorage storage = SaHolder.getStorage();
        storage.set(LOGIN_USER_KEY, user);
        storage.set(USER_KEY, user.getUserId());

        StpUtil.login(user.getUserId(), model.setExtra(USER_KEY, user.getUserId()));
        StpUtil.getTokenSession().set(LOGIN_USER_KEY, user);
        user.setToken(StpUtil.getTokenValue());
        user.setExpireTime(StpUtil.getTokenTimeout());
        user.setLoginTime(LocalDateTime.now());
        return user;
    }

    public void logout() {
        try {
            StpUtil.logout();
        } catch (NotLoginException e) {
            Exceptions.throwExceptions("用户未登录");
        }
    }
}
