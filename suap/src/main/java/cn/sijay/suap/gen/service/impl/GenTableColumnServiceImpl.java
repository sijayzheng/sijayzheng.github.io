package cn.sijay.suap.gen.service.impl;

import cn.sijay.suap.gen.entity.GenTableColumn;
import cn.sijay.suap.gen.mapper.GenTableColumnMapper;
import cn.sijay.suap.gen.service.IGenTableColumnService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * <em>GenTableColumnService 列信息服务层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/01/26 10:46
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GenTableColumnServiceImpl extends ServiceImpl<GenTableColumnMapper, GenTableColumn> implements IGenTableColumnService {

    @Transactional
    @Override
    public boolean removeBatchByTableId(Long genTableId) {
        List<GenTableColumn> list = listColumnByTableId(genTableId);
        return removeBatchByIds(list);
    }

    @Override
    public List<GenTableColumn> listTableColumns(String tableName) {
        return baseMapper.listTableColumns(tableName);
    }

    @Override
    public List<GenTableColumn> listColumnByTableId(Long tableId) {
        return list(new LambdaQueryWrapper<GenTableColumn>().eq(GenTableColumn::getTableId, tableId)
                                                            .orderByAsc(GenTableColumn::getSort));
    }

}
