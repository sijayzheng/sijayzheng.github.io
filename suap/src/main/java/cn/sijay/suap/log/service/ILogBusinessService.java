package cn.sijay.suap.log.service;

import cn.sijay.suap.core.base.IBaseService;
import cn.sijay.suap.log.entity.LogBusiness;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * <em>ILogBusinessService 业务日志服务层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
public interface ILogBusinessService extends IService<LogBusiness>, IBaseService<LogBusiness> {

    /**
     * 唯一性校验
     *
     * @param entity 待校验的对象
     * @return true-校验通过，false-校验不通过
     */
    default boolean checkUnique(LogBusiness entity) {
        return true;
    }

    /**
     * 校验是否存在
     *
     * @param id 待校验的对象ID
     * @return true-存在，false-不存在
     */
    default boolean exists(Long id) {
        return exists(new LambdaQueryWrapper<LogBusiness>().eq(LogBusiness::getId, id));
    }

    /**
     * 构建查询条件构造器
     *
     * @param entity 实体类
     * @return 查询条件构造器
     */
    default LambdaQueryWrapper<LogBusiness> buildWrapper(LogBusiness entity) {
        LambdaQueryWrapper<LogBusiness> wrapper = new LambdaQueryWrapper<>();
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
