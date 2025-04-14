package cn.sijay.bun.system.service;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.Spec;
import cn.sijay.bun.system.entity.SystemConfig;
import cn.sijay.bun.system.repository.SystemConfigRepository;
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
 * <strong>SystemConfigService</strong>
 * <p>
 * 系统配置
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemConfigService {
    private final SystemConfigRepository systemConfigRepository;

    /**
     * 查询系统配置
     *
     * @param id 主键
     * @return 系统配置
     */
    public SystemConfig findById(Long id) {
        return systemConfigRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统配置列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统配置分页列表
     */
    public ResponseResult<SystemConfig> page(SystemConfig entity, PageQuery pageQuery) {
        Specification<SystemConfig> specification = buildSpecification(entity);
        Page<SystemConfig> page = systemConfigRepository.findAll(specification, pageQuery.build());
        return ResponseResult.of(page);
    }

    /**
     * 查询符合条件的系统配置列表
     *
     * @param entity 查询条件
     * @return 系统配置列表
     */
    public List<SystemConfig> list(SystemConfig entity) {
        Specification<SystemConfig> specification = buildSpecification(entity);
        return systemConfigRepository.findAll(specification);
    }

    /**
     * 保存系统配置
     *
     * @param entity 系统配置
     */
    public void save(SystemConfig entity) {
        validate(entity);
        systemConfigRepository.save(entity);
    }

    /**
     * 批量保存系统配置
     *
     * @param list 系统配置集合
     */
    public void save(List<SystemConfig> list) {
        for (SystemConfig entity : list) {
            validate(entity);
        }
        systemConfigRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统系统配置
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        systemConfigRepository.deleteAllById(ids);
    }

    /**
     * 数据校验
     *
     * @param entity 系统配置
     */
    private void validate(SystemConfig entity) {
    }

    private Specification<SystemConfig> buildSpecification(SystemConfig entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        List<Specification<SystemConfig>> list = new ArrayList<>();
        if (StringUtils.isNotBlank(entity.getName())) {
            list.add(Spec.like(SystemConfig::getName, entity.getName()));
        }
        if (StringUtils.isNotBlank(entity.getCode())) {
            list.add(Spec.like(SystemConfig::getCode, entity.getCode()));
        }
        if (StringUtils.isNotBlank(entity.getValue())) {
            list.add(Spec.like(SystemConfig::getValue, entity.getValue()));
        }
        if (ObjectUtils.isNotEmpty(entity.getInternal())) {
            list.add(Spec.equal(SystemConfig::getInternal, entity.getInternal()));
        }
        return Specification.allOf(list);
    }
}
