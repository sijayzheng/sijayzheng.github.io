package cn.sijay.suap.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>JavaType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Getter
@AllArgsConstructor
public enum JavaType {
    Boolean("布尔型", ""),

    Byte("字节型", ""),

    Character("字符型", ""),

    Double("浮点型", ""),

    Integer("整型", ""),

    Long("长整型", ""),

    Object("对象", ""),

    String("字符串", ""),

    BigDecimal("大数值", "java.math.BigDecimal"),

    LocalDate("日期", "java.time.LocalDate"),

    LocalDateTime("日期时间", "java.time.LocalDateTime"),

    LocalTime("时间", "java.time.LocalTime"),

    List("列表", "java.util.List"),

    Map("映射", "java.util.Map"),

    Set("集", "java.util.Set"),

    MenuType("菜单类型", "cn.sijay.suap.core.enums.MenuType"),

    OperateType("操作类型", "cn.sijay.suap.core.enums.OperateType"),

    QueryType("查询类型", "cn.sijay.suap.core.enums.QueryType"),

    GenType("生成方式", "cn.sijay.suap.core.enums.GenType"),

    InputType("输入方式", "cn.sijay.suap.core.enums.InputType"),

    JavaType("Java数据类型", "cn.sijay.suap.core.enums.JavaType"),

    TemplateType("前端类型", "cn.sijay.suap.core.enums.TemplateType"),

    DisplayType("回显样式", "cn.sijay.suap.core.enums.DisplayType"),

    GenderType("性别", "cn.sijay.suap.core.enums.GenderType"),

    NoticeType("公告类型", "cn.sijay.suap.core.enums.NoticeType"),

    ;

    @JsonValue
    private final String desc;
    private final String fullName;

    public static String getFullName(String name) {
        for (JavaType javaType : values()) {
            if (javaType.name().equals(name)) {
                return javaType.getFullName();
            }
        }
        return "";
    }
}
