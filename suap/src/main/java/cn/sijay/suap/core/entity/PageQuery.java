package cn.sijay.suap.core.entity;

import cn.sijay.suap.core.utils.NumberUtil;
import cn.sijay.suap.core.utils.StringUtil;
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
 * @since 2024-06-01
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
    private Integer size = 10;
    /**
     * 当前页数
     */
    private Integer current = 1;
    /**
     * 排序列
     */
    private List<String> asc;
    private List<String> desc;

    public PageRequest build() {
        int pageNum = NumberUtil.firstGreatThanZero(Optional.ofNullable(current).orElse(DEFAULT_PAGE_NUM), DEFAULT_PAGE_NUM);
        int pageSize = NumberUtil.firstGreatThanZero(Optional.ofNullable(size).orElse(DEFAULT_PAGE_SIZE), DEFAULT_PAGE_SIZE);
        Sort sort = Sort.unsorted();
        if (CollectionUtils.isNotEmpty(asc)) {
            sort = Sort.by(asc.stream().map(StringUtil::toLowerSnakeCase).map(Sort.Order::asc).toList());
        } else if (CollectionUtils.isNotEmpty(desc)) {
            sort = Sort.by(asc.stream().map(StringUtil::toLowerSnakeCase).map(Sort.Order::desc).toList());
        }
        return PageRequest.of(pageNum, pageSize, sort);
    }
}
