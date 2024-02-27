package cn.sijay.suap.data.entity;

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
 * <em>DataRegion 行政区划数据</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("data_region")
@Schema(name = "DataRegion", title = "行政区划数据", description = "行政区划数据")
public class DataRegion extends BaseEntity {

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
     * 名称
     */
    @Schema(title = "名称")
    @ExcelProperty(value = "名称", sort = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[名称]最大长度为{max}")
    @TableField("name")
    private String name;

    /**
     * 编码
     */
    @Schema(title = "编码")
    @ExcelProperty(value = "编码", sort = 2)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[编码]最大长度为{max}")
    @TableField("code")
    private String code;

    /**
     * 级别
     */
    @Schema(title = "级别")
    @ExcelProperty(value = "级别", sort = 3)
    @TableField("level")
    private Integer level;

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
