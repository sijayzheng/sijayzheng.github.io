package cn.sijay.suap.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <em>ConfigProperties</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/8 15:55
 */
@Data
@Component
@ConfigurationProperties(prefix = "suap.config")
public class ConfigProperties {
    private Integer passwordMaxRetryCount;
    private Integer passwordLockTime;
    private String fileStorage;
    private String tempFolder;
    private Integer tokenTimeout;
}
