/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态类型
 *
 * @author sijay
 * @date 2022/9/3 17:27
 */
@AllArgsConstructor
@Getter
public enum CommResultTypeEnum {
    /**
     * 成功
     */
    SUCCESS(0, "操作成功"),
    /**
     * 错误
     */
    ERROR(1, "操作失败"),

    ;
    private final int code;
    private final String msg;

}
