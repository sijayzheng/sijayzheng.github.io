package cn.sijay.suap.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>HttpTypeEnum</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@AllArgsConstructor
@Getter
public enum HttpTypeEnum {
    /**
     * POST
     */
    POST("POST"),
    /**
     * GET
     */
    GET("GET"),
    /**
     * PUT
     */
    PUT("PUT"),
    /**
     * DELETE
     */
    DELETE("DELETE"),
    ;

    private final String type;
}
