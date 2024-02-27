package cn.sijay.suap.sys.service;

import cn.sijay.suap.core.base.IBaseService;
import cn.sijay.suap.core.util.StringUtil;
import cn.sijay.suap.sys.entity.SysNotice;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * <em>ISysNoticeService 通知公告服务层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
public interface ISysNoticeService extends IService<SysNotice>, IBaseService<SysNotice> {

    /**
     * 唯一性校验
     *
     * @param entity 待校验的对象
     * @return true-校验通过，false-校验不通过
     */
    default boolean checkUnique(SysNotice entity) {
        return true;
    }

    /**
     * 校验是否存在
     *
     * @param id 待校验的对象ID
     * @return true-存在，false-不存在
     */
    default boolean exists(Long id) {
        return exists(new LambdaQueryWrapper<SysNotice>().eq(SysNotice::getId, id));
    }

    /**
     * 构建查询条件构造器
     *
     * @param entity 实体类
     * @return 查询条件构造器
     */
    default LambdaQueryWrapper<SysNotice> buildWrapper(SysNotice entity) {
        LambdaQueryWrapper<SysNotice> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtil.isNotBlank(entity.getTitle()), SysNotice::getTitle, entity.getTitle());
        wrapper.like(StringUtil.isNotBlank(entity.getContent()), SysNotice::getContent, entity.getContent());
        return wrapper;
    }

    /**
     * 导入
     *
     * @param file 要导入的excel文件。
     * @return 是否导入成功
     */
    String importData(MultipartFile file);

}
