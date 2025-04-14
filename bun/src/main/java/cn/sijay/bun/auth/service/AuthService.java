package cn.sijay.bun.auth.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.sijay.bun.auth.dto.LoginParam;
import cn.sijay.bun.auth.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <strong>AuthService</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-12-03
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class AuthService {
//    private final SystemUserService systemUserService;

    public LoginRequest login(LoginParam loginParam) {
//        Optional<SystemUser> userOptional = systemUserService.findByUsername(loginParam.getUsername());
//        if (userOptional.isPresent()) {
//            SystemUser user = userOptional.get();
//            if (Objects.equals(loginParam.getPassword(), user.getPassword())) {
//                SystemUserDTO dto = user.toDTO();
//                AuthHelper.login(dto);
        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setAccessToken(StpUtil.getTokenValue());
//        loginRequest.setExpireIn(StpUtil.getTokenTimeout());
        loginRequest.setAccessToken("1234567890");
        loginRequest.setExpireIn(StpUtil.getTokenTimeout());
        return loginRequest;
//            } else {
//                throw new BaseException(ExceptionConstant.LOGIN_ERROR);
//            }
//        } else {
//            throw new BaseException(ExceptionConstant.LOGIN_ERROR);
//        }
    }
}
