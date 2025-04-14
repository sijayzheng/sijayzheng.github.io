package cn.sijay.biu.system.service;

import cn.sijay.biu.core.base.BaseService;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.util.CollectionUtil;
import cn.sijay.biu.system.entity.SystemConfig;
import cn.sijay.biu.system.repository.SystemConfigRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SystemConfigService
 * 系统配置逻辑处理层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemConfigService implements BaseService {
    private final SystemConfigRepository systemConfigRepository;

    /**
     * 查询系统配置
     *
     * @param id 主键
     * @return 系统配置
     */
    public SystemConfig getById(Long id) {
        return systemConfigRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统配置列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统配置分页列表
     */
    public Page<SystemConfig> page(SystemConfig entity, PageQuery pageQuery) {
        return systemConfigRepository.findAll(buildSpecification(entity), pageQuery.build());
    }

    /**
     * 查询符合条件的系统配置列表
     *
     * @param entity 查询条件
     * @return 系统配置列表
     */
    public List<SystemConfig> list(SystemConfig entity) {
        return systemConfigRepository.findAll(buildSpecification(entity));
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
     * 导入数据
     *
     * @param list 导入的数据
     */
    public void importData(List<SystemConfig> list) {
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
     * @param entity 系统配置
     */
    private void validate(SystemConfig entity) {
    }

    private Specification<SystemConfig> buildSpecification(SystemConfig entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        return Specification.allOf(
                like("name", entity.getName()),
                like("code", entity.getCode())
        );
    }
}
