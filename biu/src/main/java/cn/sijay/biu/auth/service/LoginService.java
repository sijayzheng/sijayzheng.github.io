package cn.sijay.biu.auth.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.sijay.biu.auth.entity.LoginParam;
import cn.sijay.biu.auth.entity.LoginUser;
import cn.sijay.biu.core.constant.CommonConstants;
import cn.sijay.biu.core.constant.RedisConstants;
import cn.sijay.biu.core.entity.LoginInfoEvent;
import cn.sijay.biu.core.entity.RoleDto;
import cn.sijay.biu.core.exception.CaptchaException;
import cn.sijay.biu.core.exception.CaptchaExpireException;
import cn.sijay.biu.core.exception.UserException;
import cn.sijay.biu.core.helper.LoginHelper;
import cn.sijay.biu.core.properties.CommonProperties;
import cn.sijay.biu.core.util.RedisUtil;
import cn.sijay.biu.core.util.SecureUtil;
import cn.sijay.biu.core.util.ServletUtil;
import cn.sijay.biu.core.util.SpringUtil;
import cn.sijay.biu.system.entity.SystemDept;
import cn.sijay.biu.system.entity.SystemUser;
import cn.sijay.biu.system.repository.SystemDeptRepository;
import cn.sijay.biu.system.repository.SystemMenuRepository;
import cn.sijay.biu.system.repository.SystemRoleRepository;
import cn.sijay.biu.system.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * LoginService
 *
 * @author Sijay
 * @since 2025-02-14
 */

@RequiredArgsConstructor
@Slf4j
@Service
public class LoginService {
    private final SystemPermissionService permissionService;
    private final SystemDeptRepository deptRepository;
    private final SystemUserRepository userRepository;
    private final CommonProperties commonProperties;
    private final SystemRoleRepository roleRepository;
    private final SystemMenuRepository menuRepository;

    public String login(LoginParam param) {
        String username = param.getUsername();
        String password = param.getPassword();
        String code = param.getCode();
        String uuid = param.getUuid();
        // 验证码开关
        if (commonProperties.getCaptcha().isEnable()) {
            String verifyKey = RedisConstants.CAPTCHA_CODE_KEY + StringUtils.defaultString(uuid);
            String captcha = RedisUtil.get(verifyKey);
            RedisUtil.delete(verifyKey);
            if (captcha == null) {
                recordLoginInfo(username, CommonConstants.FAIL, "验证码已失效");
                throw new CaptchaExpireException();
            }
            if (!code.equalsIgnoreCase(captcha)) {
                recordLoginInfo(username, CommonConstants.FAIL, "验证码错误");
                throw new CaptchaException();
            }
        }
        SystemUser user = userRepository.findByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new UserException("对不起, 您的账号：{} 不存在.", username);
        } else if (!user.getEnable()) {
            log.info("登录用户：{} 已被停用.", username);
            throw new UserException("对不起，您的账号：{} 已禁用，请联系管理员", username);
        }
        String errorKey = RedisConstants.PWD_ERR_CNT_KEY + username;
        String loginFail = CommonConstants.FAIL;

        int maxRetryCount = commonProperties.getUser().getPassword().getMaxRetryCount();
        long lockTime = commonProperties.getUser().getPassword().getLockTime();
        // 获取用户登录错误次数，默认为0
        Integer errorNumber = RedisUtil.get(errorKey);
        errorNumber = errorNumber == null ? 0 : errorNumber;
        // 锁定时间内登录 则踢出
        if (errorNumber >= maxRetryCount) {
            recordLoginInfo(username, loginFail, "密码输入错误" + maxRetryCount + "次，帐户锁定" + lockTime + "分钟");
            throw new UserException("密码输入错误" + maxRetryCount + "次，帐户锁定" + lockTime + "分钟");
        }
        if (!user.getPassword().equals(SecureUtil.hashPasswd(password))) {
            // 错误次数递增
            errorNumber++;
            RedisUtil.set(errorKey, errorNumber, lockTime * 60);
            // 达到规定错误次数 则锁定登录
            if (errorNumber >= maxRetryCount) {
                recordLoginInfo(username, loginFail, "密码输入错误" + maxRetryCount + "次，帐户锁定" + lockTime + "分钟");
                throw new UserException("密码输入错误" + maxRetryCount + "次，帐户锁定" + lockTime + "分钟");
            } else {
                // 未达到规定错误次数
                recordLoginInfo(username, loginFail, "密码输入错误" + errorNumber + "次");
                throw new UserException("密码输入错误" + errorNumber + "次");
            }
        }

        // 登录成功 清空错误次数
        RedisUtil.delete(errorKey);

        // 此处可根据登录用户的数据不同 自行创建 loginUser
        LoginUser loginUser = new LoginUser();
        loginUser.setId(user.getId());
        loginUser.setDeptId(user.getDeptId());
        loginUser.setUsername(user.getUsername());
        loginUser.setMenuPermission(permissionService.getMenuPermission(user.getId()));
        loginUser.setRolePermission(permissionService.getRolePermission(user.getId()));
        if (!Objects.isNull(user.getDeptId())) {
            loginUser.setDeptName(deptRepository.findById(user.getDeptId()).orElse(new SystemDept()).getName());
        }
        SystemUser systemUser = userRepository.getReferenceById(user.getId());
        List<RoleDto> roles = roleRepository.findAllById(systemUser.getRoles())
                                            .stream().map(role -> {
                    RoleDto dto = new RoleDto();
                    dto.setId(role.getId());
                    dto.setName(role.getName());
                    dto.setCode(role.getCode());
                    dto.setDataScope(role.getDataScope());
                    return dto;
                }).collect(Collectors.toList());
        loginUser.setRoles(roles);
        // 生成token
        LoginHelper.login(loginUser);
        return StpUtil.getTokenValue();
    }

    /**
     * 退出登录
     */
    public void logout() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        StpUtil.logout();
        if (ObjectUtils.isEmpty(loginUser)) {
            return;
        }
        recordLoginInfo(loginUser.getUsername(), CommonConstants.LOGOUT, "退出成功");
    }

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息内容
     */
    public void recordLoginInfo(String username, String status, String message) {
        LoginInfoEvent loginInfoEvent = new LoginInfoEvent();
        loginInfoEvent.setUsername(username);
        loginInfoEvent.setStatus(status);
        loginInfoEvent.setMessage(message);
        loginInfoEvent.setRequest(ServletUtil.getRequest());
        SpringUtil.context().publishEvent(loginInfoEvent);
    }

}

