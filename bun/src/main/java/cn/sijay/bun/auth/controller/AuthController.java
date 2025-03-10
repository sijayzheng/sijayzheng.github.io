package cn.sijay.bun.auth.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.sijay.bun.auth.dto.LoginParam;
import cn.sijay.bun.auth.dto.LoginRequest;
import cn.sijay.bun.auth.service.AuthService;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.JSONUtil;
import cn.sijay.bun.core.base.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <strong>AuthController</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-12-03
 */
@SaIgnore
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseResult<LoginRequest> login(@RequestBody LoginParam loginParam) {
        System.out.println(JSONUtil.toJSONString(loginParam));
        return success(authService.login(loginParam));
    }

    @PostMapping("/logout")
    public ResponseResult<Void> logout() {
        return success();
    }
}
