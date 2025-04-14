package cn.sijay.bun.system.service;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.Spec;
import cn.sijay.bun.system.entity.SystemDictData;
import cn.sijay.bun.system.repository.SystemDictDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>SystemDictDataService</strong>
 * <p>
 * 系统字典数据
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemDictDataService {
    private final SystemDictDataRepository systemDictDataRepository;

    /**
     * 查询系统字典数据
     *
     * @param id 主键
     * @return 系统字典数据
     */
    public SystemDictData findById(Long id) {
        return systemDictDataRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统字典数据列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统字典数据分页列表
     */
    public ResponseResult<SystemDictData> page(SystemDictData entity, PageQuery pageQuery) {
        Specification<SystemDictData> specification = buildSpecification(entity);
        Page<SystemDictData> page = systemDictDataRepository.findAll(specification, pageQuery.build());
        return ResponseResult.of(page);
    }

    /**
     * 查询符合条件的系统字典数据列表
     *
     * @param entity 查询条件
     * @return 系统字典数据列表
     */
    public List<SystemDictData> list(SystemDictData entity) {
        Specification<SystemDictData> specification = buildSpecification(entity);
        return systemDictDataRepository.findAll(specification);
    }

    /**
     * 保存系统字典数据
     *
     * @param entity 系统字典数据
     */
    public void save(SystemDictData entity) {
        validate(entity);
        systemDictDataRepository.save(entity);
    }

    /**
     * 批量保存系统字典数据
     *
     * @param list 系统字典数据集合
     */
    public void save(List<SystemDictData> list) {
        for (SystemDictData entity : list) {
            validate(entity);
        }
        systemDictDataRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统系统字典数据
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        systemDictDataRepository.deleteAllById(ids);
    }

    /**
     * 数据校验
     *
     * @param entity 系统字典数据
     */
    private void validate(SystemDictData entity) {
    }

    private Specification<SystemDictData> buildSpecification(SystemDictData entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        List<Specification<SystemDictData>> list = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(entity.getDictId())) {
            list.add(Spec.equal(SystemDictData::getDictId, entity.getDictId()));
        }
        if (StringUtils.isNotBlank(entity.getLabel())) {
            list.add(Spec.like(SystemDictData::getLabel, entity.getLabel()));
        }
        if (StringUtils.isNotBlank(entity.getValue())) {
            list.add(Spec.like(SystemDictData::getValue, entity.getValue()));
        }
        if (ObjectUtils.isNotEmpty(entity.getSort())) {
            list.add(Spec.equal(SystemDictData::getSort, entity.getSort()));
        }
        if (StringUtils.isNotBlank(entity.getClassName())) {
            list.add(Spec.like(SystemDictData::getClassName, entity.getClassName()));
        }
//        if(StringUtils.isNotBlank(entity.getShowType())) {
//            list.add(Spec.like(SystemDictData::getShowType, entity.getShowType()));
//        }
        if (ObjectUtils.isNotEmpty(entity.getDefaultValue())) {
            list.add(Spec.equal(SystemDictData::getDefaultValue, entity.getDefaultValue()));
        }
        return Specification.allOf(list);
    }
}
