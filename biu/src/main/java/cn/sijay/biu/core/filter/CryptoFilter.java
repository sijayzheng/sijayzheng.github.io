package cn.sijay.biu.core.filter;

import cn.sijay.biu.core.annotation.ApiEncrypt;
import cn.sijay.biu.core.exception.ServiceException;
import cn.sijay.biu.core.properties.ApiDecryptProperties;
import cn.sijay.biu.core.util.ObjectUtil;
import cn.sijay.biu.core.util.SpringUtil;
import cn.sijay.biu.core.util.StringUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.util.Objects;

/**
 * Crypto 过滤器
 *
 * @author Sijay
 */
public class CryptoFilter implements Filter {
    private final ApiDecryptProperties properties;

    public CryptoFilter(ApiDecryptProperties properties) {
        this.properties = properties;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        // 获取加密注解
        ApiEncrypt apiEncrypt = this.getApiEncryptAnnotation(servletRequest);
        boolean responseFlag = apiEncrypt != null && apiEncrypt.response();
        ServletRequest requestWrapper = null;
        ServletResponse responseWrapper = null;
        EncryptResponseBodyWrapper responseBodyWrapper = null;

        // 是否为 put 或者 post 请求
        if (HttpMethod.PUT.matches(servletRequest.getMethod()) || HttpMethod.POST.matches(servletRequest.getMethod())) {
            // 是否存在加密标头
            String headerValue = servletRequest.getHeader(properties.getHeaderFlag());
            if (StringUtil.isNotBlank(headerValue)) {
                // 请求解密
                requestWrapper = new DecryptRequestBodyWrapper(servletRequest, properties.getPrivateKey(), properties.getHeaderFlag());
            } else {
                // 是否有注解，有就报错，没有放行
                if (!Objects.isNull(apiEncrypt)) {
                    HandlerExceptionResolver exceptionResolver = SpringUtil.getBean(HandlerExceptionResolver.class);
                    exceptionResolver.resolveException(
                            servletRequest, servletResponse, null,
                            new ServiceException("没有访问权限，请联系管理员授权", HttpStatus.FORBIDDEN.value()));
                    return;
                }
            }
        }

        // 判断是否响应加密
        if (responseFlag) {
            responseBodyWrapper = new EncryptResponseBodyWrapper(servletResponse);
            responseWrapper = responseBodyWrapper;
        }

        chain.doFilter(
                ObjectUtil.defaultIfNull(requestWrapper, request),
                ObjectUtil.defaultIfNull(responseWrapper, response));

        if (responseFlag) {
            servletResponse.reset();
            // 对原始内容加密
            String encryptContent = responseBodyWrapper.getEncryptContent(
                    servletResponse, properties.getPublicKey(), properties.getHeaderFlag());
            // 对加密后的内容写出
            servletResponse.getWriter().write(encryptContent);
        }
    }

    @Override
    public void destroy() {
    }

    /**
     * 获取 ApiEncrypt 注解
     */
    private ApiEncrypt getApiEncryptAnnotation(HttpServletRequest servletRequest) {
        RequestMappingHandlerMapping handlerMapping = SpringUtil.getBean(RequestMappingHandlerMapping.class);
        // 获取注解
        try {
            HandlerExecutionChain mappingHandler = handlerMapping.getHandler(servletRequest);
            if (!Objects.isNull(mappingHandler)) {
                Object handler = mappingHandler.getHandler();
                // 从handler获取注解
                if (handler instanceof HandlerMethod handlerMethod) {
                    return handlerMethod.getMethodAnnotation(ApiEncrypt.class);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
