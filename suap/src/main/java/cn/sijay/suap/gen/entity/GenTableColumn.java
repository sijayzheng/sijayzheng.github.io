package cn.sijay.suap.gen.entity;

import cn.sijay.suap.core.annotation.ExcelProperty;
import cn.sijay.suap.core.annotation.QueryColumn;
import cn.sijay.suap.core.base.BaseEntity;
import cn.sijay.suap.core.enums.*;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.Map;

/**
 * <p>
 * <em>GenTableColumn 列信息</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/01/26 10:46
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gen_table_column")
@Schema(name = "GenTableColumn", title = "列信息", description = "列信息")
public class GenTableColumn extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
    @Schema(title = "表id")
    @QueryColumn(QueryType.EQUAL)
    @TableField("table_id")
    private Long tableId;

    /**
     * 列名
     */
    @Schema(title = "列名")
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[列名]最大长度为{max}")
    @TableField("name")
    private String name;

    /**
     * 列注释
     */
    @Schema(title = "列注释")
    @QueryColumn(QueryType.LIKE)
    @Size(max = 200, message = "字段[列注释]最大长度为{max}")
    @TableField("comment")
    private String comment;

    /**
     * 列类型
     */
    @Schema(title = "列类型")
    @Size(max = 50, message = "字段[列类型]最大长度为{max}")
    @TableField("type")
    private String type;

    /**
     * 长度
     */
    @Schema(title = "长度")
    @TableField("length")
    private Integer length;

    /**
     * 小数位
     */
    @Schema(title = "小数位")
    @TableField("scale")
    private Integer scale;

    /**
     * 字段名称
     */
    @Schema(title = "字段名称")
    @Size(max = 100, message = "字段[字段名称]最大长度为{max}")
    @TableField("field_name")
    private String fieldName;

    /**
     * 实体类类型
     */
    @Schema(title = "实体类类型")
    @TableField("java_type")
    private JavaType javaType;

    /**
     * 输入类型
     */
    @Schema(title = "输入类型")
    @TableField("input_type")
    private InputType inputType;

    /**
     * 是否显示
     */
    @Schema(title = "是否显示")
    @TableField("visible")
    private YesOrNo visible;

    /**
     * 是否可编辑
     */
    @Schema(title = "是否可编辑")
    @TableField("editable")
    private YesOrNo editable;

    /**
     * 是否必填
     */
    @Schema(title = "是否必填")
    @TableField("required")
    private YesOrNo required;

    /**
     * 是否可查询
     */
    @Schema(title = "是否可查询")
    @TableField("queryable")
    private YesOrNo queryable;

    /**
     * 查询方式
     */
    @Schema(title = "查询方式")
    @TableField("query_type")
    private QueryType queryType;

    /**
     * 排序
     */
    @Schema(title = "排序")
    @TableField("sort")
    private Integer sort;

    /**
     * 是否主键
     */
    @Schema(title = "是否主键")
    @TableField("primary_key")
    private YesOrNo primaryKey;

    /**
     * 是否可为空
     */
    @Schema(title = "是否可为空")
    @TableField("nullable")
    private YesOrNo nullable;

    /**
     * 是否父类字段
     */
    @Schema(title = "是否父类字段")
    @TableField("super_column")
    private YesOrNo superColumn;

    /**
     * 是否唯一
     */
    @Schema(title = "是否唯一")
    @TableField("unique_key")
    private YesOrNo uniqueKey;

    /**
     * 是否是导入导出字段
     */
    @Schema(title = "是否是导入导出字段")
    @ExcelProperty(value = "是否是导入导出字段", sort = 0)
    @QueryColumn(QueryType.EQUAL)
    @TableField("excel_column")
    private YesOrNo excelColumn;

    /**
     * 数据来源
     */
    @Schema(title = "数据来源")
    @TableField("data_source")
    private DataSource dataSource;

    /**
     * 数据
     */
    @Schema(title = "数据")
    @TableField("data")
    private String data;

    @TableField(exist = false)
    private String getMethodName;

    @TableField(exist = false)
    private Map<String, String> optionsMap;

    @TableField(exist = false)
    private boolean disable;

    public boolean getBoolQueryable() {
        return YesOrNo.isYes(queryable);
    }

    public boolean getBoolUnique() {
        return YesOrNo.isYes(uniqueKey);
    }

    public boolean getBoolSuperColumn() {
        return YesOrNo.isYes(superColumn);
    }

    public boolean getBoolExcelColumn() {
        return YesOrNo.isYes(excelColumn);
    }

    public boolean getBoolVisible() {
        return YesOrNo.isYes(visible);
    }

    public boolean getBoolEditable() {
        return YesOrNo.isYes(editable);
    }

    public boolean getBoolRequired() {
        return YesOrNo.isYes(required);
    }

    public boolean getBoolPrimaryKey() {
        return YesOrNo.isYes(primaryKey);
    }
}
