package cn.sijay.suap.core.entity;

import lombok.Data;

import java.util.List;

/**
 * <p>
 * <em>Id</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/9 14:50
 */
@Data
public class Ids<T> {
    private T id;
    private List<T> ids;
}
