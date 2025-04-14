package cn.sijay.biu.generate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * QueryEnum
 *
 * @author Sijay
 * @since 2024-11-08
 */
@Getter
@AllArgsConstructor
public enum QueryTypeEnum {
    NONE("无"),
    EQUAL("等于"),
    BETWEEN("介于"),
    LIKE("包含"),
    ;
    private final String value;
}
