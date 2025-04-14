package cn.sijay.bun.system.service;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.Spec;
import cn.sijay.bun.system.entity.SystemMenu;
import cn.sijay.bun.system.repository.SystemMenuRepository;
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
 * <strong>SystemMenuService</strong>
 * <p>
 * 系统菜单
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemMenuService {
    private final SystemMenuRepository systemMenuRepository;

    /**
     * 查询系统菜单
     *
     * @param id 主键
     * @return 系统菜单
     */
    public SystemMenu findById(Long id) {
        return systemMenuRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统菜单列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统菜单分页列表
     */
    public ResponseResult<SystemMenu> page(SystemMenu entity, PageQuery pageQuery) {
        Specification<SystemMenu> specification = buildSpecification(entity);
        Page<SystemMenu> page = systemMenuRepository.findAll(specification, pageQuery.build());
        return ResponseResult.of(page);
    }

    /**
     * 查询符合条件的系统菜单列表
     *
     * @param entity 查询条件
     * @return 系统菜单列表
     */
    public List<SystemMenu> list(SystemMenu entity) {
        Specification<SystemMenu> specification = buildSpecification(entity);
        return systemMenuRepository.findAll(specification);
    }

    /**
     * 保存系统菜单
     *
     * @param entity 系统菜单
     */
    public void save(SystemMenu entity) {
        validate(entity);
        systemMenuRepository.save(entity);
    }

    /**
     * 批量保存系统菜单
     *
     * @param list 系统菜单集合
     */
    public void save(List<SystemMenu> list) {
        for (SystemMenu entity : list) {
            validate(entity);
        }
        systemMenuRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统系统菜单
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        systemMenuRepository.deleteAllById(ids);
    }

    /**
     * 数据校验
     *
     * @param entity 系统菜单
     */
    private void validate(SystemMenu entity) {
    }

    private Specification<SystemMenu> buildSpecification(SystemMenu entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        List<Specification<SystemMenu>> list = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(entity.getParentId())) {
            list.add(Spec.equal(SystemMenu::getParentId, entity.getParentId()));
        }
        if (StringUtils.isNotBlank(entity.getName())) {
            list.add(Spec.like(SystemMenu::getName, entity.getName()));
        }
        if (StringUtils.isNotBlank(entity.getPermsCode())) {
            list.add(Spec.like(SystemMenu::getPermsCode, entity.getPermsCode()));
        }
        if (ObjectUtils.isNotEmpty(entity.getSort())) {
            list.add(Spec.equal(SystemMenu::getSort, entity.getSort()));
        }
        if (StringUtils.isNotBlank(entity.getPath())) {
            list.add(Spec.like(SystemMenu::getPath, entity.getPath()));
        }
        if (StringUtils.isNotBlank(entity.getComponent())) {
            list.add(Spec.like(SystemMenu::getComponent, entity.getComponent()));
        }
//        if(StringUtils.isNotBlank(entity.getType())) {
//            list.add(Spec.like(SystemMenu::getType, entity.getType()));
//        }
        if (ObjectUtils.isNotEmpty(entity.getExternalLink())) {
            list.add(Spec.equal(SystemMenu::getExternalLink, entity.getExternalLink()));
        }
        if (ObjectUtils.isNotEmpty(entity.getCacheable())) {
            list.add(Spec.equal(SystemMenu::getCacheable, entity.getCacheable()));
        }
        if (ObjectUtils.isNotEmpty(entity.getVisible())) {
            list.add(Spec.equal(SystemMenu::getVisible, entity.getVisible()));
        }
        if (ObjectUtils.isNotEmpty(entity.getEnable())) {
            list.add(Spec.equal(SystemMenu::getEnable, entity.getEnable()));
        }
        if (StringUtils.isNotBlank(entity.getIcon())) {
            list.add(Spec.like(SystemMenu::getIcon, entity.getIcon()));
        }
        return Specification.allOf(list);
    }
}
