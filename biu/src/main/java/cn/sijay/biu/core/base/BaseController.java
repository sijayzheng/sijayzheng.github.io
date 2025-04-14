package cn.sijay.biu.core.base;

import cn.sijay.biu.core.entity.Result;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * BaseController
 *
 * @author Sijay
 * @since 2025-02-13
 */
public class BaseController {

    public <T> Result<T> success(Page<T> page) {
        return Result.of(page);
    }

    public <T> Result<T> success(List<T> list) {
        return Result.of(list);
    }

    public <T> Result<T> success() {
        return Result.of(HttpStatus.OK.value());
    }

    public <T> Result<T> success(String message, T body) {
        return Result.of(HttpStatus.OK.value(), message, body);
    }

    public <T> Result<T> success(T body) {
        if (body instanceof String s) {
            return Result.of(HttpStatus.OK.value(), s, null);
        }
        return Result.of(HttpStatus.OK.value(), "操作成功", body);
    }

    public <T> Result<T> fail() {
        return Result.of(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    public <T> Result<T> fail(String message) {
        return Result.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }
}
