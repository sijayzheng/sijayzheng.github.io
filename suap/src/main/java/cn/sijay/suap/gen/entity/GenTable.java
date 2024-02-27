package cn.sijay.suap.gen.entity;

import cn.sijay.suap.core.annotation.QueryColumn;
import cn.sijay.suap.core.base.BaseEntity;
import cn.sijay.suap.core.enums.GenType;
import cn.sijay.suap.core.enums.QueryType;
import cn.sijay.suap.core.enums.TemplateType;
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
import java.util.Set;

/**
 * <p>
 * <em>GenTable 表信息</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/01/26 10:46
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gen_table")
@Schema(name = "GenTable", title = "表信息", description = "表信息")
public class GenTable extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    @Schema(title = "表名")
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[表名]最大长度为{max}")
    @TableField("table_name")
    private String tableName;

    /**
     * 表备注
     */
    @Schema(title = "表备注")
    @QueryColumn(QueryType.LIKE)
    @Size(max = 200, message = "字段[表备注]最大长度为{max}")
    @TableField("comment")
    private String comment;

    /**
     * 实体类名称
     */
    @Schema(title = "实体类名称")
    @Size(max = 100, message = "字段[实体类名称]最大长度为{max}")
    @TableField("class_name")
    private String className;

    /**
     * 模板类型
     */
    @Schema(title = "模板类型")
    @TableField("template")
    private TemplateType template;

    /**
     * 包路径
     */
    @Schema(title = "包路径")
    @Size(max = 100, message = "字段[包路径]最大长度为{max}")
    @TableField("package_name")
    private String packageName;

    /**
     * 模块名
     */
    @Schema(title = "模块名")
    @Size(max = 30, message = "字段[模块名]最大长度为{max}")
    @TableField("module")
    private String module;

    /**
     * 作者
     */
    @Schema(title = "作者")
    @Size(max = 50, message = "字段[作者]最大长度为{max}")
    @TableField("author")
    private String author;

    /**
     * 生成方式
     */
    @Schema(title = "生成方式")
    @TableField("gen_type")
    private GenType genType;

    /**
     * 父类
     */
    @Schema(title = "父类")
    @Size(max = 100, message = "字段[父类]最大长度为{max}")
    @TableField("super_class")
    private String superClass;

    /**
     * 所属菜单
     */
    @Schema(title = "所属菜单")
    @TableField("menu_id")
    private Long menuId;

    /**
     * 是否已生成
     */
    @Schema(title = "是否已生成")
    @TableField("created")
    private YesOrNo created;

    /**
     * 小驼峰类名
     */
    @TableField(exist = false)
    private String lowerName;

    /**
     * 导入的包
     */
    @TableField(exist = false)
    private Set<String> imports;

    /**
     * 表字段
     */
    @TableField(exist = false)
    private List<GenTableColumn> fields;
}
