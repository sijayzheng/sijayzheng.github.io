package cn.sijay.suap.sys.service;

import cn.sijay.suap.core.base.IBaseService;
import cn.sijay.suap.core.entity.Option;
import cn.sijay.suap.core.enums.ExceptionEnum;
import cn.sijay.suap.core.exception.BaseException;
import cn.sijay.suap.core.util.StringUtil;
import cn.sijay.suap.sys.entity.SysUser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * <em>ISysUserService 登录用户服务层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
public interface ISysUserService extends IService<SysUser>, IBaseService<SysUser> {

    /**
     * 唯一性校验
     *
     * @param entity 待校验的对象
     * @return true-校验通过，false-校验不通过
     */
    default boolean checkUnique(SysUser entity) {
        if (StringUtil.isNotBlank(entity.getUsername())) {
            QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
            wrapper.eq("username", entity.getUsername());
            if (ObjectUtils.isNotEmpty(entity.getId())) {
                wrapper.ne("id", entity.getId());
            }
            if (exists(wrapper)) {
                throw new BaseException(ExceptionEnum.VALIDATE_UNIQUE_ERROR, "username", entity.getUsername());
            }
        }
        if (StringUtil.isNotBlank(entity.getPhone())) {
            QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
            wrapper.eq("phone", entity.getPhone());
            if (ObjectUtils.isNotEmpty(entity.getId())) {
                wrapper.ne("id", entity.getId());
            }
            if (exists(wrapper)) {
                throw new BaseException(ExceptionEnum.VALIDATE_UNIQUE_ERROR, "phone", entity.getPhone());
            }
        }
        if (StringUtil.isNotBlank(entity.getEmail())) {
            QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
            wrapper.eq("email", entity.getEmail());
            if (ObjectUtils.isNotEmpty(entity.getId())) {
                wrapper.ne("id", entity.getId());
            }
            if (exists(wrapper)) {
                throw new BaseException(ExceptionEnum.VALIDATE_UNIQUE_ERROR, "email", entity.getEmail());
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
        return exists(new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, id));
    }

    /**
     * 构建查询条件构造器
     *
     * @param entity 实体类
     * @return 查询条件构造器
     */
    default LambdaQueryWrapper<SysUser> buildWrapper(SysUser entity) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtil.isNotBlank(entity.getUsername()), SysUser::getUsername, entity.getUsername());
        wrapper.like(StringUtil.isNotBlank(entity.getPhone()), SysUser::getPhone, entity.getPhone());
        wrapper.like(StringUtil.isNotBlank(entity.getEmail()), SysUser::getEmail, entity.getEmail());
        return wrapper;
    }

    /**
     * 导入
     *
     * @param file 要导入的excel文件。
     * @return 是否导入成功
     */
    String importData(MultipartFile file);

    List<Option<Long>> getOptionData();
}
