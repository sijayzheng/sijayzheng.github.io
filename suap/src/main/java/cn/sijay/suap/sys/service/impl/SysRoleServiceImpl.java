package cn.sijay.suap.sys.service.impl;

import cn.sijay.suap.core.base.BaseException;
import cn.sijay.suap.core.constant.ExceptionConstant;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.utils.ExcelUtil;
import cn.sijay.suap.core.utils.ObjectUtil;
import cn.sijay.suap.sys.entity.SysRole;
import cn.sijay.suap.sys.repository.SysRoleRepository;
import cn.sijay.suap.sys.service.ISysRoleService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>SysRoleServiceImpl</strong>
 * <p>
 * 角色服务层实现类
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysRoleServiceImpl implements ISysRoleService {
    private final SysRoleRepository repository;

    /**
     * 分页查询
     */
    @Override
    public Page<SysRole> page(SysRole sysRole, PageQuery pageQuery) {
        return repository.findAll(buildSpecification(sysRole), pageQuery.build());
    }

    /**
     * 根据条件查询所有数据
     */
    @Override
    public List<SysRole> list(SysRole sysRole) {
        return repository.findAll(buildSpecification(sysRole));
    }

    /**
     * 根据id查询数据
     */
    @Override
    public SysRole getById(Long id) {
        return repository.getReferenceById(id);
    }

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(SysRole sysRole) {
        checkUnique(sysRole);
        repository.save(sysRole);
    }

    /**
     * 删除
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void remove(List<Long> ids) {
        repository.deleteAllByIdInBatch(ids);
    }

    /**
     * 导入
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importData(MultipartFile file) {
        repository.saveAll(ExcelUtil.read(file, SysRole.class));
    }

    @Override
    public List<SysRole> findAllById(List<Long> roleIds) {
        return repository.findAllById(roleIds);
    }

    /**
     * 唯一性校验
     */
    private void checkUnique(SysRole sysRole) {
        if (repository.existsByCode(sysRole.getCode()) && !repository.existsByCodeAndId(sysRole.getCode(), sysRole.getId())) {
            throw new BaseException(ExceptionConstant.VALIDATE_UNIQUE_ERROR, "code", sysRole.getCode());
        }
    }

    private Specification<SysRole> buildSpecification(SysRole sysRole) {
        if (sysRole == null) {
            return null;
        }
        return (root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            if (ObjectUtil.isNotNull(sysRole.getName())) {
                list.add(builder.like(root.get("name"), "%" + sysRole.getName() + "%"));
            }
            if (ObjectUtil.isNotNull(sysRole.getCode())) {
                list.add(builder.like(root.get("code"), "%" + sysRole.getCode() + "%"));
            }
            if (ObjectUtil.isNotNull(sysRole.getEnabled())) {
                list.add(builder.equal(root.get("enabled"), sysRole.getEnabled()));
            }
            return builder.and(list.toArray(Predicate[]::new));
        };
    }

}
