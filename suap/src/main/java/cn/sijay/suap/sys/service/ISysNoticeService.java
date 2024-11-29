package cn.sijay.suap.sys.service;

import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.sys.entity.SysNotice;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>ISysNoticeService</strong>
 * <p>
 * 通知公告服务层
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
public interface ISysNoticeService {
    /**
     * 分页查询
     */
    Page<SysNotice> page(SysNotice sysNotice, PageQuery pageQuery);

    /**
     * 查询所有数据
     */
    List<SysNotice> list(SysNotice sysNotice);

    /**
     * 根据id查询数据
     */
    SysNotice getById(Long id);

    /**
     * 保存，数据库中存在对应主键值时更新，否则新增
     */
    void save(SysNotice sysNotice);

    /**
     * 删除
     */
    void remove(List<Long> ids);

    /**
     * 导入
     */
    void importData(MultipartFile file);

}
