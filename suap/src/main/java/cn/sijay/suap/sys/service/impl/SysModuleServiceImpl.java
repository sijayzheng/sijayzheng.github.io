package cn.sijay.suap.sys.service.impl;

import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.utils.ExcelUtil;
import cn.sijay.suap.core.utils.ObjectUtil;
import cn.sijay.suap.sys.entity.SysModule;
import cn.sijay.suap.sys.repository.SysModuleRepository;
import cn.sijay.suap.sys.service.ISysModuleService;
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
 * <strong>SysModuleServiceImpl</strong>
 * <p>
 * 模块服务层实现类
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysModuleServiceImpl implements ISysModuleService {
    private final SysModuleRepository repository;

    /**
     * 分页查询
     */
    @Override
    public Page<SysModule> page(SysModule sysModule, PageQuery pageQuery) {
        return repository.findAll(buildSpecification(sysModule), pageQuery.build());
    }

    /**
     * 根据条件查询所有数据
     */
    @Override
    public List<SysModule> list(SysModule sysModule) {
        return repository.findAll(buildSpecification(sysModule));
    }

    /**
     * 根据id查询数据
     */
    @Override
    public SysModule getById(Long id) {
        return repository.getReferenceById(id);
    }

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(SysModule sysModule) {
        repository.save(sysModule);
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
        repository.saveAll(ExcelUtil.read(file, SysModule.class));
    }

    private Specification<SysModule> buildSpecification(SysModule sysModule) {
        if (sysModule == null) {
            return null;
        }
        return (root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            if (ObjectUtil.isNotNull(sysModule.getName())) {
                list.add(builder.like(root.get("name"), "%" + sysModule.getName() + "%"));
            }
            if (ObjectUtil.isNotNull(sysModule.getEnabled())) {
                list.add(builder.equal(root.get("enabled"), sysModule.getEnabled()));
            }
            return builder.and(list.toArray(Predicate[]::new));
        };
    }

}
