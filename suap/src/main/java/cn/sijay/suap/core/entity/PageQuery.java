package cn.sijay.suap.core.entity;

import cn.sijay.suap.core.utils.StringUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * <strong>PageQuery</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Data
public class PageQuery implements Serializable {

    /**
     * 当前记录起始索引 默认值
     */
    public static final int DEFAULT_PAGE_NUM = 1;
    /**
     * 每页显示记录数 默认值 默认查全部
     */
    public static final int DEFAULT_PAGE_SIZE = Integer.MAX_VALUE;
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 分页大小
     */
    @Schema(name = "size", title = "分页大小")
    private Integer size;
    /**
     * 当前页数
     */
    @Schema(name = "current", title = "当前页数")
    private Integer current;
    /**
     * 正序排序列
     */
    @Schema(name = "asc", title = "正序排序列")
    private List<String> asc;
    /**
     * 逆序排序列
     */
    @Schema(name = "desc", title = "逆序排序列")
    private List<String> desc;

    public PageRequest build() {
        int pageNum = Optional.ofNullable(current).orElse(DEFAULT_PAGE_NUM) - 1;
        int pageSize = Optional.ofNullable(size).orElse(DEFAULT_PAGE_SIZE);
        Sort sort = Sort.unsorted();
        if (CollectionUtils.isNotEmpty(asc)) {
            sort = Sort.by(asc.stream().map(StringUtil::toLowerSnakeCase).map(Sort.Order::asc).toList());
        } else if (CollectionUtils.isNotEmpty(desc)) {
            sort = Sort.by(asc.stream().map(StringUtil::toLowerSnakeCase).map(Sort.Order::desc).toList());
        }
        return PageRequest.of(pageNum, pageSize, sort);
    }
}
