package cn.sijay.suap.sys.service;

import cn.sijay.suap.sys.entity.SysDept;

import java.util.List;

/**
 * <strong>ISysDeptService</strong>
 * <p>
 * 部门服务层
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
public interface ISysDeptService {
    /**
     * 查询所有数据
     */
    List<SysDept> listTree(SysDept sysDept);

    /**
     * 查询所有数据
     */
    List<SysDept> list(SysDept sysDept);

    /**
     * 根据id查询数据
     */
    SysDept getById(Long id);

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    void save(SysDept sysDept);

    /**
     * 删除
     */
    void remove(List<Long> ids);

}
