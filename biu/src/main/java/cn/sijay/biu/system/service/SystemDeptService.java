package cn.sijay.biu.system.service;

import cn.sijay.biu.core.base.BaseService;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.util.CollectionUtil;
import cn.sijay.biu.system.entity.SystemDept;
import cn.sijay.biu.system.repository.SystemDeptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SystemDeptService
 * 系统部门逻辑处理层
 *
 * @author Sijay
 * @since 2025-04-09
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemDeptService implements BaseService {
    private final SystemDeptRepository systemDeptRepository;

    /**
     * 查询系统部门
     *
     * @param id 主键
     * @return 系统部门
     */
    public SystemDept getById(Long id) {
        return systemDeptRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统部门列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统部门分页列表
     */
    public Page<SystemDept> page(SystemDept entity, PageQuery pageQuery) {
        return systemDeptRepository.findAll(buildSpecification(entity), pageQuery.build());
    }

    /**
     * 查询符合条件的系统部门列表
     *
     * @param entity 查询条件
     * @return 系统部门列表
     */
    public List<SystemDept> list(SystemDept entity) {
        return systemDeptRepository.findAll(buildSpecification(entity));
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
     * 导入数据
     *
     * @param list 导入的数据
     */
    public void importData(List<SystemDept> list) {
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
     * @param entity 系统部门
     */
    private void validate(SystemDept entity) {
    }

    private Specification<SystemDept> buildSpecification(SystemDept entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        return Specification.allOf(
                like("name", entity.getName())
        );
    }
}
