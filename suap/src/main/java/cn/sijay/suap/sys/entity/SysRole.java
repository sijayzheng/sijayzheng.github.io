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
 * <em>SysRole 角色信息</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
@Schema(name = "SysRole", title = "角色信息", description = "角色信息")
public class SysRole extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @Schema(title = "角色名称")
    @ExcelProperty(value = "角色名称", sort = 0)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[角色名称]最大长度为{max}")
    @TableField("name")
    private String name;

    /**
     * 角色编码
     */
    @Schema(title = "角色编码")
    @ExcelProperty(value = "角色编码", sort = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[角色编码]最大长度为{max}")
    @TableField("code")
    private String code;

    /**
     * 排序
     */
    @Schema(title = "排序")
    @ExcelProperty(value = "排序", sort = 2)
    @TableField("sort")
    private Integer sort;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    @TableField("deleted")
    private YesOrNo deleted;

}
