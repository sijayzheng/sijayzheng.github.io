package cn.sijay.suap.sys.service;

import cn.sijay.suap.sys.entity.SysMenu;

import java.util.List;

/**
 * <strong>ISysMenuService</strong>
 * <p>
 * 菜单服务层
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
public interface ISysMenuService {
    /**
     * 查询所有数据
     */
    List<SysMenu> listTree(SysMenu sysMenu);

    /**
     * 查询所有数据
     */
    List<SysMenu> list(SysMenu sysMenu);

    /**
     * 根据id查询数据
     */
    SysMenu getById(Long id);

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    void save(SysMenu sysMenu);

    /**
     * 删除
     */
    void remove(List<Long> ids);

    default List<SysMenu> list() {
        return list(null);
    }

    List<SysMenu> findAllById(List<Long> menuIds);
}
