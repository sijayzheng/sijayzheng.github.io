package cn.sijay.suap.core.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.sijay.suap.core.base.BaseException;
import cn.sijay.suap.core.constant.ExceptionConstant;
import cn.sijay.suap.core.entity.Res;
import cn.sijay.suap.core.enums.ResultCodeEnum;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
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
 * @since 2024-07-18
 */
@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Res<Void> handleException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        FieldError fieldError = e.getBindingResult()
                                 .getFieldError();
        String msg = fieldError != null ? fieldError.getDefaultMessage() : "参数校验异常";
        log.error("参数校验异常:{}", msg);
        return Res.error(msg);
    }

    @ExceptionHandler(BaseException.class)
    public Res<Void> handleException(BaseException e) {
        e.printStackTrace();
        log.error("系统异常:{}", e.getMessage());
        return Res.error(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public Res<Void> handleException(EntityNotFoundException e) {
        e.printStackTrace();
        log.error(ExceptionConstant.ENTITY_NOT_FOUND + "实体类内部异常:{}", e.getLocalizedMessage(), e.getMessage());
        return Res.error(e.getMessage());
    }

    /**
     * 主键或UNIQUE索引，数据重复异常
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Res<Void> handleException(DuplicateKeyException e) {
        e.printStackTrace();
        log.error("数据库中已存在记录'{}'", e.getMessage());
        return Res.error("数据库中已存在该记录");
    }

    @ExceptionHandler(MysqlDataTruncation.class)
    public Res<Void> handleException(MysqlDataTruncation e) {
        e.printStackTrace();
        log.error("数据超长：{}", e.getMessage());
        return Res.error("数据超长");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Res<Void> handleException(MissingServletRequestParameterException e) {
        e.printStackTrace();
        log.error("异常信息:{}", "必填参数[" + e.getParameterName() + "]为空");
        return Res.error("必填参数[" + e.getParameterName() + "]为空");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Res<Void> handleHttpRequestMethodNotSupported(DataIntegrityViolationException e) {
        e.printStackTrace();
        String message = NestedExceptionUtils.getMostSpecificCause(e)
                                             .getMessage();
        if (message.startsWith("Duplicate entry")) {
            message = message.replaceAll("Duplicate entry '(\\w+)' for key '(\\w+)\\.(\\w+)'", "表$2字段$3已存在相同值[$1]");
        } else if (message.startsWith("Data truncation: Data too long")) {
            message = message.replaceAll("Data truncation: Data too long for column '(\\w+)' at row (\\w+)", "字段$1超出长度限制");
        }
        log.error("数据库异常信息:{}", message);
        return Res.error(message);
    }

    @ExceptionHandler(NotLoginException.class)
    public Res<Void> handleNotLoginException(NotLoginException e) {
        e.printStackTrace();
        return Res.of(ResultCodeEnum.HTTP403.getCode(), ExceptionConstant.NO_LOGIN_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public Res<Void> handleException(Exception e) {
        e.printStackTrace();
        log.error("统一异常信息:{}", e.getMessage());
        return Res.error(e.getMessage());
    }
}
