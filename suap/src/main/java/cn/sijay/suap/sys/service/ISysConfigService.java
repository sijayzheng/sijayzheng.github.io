package cn.sijay.suap.sys.service;

import cn.sijay.suap.core.base.IBaseService;
import cn.sijay.suap.core.enums.ExceptionEnum;
import cn.sijay.suap.core.exception.BaseException;
import cn.sijay.suap.core.util.StringUtil;
import cn.sijay.suap.sys.entity.SysConfig;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * <em>ISysConfigService 系统配置服务层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
public interface ISysConfigService extends IService<SysConfig>, IBaseService<SysConfig> {

    /**
     * 唯一性校验
     *
     * @param entity 待校验的对象
     * @return true-校验通过，false-校验不通过
     */
    default boolean checkUnique(SysConfig entity) {
        if (StringUtil.isNotBlank(entity.getCode())) {
            QueryWrapper<SysConfig> wrapper = new QueryWrapper<>();
            wrapper.eq("code", entity.getCode());
            if (ObjectUtils.isNotEmpty(entity.getId())) {
                wrapper.ne("id", entity.getId());
            }
            if (exists(wrapper)) {
                throw new BaseException(ExceptionEnum.VALIDATE_UNIQUE_ERROR, "code", entity.getCode());
            }
        }
        return true;
    }

    /**
     * 校验是否存在
     *
     * @param id 待校验的对象ID
     * @return true-存在，false-不存在
     */
    default boolean exists(Long id) {
        return exists(new LambdaQueryWrapper<SysConfig>().eq(SysConfig::getId, id));
    }

    /**
     * 构建查询条件构造器
     *
     * @param entity 实体类
     * @return 查询条件构造器
     */
    default LambdaQueryWrapper<SysConfig> buildWrapper(SysConfig entity) {
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtil.isNotBlank(entity.getName()), SysConfig::getName, entity.getName());
        wrapper.like(StringUtil.isNotBlank(entity.getCode()), SysConfig::getCode, entity.getCode());
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
