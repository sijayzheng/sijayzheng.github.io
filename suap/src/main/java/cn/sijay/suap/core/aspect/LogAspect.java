package cn.sijay.suap.core.aspect;

import cn.sijay.suap.core.annotation.OprLog;
import cn.sijay.suap.core.constant.Constants;
import cn.sijay.suap.core.utils.JsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * <strong>LogAspect</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    /**
     * 处理完请求后执行
     *
     * @param point 切点
     */
    @AfterReturning(pointcut = "@annotation(log)", returning = "result")
    public void doAfterReturning(JoinPoint point, OprLog log, Object result) {
        handleLog(point, log, result, null);
    }

    /**
     * 拦截异常操作
     *
     * @param point 切点
     * @param e     异常
     */
    @AfterThrowing(value = "@annotation(oprLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint point, OprLog oprLog, Exception e) {
        handleLog(point, oprLog, null, e);
    }

    void handleLog(final JoinPoint point, OprLog oprLog, Object result, final Exception e) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String url = request.getRequestURI();
        Signature signature = point.getStaticPart().getSignature();
        String params = JsonUtil.toJsonString(Arrays.stream(point.getArgs()).filter(argFilter()).toList());
        log.info("开始请求 => URL【{}】,参数为:【{}】", url, params);
        String method = signature.getDeclaringTypeName() + Constants.DOT + signature.getName() + "()";
        String returnResult = ObjectUtils.isEmpty(result) ? "" : JsonUtil.toJsonString(result);
//        LogBusiness logBusiness = new LogBusiness().setUserId(1L).setIp(request.getRemoteAddr()).setMethod(method).setParams(params)
//                                                   .setRequestType(request.getMethod()).setRequestUrl(request.getRequestURI())
//                                                   .setBusinessName(oprLog.value()).setOperationType(oprLog.operateType()).setReturnResult(returnResult)
//                                                   .setOperationTime(LocalDateTime.now());
        if (e != null) {
//            logBusiness.setOperationResult(ResultCodeEnum.FAILURE);
//            logBusiness.setErrorMessage(e.getMessage());
        }
//        logBusiness.setOperationResult(ResultCodeEnum.SUCCESS);
//        System.out.println(logBusiness);
        log.info("请求结束 => URL【{}】,请求结果为【{}】", url, returnResult);
    }

    Predicate<Object> argFilter() {
        return arg -> !(arg instanceof HttpServletResponse || arg instanceof HttpServletRequest || arg instanceof MultipartFile);
    }

}

