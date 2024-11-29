package cn.sijay.suap.core.entity;

import lombok.Data;

/**
 * <strong>LoginReq</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Data
public class LoginParam {
    private Long module;
    private String username;
    private String password;
}
