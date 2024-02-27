package cn.sijay.suap.auth.service;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.sijay.suap.auth.entity.LoginReq;
import cn.sijay.suap.auth.entity.LoginResp;
import cn.sijay.suap.auth.util.AuthUtil;
import cn.sijay.suap.core.exception.BaseException;
import cn.sijay.suap.core.properties.ConfigProperties;
import cn.sijay.suap.sys.entity.SysUser;
import cn.sijay.suap.sys.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * <p>
 * <em>authService 登录用户服务层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/8 17:10
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {
    final private ISysUserService sysUserService;
    final private ConfigProperties configProperties;

    public LoginResp login(LoginReq loginReq) {
        String username = loginReq.getUsername();
        String password = loginReq.getPassword();
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username).or()
               .eq(SysUser::getPhone, username).or()
               .eq(SysUser::getEmail, username);
        SysUser user = sysUserService.getOne(wrapper);
        if (Objects.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new BaseException("对不起, 您的账号：" + username + " 不存在.");
        }
        log.info("登录用户：{} 密码：{}", username, password);
        log.info(user.getPassword());
        checkLogin(username, () -> !BCrypt.checkpw(password, user.getPassword()));
//        user.setMenuPermission(permissionService.getMenuPermission(user.getId()));
//        user.setRolePermission(permissionService.getRolePermission(user.getId()));
        // 此处可根据登录用户的数据不同 自行创建 loginUser
        AuthUtil.login(user);
        LoginResp loginResp = new LoginResp();
        loginResp.setAccessToken(StpUtil.getTokenValue());
        loginResp.setExpireIn(StpUtil.getTokenTimeout());
//        loginInfoService.recordLoginInfo(username, CommConstant.SUCCESS, "登录成功");
        return new LoginResp(StpUtil.getTokenValue(), StpUtil.getTokenTimeout());
    }

    /**
     * 登录校验
     */
    public void checkLogin(String username, Supplier<Boolean> supplier) {
        // 获取用户登录错误次数，默认为0 (可自定义限制策略 例如: key + username + ip)
//        int errorNumber = ObjectUtil.defaultIfNull(RedisUtils.getCacheObject(errorKey), 0);
//        // 锁定时间内登录 则踢出
//        if (errorNumber >= maxRetryCount) {
//            recordLogininfor(tenantId, username, loginFail, MessageUtils.message(loginType.getRetryLimitExceed(), maxRetryCount, lockTime));
//            throw new BaseException(loginType.getRetryLimitExceed(), maxRetryCount, lockTime);
//        }
//
//        if (supplier.get()) {
//            // 错误次数递增
//            errorNumber++;
//            RedisUtils.setCacheObject(errorKey, errorNumber, Duration.ofMinutes(lockTime));
//            // 达到规定错误次数 则锁定登录
//            if (errorNumber >= maxRetryCount) {
//                recordLogininfor(tenantId, username, loginFail, MessageUtils.message(loginType.getRetryLimitExceed(), maxRetryCount, lockTime));
//                throw new BaseException(loginType.getRetryLimitExceed(), maxRetryCount, lockTime);
//            } else {
//                // 未达到规定错误次数
//                recordLogininfor(tenantId, username, loginFail, MessageUtils.message(loginType.getRetryLimitCount(), errorNumber));
//                throw new BaseException(loginType.getRetryLimitCount(), errorNumber);
//            }
//        }
//
//        // 登录成功 清空错误次数
//        RedisUtils.deleteObject(errorKey);
    }

    public void logout() {
    }
}
