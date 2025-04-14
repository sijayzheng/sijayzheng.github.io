package cn.sijay.biu.system.service;

import cn.sijay.biu.core.base.BaseService;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.util.CollectionUtil;
import cn.sijay.biu.system.entity.SystemUserAvatar;
import cn.sijay.biu.system.repository.SystemUserAvatarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SystemUserAvatarService
 * 用户头像逻辑处理层
 *
 * @author Sijay
 * @since 2025-04-14
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemUserAvatarService implements BaseService {
    private final SystemUserAvatarRepository systemUserAvatarRepository;

    /**
     * 查询用户头像
     *
     * @param id 主键
     * @return 用户头像
     */
    public SystemUserAvatar getById(Long id) {
        return systemUserAvatarRepository.getReferenceById(id);
    }

    /**
     * 分页查询用户头像列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 用户头像分页列表
     */
    public Page<SystemUserAvatar> page(SystemUserAvatar entity, PageQuery pageQuery) {
        return systemUserAvatarRepository.findAll(buildSpecification(entity), pageQuery.build());
    }

    /**
     * 查询符合条件的用户头像列表
     *
     * @param entity 查询条件
     * @return 用户头像列表
     */
    public List<SystemUserAvatar> list(SystemUserAvatar entity) {
        return systemUserAvatarRepository.findAll(buildSpecification(entity));
    }

    /**
     * 保存用户头像
     *
     * @param entity 用户头像
     */
    public void save(SystemUserAvatar entity) {
        validate(entity);
        systemUserAvatarRepository.save(entity);
    }

    /**
     * 批量保存用户头像
     *
     * @param list 用户头像集合
     */
    public void save(List<SystemUserAvatar> list) {
        for (SystemUserAvatar entity : list) {
            validate(entity);
        }
        systemUserAvatarRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统用户头像
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        systemUserAvatarRepository.deleteAllById(ids);
    }

    /**
     * 导入数据
     *
     * @param list 导入的数据
     */
    public void importData(List<SystemUserAvatar> list) {
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
     * @param entity 用户头像
     */
    private void validate(SystemUserAvatar entity) {
    }

    private Specification<SystemUserAvatar> buildSpecification(SystemUserAvatar entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        return Specification.allOf(
        );
    }
}
