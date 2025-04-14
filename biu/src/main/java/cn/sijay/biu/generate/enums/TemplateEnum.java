package cn.sijay.biu.generate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * templateEnum
 *
 * @author Sijay
 * @since 2024-11-08
 */
@Getter
@AllArgsConstructor
public enum TemplateEnum {
    LIST("列表"),
    TREE("树表"),
    ;
    private final String value;
}
