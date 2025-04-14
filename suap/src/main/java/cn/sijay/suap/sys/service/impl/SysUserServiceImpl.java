package cn.sijay.suap.sys.service.impl;

import cn.sijay.suap.core.base.BaseException;
import cn.sijay.suap.core.constant.ExceptionConstant;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.utils.ExcelUtil;
import cn.sijay.suap.core.utils.LoginHelper;
import cn.sijay.suap.core.utils.ObjectUtil;
import cn.sijay.suap.core.utils.StringUtil;
import cn.sijay.suap.sys.entity.SysMenu;
import cn.sijay.suap.sys.entity.SysRole;
import cn.sijay.suap.sys.entity.SysUser;
import cn.sijay.suap.sys.repository.SysUserRepository;
import cn.sijay.suap.sys.service.ISysMenuService;
import cn.sijay.suap.sys.service.ISysRoleService;
import cn.sijay.suap.sys.service.ISysUserService;
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
 * <strong>SysUserServiceImpl</strong>
 * <p>
 * 用户服务层实现类
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl implements ISysUserService {
    private final SysUserRepository repository;
    private final ISysRoleService roleService;
    private final ISysMenuService menuService;

    /**
     * 分页查询
     */
    @Override
    public Page<SysUser> page(SysUser sysUser, PageQuery pageQuery) {
        return repository.findAll(buildSpecification(sysUser), pageQuery.build());
    }

    /**
     * 根据条件查询所有数据
     */
    @Override
    public List<SysUser> list(SysUser sysUser) {
        return repository.findAll(buildSpecification(sysUser));
    }

    /**
     * 根据id查询数据
     */
    @Override
    public SysUser getById(Long id) {
        return repository.getReferenceById(id);
    }

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(SysUser sysUser) {
        checkUnique(sysUser);
        repository.save(sysUser);
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
        repository.saveAll(ExcelUtil.read(file, SysUser.class));
    }

    @Override
    public SysUser getByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public SysUser getUserInfo() {
        SysUser user = repository.findById(LoginHelper.getUserId())
                                 .orElseThrow(() -> new BaseException(ExceptionConstant.NO_LOGIN_ERROR));
        List<String> list = menuService.findAllById(roleService.findAllById(user.getRoles())
                                                               .parallelStream()
                                                               .map(SysRole::getMenus)
                                                               .flatMap(List::stream)
                                                               .toList())
                                       .parallelStream()
                                       .map(SysMenu::getPerms)
                                       .filter(StringUtil::isNotBlank)
                                       .distinct()
                                       .toList();
        user.setPermissions(list);
        return user;
    }

    /**
     * 唯一性校验
     */
    private void checkUnique(SysUser sysUser) {
        if (repository.existsByUsername(sysUser.getUsername()) && !repository.existsByUsernameAndId(sysUser.getUsername(), sysUser.getId())) {
            throw new BaseException(ExceptionConstant.VALIDATE_UNIQUE_ERROR, "username", sysUser.getUsername());
        }
        if (repository.existsByPhone(sysUser.getPhone()) && !repository.existsByPhoneAndId(sysUser.getPhone(), sysUser.getId())) {
            throw new BaseException(ExceptionConstant.VALIDATE_UNIQUE_ERROR, "phone", sysUser.getPhone());
        }
    }

    private Specification<SysUser> buildSpecification(SysUser sysUser) {
        if (sysUser == null) {
            return null;
        }
        return (root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            if (ObjectUtil.isNotNull(sysUser.getUsername())) {
                list.add(builder.like(root.get("username"), "%" + sysUser.getUsername() + "%"));
            }
            if (ObjectUtil.isNotNull(sysUser.getName())) {
                list.add(builder.like(root.get("name"), "%" + sysUser.getName() + "%"));
            }
            if (ObjectUtil.isNotNull(sysUser.getPhone())) {
                list.add(builder.like(root.get("phone"), "%" + sysUser.getPhone() + "%"));
            }
            if (ObjectUtil.isNotNull(sysUser.getDeptId())) {
                list.add(builder.equal(root.get("deptId"), sysUser.getDeptId()));
            }
            if (ObjectUtil.isNotNull(sysUser.getEnable())) {
                list.add(builder.equal(root.get("enable"), sysUser.getEnable()));
            }
            return builder.and(list.toArray(Predicate[]::new));
        };
    }

}
