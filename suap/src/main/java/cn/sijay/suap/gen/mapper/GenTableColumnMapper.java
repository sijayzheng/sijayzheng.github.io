package cn.sijay.suap.gen.mapper;

import cn.sijay.suap.gen.entity.GenTableColumn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * <em>GenTableColumnMapper 列信息数据层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/01/26 10:46
 */
@Mapper
public interface GenTableColumnMapper extends BaseMapper<GenTableColumn> {
    List<GenTableColumn> listTableColumns(String tableName);
}
