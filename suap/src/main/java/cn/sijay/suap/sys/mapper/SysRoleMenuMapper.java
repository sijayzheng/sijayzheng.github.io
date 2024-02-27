package cn.sijay.suap.sys.mapper;

import cn.sijay.suap.sys.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * <em>SysRoleMenuMapper 角色菜单数据层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Mapper
public interface SysRoleMenuMapper {
    List<SysRoleMenu> list(SysRoleMenu sysRoleMenu);

    List<SysRoleMenu> listByRoleId(Long roleId);

    List<SysRoleMenu> listByMenuId(Long menuId);

    int deleteByRoleId(Long roleId);

    int deleteByMenuId(Long menuId);

    int insert(SysRoleMenu sysRoleMenu);

    int batchInsert(List<SysRoleMenu> list);
}
