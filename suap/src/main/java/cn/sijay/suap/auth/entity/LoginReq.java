package cn.sijay.suap.auth.entity;

import lombok.Data;

/**
 * <p>
 * <em>LoginReq</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/2/6 11:18
 */
@Data
public class LoginReq {
    private String username;
    private String password;
}
