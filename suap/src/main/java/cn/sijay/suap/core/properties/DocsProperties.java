package cn.sijay.suap.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <em>DocsProperties</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/8 14:36
 */
@Data
@Component
@ConfigurationProperties(prefix = "suap.docs")
public class DocsProperties {
    private String title;
    private String description;
    private String contact;
    private String version;
}
