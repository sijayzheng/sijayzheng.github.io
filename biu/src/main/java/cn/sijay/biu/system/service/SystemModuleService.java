package cn.sijay.biu.system.service;

import cn.sijay.biu.core.base.BaseService;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.util.CollectionUtil;
import cn.sijay.biu.system.entity.SystemModule;
import cn.sijay.biu.system.repository.SystemModuleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SystemModuleService
 * 系统模块逻辑处理层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemModuleService implements BaseService {
    private final SystemModuleRepository systemModuleRepository;

    /**
     * 查询系统模块
     *
     * @param id 主键
     * @return 系统模块
     */
    public SystemModule getById(Long id) {
        return systemModuleRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统模块列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统模块分页列表
     */
    public Page<SystemModule> page(SystemModule entity, PageQuery pageQuery) {
        return systemModuleRepository.findAll(buildSpecification(entity), pageQuery.build());
    }

    /**
     * 查询符合条件的系统模块列表
     *
     * @param entity 查询条件
     * @return 系统模块列表
     */
    public List<SystemModule> list(SystemModule entity) {
        return systemModuleRepository.findAll(buildSpecification(entity));
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
     * 导入数据
     *
     * @param list 导入的数据
     */
    public void importData(List<SystemModule> list) {
        if (CollectionUtil.isNotEmpty(list)) {
            list = list.stream()
                       .peek(item -> {
                       })
                       .toList();
            save(list);
        }
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
        return Specification.allOf(
                like("code", entity.getCode()),
                like("name", entity.getName())
        );
    }
}
