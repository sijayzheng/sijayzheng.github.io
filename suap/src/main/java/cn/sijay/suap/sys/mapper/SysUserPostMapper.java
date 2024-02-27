package cn.sijay.suap.sys.mapper;

import cn.sijay.suap.sys.entity.SysUserPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * <em>SysUserPostMapper 用户岗位数据层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Mapper
public interface SysUserPostMapper {
    List<SysUserPost> list(SysUserPost sysUserPost);

    List<SysUserPost> listByPostId(Long postId);

    List<SysUserPost> listByUserId(Long userId);

    int deleteByPostId(Long postId);

    int deleteByUserId(Long userId);

    int insert(SysUserPost sysUserPost);

    int batchInsert(List<SysUserPost> list);
}
