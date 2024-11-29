package cn.sijay.suap.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>GenType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Getter
@AllArgsConstructor
public enum GenType {
    PROJECT("项目中"),
    ZIP("压缩包"),
    CUSTOM("自定义位置"),
    ;

    @JsonValue
    private final String desc;

}
