package cn.sijay.suap.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * <em>LoginResp</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/2/6 11:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResp {
    private String accessToken;
    private long expireIn;
}
