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
 * @since 2024-06-01
 */
@Getter
@AllArgsConstructor
public enum JavaType {
    Boolean("布尔", ""),
    Byte("字节", ""),
    Character("字符", ""),
    Double("浮点", ""),
    Integer("整数", ""),
    Long("长整数", ""),
    Object("对象", ""),
    String("字符串", ""),

    BigDecimal("大数值", "java.math.BigDecimal"),

    LocalDate("日期", "java.time.LocalDate"),
    LocalDateTime("日期时间", "java.time.LocalDateTime"),
    LocalTime("时间", "java.time.LocalTime"),

    List("列表", "java.util.List"),
    Map("映射", "java.util.Map"),
    Set("集", "java.util.Set"),
    List_Long("列表", "java.util.List"),

    MenuType("菜单类型", "cn.sijay.tit.core.dictionary.MenuType"),
    OperateType("操作类型", "cn.sijay.tit.core.dictionary.OperateType"),
    QueryType("查询类型", "cn.sijay.tit.core.dictionary.QueryType"),
    GenType("生成方式", "cn.sijay.tit.core.dictionary.GenType"),
    InputType("输入方式", "cn.sijay.tit.core.dictionary.InputType"),
    JavaType("Java数据类型", "cn.sijay.tit.core.dictionary.JavaType"),
    SuperClassType("父类", "cn.sijay.tit.core.dictionary.SuperClassType"),
    TemplateType("前端类型", "cn.sijay.tit.core.dictionary.TemplateType"),
    DisplayType("回显样式", "cn.sijay.tit.core.dictionary.DisplayType"),
    GenderType("性别", "cn.sijay.tit.core.dictionary.GenderType"),
    ;

    @JsonValue
    private final String desc;
    private final String fullName;

}
