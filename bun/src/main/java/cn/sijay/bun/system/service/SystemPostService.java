package cn.sijay.bun.system.service;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.Spec;
import cn.sijay.bun.system.entity.SystemPost;
import cn.sijay.bun.system.repository.SystemPostRepository;
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
 * <strong>SystemPostService</strong>
 * <p>
 * 系统岗位
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemPostService {
    private final SystemPostRepository systemPostRepository;

    /**
     * 查询系统岗位
     *
     * @param id 主键
     * @return 系统岗位
     */
    public SystemPost findById(Long id) {
        return systemPostRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统岗位列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统岗位分页列表
     */
    public ResponseResult<SystemPost> page(SystemPost entity, PageQuery pageQuery) {
        Specification<SystemPost> specification = buildSpecification(entity);
        Page<SystemPost> page = systemPostRepository.findAll(specification, pageQuery.build());
        return ResponseResult.of(page);
    }

    /**
     * 查询符合条件的系统岗位列表
     *
     * @param entity 查询条件
     * @return 系统岗位列表
     */
    public List<SystemPost> list(SystemPost entity) {
        Specification<SystemPost> specification = buildSpecification(entity);
        return systemPostRepository.findAll(specification);
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
        List<Specification<SystemPost>> list = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(entity.getDeptId())) {
            list.add(Spec.equal(SystemPost::getDeptId, entity.getDeptId()));
        }
        if (StringUtils.isNotBlank(entity.getCode())) {
            list.add(Spec.like(SystemPost::getCode, entity.getCode()));
        }
        if (StringUtils.isNotBlank(entity.getName())) {
            list.add(Spec.like(SystemPost::getName, entity.getName()));
        }
        if (ObjectUtils.isNotEmpty(entity.getSort())) {
            list.add(Spec.equal(SystemPost::getSort, entity.getSort()));
        }
        if (ObjectUtils.isNotEmpty(entity.getEnable())) {
            list.add(Spec.equal(SystemPost::getEnable, entity.getEnable()));
        }
        return Specification.allOf(list);
    }
}
