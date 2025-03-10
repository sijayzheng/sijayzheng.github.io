package cn.sijay.bun.auth.dto;

import lombok.Data;

/**
 * <strong>LoginRequest</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-12-04
 */
@Data
public class LoginRequest {
    private String accessToken;
    private Long expireIn;
}
