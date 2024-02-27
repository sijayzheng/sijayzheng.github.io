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
 * <em>SysDept 部门信息</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dept")
@Schema(name = "SysDept", title = "部门信息", description = "部门信息")
public class SysDept extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 上级
     */
    @Schema(title = "上级")
    @ExcelProperty(value = "上级", sort = 0)
    @TableField("parent_id")
    private Long parentId;

    /**
     * 部门名称
     */
    @Schema(title = "部门名称")
    @ExcelProperty(value = "部门名称", sort = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[部门名称]最大长度为{max}")
    @TableField("name")
    private String name;

    /**
     * 部门负责人
     */
    @Schema(title = "部门负责人")
    @ExcelProperty(value = "部门负责人", sort = 2)
    @TableField("leader")
    private Long leader;

    /**
     * 部门电话
     */
    @Schema(title = "部门电话")
    @ExcelProperty(value = "部门电话", sort = 3)
    @Size(max = 11, message = "字段[部门电话]最大长度为{max}")
    @TableField("phone")
    private String phone;

    /**
     * 排序
     */
    @Schema(title = "排序")
    @ExcelProperty(value = "排序", sort = 4)
    @TableField("sort")
    private Integer sort;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    @TableField("deleted")
    private YesOrNo deleted;

}
