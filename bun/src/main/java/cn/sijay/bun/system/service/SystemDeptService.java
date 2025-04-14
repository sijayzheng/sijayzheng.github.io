package cn.sijay.bun.system.service;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.Spec;
import cn.sijay.bun.system.entity.SystemDept;
import cn.sijay.bun.system.repository.SystemDeptRepository;
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
 * <strong>SystemDeptService</strong>
 * <p>
 * 系统部门
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemDeptService {
    private final SystemDeptRepository systemDeptRepository;

    /**
     * 查询系统部门
     *
     * @param id 主键
     * @return 系统部门
     */
    public SystemDept findById(Long id) {
        return systemDeptRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统部门列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统部门分页列表
     */
    public ResponseResult<SystemDept> page(SystemDept entity, PageQuery pageQuery) {
        Specification<SystemDept> specification = buildSpecification(entity);
        Page<SystemDept> page = systemDeptRepository.findAll(specification, pageQuery.build());
        return ResponseResult.of(page);
    }

    /**
     * 查询符合条件的系统部门列表
     *
     * @param entity 查询条件
     * @return 系统部门列表
     */
    public List<SystemDept> list(SystemDept entity) {
        Specification<SystemDept> specification = buildSpecification(entity);
        return systemDeptRepository.findAll(specification);
    }

    /**
     * 保存系统部门
     *
     * @param entity 系统部门
     */
    public void save(SystemDept entity) {
        validate(entity);
        systemDeptRepository.save(entity);
    }

    /**
     * 批量保存系统部门
     *
     * @param list 系统部门集合
     */
    public void save(List<SystemDept> list) {
        for (SystemDept entity : list) {
            validate(entity);
        }
        systemDeptRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统系统部门
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        systemDeptRepository.deleteAllById(ids);
    }

    /**
     * 数据校验
     *
     * @param entity 系统部门
     */
    private void validate(SystemDept entity) {
    }

    private Specification<SystemDept> buildSpecification(SystemDept entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        List<Specification<SystemDept>> list = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(entity.getParentId())) {
            list.add(Spec.equal(SystemDept::getParentId, entity.getParentId()));
        }
        if (StringUtils.isNotBlank(entity.getDeptName())) {
            list.add(Spec.like(SystemDept::getDeptName, entity.getDeptName()));
        }
        if (ObjectUtils.isNotEmpty(entity.getSort())) {
            list.add(Spec.equal(SystemDept::getSort, entity.getSort()));
        }
        if (ObjectUtils.isNotEmpty(entity.getLeader())) {
            list.add(Spec.equal(SystemDept::getLeader, entity.getLeader()));
        }
        if (StringUtils.isNotBlank(entity.getPhone())) {
            list.add(Spec.like(SystemDept::getPhone, entity.getPhone()));
        }
        if (StringUtils.isNotBlank(entity.getEmail())) {
            list.add(Spec.like(SystemDept::getEmail, entity.getEmail()));
        }
        if (ObjectUtils.isNotEmpty(entity.getEnable())) {
            list.add(Spec.equal(SystemDept::getEnable, entity.getEnable()));
        }
        return Specification.allOf(list);
    }
}
