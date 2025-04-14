package cn.sijay.biu.generate.entity;

import cn.sijay.biu.core.base.IdEntity;
import cn.sijay.biu.generate.enums.GenEnum;
import cn.sijay.biu.generate.enums.TemplateEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.util.List;

/**
 * GenTable
 * 代码生成表定义
 *
 * @author Sijay
 * @since 2024-11-01
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Comment("代码生成表定义")
@Entity
@Table(name = "gen_table")
public class GenTable extends IdEntity {

    /**
     * 表名称
     */
    @Comment("表名称")
    @Size(max = 200)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "table_name", nullable = false, length = 200)
    private String tableName;

    /**
     * 表描述
     */
    @Comment("表描述")
    @Size(max = 500)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "table_comment", nullable = false, length = 500)
    private String tableComment;

    /**
     * 类名称
     */
    @Comment("类名称")
    @Size(max = 100)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "class_name", nullable = false, length = 100)
    private String className;

    /**
     * 类描述
     */
    @Comment("类描述")
    @Size(max = 50)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "class_comment", nullable = false, length = 50)
    private String classComment;

    /**
     * 模板类型
     */
    @Comment("模板类型")
    @ColumnDefault("'LIST'")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "template_type", length = 4)
    private TemplateEnum templateType;

    /**
     * 生成包路径
     */
    @Comment("生成包路径")
    @Size(max = 100)
    @ColumnDefault("''")
    @Column(name = "package_name", length = 100)
    private String packageName;

    /**
     * 生成模块名
     */
    @Comment("生成模块名")
    @Size(max = 30)
    @ColumnDefault("''")
    @Column(name = "module_name", length = 30)
    private String moduleName;

    /**
     * 作者
     */
    @Comment("作者")
    @Size(max = 50)
    @ColumnDefault("''")
    @Column(name = "author", length = 50)
    private String author;

    /**
     * 生成类型
     */
    @Comment("生成类型")
    @ColumnDefault("'ZIP'")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "gen_type", length = 10)
    private GenEnum genType;

    /**
     * 父菜单编号
     */
    @Comment("父菜单编号")
    @Column(name = "parent_menu_id")
    private Long parentMenuId;

    /**
     * 树节点标识
     */
    @Comment("树节点标识")
    @Column(name = "tree_key", length = 20)
    private String treeKey;

    /**
     * 树节点标签
     */
    @Comment("树节点标签")
    @Column(name = "tree_label", length = 20)
    private String treeLabel;

    /**
     * 树父节点标识
     */
    @Comment("树父节点标识")
    @Column(name = "tree_parent_key", length = 20)
    private String treeParentKey;

    @Transient
    private transient List<GenColumn> columns;

    @Transient
    private transient GenColumn pkColumn;

}
