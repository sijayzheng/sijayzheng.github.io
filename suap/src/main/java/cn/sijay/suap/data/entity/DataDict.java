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
import java.util.List;

/**
 * <p>
 * <em>DataDict 数据字典</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("data_dict")
@Schema(name = "DataDict", title = "数据字典", description = "数据字典")
public class DataDict extends BaseEntity {

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
     * 字典名称
     */
    @Schema(title = "字典名称")
    @ExcelProperty(value = "字典名称", sort = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[字典名称]最大长度为{max}")
    @TableField("name")
    private String name;

    /**
     * 字典编码
     */
    @Schema(title = "字典编码")
    @ExcelProperty(value = "字典编码", sort = 2)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[字典编码]最大长度为{max}")
    @TableField("code")
    private String code;

    /**
     * 字典值
     */
    @Schema(title = "字典值")
    @ExcelProperty(value = "字典值", sort = 3)
    @Size(max = 50, message = "字段[字典值]最大长度为{max}")
    @TableField("value")
    private String value;

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

    @TableField(exist = false)
    private List<DataDict> children;
}
