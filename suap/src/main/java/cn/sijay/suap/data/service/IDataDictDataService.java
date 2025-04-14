package cn.sijay.suap.data.service;

import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.data.entity.DataDictData;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>IDataDictDataService</strong>
 * <p>
 * 数据字典项服务层
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
public interface IDataDictDataService {
    /**
     * 分页查询
     */
    Page<DataDictData> page(DataDictData dataDictData, PageQuery pageQuery);

    /**
     * 查询所有数据
     */
    List<DataDictData> list(DataDictData dataDictData);

    /**
     * 根据id查询数据
     */
    DataDictData getById(Long id);

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    void save(DataDictData dataDictData);

    /**
     * 删除
     */
    void remove(List<Long> ids);

    /**
     * 导入
     */
    void importData(MultipartFile file);

}
