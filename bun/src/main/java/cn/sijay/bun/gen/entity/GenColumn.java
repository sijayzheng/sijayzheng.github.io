package cn.sijay.bun.gen.entity;

import cn.sijay.bun.gen.enums.ColumnKeyType;
import cn.sijay.bun.gen.enums.HtmlType;
import cn.sijay.bun.gen.enums.JavaType;
import cn.sijay.bun.gen.enums.QueryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.io.Serializable;

/**
 * <strong>GenColumn</strong>
 * <p>
 * 代码生成表定义
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
@Comment("代码生成表定义")
@Entity
@Table(name = "gen_column")
public class GenColumn implements Serializable {
    @Serial
    private static final long serialVersionUID = -2694054299786734327L;

    /**
     * 编号id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 表编号
     */
    @NotNull
    @Column(name = "table_id", nullable = false)
    private Long tableId;

    /**
     * 字段名
     */
    @Size(max = 64)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "column_name", nullable = false, length = 64)
    private String columnName;

    /**
     * 字段描述
     */
    @Size(max = 255)
    @NotNull
    @Column(name = "column_comment", nullable = false)
    private String columnComment;

    /**
     * 字段类型
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "data_type", nullable = false, length = 32)
    private String dataType;

    /**
     * 排序
     */
    @ColumnDefault("0")
    @Column(name = "sort")
    private Integer sort;

    /**
     * 可空
     */
    @ColumnDefault("0")
    @Column(name = "nullable")
    private Boolean nullable;

    /**
     * 文本长度
     */
    @Column(name = "length")
    private Long length;

    /**
     * 数值长度
     */
    @Column(name = "numeric_precision")
    private Integer numericPrecision;

    /**
     * 小数位数
     */
    @Column(name = "numeric_scale")
    private Integer numericScale;

    /**
     * 列类型
     */
    @ColumnDefault("'NONE'")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "column_key", length = 4)
    private ColumnKeyType columnKey;

    /**
     * java 属性名
     */
    @Size(max = 64)
    @NotNull
    @Column(name = "java_field", nullable = false, length = 64)
    private String javaField;

    /**
     * java 属性类型
     */
    @ColumnDefault("'STRING'")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "java_type", length = 15)
    private JavaType javaType;

    /**
     * 字典类型
     */
    @Size(max = 200)
    @Column(name = "dict_type", length = 200)
    private String dictType;

    /**
     * 可插入
     */
    @ColumnDefault("0")
    @Column(name = "insertable")
    private Boolean insertable;

    /**
     * 可编辑
     */
    @ColumnDefault("0")
    @Column(name = "editable")
    private Boolean editable;

    /**
     * 可列出
     */
    @ColumnDefault("0")
    @Column(name = "listable")
    private Boolean listable;

    /**
     * 可查询
     */
    @ColumnDefault("0")
    @Column(name = "queryable")
    private Boolean queryable;

    /**
     * 查询方式
     */
    @ColumnDefault("'NONE'")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "query_type", length = 10)
    private QueryType queryType;

    /**
     * 显示类型
     */
    @ColumnDefault("'INPUT'")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "html_type", length = 15)
    private HtmlType htmlType;

    /**
     * 可导出
     */
    @ColumnDefault("0")
    @Column(name = "exportable")
    private Boolean exportable;

    /**
     * 默认值
     */
    @Size(max = 500)
    @Column(name = "default_value", length = 500)
    private String defaultValue;

    public boolean isPk() {
        return ColumnKeyType.PRI.equals(columnKey);
    }
}
