/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sijay.zheng.z.app.auth.dto.LoginReqDTO;
import sijay.zheng.z.app.common.core.BaseController;
import sijay.zheng.z.app.common.core.Returns;

/**
 * TODO
 *
 * @author zhengshijie
 * @date 2023/6/15 13:59
 */
@RestController
public class AuthController extends BaseController {

    /**
     * 登录方法
     *
     * @param loginReqDTO 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public Returns login(@RequestBody LoginReqDTO loginReqDTO) {

        return success();
    }

    /**
     * 登录方法
     *
     * @param loginReqDTO 登录信息
     * @return 结果
     */
    @PostMapping("/logout")
    public Returns logout(@RequestBody LoginReqDTO loginReqDTO) {

        return success();
    }

}
