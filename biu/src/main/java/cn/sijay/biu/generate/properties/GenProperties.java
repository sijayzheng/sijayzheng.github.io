package cn.sijay.biu.generate.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * GenProperties
 *
 * @author Sijay
 * @since 2025-02-28
 */
@Data
@Component
@ConfigurationProperties(prefix = "gen")
public class GenProperties {
    // 作者
    private String author;
    // 包名
    private String packageName;
}
