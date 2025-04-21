package cn.sijay.biu.auth.entity;

import cn.sijay.biu.core.entity.RoleDto;
import cn.sijay.biu.core.exception.ServiceException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LoginUser
 *
 * @author Sijay
 * @since 2025-02-18
 */
@Data
@NoArgsConstructor
public class LoginUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 部门
     */
    private Long deptId;
    private String deptName;

    /**
     * 账号
     */
    private String username;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 菜单权限
     */
    private Set<String> menuPermission = new HashSet<>();

    /**
     * 角色权限
     */
    private Set<String> rolePermission = new HashSet<>();

    /**
     * 角色对象
     */
    private List<RoleDto> roles = new ArrayList<>();
    /**
     * 数据权限 当前角色ID
     */
    private Long roleId;

    /**
     * 获取登录id
     */
    public Long getLoginId() {
        if (id == null) {
            throw new ServiceException("用户ID为空");
        }
        return id;
    }
}
