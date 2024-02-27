package cn.sijay.suap.core.enums;

import cn.sijay.suap.core.base.BaseDataEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <em>GenType 生成方式</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/12 17:10
 */
@Getter
@AllArgsConstructor
public enum GenType implements BaseDataEnum {
    PROJECT("P", "项目中"),
    ZIP_FILE("Z", "压缩包"),
    ;
    /**
     * 模板类型
     */
    @EnumValue
    private final String code;
    /**
     * 模板描述
     */
    @JsonValue
    private final String desc;
}
