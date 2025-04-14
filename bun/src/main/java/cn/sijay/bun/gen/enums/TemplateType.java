package cn.sijay.bun.gen.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>templateType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-08
 */
@Getter
@AllArgsConstructor
public enum TemplateType {
    LIST("列表"),
    TREE("树表"),
    ;
    private final String value;
}
