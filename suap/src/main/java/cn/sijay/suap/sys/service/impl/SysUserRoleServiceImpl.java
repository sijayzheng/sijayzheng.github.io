package cn.sijay.suap.sys.service.impl;

import cn.sijay.suap.sys.entity.SysUserRole;
import cn.sijay.suap.sys.mapper.SysUserRoleMapper;
import cn.sijay.suap.sys.service.ISysUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * <em>SysUserRoleService 用户角色服务层实现类</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysUserRoleServiceImpl implements ISysUserRoleService {

    private final SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUserRole> list() {
        return sysUserRoleMapper.list(new SysUserRole());
    }

    @Override
    public List<SysUserRole> list(SysUserRole sysUserRole) {
        return sysUserRoleMapper.list(sysUserRole);
    }

    @Override
    public List<SysUserRole> listByRoleId(Long roleId) {
        return sysUserRoleMapper.listByRoleId(roleId);
    }

    @Override
    public List<SysUserRole> listByUserId(Long userId) {
        return sysUserRoleMapper.listByUserId(userId);
    }

    @Override
    public boolean deleteByRoleId(Long roleId) {
        return toBool(sysUserRoleMapper.deleteByRoleId(roleId));
    }

    @Override
    public boolean deleteByUserId(Long userId) {
        return toBool(sysUserRoleMapper.deleteByUserId(userId));
    }

    @Override
    public boolean insert(SysUserRole sysUserRole) {
        return toBool(sysUserRoleMapper.insert(sysUserRole));
    }

    @Override
    public boolean batchInsert(List<SysUserRole> list) {
        return toBool(sysUserRoleMapper.batchInsert(list));
    }

    boolean toBool(Integer result) {
        return null != result && result >= 1;
    }
}
