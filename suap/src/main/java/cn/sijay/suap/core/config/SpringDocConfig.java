package cn.sijay.suap.core.config;

import cn.sijay.suap.core.properties.DocsProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <strong>SpringDocConfig</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@RequiredArgsConstructor
@Configuration
public class SpringDocConfig {
    private final DocsProperties docsProperties;

    @Bean
    public OpenAPI openApi() {
        OpenAPI openApi = new OpenAPI();
        // 文档基本信息
        Info info = new Info();
        info.setTitle(docsProperties.getTitle());
        info.setDescription("描述：" + docsProperties.getDescription());
        Contact contact = new Contact();
        contact.setName(docsProperties.getContact());
        info.setContact(contact);
        info.setVersion(docsProperties.getVersion());
        openApi.info(info);
        return openApi;
    }

}


