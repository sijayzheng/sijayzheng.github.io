package cn.sijay.bun.system.service;

import cn.sijay.bun.common.entity.PageQuery;
import cn.sijay.bun.common.entity.ResponseResult;
import cn.sijay.bun.common.util.Spec;
import cn.sijay.bun.system.entity.SystemUser;
import cn.sijay.bun.system.repository.SystemUserRepository;
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
 * <strong>SystemUserService</strong>
 * <p>
 * 系统用户
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SystemUserService {
    private final SystemUserRepository systemUserRepository;

    /**
     * 查询系统用户
     *
     * @param id 主键
     * @return 系统用户
     */
    public SystemUser findById(Long id) {
        return systemUserRepository.getReferenceById(id);
    }

    /**
     * 分页查询系统用户列表
     *
     * @param entity    查询条件
     * @param pageQuery 分页参数
     * @return 系统用户分页列表
     */
    public ResponseResult<SystemUser> page(SystemUser entity, PageQuery pageQuery) {
        Specification<SystemUser> specification = buildSpecification(entity);
        Page<SystemUser> page = systemUserRepository.findAll(specification, pageQuery.build());
        return ResponseResult.of(page);
    }

    /**
     * 查询符合条件的系统用户列表
     *
     * @param entity 查询条件
     * @return 系统用户列表
     */
    public List<SystemUser> list(SystemUser entity) {
        Specification<SystemUser> specification = buildSpecification(entity);
        return systemUserRepository.findAll(specification);
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
        List<Specification<SystemUser>> list = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(entity.getDeptId())) {
            list.add(Spec.equal(SystemUser::getDeptId, entity.getDeptId()));
        }
        if (StringUtils.isNotBlank(entity.getUserName())) {
            list.add(Spec.like(SystemUser::getUserName, entity.getUserName()));
        }
        if (StringUtils.isNotBlank(entity.getRealName())) {
            list.add(Spec.like(SystemUser::getRealName, entity.getRealName()));
        }
        if (StringUtils.isNotBlank(entity.getEmail())) {
            list.add(Spec.like(SystemUser::getEmail, entity.getEmail()));
        }
        if (StringUtils.isNotBlank(entity.getPhone())) {
            list.add(Spec.like(SystemUser::getPhone, entity.getPhone()));
        }
//        if(StringUtils.isNotBlank(entity.getGender())) {
//            list.add(Spec.like(SystemUser::getGender, entity.getGender()));
//        }
        if (ObjectUtils.isNotEmpty(entity.getAvatar())) {
            list.add(Spec.equal(SystemUser::getAvatar, entity.getAvatar()));
        }
        if (StringUtils.isNotBlank(entity.getPassword())) {
            list.add(Spec.like(SystemUser::getPassword, entity.getPassword()));
        }
        if (ObjectUtils.isNotEmpty(entity.getEnable())) {
            list.add(Spec.equal(SystemUser::getEnable, entity.getEnable()));
        }
        if (ObjectUtils.isNotEmpty(entity.getPwdChanged())) {
            list.add(Spec.equal(SystemUser::getPwdChanged, entity.getPwdChanged()));
        }
        return Specification.allOf(list);
    }
}
