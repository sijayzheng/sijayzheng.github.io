package cn.sijay.suap.core.config;

import cn.sijay.suap.core.properties.DocsProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.apache.poi.util.StringUtil;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <strong>SpringDocConfig</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@RequiredArgsConstructor
@Configuration
public class SpringDocConfig {
    private final ServerProperties serverProperties;
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

    /**
     * 对已经生成好的 OpenApi 进行自定义操作
     */
    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        String contextPath = serverProperties.getServlet().getContextPath();
        String finalContextPath;
        if (StringUtil.isBlank(contextPath) || "/".equals(contextPath)) {
            finalContextPath = "";
        } else {
            finalContextPath = contextPath;
        }
        // 对所有路径增加前置上下文路径
        return openApi -> {
            Paths oldPaths = openApi.getPaths();
            if (oldPaths instanceof PlusPaths) {
                return;
            }
            PlusPaths newPaths = new PlusPaths();
            oldPaths.forEach((k, v) -> newPaths.addPathItem(finalContextPath + k, v));
            openApi.setPaths(newPaths);
        };
    }

    /**
     * 单独使用一个类便于判断 解决springdoc路径拼接重复问题
     *
     * @author Lion Li
     */
    static class PlusPaths extends Paths {

        public PlusPaths() {
            super();
        }
    }

}


