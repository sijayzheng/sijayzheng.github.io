package sijay.zheng.z.app.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sijay.zheng.z.app.common.core.BaseController;
import sijay.zheng.z.app.common.core.Returns;

import java.util.List;

/**
 * @author sijay
 * @date 2023/7/18 0:04
 */
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController extends BaseController {
    private final UserRepository userService;

    @GetMapping("get")
    public Returns<SysUser> getUserByUserName(@RequestParam String userName) {
        SysUser sysUser = userService.getSysUserByUserName(userName);
        return success(sysUser);
    }

    @GetMapping("list")
    public Returns<List<SysUser>> listAll() {
        return success(userService.findAll());
    }
}
