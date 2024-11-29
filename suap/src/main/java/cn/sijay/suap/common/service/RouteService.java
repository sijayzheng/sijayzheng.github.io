package cn.sijay.suap.common.service;

import cn.sijay.suap.core.entity.Meta;
import cn.sijay.suap.core.entity.Route;
import cn.sijay.suap.core.utils.LoginHelper;
import cn.sijay.suap.core.utils.StringUtil;
import cn.sijay.suap.sys.entity.SysMenu;
import cn.sijay.suap.sys.entity.SysModule;
import cn.sijay.suap.sys.entity.SysRole;
import cn.sijay.suap.sys.entity.SysUser;
import cn.sijay.suap.sys.service.ISysMenuService;
import cn.sijay.suap.sys.service.ISysModuleService;
import cn.sijay.suap.sys.service.ISysRoleService;
import cn.sijay.suap.sys.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <strong>RouteService</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-09-11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RouteService {
    private final ISysMenuService sysMenuService;
    private final ISysUserService sysUserService;
    private final ISysRoleService sysRoleService;
    private final ISysModuleService sysModuleService;

    public List<Route> getRoutes() {
        List<SysMenu> list;
        if (LoginHelper.isSuperAdmin()) {
            list = sysMenuService.list();
        } else {
            Long userId = LoginHelper.getUserId();
            SysUser user = sysUserService.getById(userId);
            List<SysRole> roles = sysRoleService.findAllById(user.getRoles());
            SysModule module = sysModuleService.getById(LoginHelper.getModuleId());
            List<Long> menus = module.getMenus();
            List<Long> menuIds = roles.parallelStream()
                                      .map(SysRole::getMenus)
                                      .flatMap(List::stream)
                                      .filter(menus::contains)
                                      .toList();
            list = sysMenuService.findAllById(menuIds);
        }
        list = list.parallelStream()
                   .filter(SysMenu::getEnabled)
                   .filter(sysMenu -> !"BUTTON".equals(sysMenu.getType()))
                   .toList();
        return buildRouteTree(list, 0L);
    }

    private List<Route> buildRouteTree(List<SysMenu> list, Long parentId) {
        return list.parallelStream()
                   .filter(sysMenu -> sysMenu.getParentId()
                                             .equals(parentId))
                   .map(sysMenu -> {
                       Route route = new Route();
                       route.setName(StringUtil.toUpperCamelCase(sysMenu.getPath()) + "#" + sysMenu.getId());
                       boolean topLevel = 0L == sysMenu.getParentId() && "DIRECTORY".equals(sysMenu.getType()) && !sysMenu.getLink();
                       String prefix = topLevel ? "/" : "";
                       route.setPath(prefix + sysMenu.getPath());
                       route.setComponent(topLevel ? StringUtil.defaultIfBlank(sysMenu.getComponent(), "Layout") : sysMenu.getComponent());
                       Meta meta = new Meta();
                       meta.setTitle(sysMenu.getName());
                       if (sysMenu.getLink()) {
                           meta.setLink(sysMenu.getPath());
                       }
                       meta.setIcon(sysMenu.getIcon());
                       meta.setVisible(sysMenu.getVisible());
                       route.setMeta(meta);
                       route.setChildren(buildRouteTree(list, sysMenu.getId()));
                       return route;
                   })
                   .toList();
    }

}
