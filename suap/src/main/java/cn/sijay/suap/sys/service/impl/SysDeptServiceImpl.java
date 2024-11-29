package cn.sijay.suap.sys.service.impl;

import cn.sijay.suap.core.base.BaseException;
import cn.sijay.suap.core.constant.ExceptionConstant;
import cn.sijay.suap.core.utils.ObjectUtil;
import cn.sijay.suap.core.utils.TreeUtil;
import cn.sijay.suap.sys.entity.SysDept;
import cn.sijay.suap.sys.repository.SysDeptRepository;
import cn.sijay.suap.sys.service.ISysDeptService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <strong>SysDeptServiceImpl</strong>
 * <p>
 * 部门服务层实现类
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysDeptServiceImpl implements ISysDeptService {
    private final SysDeptRepository repository;

    /**
     * 根据条件查询所有数据
     */
    @Override
    public List<SysDept> listTree(SysDept sysDept) {
        List<SysDept> children = repository.findAll(buildSpecification(sysDept));
        if (children.isEmpty()) {
            return new ArrayList<>();
        }
        List<SysDept> all = repository.findAll();
        Set<Long> parent = getParent(children.parallelStream()
                                             .map(SysDept::getId)
                                             .collect(Collectors.toSet()), all);
        return TreeUtil.listToTree(all.parallelStream()
                                      .filter(item -> parent.contains(item.getId()))
                                      .toList(), 0L, SysDept::getId, SysDept::getParentId, SysDept::setChildren);
    }

    /**
     * 根据条件查询所有数据
     */
    @Override
    public List<SysDept> list(SysDept sysDept) {
        return repository.findAll(buildSpecification(sysDept));
    }

    /**
     * 根据id查询数据
     */
    @Override
    public SysDept getById(Long id) {
        return repository.getReferenceById(id);
    }

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(SysDept sysDept) {
        repository.save(sysDept);
    }

    /**
     * 删除
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void remove(List<Long> ids) {
        if (repository.existsByParentIdIn(ids)) {
            throw new BaseException(ExceptionConstant.HAS_CHILDREN, ids);
        }
        repository.deleteAllByIdInBatch(ids);
    }

    private Specification<SysDept> buildSpecification(SysDept sysDept) {
        if (sysDept == null) {
            return null;
        }
        return (root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            if (ObjectUtil.isNotNull(sysDept.getParentId())) {
                list.add(builder.equal(root.get("parentId"), sysDept.getParentId()));
            }
            if (ObjectUtil.isNotNull(sysDept.getName())) {
                list.add(builder.like(root.get("name"), "%" + sysDept.getName() + "%"));
            }
            if (ObjectUtil.isNotNull(sysDept.getEnabled())) {
                list.add(builder.equal(root.get("enabled"), sysDept.getEnabled()));
            }
            return builder.and(list.toArray(Predicate[]::new));
        };
    }

    private Set<Long> getParent(Set<Long> parentIds, List<SysDept> all) {
        Set<Long> parent = new HashSet<>(parentIds);
        if (!parentIds.isEmpty()) {
            Set<Long> pIds = all.parallelStream()
                                .filter(item -> parentIds.contains(item.getId()))
                                .map(SysDept::getParentId)
                                .filter(parentId -> parentId > 0L)
                                .collect(Collectors.toSet());
            parent.addAll(pIds);
            Set<Long> parentSet = getParent(pIds, all);
            parent.addAll(parentSet);
        }
        return parent;
    }

}
