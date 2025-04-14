package cn.sijay.biu.system.service;

import cn.sijay.biu.core.base.BaseService;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.util.CollectionUtil;
import cn.sijay.biu.system.entity.SystemPost;
import cn.sijay.biu.system.repository.SystemPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SystemPostService
 * 系统岗位逻辑处理层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemPostService implements BaseService {
    private final SystemPostRepository systemPostRepository;

    /**
     * 查询系统岗位
     *
     * @param id 主键
     * @return 系统岗位
     */
    public SystemPost getById(Long id) {
        return systemPostRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统岗位列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统岗位分页列表
     */
    public Page<SystemPost> page(SystemPost entity, PageQuery pageQuery) {
        return systemPostRepository.findAll(buildSpecification(entity), pageQuery.build());
    }

    /**
     * 查询符合条件的系统岗位列表
     *
     * @param entity 查询条件
     * @return 系统岗位列表
     */
    public List<SystemPost> list(SystemPost entity) {
        return systemPostRepository.findAll(buildSpecification(entity));
    }

    /**
     * 保存系统岗位
     *
     * @param entity 系统岗位
     */
    public void save(SystemPost entity) {
        validate(entity);
        systemPostRepository.save(entity);
    }

    /**
     * 批量保存系统岗位
     *
     * @param list 系统岗位集合
     */
    public void save(List<SystemPost> list) {
        for (SystemPost entity : list) {
            validate(entity);
        }
        systemPostRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统系统岗位
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        systemPostRepository.deleteAllById(ids);
    }

    /**
     * 导入数据
     *
     * @param list 导入的数据
     */
    public void importData(List<SystemPost> list) {
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
     * @param entity 系统岗位
     */
    private void validate(SystemPost entity) {
    }

    private Specification<SystemPost> buildSpecification(SystemPost entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        return Specification.allOf(
                like("name", entity.getName()),
                like("code", entity.getCode()),
                equal("deptId", entity.getDeptId())
        );
    }
}
