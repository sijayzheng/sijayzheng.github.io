package cn.sijay.bun.system.service;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.Spec;
import cn.sijay.bun.system.entity.SystemRole;
import cn.sijay.bun.system.repository.SystemRoleRepository;
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
 * <strong>SystemRoleService</strong>
 * <p>
 * 系统权限
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemRoleService {
    private final SystemRoleRepository systemRoleRepository;

    /**
     * 查询系统权限
     *
     * @param id 主键
     * @return 系统权限
     */
    public SystemRole findById(Long id) {
        return systemRoleRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统权限列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统权限分页列表
     */
    public ResponseResult<SystemRole> page(SystemRole entity, PageQuery pageQuery) {
        Specification<SystemRole> specification = buildSpecification(entity);
        Page<SystemRole> page = systemRoleRepository.findAll(specification, pageQuery.build());
        return ResponseResult.of(page);
    }

    /**
     * 查询符合条件的系统权限列表
     *
     * @param entity 查询条件
     * @return 系统权限列表
     */
    public List<SystemRole> list(SystemRole entity) {
        Specification<SystemRole> specification = buildSpecification(entity);
        return systemRoleRepository.findAll(specification);
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
        List<Specification<SystemRole>> list = new ArrayList<>();
        if (StringUtils.isNotBlank(entity.getCode())) {
            list.add(Spec.like(SystemRole::getCode, entity.getCode()));
        }
        if (StringUtils.isNotBlank(entity.getName())) {
            list.add(Spec.like(SystemRole::getName, entity.getName()));
        }
        if (ObjectUtils.isNotEmpty(entity.getSort())) {
            list.add(Spec.equal(SystemRole::getSort, entity.getSort()));
        }
        if (ObjectUtils.isNotEmpty(entity.getEnable())) {
            list.add(Spec.equal(SystemRole::getEnable, entity.getEnable()));
        }
        if (ObjectUtils.isNotEmpty(entity.getMenuCheckStrictly())) {
            list.add(Spec.equal(SystemRole::getMenuCheckStrictly, entity.getMenuCheckStrictly()));
        }
        if (ObjectUtils.isNotEmpty(entity.getDeptCheckStrictly())) {
            list.add(Spec.equal(SystemRole::getDeptCheckStrictly, entity.getDeptCheckStrictly()));
        }
//        if(StringUtils.isNotBlank(entity.getDataScope())) {
//            list.add(Spec.like(SystemRole::getDataScope, entity.getDataScope()));
//        }
        return Specification.allOf(list);
    }
}
