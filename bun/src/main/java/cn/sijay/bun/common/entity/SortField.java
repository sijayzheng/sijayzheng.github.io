package cn.sijay.bun.common.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <strong>Sort</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-19
 */
@Data
public class SortField {
    @NotNull
    private String field;
    private boolean asc = true;
}
