package cn.sijay.suap.common.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.core.base.BaseException;
import cn.sijay.suap.core.constant.Constants;
import cn.sijay.suap.core.constant.ExceptionConstant;
import cn.sijay.suap.core.entity.LoginParam;
import cn.sijay.suap.core.entity.LoginRes;
import cn.sijay.suap.core.entity.Res;
import cn.sijay.suap.core.utils.LoginHelper;
import cn.sijay.suap.sys.entity.SysUser;
import cn.sijay.suap.sys.service.ISysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * <strong>AuthController</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-09-11
 */
@SaIgnore
@Tag(name = "认证")
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final ISysUserService sysUserService;

    /**
     * 登录
     */
    @Operation(summary = "登录")
    @PostMapping("/login")
    public Res<LoginRes> login(@RequestBody LoginParam loginParam) {
        System.out.println(loginParam);
        SysUser user = Optional.ofNullable(sysUserService.getByUsername(loginParam.getUsername()))
                               .orElseThrow(() -> new BaseException(ExceptionConstant.LOGIN_ERROR));
        if (LoginHelper.isSuperAdmin(user.getId()) || user.getModules()
                                                          .contains(loginParam.getModule())) {
            SaLoginModel model = new SaLoginModel();
            model.setExtra(Constants.MODULE_ID, loginParam.getModule());
            LoginHelper.login(user, model);
            LoginRes loginRes = new LoginRes();
            loginRes.setAccessToken(StpUtil.getTokenValue());
            loginRes.setExpireIn(StpUtil.getTokenActiveTimeout());
            return success(loginRes);
        } else {
            throw new BaseException(ExceptionConstant.NO_MODULE_PERMISSION_ERROR);
        }
    }
}
