package cn.sijay.suap.sys.service;

import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.sys.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>ISysUserService</strong>
 * <p>
 * 用户服务层
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
public interface ISysUserService {
    /**
     * 分页查询
     */
    Page<SysUser> page(SysUser sysUser, PageQuery pageQuery);

    /**
     * 查询所有数据
     */
    List<SysUser> list(SysUser sysUser);

    /**
     * 根据id查询数据
     */
    SysUser getById(Long id);

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    void save(SysUser sysUser);

    /**
     * 删除
     */
    void remove(List<Long> ids);

    /**
     * 导入
     */
    void importData(MultipartFile file);

    SysUser getByUsername(String username);

    SysUser getUserInfo();

}
