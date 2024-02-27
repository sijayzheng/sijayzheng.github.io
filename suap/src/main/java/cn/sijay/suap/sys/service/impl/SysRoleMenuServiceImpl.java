package cn.sijay.suap.sys.service.impl;

import cn.sijay.suap.sys.entity.SysRoleMenu;
import cn.sijay.suap.sys.mapper.SysRoleMenuMapper;
import cn.sijay.suap.sys.service.ISysRoleMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * <em>SysRoleMenuService 角色菜单服务层实现类</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysRoleMenuServiceImpl implements ISysRoleMenuService {

    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysRoleMenu> list() {
        return sysRoleMenuMapper.list(new SysRoleMenu());
    }

    @Override
    public List<SysRoleMenu> list(SysRoleMenu sysUserRole) {
        return sysRoleMenuMapper.list(sysUserRole);
    }

    @Override
    public List<SysRoleMenu> listByRoleId(Long roleId) {
        return sysRoleMenuMapper.listByRoleId(roleId);
    }

    @Override
    public List<SysRoleMenu> listByMenuId(Long menuId) {
        return sysRoleMenuMapper.listByMenuId(menuId);
    }

    @Override
    public boolean deleteByRoleId(Long roleId) {
        return toBool(sysRoleMenuMapper.deleteByRoleId(roleId));
    }

    @Override
    public boolean deleteByMenuId(Long menuId) {
        return toBool(sysRoleMenuMapper.deleteByMenuId(menuId));
    }

    @Override
    public boolean insert(SysRoleMenu sysUserRole) {
        return toBool(sysRoleMenuMapper.insert(sysUserRole));
    }

    @Override
    public boolean batchInsert(List<SysRoleMenu> list) {
        return toBool(sysRoleMenuMapper.batchInsert(list));
    }

    boolean toBool(Integer result) {
        return null != result && result >= 1;
    }

}
