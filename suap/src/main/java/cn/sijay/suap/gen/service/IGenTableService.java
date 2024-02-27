package cn.sijay.suap.gen.service;

import cn.sijay.suap.core.entity.Ids;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.gen.entity.GenTable;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * <em>IGenTableService 表信息服务层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/23 14:37
 */
public interface IGenTableService extends IService<GenTable> {

    Page<GenTable> page(GenTable entity, PageQuery pageQuery);

    @Transactional(rollbackFor = {Exception.class})
    boolean remove(Ids<Long> ids);

    List<GenTable> listAllTable();

    @Transactional(rollbackFor = {Exception.class})
    boolean update(GenTable entity);
}
