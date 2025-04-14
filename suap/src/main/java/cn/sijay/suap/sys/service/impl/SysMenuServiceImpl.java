package cn.sijay.suap.sys.service.impl;

import cn.sijay.suap.core.base.BaseException;
import cn.sijay.suap.core.constant.ExceptionConstant;
import cn.sijay.suap.core.utils.ObjectUtil;
import cn.sijay.suap.core.utils.TreeUtil;
import cn.sijay.suap.sys.entity.SysMenu;
import cn.sijay.suap.sys.repository.SysMenuRepository;
import cn.sijay.suap.sys.service.ISysMenuService;
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
 * <strong>SysMenuServiceImpl</strong>
 * <p>
 * 菜单服务层实现类
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysMenuServiceImpl implements ISysMenuService {
    private final SysMenuRepository repository;

    /**
     * 根据条件查询所有数据
     */
    @Override
    public List<SysMenu> listTree(SysMenu sysMenu) {
        List<SysMenu> children = repository.findAll(buildSpecification(sysMenu));
        if (children.isEmpty()) {
            return new ArrayList<>();
        }
        List<SysMenu> all = repository.findAll();
        Set<Long> parent = getParent(children.parallelStream()
                                             .map(SysMenu::getId)
                                             .collect(Collectors.toSet()), all);
        return TreeUtil.listToTree(all.parallelStream()
                                      .filter(item -> parent.contains(item.getId()))
                                      .toList(), 0L, SysMenu::getId, SysMenu::getParentId, SysMenu::setChildren);
    }

    /**
     * 根据条件查询所有数据
     */
    @Override
    public List<SysMenu> list(SysMenu sysMenu) {
        return repository.findAll(buildSpecification(sysMenu));
    }

    /**
     * 根据id查询数据
     */
    @Override
    public SysMenu getById(Long id) {
        return repository.getReferenceById(id);
    }

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(SysMenu sysMenu) {
        repository.save(sysMenu);
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

    @Override
    public List<SysMenu> findAllById(List<Long> menuIds) {
        return repository.findAllById(menuIds);
    }

    private Specification<SysMenu> buildSpecification(SysMenu sysMenu) {
        if (sysMenu == null) {
            return null;
        }
        return (root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            if (ObjectUtil.isNotNull(sysMenu.getParentId())) {
                list.add(builder.equal(root.get("parentId"), sysMenu.getParentId()));
            }
            if (ObjectUtil.isNotNull(sysMenu.getName())) {
                list.add(builder.like(root.get("name"), "%" + sysMenu.getName() + "%"));
            }
            if (ObjectUtil.isNotNull(sysMenu.getEnabled())) {
                list.add(builder.equal(root.get("enabled"), sysMenu.getEnabled()));
            }
            return builder.and(list.toArray(Predicate[]::new));
        };
    }

    private Set<Long> getParent(Set<Long> parentIds, List<SysMenu> all) {
        Set<Long> parent = new HashSet<>(parentIds);
        if (!parentIds.isEmpty()) {
            Set<Long> pIds = all.parallelStream()
                                .filter(item -> parentIds.contains(item.getId()))
                                .map(SysMenu::getParentId)
                                .filter(parentId -> parentId > 0L)
                                .collect(Collectors.toSet());
            parent.addAll(pIds);
            Set<Long> parentSet = getParent(pIds, all);
            parent.addAll(parentSet);
        }
        return parent;
    }

}
