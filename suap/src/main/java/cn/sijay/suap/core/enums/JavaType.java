package cn.sijay.suap.core.enums;

import cn.sijay.suap.core.base.BaseDataEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <em>JavaType Java数据类型</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/11 16:34
 */
@Getter
@AllArgsConstructor
public enum JavaType implements BaseDataEnum {
    STRING("String", "字符串", ""),
    Character("Character", "字符", ""),
    Long("Long", "长整数", ""),
    Integer("Integer", "整数", ""),
    Byte("Byte", "字节", ""),
    BIG_DECIMAL("BigDecimal", "大数字", "java.math.BigDecimal"),
    OBJECT("Object", "对象", ""),
    BOOLEAN("Boolean", "布尔", ""),
    DOUBLE("Double", "浮点", ""),
    DATE("LocalDate", "日期时间", "java.time.LocalDate"),
    TIME("LocalTime", "日期时间", "java.time.LocalTime"),
    DATE_TIME("LocalDateTime", "日期时间", "java.time.LocalDateTime"),

    //自定义类型
    DATA_SOURCE("DataSource", "数据来源", "cn.sijay.suap.core.enums.DataSource"),
    GEN_TYPE("GenType", "生成方式", "cn.sijay.suap.core.enums.GenType"),
    JAVA_TYPE("JavaType", "Java数据类型", "cn.sijay.suap.core.enums.JavaType"),
    OPERATE_TYPE("OperateType", "操作类型", "cn.sijay.suap.core.enums.OperateType"),
    QUERY_TYPE("QueryType", "查询类型", "cn.sijay.suap.core.enums.QueryType"),
    TEMPLATE_TYPE("TemplateType", "前端页面模板类型", "cn.sijay.suap.core.enums.TemplateType"),
    YES_OR_NO("YesOrNo", "是否", "cn.sijay.suap.core.enums.YesOrNo"),
    GENDER_TYPE("GenderType", "性别", "cn.sijay.suap.core.enums.GenderType"),
    INPUT_TYPE("InputType", "前端字段输入类型", "cn.sijay.suap.core.enums.InputType"),
    SUPER_CLASS_ENUM("SuperClassEnum", "父类", "cn.sijay.suap.core.enums.SuperClassEnum"),
    MENU_TYPE("MenuType", "菜单类型", "cn.sijay.suap.core.enums.MenuType"),
    ;

    @EnumValue
    private final String code;
    @JsonValue
    private final String desc;
    private final String pkg;

    public static JavaType getByCode(String code) {
        if (code == null) {
            return JavaType.STRING;
        }
        for (JavaType javaType : JavaType.values()) {
            if (javaType.getCode().equals(code)) {
                return javaType;
            }
        }
        return JavaType.STRING;
    }

}
