package cn.sijay.bun.gen.properties;

import cn.sijay.bun.gen.enums.GenType;
import cn.sijay.bun.gen.enums.TemplateType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <strong>GenProperties</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-08
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "gen")
public class GenProperties {
    private String packageName;
    private TemplateType templateType;
    private String author;
    private GenType genType;
    private String genPath;
}
