package cn.sijay.bun.core.base;

import cn.sijay.bun.common.entity.ResponseResult;
import org.springframework.http.HttpStatus;

/**
 * <strong>BaseController</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-11-15
 */
public class BaseController {

    public <T> ResponseResult<T> success() {
        return ResponseResult.of(HttpStatus.OK.value());
    }

    public <T> ResponseResult<T> success(String message, T body) {
        return ResponseResult.of(HttpStatus.OK.value(), message, body);
    }

    public <T> ResponseResult<T> success(T body) {
        return ResponseResult.of(HttpStatus.OK.value(), "操作成功", body);
    }

    public <T> ResponseResult<T> fail() {
        return ResponseResult.of(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    public <T> ResponseResult<T> fail(String message) {
        return ResponseResult.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }
}
