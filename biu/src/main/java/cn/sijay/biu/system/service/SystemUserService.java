package cn.sijay.biu.system.service;

import cn.sijay.biu.core.base.BaseService;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.util.CollectionUtil;
import cn.sijay.biu.system.entity.SystemUser;
import cn.sijay.biu.system.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SystemUserService
 * 系统用户逻辑处理层
 *
 * @author Sijay
 * @since 2025-04-09
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemUserService implements BaseService {
    private final SystemUserRepository systemUserRepository;

    /**
     * 查询系统用户
     *
     * @param id 主键
     * @return 系统用户
     */
    public SystemUser getById(Long id) {
        return systemUserRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统用户列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统用户分页列表
     */
    public Page<SystemUser> page(SystemUser entity, PageQuery pageQuery) {
        return systemUserRepository.findAll(buildSpecification(entity), pageQuery.build());
    }

    /**
     * 查询符合条件的系统用户列表
     *
     * @param entity 查询条件
     * @return 系统用户列表
     */
    public List<SystemUser> list(SystemUser entity) {
        return systemUserRepository.findAll(buildSpecification(entity));
    }

    /**
     * 保存系统用户
     *
     * @param entity 系统用户
     */
    public void save(SystemUser entity) {
        validate(entity);
        systemUserRepository.save(entity);
    }

    /**
     * 批量保存系统用户
     *
     * @param list 系统用户集合
     */
    public void save(List<SystemUser> list) {
        for (SystemUser entity : list) {
            validate(entity);
        }
        systemUserRepository.saveAll(list);
    }

    /**
     * 校验并批量删除系统系统用户
     *
     * @param ids 待删除的主键集合
     */
    public void remove(List<Long> ids) {
        systemUserRepository.deleteAllById(ids);
    }

    /**
     * 导入数据
     *
     * @param list 导入的数据
     */
    public void importData(List<SystemUser> list) {
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
     * @param entity 系统用户
     */
    private void validate(SystemUser entity) {
    }

    private Specification<SystemUser> buildSpecification(SystemUser entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return Specification.allOf();
        }
        return Specification.allOf(
                like("username", entity.getUsername()),
                like("realName", entity.getRealName()),
                like("phone", entity.getPhone())
        );
    }
}
