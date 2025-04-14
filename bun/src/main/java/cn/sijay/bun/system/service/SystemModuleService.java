package cn.sijay.bun.system.service;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.Spec;
import cn.sijay.bun.system.entity.SystemModule;
import cn.sijay.bun.system.repository.SystemModuleRepository;
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
 * <strong>SystemModuleService</strong>
 * <p>
 * 系统模块
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemModuleService {
    private final SystemModuleRepository systemModuleRepository;

    /**
     * 查询系统模块
     *
     * @param id 主键
     * @return 系统模块
     */
    public SystemModule findById(Long id) {
        return systemModuleRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统模块列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统模块分页列表
     */
    public ResponseResult<SystemModule> page(SystemModule entity, PageQuery pageQuery) {
        Specification<SystemModule> specification = buildSpecification(entity);
        Page<SystemModule> page = systemModuleRepository.findAll(specification, pageQuery.build());
        return ResponseResult.of(page);
    }

    /**
     * 查询符合条件的系统模块列表
     *
     * @param entity 查询条件
     * @return 系统模块列表
     */
    public List<SystemModule> list(SystemModule entity) {
        Specification<SystemModule> specification = buildSpecification(entity);
        return systemModuleRepository.findAll(specification);
    }

    /**
     * 保存系统模块
     *
     * @param entity 系统模块
     */
    public void save(SystemModule entity) {
        validate(entity);
        systemModuleRepository.save(entity);
    }

    /**
     * 批量保存系统模块
     *
     * @param list 系统模块集合
     */
    public void save(List<SystemModule> list) {
        for (SystemModule entity : list) {
            validate(entity);
        }
        systemModuleRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统系统模块
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        systemModuleRepository.deleteAllById(ids);
    }

    /**
     * 数据校验
     *
     * @param entity 系统模块
     */
    private void validate(SystemModule entity) {
    }

    private Specification<SystemModule> buildSpecification(SystemModule entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        List<Specification<SystemModule>> list = new ArrayList<>();
        if (StringUtils.isNotBlank(entity.getCode())) {
            list.add(Spec.like(SystemModule::getCode, entity.getCode()));
        }
        if (StringUtils.isNotBlank(entity.getName())) {
            list.add(Spec.like(SystemModule::getName, entity.getName()));
        }
        if (ObjectUtils.isNotEmpty(entity.getEnable())) {
            list.add(Spec.equal(SystemModule::getEnable, entity.getEnable()));
        }
        return Specification.allOf(list);
    }
}
