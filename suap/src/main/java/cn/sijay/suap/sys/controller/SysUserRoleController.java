package cn.sijay.suap.sys.controller;

import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.sys.service.ISysUserRoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * <em>SysUserRoleController 用户角色控制层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Tag(name = "用户角色", description = "/sysUserRole")
@RequiredArgsConstructor
@RestController
@RequestMapping("sysUserRole")
public class SysUserRoleController extends BaseController {
    private final ISysUserRoleService sysUserRoleService;

}
