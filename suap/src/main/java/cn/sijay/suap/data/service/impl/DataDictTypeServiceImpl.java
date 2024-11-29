package cn.sijay.suap.data.service.impl;

import cn.sijay.suap.core.base.BaseException;
import cn.sijay.suap.core.constant.ExceptionConstant;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.utils.ExcelUtil;
import cn.sijay.suap.core.utils.ObjectUtil;
import cn.sijay.suap.data.entity.DataDictType;
import cn.sijay.suap.data.repository.DataDictTypeRepository;
import cn.sijay.suap.data.service.IDataDictTypeService;
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
 * <strong>DataDictTypeServiceImpl</strong>
 * <p>
 * 数据字典类型服务层实现类
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DataDictTypeServiceImpl implements IDataDictTypeService {
    private final DataDictTypeRepository repository;

    /**
     * 分页查询
     */
    @Override
    public Page<DataDictType> page(DataDictType dataDictType, PageQuery pageQuery) {
        return repository.findAll(buildSpecification(dataDictType), pageQuery.build());
    }

    /**
     * 根据条件查询所有数据
     */
    @Override
    public List<DataDictType> list(DataDictType dataDictType) {
        return repository.findAll(buildSpecification(dataDictType));
    }

    /**
     * 根据id查询数据
     */
    @Override
    public DataDictType getById(Long id) {
        return repository.getReferenceById(id);
    }

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(DataDictType dataDictType) {
        checkUnique(dataDictType);
        repository.save(dataDictType);
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
        repository.saveAll(ExcelUtil.read(file, DataDictType.class));
    }

    /**
     * 唯一性校验
     */
    private void checkUnique(DataDictType dataDictType) {
        if (repository.existsByCode(dataDictType.getCode()) && !repository.existsByCodeAndId(dataDictType.getCode(), dataDictType.getId())) {
            throw new BaseException(ExceptionConstant.VALIDATE_UNIQUE_ERROR, "code", dataDictType.getCode());
        }
    }

    private Specification<DataDictType> buildSpecification(DataDictType dataDictType) {
        if (dataDictType == null) {
            return null;
        }
        return (root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            if (ObjectUtil.isNotNull(dataDictType.getName())) {
                list.add(builder.like(root.get("name"), "%" + dataDictType.getName() + "%"));
            }
            if (ObjectUtil.isNotNull(dataDictType.getCode())) {
                list.add(builder.like(root.get("code"), "%" + dataDictType.getCode() + "%"));
            }
            return builder.and(list.toArray(Predicate[]::new));
        };
    }

}
