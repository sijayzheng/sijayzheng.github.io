package cn.sijay.suap.gen.service;

import cn.sijay.suap.gen.entity.GenTableColumn;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * <em>IGenTableColumnService 列信息服务层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/23 14:37
 */
public interface IGenTableColumnService extends IService<GenTableColumn> {

    boolean removeBatchByTableId(Long genTableId);

    List<GenTableColumn> listTableColumns(String tableName);

    List<GenTableColumn> listColumnByTableId(Long tableId);
}
