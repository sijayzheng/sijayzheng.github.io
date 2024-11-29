package cn.sijay.suap.core.entity;

import cn.sijay.suap.core.enums.ResultCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * <strong>PageResult</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Data
@NoArgsConstructor
public class PageResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    @Schema(name = "total", title = "总记录数")
    private long total;

    /**
     * 列表数据
     */
    @Schema(name = "rows", title = "列表数据")
    private List<T> rows;

    /**
     * 消息状态码
     */
    @Schema(name = "code", title = "消息状态码")
    private int code;

    /**
     * 消息内容
     */
    @Schema(name = "message", title = "消息内容")
    private String message;

    public static <T> PageResult<T> of(Page<T> page) {
        PageResult<T> rspData = new PageResult<>();
        rspData.setCode(ResultCodeEnum.SUCCESS.getCode());
        rspData.setMessage("查询成功");
        rspData.setRows(page.getContent());
        rspData.setTotal(page.getTotalElements());
        return rspData;
    }
}
