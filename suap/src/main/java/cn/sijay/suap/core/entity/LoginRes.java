package cn.sijay.suap.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <strong>LoginRes</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRes {
    private String accessToken;
    private long expireIn;
}
