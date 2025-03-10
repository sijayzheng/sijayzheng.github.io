package cn.sijay.bun.gen.entity;

import cn.sijay.bun.gen.enums.GenType;
import cn.sijay.bun.gen.enums.TemplateType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * <strong>GenTable</strong>
 * <p>
 * 代码生成列定义
 * </p>
 *
 * @author sijay
 * @since 2024-11-01
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Comment("代码生成列定义")
@Entity
@Table(name = "gen_table")
public class GenTable implements Serializable {
    @Serial
    private static final long serialVersionUID = 3974079746569537798L;

    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 表名称
     */
    @Size(max = 200)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "table_name", nullable = false, length = 200)
    private String tableName;

    /**
     * 表描述
     */
    @Size(max = 500)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "table_comment", nullable = false, length = 500)
    private String tableComment;

    /**
     * 类名称
     */
    @Size(max = 100)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "class_name", nullable = false, length = 100)
    private String className;

    /**
     * 类描述
     */
    @Size(max = 50)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "class_comment", nullable = false, length = 50)
    private String classComment;

    /**
     * 模板类型
     */
    @ColumnDefault("'LIST'")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "template_type", length = 4)
    private TemplateType templateType;

    /**
     * 生成包路径
     */
    @Size(max = 100)
    @ColumnDefault("''")
    @Column(name = "package_name", length = 100)
    private String packageName;

    /**
     * 生成模块名
     */
    @Size(max = 30)
    @ColumnDefault("''")
    @Column(name = "module_name", length = 30)
    private String moduleName;

    /**
     * 作者
     */
    @Size(max = 50)
    @ColumnDefault("''")
    @Column(name = "author", length = 50)
    private String author;

    /**
     * 生成类型
     */
    @ColumnDefault("'ZIP'")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "gen_type", length = 10)
    private GenType genType;

    /**
     * 生成路径
     */
    @Size(max = 200)
    @ColumnDefault("'/'")
    @Column(name = "gen_path", length = 200)
    private String genPath;

    /**
     * 父菜单编号
     */
    @Column(name = "parent_menu_id")
    private Long parentMenuId;

    /**
     * 树表的父字段
     */
    @Column(name = "tree_parent_column")
    private Long treeParentColumn;

    @Transient
    private transient List<GenColumn> columns;

}
