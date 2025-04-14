package cn.sijay.bun.auth.dto;

import lombok.Data;

/**
 * <strong>LoginParam</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-12-03
 */
@Data
public class LoginParam {
    private Long module;
    private String username;
    private String password;
    private String code;
    private String uuid;
}
