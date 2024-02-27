package cn.sijay.suap.sys.service;

import cn.sijay.suap.sys.entity.SysRoleMenu;

import java.util.List;

/**
 * <p>
 * <em>ISysRoleMenuService 角色菜单服务层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
public interface ISysRoleMenuService {
    List<SysRoleMenu> list();

    List<SysRoleMenu> list(SysRoleMenu sysRoleMenu);

    List<SysRoleMenu> listByRoleId(Long roleId);

    List<SysRoleMenu> listByMenuId(Long menuId);

    boolean deleteByRoleId(Long roleId);

    boolean deleteByMenuId(Long menuId);

    boolean insert(SysRoleMenu sysRoleMenu);

    boolean batchInsert(List<SysRoleMenu> list);
}
