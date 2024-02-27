package cn.sijay.suap.sys.service.impl;

import cn.sijay.suap.core.entity.Ids;
import cn.sijay.suap.core.entity.Option;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.entity.TreeNode;
import cn.sijay.suap.core.enums.ExceptionEnum;
import cn.sijay.suap.core.exception.BaseException;
import cn.sijay.suap.core.util.BeanUtil;
import cn.sijay.suap.core.util.ExcelUtil;
import cn.sijay.suap.core.util.StringUtil;
import cn.sijay.suap.core.util.TreeUtil;
import cn.sijay.suap.sys.entity.SysDept;
import cn.sijay.suap.sys.mapper.SysDeptMapper;
import cn.sijay.suap.sys.service.ISysDeptService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * <em>SysDeptService 部门信息服务层实现类</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    private final SysDeptMapper sysDeptMapper;

    /**
     * 分页查询
     *
     * @param entity    实体类
     * @param pageQuery 分页查询条件
     * @return 分页查询结果
     */
    @Override
    public Page<SysDept> page(SysDept entity, PageQuery pageQuery) {
        return page(pageQuery.build(), buildQueryWrapper(entity, SysDept.class));
    }

    /**
     * 新增
     *
     * @param entity 实体类
     * @return 新增结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean add(SysDept entity) {
        if (checkUnique(entity)) {
            return save(entity);
        }
        return false;
    }

    /**
     * 更新
     *
     * @param entity 实体类
     * @return 更新结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean update(SysDept entity) {
        if (checkUnique(entity) && exists(entity.getId())) {
            SysDept sysDept = getById(entity.getId());
            BeanUtil.copyEntityProperties(entity, sysDept);
            return updateById(sysDept);
        }
        return false;
    }

    /**
     * 删除
     *
     * @param ids id
     * @return 删除结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean remove(Ids<Long> ids) {
        if (ObjectUtils.isNotEmpty(ids.getId())) {
            if (exists(ids.getId())) {
                return removeById(ids.getId());
            } else {
                throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, ids.getId());
            }
        } else if (CollectionUtils.isNotEmpty(ids.getIds())) {
            List<Long> list = ids.getIds().stream().filter(id -> !exists(id)).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(list)) {
                return removeByIds(ids.getIds());
            } else {
                throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, list);
            }
        } else {
            throw new BaseException(ExceptionEnum.REQUEST_PARAM_ERROR);
        }
    }

    /**
     * 导入
     *
     * @param file 要导入的excel文件。
     * @return 是否导入成功
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String importData(MultipartFile file) {
        int successCount = 0;
        int failCount = 0;
        for (SysDept entity : ExcelUtil.read(file, SysDept.class)) {
            if (add(entity)) {
                successCount++;
            } else {
                failCount++;
            }
        }
        log.info("导入成功{}条，失败{}条", successCount, failCount);
        return StringUtil.format("导入成功{}条，失败{}条", successCount, failCount);
    }

    @Override
    public List<Option<Long>> getOptionData() {
        List<TreeNode<Long>> list = TreeUtil.buildTree(list(), 0L, (item, treeNode) -> {
            treeNode.setId(item.getId()).setPid(item.getParentId()).setName(item.getName()).setSort(item.getSort());
        });
        return TreeUtil.convertOptionTree(list);
    }

}
