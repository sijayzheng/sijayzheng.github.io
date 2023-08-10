package sijay.zheng.z.app.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import sijay.zheng.z.common.util.JsonUtil;

import java.util.Map;

/**
 * @author sijay
 * @description in
 * @date 2023/8/4 11:24
 */
@Slf4j
public class Interceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getMethod() + " " + request.getRequestURI();
        // 打印请求参数
        String parameters = "";
        if (MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(request.getContentType())) {
            parameters = new String(request.getInputStream().readAllBytes());
        } else {
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (parameterMap.isEmpty()) {
                log.info("请求开始 => URL[{}]", url);
            } else {
                parameters = JsonUtil.toJsonString(parameterMap);
            }
        }
        log.info("请求开始 => URL[{}],参数:[{}]", url, parameters);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("请求结束 => URL[{}]", request.getMethod() + " " + request.getRequestURI());
    }
}
