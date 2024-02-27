package cn.sijay.suap.sys.mapper;

import cn.sijay.suap.sys.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * <em>SysUserMapper 登录用户数据层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
