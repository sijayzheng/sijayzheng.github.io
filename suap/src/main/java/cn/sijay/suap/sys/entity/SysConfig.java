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
 * <em>SysConfig 系统配置</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_config")
@Schema(name = "SysConfig", title = "系统配置", description = "系统配置")
public class SysConfig extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 配置名称
     */
    @Schema(title = "配置名称")
    @ExcelProperty(value = "配置名称", sort = 0)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[配置名称]最大长度为{max}")
    @TableField("name")
    private String name;

    /**
     * 配置编码
     */
    @Schema(title = "配置编码")
    @ExcelProperty(value = "配置编码", sort = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[配置编码]最大长度为{max}")
    @TableField("code")
    private String code;

    /**
     * 配置值
     */
    @Schema(title = "配置值")
    @ExcelProperty(value = "配置值", sort = 2)
    @Size(max = 50, message = "字段[配置值]最大长度为{max}")
    @TableField("value")
    private String value;

    /**
     * 排序
     */
    @Schema(title = "排序")
    @ExcelProperty(value = "排序", sort = 3)
    @TableField("sort")
    private Integer sort;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    @TableField("deleted")
    private YesOrNo deleted;

}
