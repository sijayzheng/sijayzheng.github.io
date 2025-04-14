package cn.sijay.biu.system.service;

import cn.sijay.biu.core.base.BaseService;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.util.CollectionUtil;
import cn.sijay.biu.system.entity.SystemMenu;
import cn.sijay.biu.system.repository.SystemMenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SystemMenuService
 * 系统菜单逻辑处理层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemMenuService implements BaseService {
    private final SystemMenuRepository systemMenuRepository;

    /**
     * 查询系统菜单
     *
     * @param id 主键
     * @return 系统菜单
     */
    public SystemMenu getById(Long id) {
        return systemMenuRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统菜单列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统菜单分页列表
     */
    public Page<SystemMenu> page(SystemMenu entity, PageQuery pageQuery) {
        return systemMenuRepository.findAll(buildSpecification(entity), pageQuery.build());
    }

    /**
     * 查询符合条件的系统菜单列表
     *
     * @param entity 查询条件
     * @return 系统菜单列表
     */
    public List<SystemMenu> list(SystemMenu entity) {
        return systemMenuRepository.findAll(buildSpecification(entity));
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
     * 导入数据
     *
     * @param list 导入的数据
     */
    public void importData(List<SystemMenu> list) {
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
     * @param entity 系统菜单
     */
    private void validate(SystemMenu entity) {
    }

    private Specification<SystemMenu> buildSpecification(SystemMenu entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        return Specification.allOf(
                equal("parentId", entity.getParentId()),
                like("name", entity.getName()),
                equal("enable", entity.getEnable())
        );
    }
}
