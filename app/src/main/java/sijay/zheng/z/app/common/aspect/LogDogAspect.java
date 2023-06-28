/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app.common.aspect;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.function.ServerResponse;
import sijay.zheng.z.app.common.annotation.LogDog;
import sijay.zheng.z.app.common.util.JsonUtil;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sijay
 */
@Aspect
@Component
public class LogDogAspect {
    private final Logger logger = LoggerFactory.getLogger("LogDog");

    /**
     * @param LogDog log annotation
     */
    @Pointcut("@annotation(LogDog)")
    public void pointCut(LogDog LogDog) {
    }

    /**
     * @param point  cutpoint
     * @param LogDog log annotation
     */
    @Before(value = "pointCut(LogDog)", argNames = "point,LogDog")
    public void doLog(JoinPoint point, LogDog LogDog) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra == null) {
            return;
        }
        HttpServletRequest request = sra.getRequest();
        String commHead = getCommHead(sra, LogDog.value());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(commHead).append("入参：【");
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap != null && parameterMap.size() > 0) {
            stringBuilder.append(JsonUtil.toJsonString(parameterMap));
        } else {
            stringBuilder.append(Stream.of(point.getArgs()).filter(e -> !(e instanceof ServletRequest || e instanceof ServerResponse || e instanceof InputStreamResource)).map(JsonUtil::toJsonString).collect(Collectors.joining(";;")));
        }
        stringBuilder.append("】");
        logger.info(stringBuilder.toString());
    }

    /**
     * @param LogDog log annotation
     */
    @AfterReturning(value = "pointCut(LogDog)", returning = "result", argNames = "LogDog,result")
    public void doLog(LogDog LogDog, Object result) {
        String commHead = getCommHead((ServletRequestAttributes) RequestContextHolder.getRequestAttributes(), LogDog.value());
        logger.info(commHead + "出参：【" + JsonUtil.toJsonString(result) + "】");

    }

    /**
     * @param LogDog log annotation
     */
    @AfterThrowing(value = "pointCut(LogDog)", throwing = "exception", argNames = "LogDog,exception")
    public void doLog(LogDog LogDog, Throwable exception) {
        String commHead = getCommHead((ServletRequestAttributes) RequestContextHolder.getRequestAttributes(), LogDog.value());
        logger.error(commHead + "发生异常：【" + exception.getMessage() + "】");
    }

    private String getCommHead(ServletRequestAttributes servletRequestAttributes, String funName) {
        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            return String.join("", "[", request.getRequestURI(), ("("), request.getMethod(), ")]", funName, "，");
        }
        return "";
    }
}
