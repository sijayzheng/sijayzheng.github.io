package cn.sijay.suap.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>DataSource</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Getter
@AllArgsConstructor
public enum DataSource  {
    DICT("D", "字典"),
    ENUM("E", "枚举"),
    SELF("S", "自身"),
    TABLE("T", "表数据"),
    ;

    private final String code;
    @JsonValue
    private final String desc;

}
