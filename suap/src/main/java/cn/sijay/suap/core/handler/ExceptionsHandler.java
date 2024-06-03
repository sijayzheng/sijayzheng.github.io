package cn.sijay.suap.core.handler;

import cn.sijay.suap.core.entity.Res;
import cn.sijay.suap.core.exception.BaseException;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <strong>ExceptionsHandler</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Res<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String msg = fieldError != null ? fieldError.getDefaultMessage() : "参数校验异常";
        log.error("参数校验异常:{}", msg);
        e.printStackTrace();
        return Res.failure(msg);
    }

    @ExceptionHandler(BaseException.class)
    public Res<Void> handleBaseException(BaseException e) {
        log.error("通用内部异常:{}", e.getMessage());
        e.printStackTrace();
        return Res.failure(e.getMessage());
    }

    /**
     * 主键或UNIQUE索引，数据重复异常
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Res<Void> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("数据库中已存在记录'{}'", e.getMessage());
        e.printStackTrace();
        return Res.failure("数据库中已存在该记录");
    }

    @ExceptionHandler(MysqlDataTruncation.class)
    public Res<Void> handleDataTruncation(MysqlDataTruncation e) {
        log.error("数据超长：{}", e.getMessage());
        e.printStackTrace();
        return Res.failure("数据超长");
    }

    @ExceptionHandler(Exception.class)
    public Res<String> handleException(Exception e) {
        log.error("捕获到异常:{},异常信息:{}", e.getClass(), e.getMessage());
        e.printStackTrace();
        return Res.failure(e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Res<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("捕获到异常:{},异常信息:{}", e.getClass(), "必填参数[" + e.getParameterName() + "]为空");
        e.printStackTrace();
        return Res.failure("必填参数[" + e.getParameterName() + "]为空");
    }
}
