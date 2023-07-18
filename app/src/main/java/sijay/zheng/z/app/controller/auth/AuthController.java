/*
 * Ownership belongs to Sijay Zheng
 */

/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sijay.zheng.z.app.core.BaseController;
import sijay.zheng.z.app.core.Returns;
import sijay.zheng.z.app.dto.auth.LoginReq;
import sijay.zheng.z.app.entity.sys.SysUser;
import sijay.zheng.z.app.service.AuthService;

/**
 * TODO
 *
 * @author zhengshijie
 * @date 2023/6/15 13:59
 */
@RestController
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final AuthService authService;

    @RequestMapping("/")
    public Returns<String> index() {
        return success("It works!");
    }

    /**
     * 登录方法
     *
     * @param loginReq 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public Returns<SysUser> login(@RequestBody LoginReq loginReq) {
        return success(authService.login(loginReq));
    }

    /**
     * 登录方法
     *
     * @return 结果
     */
    @PostMapping("/logout")
    public Returns<String> logout() {
        authService.logout();
        return success("退出登录成功");
    }


}
