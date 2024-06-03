package cn.sijay.suap.core.entity;

import lombok.Data;

import java.util.List;

/**
 * <strong>Id</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Data
public class PrimaryKey<T> {
    private T id;
    private List<T> ids;

    public PrimaryKey<T> of(T id) {
        this.id = id;
        return this;
    }

    public PrimaryKey<T> of(List<T> ids) {
        this.ids = ids;
        return this;
    }

}
