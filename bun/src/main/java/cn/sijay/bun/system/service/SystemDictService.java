package cn.sijay.bun.system.service;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.Spec;
import cn.sijay.bun.system.entity.SystemDict;
import cn.sijay.bun.system.repository.SystemDictRepository;
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
 * <strong>SystemDictService</strong>
 * <p>
 * 系统字典
 * </p>
 *
 * @author sijay
 * @since 2024-11-11
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemDictService {
    private final SystemDictRepository systemDictRepository;

    /**
     * 查询系统字典
     *
     * @param id 主键
     * @return 系统字典
     */
    public SystemDict findById(Long id) {
        return systemDictRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统字典列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统字典分页列表
     */
    public ResponseResult<SystemDict> page(SystemDict entity, PageQuery pageQuery) {
        Specification<SystemDict> specification = buildSpecification(entity);
        Page<SystemDict> page = systemDictRepository.findAll(specification, pageQuery.build());
        return ResponseResult.of(page);
    }

    /**
     * 查询符合条件的系统字典列表
     *
     * @param entity 查询条件
     * @return 系统字典列表
     */
    public List<SystemDict> list(SystemDict entity) {
        Specification<SystemDict> specification = buildSpecification(entity);
        return systemDictRepository.findAll(specification);
    }

    /**
     * 保存系统字典
     *
     * @param entity 系统字典
     */
    public void save(SystemDict entity) {
        validate(entity);
        systemDictRepository.save(entity);
    }

    /**
     * 批量保存系统字典
     *
     * @param list 系统字典集合
     */
    public void save(List<SystemDict> list) {
        for (SystemDict entity : list) {
            validate(entity);
        }
        systemDictRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统系统字典
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        systemDictRepository.deleteAllById(ids);
    }

    /**
     * 数据校验
     *
     * @param entity 系统字典
     */
    private void validate(SystemDict entity) {
    }

    private Specification<SystemDict> buildSpecification(SystemDict entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        List<Specification<SystemDict>> list = new ArrayList<>();
        if (StringUtils.isNotBlank(entity.getCode())) {
            list.add(Spec.like(SystemDict::getCode, entity.getCode()));
        }
        if (StringUtils.isNotBlank(entity.getName())) {
            list.add(Spec.like(SystemDict::getName, entity.getName()));
        }
        return Specification.allOf(list);
    }
}
