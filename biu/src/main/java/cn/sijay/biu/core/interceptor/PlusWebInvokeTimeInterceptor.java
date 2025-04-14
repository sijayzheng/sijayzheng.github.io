package cn.sijay.biu.core.interceptor;

import cn.sijay.biu.core.exception.UtilException;
import cn.sijay.biu.core.filter.RepeatedlyRequestWrapper;
import cn.sijay.biu.core.util.JsonUtil;
import cn.sijay.biu.core.util.MapUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Map;

/**
 * web的调用时间统计拦截器
 *
 * @author Sijay
 * @since 3.3.0
 */
@Slf4j
public class PlusWebInvokeTimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getMethod() + " " + request.getRequestURI();
        String contentType = request.getContentType();
        // 打印请求参数
        if (contentType != null && StringUtils.startsWithIgnoreCase(contentType, MediaType.APPLICATION_JSON_VALUE)) {
            String jsonParam = "";
            if (request instanceof RepeatedlyRequestWrapper) {

                StringBuilder builder = new StringBuilder();
                try (BufferedReader reader = request.getReader();) {
                    reader.readLine();
                    CharBuffer buffer = CharBuffer.allocate(8192);
                    while (-1 != reader.read(buffer)) {
                        builder.append(buffer.flip());
                    }
                } catch (IOException e) {
                    throw new UtilException(e.getMessage());
                }
                jsonParam = builder.toString();
            }
            log.info("[PLUS]开始请求 => URL[{}],参数类型[json],参数:[{}]", url, jsonParam);
        } else {
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (MapUtil.isNotEmpty(parameterMap)) {
                String parameters = JsonUtil.toJson(parameterMap);
                log.info("[PLUS]开始请求 => URL[{}],参数类型[param],参数:[{}]", url, parameters);
            } else {
                log.info("[PLUS]开始请求 => URL[{}],无参数", url);
            }
        }

        return true;
    }
}
