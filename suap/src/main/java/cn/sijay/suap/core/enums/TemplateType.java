package cn.sijay.suap.core.enums;

import cn.sijay.suap.core.base.BaseDataEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <em>TemplateType 前端页面模板类型</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/12 17:05
 */
@Getter
@AllArgsConstructor
public enum TemplateType implements BaseDataEnum {
    LIST("L", "列表"),
    TREE("T", "树表"),
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
