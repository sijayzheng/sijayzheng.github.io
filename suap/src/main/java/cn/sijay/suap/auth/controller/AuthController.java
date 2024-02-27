package cn.sijay.suap.auth.controller;

import cn.sijay.suap.auth.entity.LoginReq;
import cn.sijay.suap.auth.entity.LoginResp;
import cn.sijay.suap.auth.service.AuthService;
import cn.sijay.suap.core.annotation.Log;
import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.core.entity.Result;
import cn.sijay.suap.core.enums.OperateType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * <em>authController 登录用户控制层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/8 17:00
 */
@Tag(name = "登录用户", description = "/auth")
@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController extends BaseController {
    private final AuthService authService;

    /**
     * 登录
     *
     * @param loginReq 登录请求对象
     * @return 返回选项集合
     */
    @Log(value = "登录", operateType = OperateType.LOGIN)
    @Operation(summary = "登录")
    @Parameter(name = "tableName", description = "表名")
    @ApiResponse(responseCode = "200", description = "登录")
    @PostMapping("login")
    public Result<LoginResp> login(@RequestBody LoginReq loginReq) {
        return success(authService.login(loginReq));
    }

    /**
     * 登出
     */
    @Log(value = "登出", operateType = OperateType.LOGOUT)
    @Operation(summary = "登出")
    @ApiResponse(responseCode = "200", description = "登出")
    @PostMapping("logout")
    public Result<Void> logout() {
        authService.logout();
        return success("登出成功");
    }
}
