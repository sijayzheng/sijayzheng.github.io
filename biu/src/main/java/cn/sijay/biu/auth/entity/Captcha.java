package cn.sijay.biu.auth.entity;

import lombok.Data;

/**
 * Captcha
 *
 * @author Sijay
 * @since 2025-02-14
 */
@Data
public class Captcha {
    /**
     * 是否开启验证码
     */
    private Boolean enabled = true;

    private String uuid;

    /**
     * 验证码图片
     */
    private String img;

}
