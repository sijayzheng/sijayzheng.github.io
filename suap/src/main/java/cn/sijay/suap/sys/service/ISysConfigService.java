package cn.sijay.suap.sys.service;

import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.sys.entity.SysConfig;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>ISysConfigService</strong>
 * <p>
 * 系统配置服务层
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
public interface ISysConfigService {
    /**
     * 分页查询
     */
    Page<SysConfig> page(SysConfig sysConfig, PageQuery pageQuery);

    /**
     * 查询所有数据
     */
    List<SysConfig> list(SysConfig sysConfig);

    /**
     * 根据id查询数据
     */
    SysConfig getById(Long id);

    /**
     * 根据code查询数据
     */
    SysConfig getByCode(String code);

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    void save(SysConfig sysConfig);

    /**
     * 删除
     */
    void remove(List<Long> ids);

    /**
     * 导入
     */
    void importData(MultipartFile file);

    void resetConfigCache();
}
