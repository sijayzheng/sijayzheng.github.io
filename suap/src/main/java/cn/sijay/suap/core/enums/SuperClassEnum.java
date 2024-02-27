package cn.sijay.suap.core.enums;

import cn.sijay.suap.core.base.BaseDataEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <em>SuperClassEnum</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/25 9:53
 */
@Getter
@AllArgsConstructor
public enum SuperClassEnum implements BaseDataEnum {

    BASE_ENTITY("BaseEntity", "cn.sijay.suap.core.base.BaseEntity"),
    ;

    @EnumValue
    private final String code;
    @JsonValue
    private final String desc;
}
