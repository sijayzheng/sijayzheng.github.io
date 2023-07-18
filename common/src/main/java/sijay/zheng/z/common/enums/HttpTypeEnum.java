/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sijay
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
