package cn.sijay.biu.core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import java.io.IOException;

/**
 * Repeatable 过滤器
 *
 * @author Sijay
 */
public class RepeatableFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest
                && StringUtils.startsWithIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE)) {
            requestWrapper = new RepeatedlyRequestWrapper((HttpServletRequest) request, response);
        }
        if (null == requestWrapper) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
    }

    @Override
    public void destroy() {

    }
}
