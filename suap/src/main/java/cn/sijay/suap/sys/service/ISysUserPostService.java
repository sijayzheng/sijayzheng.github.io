package cn.sijay.suap.sys.service;

import cn.sijay.suap.sys.entity.SysUserPost;

import java.util.List;

/**
 * <p>
 * <em>ISysUserPostService 用户岗位服务层</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
public interface ISysUserPostService {
    List<SysUserPost> list();

    List<SysUserPost> list(SysUserPost sysUserPost);

    List<SysUserPost> listByPostId(Long postId);

    List<SysUserPost> listByUserId(Long userId);

    boolean deleteByPostId(Long postId);

    boolean deleteByUserId(Long userId);

    boolean insert(SysUserPost sysUserPost);

    boolean batchInsert(List<SysUserPost> list);
}
