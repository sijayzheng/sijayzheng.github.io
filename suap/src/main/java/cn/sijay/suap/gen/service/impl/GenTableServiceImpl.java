package cn.sijay.suap.gen.service.impl;

import cn.sijay.suap.core.entity.Ids;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.enums.ExceptionEnum;
import cn.sijay.suap.core.exception.BaseException;
import cn.sijay.suap.core.util.BeanUtil;
import cn.sijay.suap.core.util.StringUtil;
import cn.sijay.suap.gen.entity.GenTable;
import cn.sijay.suap.gen.mapper.GenTableMapper;
import cn.sijay.suap.gen.service.IGenTableColumnService;
import cn.sijay.suap.gen.service.IGenTableService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * <em>GenTableService 表信息服务层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/01/26 10:46
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GenTableServiceImpl extends ServiceImpl<GenTableMapper, GenTable> implements IGenTableService {

    private final GenTableMapper genTableMapper;
    private final IGenTableColumnService genTableColumnService;

    /**
     * 分页查询
     *
     * @param entity    实体类
     * @param pageQuery 分页查询条件
     * @return 分页查询结果
     */
    @Override
    public Page<GenTable> page(GenTable entity, PageQuery pageQuery) {
        return page(pageQuery.build(), buildQueryWrapper(entity));
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
                return genTableColumnService.removeBatchByTableId(ids.getId()) && removeById(ids.getId());
            } else {
                throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, ids.getId());
            }
        } else if (CollectionUtils.isNotEmpty(ids.getIds())) {
            List<Long> list = ids.getIds().stream().filter(id -> !exists(id)).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(list)) {
                var b = true;
                for (Long id : list) {
                    b = b && genTableColumnService.removeBatchByTableId(id);
                }
                return b && removeByIds(ids.getIds());
            } else {
                throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, list);
            }
        } else {
            throw new BaseException(ExceptionEnum.REQUEST_PARAM_ERROR);
        }
    }

    @Override
    public List<GenTable> listAllTable() {
        List<String> list = list().stream().map(GenTable::getTableName).toList();
        return baseMapper.listAllTable().stream().filter(item -> !list.contains(item.getTableName()))
                         .peek(item -> {
                             item.setClassName(StringUtil.toUpperCamelCase(item.getTableName()));
                             item.setModule(item.getTableName().split("_")[0]);
                             item.setLowerName(StringUtil.toLowerCamelCase(item.getClassName()));
                         }).collect(Collectors.toList());
    }

    /**
     * 更新
     *
     * @param entity 实体类
     * @return 更新结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean update(GenTable entity) {
        if (exists(entity.getId())) {
            GenTable genTable = getById(entity.getId());
            BeanUtil.copyEntityProperties(entity, genTable);
            return updateById(genTable);
        }
        return false;
    }

    boolean exists(Long id) {
        return exists(new LambdaQueryWrapper<GenTable>().eq(GenTable::getId, id));
    }

    LambdaQueryWrapper<GenTable> buildQueryWrapper(GenTable entity) {
        LambdaQueryWrapper<GenTable> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtil.isNotBlank(entity.getTableName()), GenTable::getTableName, entity.getTableName());
        wrapper.like(StringUtil.isNotBlank(entity.getComment()), GenTable::getComment, entity.getComment());
        return wrapper;
    }
}
