package cn.sijay.suap.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>OperateType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Getter
@AllArgsConstructor
public enum OperateType {
    OTHER("其它"),
    CREATE("新增"),
    BATCH_CREATE("批量新增"),
    MODIFY("修改"),
    REMOVE("删除"),
    BATCH_REMOVE("批量删除"),
    AUTHORIZE("授权"),
    EXPORT("导出"),
    IMPORT("导入"),
    FORCE_OUT("强退"),
    GENERATE_CODE("生成代码"),
    DROP("清空数据"),
    SELECT("查询"),
    DOWNLOAD("下载"),
    LOGIN("登录"),
    LOGOUT("登出"),
    SAVE("新增或修改"),
    ;

    @JsonValue
    private final String desc;
}
