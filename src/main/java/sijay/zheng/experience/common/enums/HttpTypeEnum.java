package sijay.zheng.experience.common.enums;

import lombok.*;

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
