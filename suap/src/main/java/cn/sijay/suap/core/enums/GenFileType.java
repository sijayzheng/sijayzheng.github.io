package cn.sijay.suap.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>GenFileType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Getter
@AllArgsConstructor
public enum GenFileType {
    ENTITY("entity"),
    CONTROLLER("controller"),
    SERVICE("service"),
    SERVICE_IMPL("serviceImpl"),
    MAPPER("mapper"),
    MAPPER_XML("mapper.xml"),
    VUE("vue"),
    JS("js"),
    SQL("sql"),
    ;
    private final String fileName;
}
