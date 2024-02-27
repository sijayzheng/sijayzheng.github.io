package cn.sijay.suap.core.aspect;

import cn.sijay.suap.core.annotation.Log;
import cn.sijay.suap.core.constant.Constants;
import cn.sijay.suap.core.util.JsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * <p>
 * <em>RequestAspect</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/9 10:35
 */
@Aspect
@Component
public class LogAspect {
    final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 处理完请求后执行
     *
     * @param point 切点
     */
    @AfterReturning(pointcut = "@annotation(log)", returning = "result")
    public void doAfterReturning(JoinPoint point, Log log, Object result) {
        handleLog(point, log, result, null);
    }

    /**
     * 拦截异常操作
     *
     * @param point 切点
     * @param e     异常
     */
    @AfterThrowing(value = "@annotation(log)", throwing = "e")
    public void doAfterThrowing(JoinPoint point, Log log, Exception e) {
        handleLog(point, log, null, e);
    }

    void handleLog(final JoinPoint point, Log log, Object result, final Exception e) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String url = request.getRequestURI();
        Signature signature = point.getStaticPart().getSignature();
        String params = JsonUtil.toJsonString(Arrays.stream(point.getArgs()).filter(argFilter()).toList());
        logger.info("开始请求 => URL【{}】,参数为:【{}】", url, params);
        String method = signature.getDeclaringTypeName() + Constants.DOT + signature.getName() + "()";
        String returnResult = ObjectUtils.isEmpty(result) ? "" : JsonUtil.toJsonString(result);
//        LogBusiness logBusiness = new LogBusiness().setUserId(1L).setIp(request.getRemoteAddr()).setMethod(method).setParams(params)
//                                                   .setRequestType(request.getMethod()).setRequestUrl(request.getRequestURI())
//                                                   .setBusinessName(log.value()).setOperationType(log.operateType()).setReturnResult(returnResult)
//                                                   .setOperationTime(LocalDateTime.now());
        if (e != null) {
//            logBusiness.setOperationResult(ResultCodeEnum.FAILURE);
//            logBusiness.setErrorMessage(e.getMessage());
        }
//        logBusiness.setOperationResult(ResultCodeEnum.SUCCESS);
//        System.out.println(logBusiness);
        logger.info("请求结束 => URL【{}】,请求结果为【{}】", url, returnResult);
    }

    Predicate<Object> argFilter() {
        return arg -> !(arg instanceof HttpServletResponse || arg instanceof HttpServletRequest || arg instanceof MultipartFile);
    }

}

