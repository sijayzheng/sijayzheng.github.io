package cn.sijay.bun.common.entity;

import cn.sijay.bun.common.util.ObjectUtil;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>PageQuery</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-11-19
 */
@Data
public class PageQuery {
    private Integer index;
    private Integer pageSize;
    private List<SortField> sortFields;

    public PageRequest build() {
        Sort sort;
        if (CollectionUtils.isEmpty(sortFields)) {
            sort = Sort.unsorted();
        } else {
            ArrayList<Sort.Order> orders = new ArrayList<>();
            for (SortField sortField : sortFields) {
                if (sortField.isAsc()) {
                    orders.add(Sort.Order.asc(sortField.getField()));
                } else {
                    orders.add(Sort.Order.desc(sortField.getField()));
                }
            }
            sort = Sort.by(orders);
        }
        return PageRequest.of(
            ObjectUtil.orDefault(getIndex(), 1),
            ObjectUtil.orDefault(getIndex(), 10),
            sort
        );
    }

}
