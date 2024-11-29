package cn.sijay.suap.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <strong>DocsProperties</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Data
@Component
@ConfigurationProperties(prefix = "doc")
public class DocsProperties {
    private String title;
    private String description;
    private String contact;
    private String version;
}
