package cn.sijay.biu.auth.listener;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import cn.sijay.biu.auth.service.LoginService;
import cn.sijay.biu.core.constant.CacheConstants;
import cn.sijay.biu.core.constant.CommonConstants;
import cn.sijay.biu.core.entity.LoginInfoEvent;
import cn.sijay.biu.core.entity.OnlineUser;
import cn.sijay.biu.core.helper.LoginHelper;
import cn.sijay.biu.core.util.RedisUtil;
import cn.sijay.biu.core.util.ServletUtil;
import cn.sijay.biu.core.util.SpringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * LoginListener
 *
 * @author Sijay
 * @since 2025-02-14
 */
@RequiredArgsConstructor
@Component
@Slf4j
public abstract class LoginListener implements SaTokenListener {
    private final SaTokenConfig tokenConfig;
    private final LoginService loginService;

    /**
     * 每次登录时触发
     */
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginParameter loginModel) {
        OnlineUser dto = new OnlineUser();
        dto.setLoginTime(System.currentTimeMillis());
        dto.setTokenId(tokenValue);
        String username = (String) loginModel.getExtra(LoginHelper.USER_NAME);
        dto.setUserName(username);
        dto.setDeptName((String) loginModel.getExtra(LoginHelper.DEPT_NAME));
        if (tokenConfig.getTimeout() == -1) {
            RedisUtil.set(CacheConstants.ONLINE_TOKEN_KEY + tokenValue, dto);
        } else {
            RedisUtil.set(CacheConstants.ONLINE_TOKEN_KEY + tokenValue, dto, Duration.ofSeconds(tokenConfig.getTimeout()));
        }
        // 记录登录日志
        LoginInfoEvent loginInfoEvent = new LoginInfoEvent();
        loginInfoEvent.setUsername(username);
        loginInfoEvent.setStatus(CommonConstants.SUCCESS);
        loginInfoEvent.setMessage("登录成功");
        loginInfoEvent.setRequest(ServletUtil.getRequest());
        SpringUtil.context().publishEvent(loginInfoEvent);
        // 更新登录信息
        loginService.recordLoginInfo(username, CommonConstants.SUCCESS, "登录成功");
        log.info("用户登录, userId:{}, token:{}", loginId, tokenValue);
    }

    /**
     * 每次注销时触发
     */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        RedisUtil.delete(CacheConstants.ONLINE_TOKEN_KEY + tokenValue);
        log.info("用户退出, userId:{}, token:{}", loginId, tokenValue);
    }

    /**
     * 每次被踢下线时触发
     */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        RedisUtil.delete(CacheConstants.ONLINE_TOKEN_KEY + tokenValue);
        log.info("用户被踢下线, userId:{}, token:{}", loginId, tokenValue);
    }

    /**
     * 每次被顶下线时触发
     */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
        RedisUtil.delete(CacheConstants.ONLINE_TOKEN_KEY + tokenValue);
        log.info("用户被顶下线, userId:{}, token:{}", loginId, tokenValue);
    }
}
