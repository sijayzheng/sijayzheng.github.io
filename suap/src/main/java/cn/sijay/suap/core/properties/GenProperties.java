package cn.sijay.suap.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <em>GenProperties</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/12 9:45
 */
@Data
@Component
@ConfigurationProperties(prefix = "suap.gen")
public class GenProperties {
    /**
     * 代码生成路径
     */
    private String path;
    /**
     * 代码生成作者
     */
    private String author;
    /**
     * 模板类型
     */
    private String template;
    /**
     * 包路径
     */
    private String packageName;
    /**
     * 生成方式
     */
    private String genType;
}
