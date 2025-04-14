package cn.sijay.biu.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * CommonConfig
 *
 * @author Sijay
 * @since 2025-02-17
 */

@Setter
@Getter
@Component
@ConfigurationProperties("config")
public class CommonProperties {

    private UserConfig user;
    private CaptchaConfig captcha;
    private SseConfig sse;
    private XssConfig xss;

    @Setter
    @Getter
    public static class UserConfig {
        private PasswordConfig password;
    }

    @Setter
    @Getter
    public static class PasswordConfig {
        private int maxRetryCount = 5;
        private long lockTime = 10;
    }

    @Setter
    @Getter
    public static class CaptchaConfig {
        private boolean enable = true;
        private int expire = 30;
    }

    @Setter
    @Getter
    public static class SseConfig {
        private boolean enabled = true;
        private String path = "/resource/sse";
    }

    @Setter
    @Getter
    public static class XssConfig {
        /**
         * Xss开关
         */
        private Boolean enabled;

        /**
         * 排除路径
         */
        private List<String> excludeUrls = new ArrayList<>();
    }

}
