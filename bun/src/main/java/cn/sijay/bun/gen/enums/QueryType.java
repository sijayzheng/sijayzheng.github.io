package cn.sijay.bun.gen.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>QueryType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-08
 */
@Getter
@AllArgsConstructor
public enum QueryType {
    EQUAL("等于"),
    BETWEEN("介于"),
    LIKE("包含"),
    ;
    private final String value;
}
