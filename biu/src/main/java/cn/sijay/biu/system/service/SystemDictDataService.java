package cn.sijay.biu.system.service;

import cn.sijay.biu.core.base.BaseService;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.util.CollectionUtil;
import cn.sijay.biu.system.entity.SystemDictData;
import cn.sijay.biu.system.repository.SystemDictDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SystemDictDataService
 * 系统字典数据逻辑处理层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemDictDataService implements BaseService {
    private final SystemDictDataRepository systemDictDataRepository;

    /**
     * 查询系统字典数据
     *
     * @param id 主键
     * @return 系统字典数据
     */
    public SystemDictData getById(Long id) {
        return systemDictDataRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统字典数据列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统字典数据分页列表
     */
    public Page<SystemDictData> page(SystemDictData entity, PageQuery pageQuery) {
        return systemDictDataRepository.findAll(buildSpecification(entity), pageQuery.build());
    }

    /**
     * 查询符合条件的系统字典数据列表
     *
     * @param entity 查询条件
     * @return 系统字典数据列表
     */
    public List<SystemDictData> list(SystemDictData entity) {
        return systemDictDataRepository.findAll(buildSpecification(entity));
    }

    /**
     * 保存系统字典数据
     *
     * @param entity 系统字典数据
     */
    public void save(SystemDictData entity) {
        validate(entity);
        systemDictDataRepository.save(entity);
    }

    /**
     * 批量保存系统字典数据
     *
     * @param list 系统字典数据集合
     */
    public void save(List<SystemDictData> list) {
        for (SystemDictData entity : list) {
            validate(entity);
        }
        systemDictDataRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统系统字典数据
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        systemDictDataRepository.deleteAllById(ids);
    }

    /**
     * 导入数据
     *
     * @param list 导入的数据
     */
    public void importData(List<SystemDictData> list) {
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
     * @param entity 系统字典数据
     */
    private void validate(SystemDictData entity) {
    }

    private Specification<SystemDictData> buildSpecification(SystemDictData entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        return Specification.allOf(
                equal("dictId", entity.getDictId()),
                like("label", entity.getLabel())
        );
    }
}
