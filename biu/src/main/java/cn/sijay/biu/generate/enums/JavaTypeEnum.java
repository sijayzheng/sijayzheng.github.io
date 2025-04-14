package cn.sijay.biu.generate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * JavaEnum
 *
 * @author Sijay
 * @since 2024-11-08
 */
@Getter
@AllArgsConstructor
public enum JavaTypeEnum {
    BOOLEAN("Boolean", "", "boolean"),

    INTEGER("Integer", "", "number"),
    LONG("Long", "", "number"),
    BIG_DECIMAL("BigDecimal", "java.math.BigDecimal", "number"),
    FLOAT("Float", "", "number"),
    DOUBLE("Double", "", "number"),

    BYTE_ARRAY("byte[]", "", "any"),

    LOCAL_DATE("LocalDate", "java.time.LocalDate", "string"),
    LOCAL_DATE_TIME("LocalDateTime", "java.time.LocalDateTime", "string"),
    LOCAL_TIME("LocalTime", "java.time.LocalTime", "string"),

    CHARACTER("Character", "", "string"),
    STRING("String", "", "string"),

    LIST("List", "java.util.List", "any[]"),
    LIST_LONG("List<Long>", "java.util.List", "number[]"),
    SET("Set", "java.util.Set", "any[]"),

    DATA_SCOPE_ENUM("DataScopeEnum", "cn.sijay.biu.core.enums.DataScopeEnum", "string"),
    GENDER_TYPE("GenderEnum", "cn.sijay.biu.core.enums.GenderEnum", "string"),
    MENU_TYPE("MenuEnum", "cn.sijay.biu.core.enums.MenuEnum", "string"),
    MESSAGE_TYPE("MessageEnum", "cn.sijay.biu.core.enums.MessageEnum", "string"),
    SHOW_TYPE("ShowEnum", "cn.sijay.biu.core.enums.ShowEnum", "string"),
    ;
    private final String value;
    private final String packageName;
    private final String tsType;

}
