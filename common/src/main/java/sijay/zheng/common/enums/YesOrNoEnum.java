package sijay.zheng.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sijay
 * @description YesOrNoEnum
 * @date 2023/9/28 9:46
 */
@Getter
@AllArgsConstructor
public enum YesOrNoEnum {
    Y("Y", "是"),
    N("N", "否"),
    YES("YES", "是"),
    NO("NO", "否"),
    ;

    private final String code;
    private final String desc;

    public static YesOrNoEnum getByCode(String code) {
        if (code == null) {
            return YesOrNoEnum.N;
        }
        for (YesOrNoEnum yesOrNoEnum : YesOrNoEnum.values()) {
            if (yesOrNoEnum.getCode().equals(code)) {
                return yesOrNoEnum; // 获取到当前YesOrNoEnum信息
            }
        }
        return YesOrNoEnum.N;
    }

    public static boolean isYes(YesOrNoEnum yesOrNoEnum) {
        return Y.equals(yesOrNoEnum) || YES.equals(yesOrNoEnum);
    }

    public static boolean isNo(YesOrNoEnum yesOrNoEnum) {
        return N.equals(yesOrNoEnum) || NO.equals(yesOrNoEnum);
    }

}
