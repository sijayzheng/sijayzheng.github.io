package cn.sijay.suap.sys.service;

import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.sys.entity.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>ISysRoleService</strong>
 * <p>
 * 角色服务层
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
public interface ISysRoleService {
    /**
     * 分页查询
     */
    Page<SysRole> page(SysRole sysRole, PageQuery pageQuery);

    /**
     * 查询所有数据
     */
    List<SysRole> list(SysRole sysRole);

    /**
     * 根据id查询数据
     */
    SysRole getById(Long id);

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    void save(SysRole sysRole);

    /**
     * 删除
     */
    void remove(List<Long> ids);

    /**
     * 导入
     */
    void importData(MultipartFile file);

    List<SysRole> findAllById(List<Long> roleIds);
}
