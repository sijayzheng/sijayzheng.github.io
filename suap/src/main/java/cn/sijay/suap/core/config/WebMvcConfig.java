package cn.sijay.suap.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <strong>WebMvcConfig</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-09-13
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
//                .addPathPatterns("/**")
//                .excludePathPatterns("/auth/login", "/swagger-ui/index.html", "/v3/docs");
    }
}
