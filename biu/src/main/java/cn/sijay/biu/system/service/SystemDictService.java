package cn.sijay.biu.system.service;

import cn.sijay.biu.core.base.BaseService;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.util.CollectionUtil;
import cn.sijay.biu.system.entity.SystemDict;
import cn.sijay.biu.system.repository.SystemDictRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SystemDictService
 * 系统字典逻辑处理层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemDictService implements BaseService {
    private final SystemDictRepository systemDictRepository;

    /**
     * 查询系统字典
     *
     * @param id 主键
     * @return 系统字典
     */
    public SystemDict getById(Long id) {
        return systemDictRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统字典列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统字典分页列表
     */
    public Page<SystemDict> page(SystemDict entity, PageQuery pageQuery) {
        return systemDictRepository.findAll(buildSpecification(entity), pageQuery.build());
    }

    /**
     * 查询符合条件的系统字典列表
     *
     * @param entity 查询条件
     * @return 系统字典列表
     */
    public List<SystemDict> list(SystemDict entity) {
        return systemDictRepository.findAll(buildSpecification(entity));
    }

    /**
     * 保存系统字典
     *
     * @param entity 系统字典
     */
    public void save(SystemDict entity) {
        validate(entity);
        systemDictRepository.save(entity);
    }

    /**
     * 批量保存系统字典
     *
     * @param list 系统字典集合
     */
    public void save(List<SystemDict> list) {
        for (SystemDict entity : list) {
            validate(entity);
        }
        systemDictRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统系统字典
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        systemDictRepository.deleteAllById(ids);
    }

    /**
     * 导入数据
     *
     * @param list 导入的数据
     */
    public void importData(List<SystemDict> list) {
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
     * @param entity 系统字典
     */
    private void validate(SystemDict entity) {
    }

    private Specification<SystemDict> buildSpecification(SystemDict entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        return Specification.allOf(
                like("code", entity.getCode()),
                like("name", entity.getName())
        );
    }
}
