package cn.sijay.suap.gen.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * <strong>GenTable</strong>
 * <p>
 * 表信息
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "gen_table")
public class GenTable implements Serializable {
    @Serial
    private static final long serialVersionUID = -2804467881506144901L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 表名
     */
    @Size(max = 50)
    @NotNull
    @Column(name = "table_name", nullable = false, length = 50)
    private String tableName;

    /**
     * 表描述
     */
    @Size(max = 200)
    @Column(name = "comment", length = 200)
    private String comment;

    /**
     * 实体类名
     */
    @Size(max = 100)
    @ColumnDefault("''")
    @Column(name = "class_name", length = 100)
    private String className;

    /**
     * 模板类型
     */
    @Size(max = 10)
    @ColumnDefault("'list'")
    @Column(name = "template", length = 10)
    private String template;

    /**
     * 包路径
     */
    @Size(max = 100)
    @ColumnDefault("'cn.sijay.suap'")
    @Column(name = "package_name", length = 100)
    private String packageName;

    /**
     * 模块名
     */
    @Size(max = 30)
    @ColumnDefault("''")
    @Column(name = "module_name", length = 30)
    private String moduleName;

    /**
     * 作者
     */
    @Size(max = 50)
    @ColumnDefault("'sijay'")
    @Column(name = "author", length = 50)
    private String author;

    /**
     * 所属菜单
     */
    @Column(name = "menu_id")
    private Long menuId;

    private transient List<GenTableColumn> fields;

    private transient Set<String> imports;

    private transient String businessName;
}
