package sijay.zheng.z.app.exception;

import cn.dev33.satoken.exception.NotLoginException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import sijay.zheng.z.app.core.Returns;

/**
 * @author sijay
 * @description GlobalExceptionHandler
 * @date 2023/8/4 10:24
 */
@Slf4j
@Configuration
@RestControllerAdvice
public class ExceptionHandler {

    /**
     * 认证失败
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(NotLoginException.class)
    public Returns<Void> handleNotLoginException(NotLoginException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',{}", requestURI, e.getMessage());
        return Returns.of("7777", "请重新登录", null);
    }

    /**
     * 请求方式不支持
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Returns<Void> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return Returns.error(e.getMessage());
    }

    /**
     * 请求路径中缺少必需的路径变量
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(MissingPathVariableException.class)
    public Returns<Void> handleMissingPathVariableException(MissingPathVariableException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求路径中缺少必需的路径变量'{}',发生系统异常.", requestURI, e);
        return Returns.error(String.format("请求路径中缺少必需的路径变量[%s]", e.getVariableName()));
    }

    /**
     * 请求参数类型不匹配
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Returns<Void> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求参数类型不匹配'{}',发生系统异常.", requestURI, e);
        return Returns.error(String.format("请求参数类型不匹配，参数[%s]要求类型为：'%s'，但输入值为：'%s'", e.getName(), e.getRequiredType().getName(), e.getValue()));
    }

    /**
     * 拦截未知的运行时异常
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public Returns<Void> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return Returns.error(e.getMessage());
    }

    /**
     * 系统异常
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public Returns<Void> handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return Returns.error(e.getMessage());
    }
}
