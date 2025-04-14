package cn.sijay.bun.common.entity;

import cn.sijay.bun.common.constant.HttpConstant;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * <strong>ResponseResult</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-15
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseResult<T> {
    private T data;
    private Integer code;
    private String message;
    private List<T> rows;
    private long total;

    public ResponseResult(List<T> rows, long total) {
        this.rows = rows;
        this.total = total;
        this.code = HttpConstant.OK;
    }

    public ResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseResult<T> of(Integer status, String message, T body) {
        return new ResponseResult<>(status, message, body);
    }

    public static <T> ResponseResult<T> of(Integer status, String message) {
        return of(status, message, null);
    }

    public static <T> ResponseResult<T> of(Integer status) {
        return of(status, null, null);
    }

    public static <T> ResponseResult<T> of(Page<T> page) {
        return new ResponseResult<>(page.getContent(), page.getTotalElements());
    }
}
