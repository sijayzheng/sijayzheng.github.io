package cn.sijay.suap.sys.entity;

import cn.sijay.suap.core.annotation.ExcelProperty;
import cn.sijay.suap.core.base.BaseEntity;
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
 * <em>SysPost 岗位信息</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_post")
@Schema(name = "SysPost", title = "岗位信息", description = "岗位信息")
public class SysPost extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 岗位名称
     */
    @Schema(title = "岗位名称")
    @ExcelProperty(value = "岗位名称", sort = 0)
    @Size(max = 50, message = "字段[岗位名称]最大长度为{max}")
    @TableField("name")
    private String name;

    /**
     * 岗位编码
     */
    @Schema(title = "岗位编码")
    @ExcelProperty(value = "岗位编码", sort = 1)
    @Size(max = 50, message = "字段[岗位编码]最大长度为{max}")
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
