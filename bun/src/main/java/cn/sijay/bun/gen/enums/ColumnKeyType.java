package cn.sijay.bun.gen.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>ColumnKeyType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-08
 */
@Getter
@AllArgsConstructor
public enum ColumnKeyType {
    PRI("主键"),
    UNI("唯一键"),
    MUL("索引字段"),
    ;
    private final String value;
}
