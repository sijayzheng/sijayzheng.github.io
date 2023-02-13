package sijay.zheng.experience.common.enums;

import lombok.*;

/**
 * @author sijay
 * @date 2022/10/3 22:15
 */
@AllArgsConstructor
@Getter
public enum CaseEnum {
    /**
     * 小驼峰命名
     */
    LOWER_CAMEL_CASE("LOWER_CAMEL_CASE", "小驼峰命名"),
    /**
     * 大驼峰命名
     */
    UPPER_CAMEL_CASE("UPPER_CAMEL_CASE", "大驼峰命名"),
    /**
     * 小写蛇形命名
     */
    LOWER_SANKE_CASE("LOWER_SANKE_CASE", "小写蛇形命名"),
    /**
     * 大写蛇形命名
     */
    UPEER_SNAKE_CASE("UPEER_SNAKE_CASE", "大写蛇形命名"),
    ;

    private final String type;
    private final String description;

}
