package cn.sijay.biu.core.config;

import cn.sijay.biu.core.properties.SpringDocProperties;
import cn.sijay.biu.core.util.StringUtil;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.configuration.SpringDocConfiguration;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Swagger 文档配置
 *
 * @author Sijay
 */
@RequiredArgsConstructor
@AutoConfiguration(before = SpringDocConfiguration.class)
@EnableConfigurationProperties(SpringDocProperties.class)
@ConditionalOnProperty(name = "springdoc.api-docs.enabled", havingValue = "true", matchIfMissing = true)
public class SpringDocConfig {

    private final ServerProperties serverProperties;

    @Bean
    @ConditionalOnMissingBean(OpenAPI.class)
    public OpenAPI openApi(SpringDocProperties properties) {
        OpenAPI openApi = new OpenAPI();
        // 文档基本信息
        SpringDocProperties.InfoProperties infoProperties = properties.getInfo();
        Info info = convertInfo(infoProperties);
        openApi.info(info);
        // 扩展文档信息
        openApi.externalDocs(properties.getExternalDocs());
        openApi.tags(properties.getTags());
        openApi.paths(properties.getPaths());
        openApi.components(properties.getComponents());
        Set<String> keySet = properties.getComponents().getSecuritySchemes().keySet();
        List<SecurityRequirement> list = new ArrayList<>();
        SecurityRequirement securityRequirement = new SecurityRequirement();
        keySet.forEach(securityRequirement::addList);
        list.add(securityRequirement);
        openApi.security(list);

        return openApi;
    }

    private Info convertInfo(SpringDocProperties.InfoProperties infoProperties) {
        Info info = new Info();
        info.setTitle(infoProperties.getTitle());
        info.setDescription(infoProperties.getDescription());
        info.setContact(infoProperties.getContact());
        info.setLicense(infoProperties.getLicense());
        info.setVersion(infoProperties.getVersion());
        return info;
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
     * @author Sijay
     */
    static class PlusPaths extends Paths {

        public PlusPaths() {
            super();
        }
    }

}
