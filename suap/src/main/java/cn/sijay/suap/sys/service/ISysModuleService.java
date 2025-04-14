package cn.sijay.suap.sys.service;

import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.sys.entity.SysModule;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>ISysModuleService</strong>
 * <p>
 * 模块服务层
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
public interface ISysModuleService {
    /**
     * 分页查询
     */
    Page<SysModule> page(SysModule sysModule, PageQuery pageQuery);

    /**
     * 查询所有数据
     */
    List<SysModule> list(SysModule sysModule);

    /**
     * 根据id查询数据
     */
    SysModule getById(Long id);

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    void save(SysModule sysModule);

    /**
     * 删除
     */
    void remove(List<Long> ids);

    /**
     * 导入
     */
    void importData(MultipartFile file);

}
