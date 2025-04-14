package cn.sijay.bun.system.dto;

import cn.sijay.bun.system.entity.SystemUser;
import lombok.Data;

import java.util.List;

/**
 * <strong>UserInfo</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-12-19
 */
@Data
public class UserInfo {
    private SystemUser user;
    private List<String> roles;
    private List<String> permissions;
}
