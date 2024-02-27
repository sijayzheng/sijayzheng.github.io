package cn.sijay.suap.sys.mapper;

import cn.sijay.suap.sys.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * <em>SysUserRoleMapper 用户角色数据层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Mapper
public interface SysUserRoleMapper {
    List<SysUserRole> list(SysUserRole sysUserRole);

    List<SysUserRole> listByRoleId(Long roleId);

    List<SysUserRole> listByUserId(Long userId);

    int deleteByRoleId(Long roleId);

    int deleteByUserId(Long userId);

    int insert(SysUserRole sysUserRole);

    int batchInsert(List<SysUserRole> list);
}
