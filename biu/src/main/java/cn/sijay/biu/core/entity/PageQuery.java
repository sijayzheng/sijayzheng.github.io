package cn.sijay.biu.core.entity;

import cn.sijay.biu.core.util.ObjectUtil;
import cn.sijay.biu.core.util.StringUtil;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serial;
import java.io.Serializable;

/**
 * PageQuery
 *
 * @author Sijay
 * @since 2025-02-28
 */
@Data
public class PageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 分页大小
     */
    private Integer pageSize;
    /**
     * 当前页数
     */
    private Integer pageNum;
    /**
     * 排序列
     */
    private String field;
    /**
     * 排序的方向desc或者asc
     */
    private Boolean asc;

    /**
     * 构建分页对象
     */
    public Pageable build() {
        int pageNum = ObjectUtil.defaultIfNull(getPageNum(), 1) - 1;
        int pageSize = ObjectUtil.defaultIfNull(getPageSize(), Integer.MAX_VALUE);
        if (pageNum < 0) {
            pageNum = 0;
        }
        if (StringUtil.isNotBlank(field)) {
            return PageRequest.of(pageNum, pageSize, Sort.by(asc ? Sort.Order.asc(field) : Sort.Order.desc(field)));
        } else {
            return PageRequest.of(pageNum, pageSize);
        }
    }
}
