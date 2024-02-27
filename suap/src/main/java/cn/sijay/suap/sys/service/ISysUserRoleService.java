package cn.sijay.suap.sys.service;

import cn.sijay.suap.sys.entity.SysUserRole;

import java.util.List;

/**
 * <p>
 * <em>ISysUserRoleService 用户角色服务层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/02 09:43
 */
public interface ISysUserRoleService {
    List<SysUserRole> list();

    List<SysUserRole> list(SysUserRole sysUserRole);

    List<SysUserRole> listByRoleId(Long roleId);

    List<SysUserRole> listByUserId(Long userId);

    boolean deleteByRoleId(Long roleId);

    boolean deleteByUserId(Long userId);

    boolean insert(SysUserRole sysUserRole);

    boolean batchInsert(List<SysUserRole> list);
}
