package cn.sijay.biu.auth.service;

import cn.sijay.biu.core.constant.UserConstants;
import cn.sijay.biu.core.helper.LoginHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * SystemPermissionService
 *
 * @author Sijay
 * @since 2025-02-24
 */
@RequiredArgsConstructor
@Service
public class SystemPermissionService {

//    private final SystemRoleRepository roleRepository;
//    private final SystemMenuRepository menuRepository;
//    private final SystemUserRepository userRepository;

    /**
     * 获取角色数据权限
     *
     * @param userId 用户id
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(Long userId) {
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (LoginHelper.isSuperAdmin(userId)) {
            roles.add(UserConstants.ADMIN_ROLE_CODE);
        } else {
//            SystemUser systemUser = userRepository.findById(userId).orElse(new SystemUser());
//            roles.addAll(roleRepository.findAllById(systemUser.getRoles())
//                                       .parallelStream().map(SystemRole::getCode).collect(Collectors.toSet()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param userId 用户id
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(Long userId) {
        Set<String> perms = new HashSet<>();
        // 管理员拥有所有权限
        if (LoginHelper.isSuperAdmin(userId)) {
            perms.add("*:*:*");
        } else {
//            SystemUser systemUser = userRepository.findById(userId).orElse(new SystemUser());
//            Set<Long> menuIds = roleRepository.findAllById(systemUser.getRoles())
//                                              .parallelStream()
//                                              .map(SystemRole::getMenus)
//                                              .flatMap(List::stream)
//                                              .collect(Collectors.toSet());
//            perms.addAll(menuRepository.findAllById(menuIds).parallelStream()
//                                       .map(SystemMenu::getPermsCode)
//                                       .collect(Collectors.toSet()));
        }
        return perms;
    }
}
