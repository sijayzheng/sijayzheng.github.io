package cn.sijay.suap.core.entity;

import cn.sijay.suap.core.enums.ResultCodeEnum;
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
 * @since 2024-06-01
 */
@Data
@NoArgsConstructor
public class PageResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 列表数据
     */
    private List<T> rows;

    /**
     * 消息状态码
     */
    private int code;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public PageResult(List<T> list, long total) {
        this.rows = list;
        this.total = total;
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.msg = "查询成功";
    }

    public static <T> PageResult<T> of(List<T> list) {
        PageResult<T> rspData = new PageResult<>();
        rspData.setCode(ResultCodeEnum.SUCCESS.getCode());
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(list.size());
        return rspData;
    }

    public static <T> PageResult<T> of() {
        PageResult<T> rspData = new PageResult<>();
        rspData.setCode(ResultCodeEnum.SUCCESS.getCode());
        rspData.setMsg("查询成功");
        return rspData;
    }

    public static <T> PageResult<T> of(Page<T> page) {
        PageResult<T> rspData = new PageResult<>();
        rspData.setCode(ResultCodeEnum.SUCCESS.getCode());
        rspData.setMsg("查询成功");
        rspData.setRows(page.getContent());
        rspData.setTotal(page.getTotalElements());
        return rspData;
    }
}
