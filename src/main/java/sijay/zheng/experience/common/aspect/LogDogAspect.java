package sijay.zheng.experience.common.aspect;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.springframework.core.io.*;
import org.springframework.stereotype.*;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.function.*;
import sijay.zheng.experience.common.annotation.*;
import sijay.zheng.experience.common.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.stream.*;

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
            stringBuilder.append(JsonUtils.toJsonString(parameterMap));
        } else {
            stringBuilder.append(Stream.of(point.getArgs()).filter(e -> !(e instanceof ServletRequest || e instanceof ServerResponse || e instanceof InputStreamResource)).map(JsonUtils::toJsonString).collect(Collectors.joining(";;")));
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
        logger.info(commHead + "出参：【" + JsonUtils.toJsonString(result) + "】");

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
