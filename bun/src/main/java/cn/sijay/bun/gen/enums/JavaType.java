package cn.sijay.bun.gen.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>JavaType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-08
 */
@Getter
@AllArgsConstructor
public enum JavaType {
    BOOLEAN("Boolean", ""),
    INTEGER("Integer", ""),
    LONG("Long", ""),
    BIG_DECIMAL("BigDecimal", "java.math.BigDecimal"),
    FLOAT("Float", ""),
    DOUBLE("Double", ""),
    BYTE_ARRAY("Byte[]", ""),
    LOCAL_DATE("LocalDate", "java.time.LocalDate"),
    LOCAL_DATE_TIME("LocalDateTime", "java.time.LocalDateTime"),
    LOCAL_TIME("LocalTime", "java.time.LocalTime"),
    CHARACTER("Character", ""),
    STRING("String", ""),
    LIST("List", "java.util.List"),
    LIST_LONG("List<Long>", "java.util.List"),
    SET("Set", "java.util.Set"),
    DATA_SCOPE_ENUM("DataScopeEnum", "cn.sijay.bun.common.enums.DataScopeEnum"),
    GENDER_TYPE("GenderType", "cn.sijay.bun.common.enums.GenderType"),
    MENU_TYPE("MenuType", "cn.sijay.bun.common.enums.MenuType"),
    MESSAGE_TYPE("MessageType", "cn.sijay.bun.common.enums.MessageType"),
    SHOW_TYPE("ShowType", "cn.sijay.bun.common.enums.ShowType"),
    ;
    private final String value;
    private final String packageName;

}
