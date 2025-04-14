package cn.sijay.biu.core.helper;

import cn.dev33.satoken.stp.StpInterface;
import cn.sijay.biu.auth.entity.LoginUser;

import java.util.ArrayList;
import java.util.List;

/**
 * sa-token 权限管理实现类
 *
 * @author Sijay
 */
public class SaPermissionImpl implements StpInterface {

    /**
     * 获取菜单权限列表
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        return new ArrayList<>(loginUser.getMenuPermission());
    }

    /**
     * 获取角色权限列表
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        return new ArrayList<>(loginUser.getRolePermission());
    }
}
