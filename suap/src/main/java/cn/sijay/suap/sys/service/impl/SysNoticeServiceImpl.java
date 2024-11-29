package cn.sijay.suap.sys.service.impl;

import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.utils.ExcelUtil;
import cn.sijay.suap.core.utils.ObjectUtil;
import cn.sijay.suap.sys.entity.SysNotice;
import cn.sijay.suap.sys.repository.SysNoticeRepository;
import cn.sijay.suap.sys.service.ISysNoticeService;
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
 * <strong>SysNoticeServiceImpl</strong>
 * <p>
 * 通知公告服务层实现类
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysNoticeServiceImpl implements ISysNoticeService {
    private final SysNoticeRepository repository;

    /**
     * 分页查询
     */
    @Override
    public Page<SysNotice> page(SysNotice sysNotice, PageQuery pageQuery) {
        return repository.findAll(buildSpecification(sysNotice), pageQuery.build());
    }

    /**
     * 根据条件查询所有数据
     */
    @Override
    public List<SysNotice> list(SysNotice sysNotice) {
        return repository.findAll(buildSpecification(sysNotice));
    }

    /**
     * 根据id查询数据
     */
    @Override
    public SysNotice getById(Long id) {
        return repository.getReferenceById(id);
    }

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(SysNotice sysNotice) {
        repository.save(sysNotice);
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
        repository.saveAll(ExcelUtil.read(file, SysNotice.class));
    }

    private Specification<SysNotice> buildSpecification(SysNotice sysNotice) {
        if (sysNotice == null) {
            return null;
        }
        return (root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            if (ObjectUtil.isNotNull(sysNotice.getTitle())) {
                list.add(builder.like(root.get("title"), "%" + sysNotice.getTitle() + "%"));
            }
            if (ObjectUtil.isNotNull(sysNotice.getType())) {
                list.add(builder.like(root.get("type"), "%" + sysNotice.getType() + "%"));
            }
            if (ObjectUtil.isNotNull(sysNotice.getStatus())) {
                list.add(builder.like(root.get("status"), "%" + sysNotice.getStatus() + "%"));
            }
            return builder.and(list.toArray(Predicate[]::new));
        };
    }

}
