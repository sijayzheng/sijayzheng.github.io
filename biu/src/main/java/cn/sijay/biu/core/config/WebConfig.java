package cn.sijay.biu.core.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.httpauth.basic.SaHttpBasicUtil;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.sijay.biu.core.exception.SseException;
import cn.sijay.biu.core.handler.AllUrlHandler;
import cn.sijay.biu.core.handler.GlobalExceptionHandler;
import cn.sijay.biu.core.interceptor.PlusWebInvokeTimeInterceptor;
import cn.sijay.biu.core.util.ServletUtil;
import cn.sijay.biu.core.util.SpringUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;
import java.util.Objects;

/**
 * WebConfig
 *
 * @author Sijay
 * @since 2025-04-01
 */
@Slf4j
@AutoConfiguration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    /**
     * 跨域配置
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置访问源地址
        config.addAllowedOriginPattern("*");
        // 设置访问源请求头
        config.addAllowedHeader("*");
        // 设置访问源请求方法
        config.addAllowedMethod("*");
        // 有效期 1800秒
        config.setMaxAge(1800L);
        // 添加映射路径，拦截一切请求
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        // 返回新的CorsFilter
        return new CorsFilter(source);
    }

    /**
     * 全局异常处理器
     */
    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    /**
     * 注册sa-token的拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 全局访问性能拦截
        registry.addInterceptor(new PlusWebInvokeTimeInterceptor());
        // 注册路由拦截器，自定义验证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
                    AllUrlHandler allUrlHandler = SpringUtil.getBean(AllUrlHandler.class);
                    // 登录验证 -- 排除多个路径
                    SaRouter
                            // 获取所有的
                            .match(allUrlHandler.getUrls())
                            // 对未排除的路径进行检查
                            .check(() -> {
                                HttpServletRequest request = ServletUtil.getRequest();
                                // 检查是否登录 是否有token
                                try {
                                    StpUtil.checkLogin();
                                } catch (NotLoginException e) {
                                    if (Objects.requireNonNull(request).getRequestURI().contains("sse")) {
                                        throw new SseException(e.getMessage(), e.getCode());
                                    } else {
                                        throw e;
                                    }
                                }

                            });
                }))
                .addPathPatterns("/**")
                // 排除不需要拦截的路径
                .excludePathPatterns("/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/favicon.ico", "/error", "/*/api-docs", "/*/api-docs/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource requestedResource = location.createRelative(resourcePath);
                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource :
                                new ClassPathResource("/templates/index.html");
                    }
                });
    }

    /**
     * 对 actuator 健康检查接口 做账号密码鉴权
     */
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/actuator", "/actuator/**")
                .setAuth(obj -> SaHttpBasicUtil.check("root:root"))
                .setError(e -> SaResult.error(e.getMessage()).setCode(HttpStatus.UNAUTHORIZED.value()));
    }
}