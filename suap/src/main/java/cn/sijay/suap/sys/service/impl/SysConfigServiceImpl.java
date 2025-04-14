package cn.sijay.suap.sys.service.impl;

import cn.sijay.suap.core.base.BaseException;
import cn.sijay.suap.core.constant.ExceptionConstant;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.utils.ExcelUtil;
import cn.sijay.suap.core.utils.ObjectUtil;
import cn.sijay.suap.sys.entity.SysConfig;
import cn.sijay.suap.sys.repository.SysConfigRepository;
import cn.sijay.suap.sys.service.ISysConfigService;
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
 * <strong>SysConfigServiceImpl</strong>
 * <p>
 * 系统配置服务层实现类
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysConfigServiceImpl implements ISysConfigService {
    private final SysConfigRepository repository;

    /**
     * 分页查询
     */
    @Override
    public Page<SysConfig> page(SysConfig sysConfig, PageQuery pageQuery) {
        return repository.findAll(buildSpecification(sysConfig), pageQuery.build());
    }

    /**
     * 根据条件查询所有数据
     */
    @Override
    public List<SysConfig> list(SysConfig sysConfig) {
        return repository.findAll(buildSpecification(sysConfig));
    }

    /**
     * 根据id查询数据
     */
    @Override
    public SysConfig getById(Long id) {
        return repository.getReferenceById(id);
    }

    /**
     * 根据code查询数据
     *
     * @param code
     */
    @Override
    public SysConfig getByCode(String code) {
        return repository.findByCode(code);
    }

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(SysConfig sysConfig) {
        checkUnique(sysConfig);
        repository.save(sysConfig);
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
        repository.saveAll(ExcelUtil.read(file, SysConfig.class));
    }

    @Override
    public void resetConfigCache() {

    }

    /**
     * 唯一性校验
     */
    private void checkUnique(SysConfig sysConfig) {
        if (repository.existsByCode(sysConfig.getCode()) && !repository.existsByCodeAndId(sysConfig.getCode(), sysConfig.getId())) {
            throw new BaseException(ExceptionConstant.VALIDATE_UNIQUE_ERROR, "code", sysConfig.getCode());
        }
    }

    private Specification<SysConfig> buildSpecification(SysConfig sysConfig) {
        if (sysConfig == null) {
            return null;
        }
        return (root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            if (ObjectUtil.isNotNull(sysConfig.getName())) {
                list.add(builder.like(root.get("name"), "%" + sysConfig.getName() + "%"));
            }
            if (ObjectUtil.isNotNull(sysConfig.getCode())) {
                list.add(builder.like(root.get("code"), "%" + sysConfig.getCode() + "%"));
            }
            return builder.and(list.toArray(Predicate[]::new));
        };
    }

}
