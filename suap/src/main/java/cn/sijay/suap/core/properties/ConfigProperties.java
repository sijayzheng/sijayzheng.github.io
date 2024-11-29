package cn.sijay.suap.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <strong>ConfigProperties</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Data
@Component
@ConfigurationProperties(prefix = "config")
public class ConfigProperties {
    private Integer passwordMaxRetryCount;
    private Integer passwordLockTime;
    private Integer tokenTimeout;
    private String tempFolder;
    private String fileStorage;
}
