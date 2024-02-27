package cn.sijay.suap.core.enums;

import cn.sijay.suap.core.base.BaseDataEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <em>QueryType</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/9 15:46
 */
@Getter
@AllArgsConstructor
public enum QueryType implements BaseDataEnum {
    LIKE("LK", "包含", "like"),
    EQUAL("EQ", "等于", "eq"),
    GREATER_THAN("GT", "大于", "gt"),
    GREATER_OR_EQUAL("GE", "大于等于", "ge"),
    LESS_THAN("LT", "小于", "lt"),
    LESS_OR_EQUAL("LE", "小于等于", "le"),
    BETWEEN("BT", "在范围内", "between"),
    IN("IN", "在列表内", "in"),
    ;

    @EnumValue
    private final String code;
    @JsonValue
    private final String desc;
    private final String mp;

    public static QueryType getByCode(String code) {
        if (code == null) {
            return QueryType.EQUAL;
        }
        for (QueryType queryType : QueryType.values()) {
            if (queryType.getCode().equals(code)) {
                return queryType;
            }
        }
        return QueryType.EQUAL;
    }

}
