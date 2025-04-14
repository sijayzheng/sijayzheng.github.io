package cn.sijay.suap.gen.entity;

import cn.sijay.suap.core.enums.JavaType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serial;
import java.io.Serializable;

/**
 * <strong>GenTableColumn</strong>
 * <p>
 * 列信息
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "gen_table_column")
public class GenTableColumn implements Serializable {
    @Serial
    private static final long serialVersionUID = -6073197071277783363L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 表
     */
    @NotNull
    @Column(name = "table_id", nullable = false)
    private Long tableId;

    /**
     * 列名
     */
    @Size(max = 50)
    @NotNull
    @Column(name = "column_name", nullable = false, length = 50)
    private String columnName;

    /**
     * 列描述
     */
    @Size(max = 200)
    @Column(name = "column_comment", length = 200)
    private String columnComment;

    /**
     * 数据库类型
     */
    @Size(max = 50)
    @NotNull
    @Column(name = "data_type", nullable = false, length = 50)
    private String dataType;

    /**
     * 长度
     */
    @Column(name = "length")
    private Integer length;

    /**
     * Java类型
     */
    @Size(max = 100)
    @ColumnDefault("''")
    @Column(name = "java_type", length = 100)
    private String javaType;

    private transient JavaType javaTypeEnum;

    /**
     * Java属性
     */
    @Size(max = 100)
    @ColumnDefault("''")
    @Column(name = "field_name", length = 100)
    private String fieldName;

    /**
     * 列类型
     */
    @Size(max = 5)
    @ColumnDefault("''")
    @Column(name = "column_key", length = 5)
    private String columnKey;

    /**
     * 可添加
     */
    @ColumnDefault("1")
    @Column(name = "addable")
    private Boolean addable;

    /**
     * 可编辑
     */
    @ColumnDefault("1")
    @Column(name = "editable")
    private Boolean editable;

    /**
     * 可显示
     */
    @ColumnDefault("1")
    @Column(name = "visible")
    private Boolean visible;

    /**
     * 可查询
     */
    @ColumnDefault("1")
    @Column(name = "queryable")
    private Boolean queryable;

    /**
     * 查询方式
     */
    @Size(max = 20)
    @ColumnDefault("'like'")
    @Column(name = "query_type", length = 20)
    private String queryType;

    /**
     * 是否可为空
     */
    @ColumnDefault("1")
    @Column(name = "nullable")
    private Boolean nullable;

    /**
     * 输入类型
     */
    @Size(max = 20)
    @ColumnDefault("'text_input'")
    @Column(name = "input_type", length = 20)
    private String inputType;

    /**
     * 字典类型
     */
    @Size(max = 100)
    @ColumnDefault("''")
    @Column(name = "dict_type", length = 100)
    private String dictType;

    /**
     * 是否是导入导出字段
     */
    @ColumnDefault("0")
    @Column(name = "excel_column")
    private Boolean excelColumn;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

}
