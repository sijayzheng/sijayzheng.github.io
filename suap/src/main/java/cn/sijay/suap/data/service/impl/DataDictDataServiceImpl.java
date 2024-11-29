package cn.sijay.suap.data.service.impl;

import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.utils.ExcelUtil;
import cn.sijay.suap.core.utils.ObjectUtil;
import cn.sijay.suap.data.entity.DataDictData;
import cn.sijay.suap.data.repository.DataDictDataRepository;
import cn.sijay.suap.data.service.IDataDictDataService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>DataDictDataServiceImpl</strong>
 * <p>
 * 数据字典项服务层实现类
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DataDictDataServiceImpl implements IDataDictDataService {
    private final DataDictDataRepository repository;

    /**
     * 分页查询
     */
    @Override
    public Page<DataDictData> page(DataDictData dataDictData, PageQuery pageQuery) {
        return repository.findAll(buildSpecification(dataDictData), pageQuery.build());
    }

    /**
     * 根据条件查询所有数据
     */
    @Override
    public List<DataDictData> list(DataDictData dataDictData) {
        return repository.findAll(buildSpecification(dataDictData));
    }

    /**
     * 根据id查询数据
     */
    @Override
    public DataDictData getById(Long id) {
        return repository.getReferenceById(id);
    }

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(DataDictData dataDictData) {
        repository.save(dataDictData);
    }

    /**
     * 删除
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void remove(List<Long> ids) {
        repository.deleteAllByIdInBatch(ids);
    }

    /**
     * 导入
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importData(MultipartFile file) {
        repository.saveAll(ExcelUtil.read(file, DataDictData.class));
    }

    private Specification<DataDictData> buildSpecification(DataDictData dataDictData) {
        if (dataDictData == null) {
            return null;
        }
        return (root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            if (ObjectUtil.isNotNull(dataDictData.getDictTypeCode())) {
                list.add(builder.like(root.get("dictTypeCode"), "%" + dataDictData.getDictTypeCode() + "%"));
            }
            if (ObjectUtil.isNotNull(dataDictData.getLabel())) {
                list.add(builder.like(root.get("label"), "%" + dataDictData.getLabel() + "%"));
            }
            return builder.and(list.toArray(Predicate[]::new));
        };
    }

}
