package cn.sijay.biu.generate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ColumnKeyEnum
 *
 * @author Sijay
 * @since 2024-11-08
 */
@Getter
@AllArgsConstructor
public enum ColumnKeyEnum {
    NONE("无"),
    PRI("主键"),
    UNI("唯一键"),
    MUL("索引字段"),
    ;
    private final String value;
}
