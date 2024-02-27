package cn.sijay.suap.sys.entity;

import cn.sijay.suap.core.annotation.ExcelProperty;
import cn.sijay.suap.core.annotation.QueryColumn;
import cn.sijay.suap.core.base.BaseEntity;
import cn.sijay.suap.core.enums.QueryType;
import cn.sijay.suap.core.enums.YesOrNo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;

/**
 * <p>
 * <em>SysNotice 通知公告</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_notice")
@Schema(name = "SysNotice", title = "通知公告", description = "通知公告")
public class SysNotice extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @Schema(title = "标题")
    @ExcelProperty(value = "标题", sort = 0)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[标题]最大长度为{max}")
    @TableField("title")
    private String title;

    /**
     * 内容
     */
    @Schema(title = "内容")
    @ExcelProperty(value = "内容", sort = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 500, message = "字段[内容]最大长度为{max}")
    @TableField("content")
    private String content;

    /**
     * 类型
     */
    @Schema(title = "类型")
    @ExcelProperty(value = "类型", sort = 2)
    @Size(max = 10, message = "字段[类型]最大长度为{max}")
    @TableField("type")
    private String type;

    /**
     * 状态
     */
    @Schema(title = "状态")
    @ExcelProperty(value = "状态", sort = 3)
    @Size(max = 10, message = "字段[状态]最大长度为{max}")
    @TableField("status")
    private String status;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    @TableField("deleted")
    private YesOrNo deleted;

}
