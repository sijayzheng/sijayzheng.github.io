/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sijay
 * @date 2023/3/23 10:06
 */
@Getter
@AllArgsConstructor
public enum NumberBaseEnum {
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
        for (NumberBaseEnum baseEnum : NumberBaseEnum.values()) {
            if (baseEnum.getBase() == base) {
                return baseEnum.getVal();
            }
        }
        return 0;
    }

    public static char getBase(int val) {
        for (NumberBaseEnum baseEnum : NumberBaseEnum.values()) {
            if (baseEnum.getVal() == val) {
                return baseEnum.getBase();
            }
        }
        return '0';
    }
}
