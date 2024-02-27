package cn.sijay.suap.sys.controller;

import cn.sijay.suap.core.base.BaseController;
import cn.sijay.suap.sys.service.ISysRoleMenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * <em>SysRoleMenuController 角色菜单控制层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Tag(name = "角色菜单", description = "/sysRoleMenu")
@RequiredArgsConstructor
@RestController
@RequestMapping("sysRoleMenu")
public class SysRoleMenuController extends BaseController {
    private final ISysRoleMenuService sysRoleMenuService;

}
