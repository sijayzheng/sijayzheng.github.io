package cn.sijay.suap.sys.service.impl;

import cn.sijay.suap.sys.entity.SysUserPost;
import cn.sijay.suap.sys.mapper.SysUserPostMapper;
import cn.sijay.suap.sys.service.ISysUserPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * <em>SysUserPostService 用户岗位服务层实现类</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysUserPostServiceImpl implements ISysUserPostService {

    private final SysUserPostMapper sysUserPostMapper;

    @Override
    public List<SysUserPost> list() {
        return sysUserPostMapper.list(new SysUserPost());
    }

    @Override
    public List<SysUserPost> list(SysUserPost sysUserRole) {
        return sysUserPostMapper.list(sysUserRole);
    }

    @Override
    public List<SysUserPost> listByPostId(Long postId) {
        return sysUserPostMapper.listByPostId(postId);
    }

    @Override
    public List<SysUserPost> listByUserId(Long userId) {
        return sysUserPostMapper.listByUserId(userId);
    }

    @Override
    public boolean deleteByPostId(Long postId) {
        return toBool(sysUserPostMapper.deleteByPostId(postId));
    }

    @Override
    public boolean deleteByUserId(Long userId) {
        return toBool(sysUserPostMapper.deleteByUserId(userId));
    }

    @Override
    public boolean insert(SysUserPost sysUserRole) {
        return toBool(sysUserPostMapper.insert(sysUserRole));
    }

    @Override
    public boolean batchInsert(List<SysUserPost> list) {
        return toBool(sysUserPostMapper.batchInsert(list));
    }

    boolean toBool(Integer result) {
        return null != result && result >= 1;
    }
}
