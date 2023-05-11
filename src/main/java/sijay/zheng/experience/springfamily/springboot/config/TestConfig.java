package sijay.zheng.experience.springfamily.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Sijay
 */
@Data
@Component
@ConfigurationProperties(prefix = "testconfig")
public class TestConfig {
    private String[] users;
    private String testa;
    private String testb;
}