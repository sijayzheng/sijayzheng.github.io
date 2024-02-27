package cn.sijay.suap.core.handler;

import cn.sijay.suap.core.entity.Result;
import cn.sijay.suap.core.exception.BaseException;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * <em>Goh</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/10 14:03
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String msg = fieldError != null ? fieldError.getDefaultMessage() : "参数校验异常";
        log.error("参数校验异常:{}", msg);
        e.printStackTrace();
        return Result.error(msg);
    }

    @ExceptionHandler(BaseException.class)
    public Result<Void> handleBaseException(BaseException e) {
        log.error("通用内部异常:{}", e.getMessage());
        e.printStackTrace();
        return Result.error(e.getMessage());
    }

    /**
     * 主键或UNIQUE索引，数据重复异常
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result<Void> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("数据库中已存在记录'{}'", e.getMessage());
        e.printStackTrace();
        return Result.error("数据库中已存在该记录");
    }

    /**
     * Mybatis系统异常 通用处理
     */
    @ExceptionHandler(MyBatisSystemException.class)
    public Result<Void> handleCannotFindDataSourceException(MyBatisSystemException e) {
        String message = e.getMessage();
        e.printStackTrace();
        if ("CannotFindDataSourceException".contains(message)) {
            log.error(" 未找到数据源");
            return Result.error("未找到数据源");
        }
        log.error(" Mybatis系统异常", e);
        return Result.error(message);
    }

    @ExceptionHandler(MysqlDataTruncation.class)
    public Result<Void> handleDataTruncation(MysqlDataTruncation e) {
        log.error("数据超长：{}", e.getMessage());
        e.printStackTrace();
        return Result.error("数据超长");
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        log.error("捕获到异常:{},异常信息:{}", e.getClass(), e.getMessage());
        e.printStackTrace();
        return Result.error(e.getMessage());
    }
}
