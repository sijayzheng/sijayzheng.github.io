package cn.sijay.biu.system.service;

import cn.sijay.biu.core.base.BaseService;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.util.CollectionUtil;
import cn.sijay.biu.system.entity.SystemRole;
import cn.sijay.biu.system.repository.SystemRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SystemRoleService
 * 系统权限逻辑处理层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemRoleService implements BaseService {
    private final SystemRoleRepository systemRoleRepository;

    /**
     * 查询系统权限
     *
     * @param id 主键
     * @return 系统权限
     */
    public SystemRole getById(Long id) {
        return systemRoleRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统权限列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统权限分页列表
     */
    public Page<SystemRole> page(SystemRole entity, PageQuery pageQuery) {
        return systemRoleRepository.findAll(buildSpecification(entity), pageQuery.build());
    }

    /**
     * 查询符合条件的系统权限列表
     *
     * @param entity 查询条件
     * @return 系统权限列表
     */
    public List<SystemRole> list(SystemRole entity) {
        return systemRoleRepository.findAll(buildSpecification(entity));
    }

    /**
     * 保存系统权限
     *
     * @param entity 系统权限
     */
    public void save(SystemRole entity) {
        validate(entity);
        systemRoleRepository.save(entity);
    }

    /**
     * 批量保存系统权限
     *
     * @param list 系统权限集合
     */
    public void save(List<SystemRole> list) {
        for (SystemRole entity : list) {
            validate(entity);
        }
        systemRoleRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统系统权限
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        systemRoleRepository.deleteAllById(ids);
    }

    /**
     * 导入数据
     *
     * @param list 导入的数据
     */
    public void importData(List<SystemRole> list) {
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
     * @param entity 系统权限
     */
    private void validate(SystemRole entity) {
    }

    private Specification<SystemRole> buildSpecification(SystemRole entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        return Specification.allOf(
                like("code", entity.getCode()),
                like("name", entity.getName()),
                equal("enable", entity.getEnable())
        );
    }
}
