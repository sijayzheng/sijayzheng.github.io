package cn.sijay.suap.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>HexEnum</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Getter
@AllArgsConstructor
public enum HexEnum {
    BASE_ENUM_0('0', 0),
    BASE_ENUM_1('1', 1),
    BASE_ENUM_2('2', 2),
    BASE_ENUM_3('3', 3),
    BASE_ENUM_4('4', 4),
    BASE_ENUM_5('5', 5),
    BASE_ENUM_6('6', 6),
    BASE_ENUM_7('7', 7),
    BASE_ENUM_8('8', 8),
    BASE_ENUM_9('9', 9),
    BASE_ENUM_a('a', 10),
    BASE_ENUM_b('b', 11),
    BASE_ENUM_c('c', 12),
    BASE_ENUM_d('d', 13),
    BASE_ENUM_e('e', 14),
    BASE_ENUM_f('f', 15),
    ;
    private final char base;
    private final int val;

    public static int getVal(char base) {
        for (HexEnum baseEnum : HexEnum.values()) {
            if (baseEnum.getBase() == base) {
                return baseEnum.getVal();
            }
        }
        return 0;
    }

    public static char getBase(int val) {
        for (HexEnum baseEnum : HexEnum.values()) {
            if (baseEnum.getVal() == val) {
                return baseEnum.getBase();
            }
        }
        return '0';
    }
}
