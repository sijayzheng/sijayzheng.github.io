package cn.sijay.biu.generate.entity;

import cn.sijay.biu.core.base.IdEntity;
import cn.sijay.biu.core.util.StringUtil;
import cn.sijay.biu.generate.enums.ColumnKeyEnum;
import cn.sijay.biu.generate.enums.HtmlTypeEnum;
import cn.sijay.biu.generate.enums.JavaTypeEnum;
import cn.sijay.biu.generate.enums.QueryTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.io.Serial;

/**
 * GenColumn
 * <p>
 * 代码生成列定义
 * </p>
 *
 * @author Sijay
 * @since 2024-11-01
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Comment("代码生成列定义")
@Entity
@Table(name = "gen_column")
public class GenColumn extends IdEntity {
    @Serial
    private static final long serialVersionUID = -2694054299786734327L;

    /**
     * 表编号
     */
    @Comment("表编号")
    @NotNull
    @Column(name = "table_id", nullable = false)
    private Long tableId;

    /**
     * 字段名
     */
    @Comment("字段名")
    @Size(max = 64)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "column_name", nullable = false, length = 64)
    private String columnName;

    /**
     * 字段描述
     */
    @Comment("字段描述")
    @Size(max = 255)
    @NotNull
    @Column(name = "column_comment", nullable = false)
    private String columnComment;

    /**
     * 字段类型
     */
    @Comment("字段类型")
    @Size(max = 32)
    @NotNull
    @Column(name = "data_type", nullable = false, length = 32)
    private String dataType;

    /**
     * 排序
     */
    @Comment("排序")
    @ColumnDefault("0")
    @Column(name = "sort")
    private Integer sort;

    /**
     * 可空
     */
    @Comment("可空")
    @ColumnDefault("0")
    @Column(name = "nullable")
    private Boolean nullable;

    /**
     * 文本长度
     */
    @Comment("文本长度")
    @Column(name = "length")
    private Long length;

    /**
     * 数值长度
     */
    @Comment("数值长度")
    @Column(name = "numeric_precision")
    private Integer numericPrecision;

    /**
     * 小数位数
     */
    @Comment("小数位数")
    @Column(name = "numeric_scale")
    private Integer numericScale;

    /**
     * 列类型
     */
    @Comment("列类型")
    @ColumnDefault("'NONE'")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "column_key", length = 4)
    private ColumnKeyEnum columnKey;

    /**
     * java 属性名
     */
    @Comment("java 属性名")
    @Size(max = 64)
    @NotNull
    @Column(name = "java_field", nullable = false, length = 64)
    private String javaField;

    /**
     * java 属性类型
     */
    @Comment("java 属性类型")
    @ColumnDefault("'STRING'")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "java_type", length = 15)
    private JavaTypeEnum javaType;

    /**
     * 字典类型
     */
    @Comment("字典类型")
    @Size(max = 200)
    @Column(name = "dict_type", length = 200)
    private String dictType;

    /**
     * 可编辑
     */
    @Comment("可编辑")
    @ColumnDefault("0")
    @Column(name = "editable")
    private Boolean editable;

    /**
     * 可列出
     */
    @Comment("可列出")
    @ColumnDefault("0")
    @Column(name = "listable")
    private Boolean listable;

    /**
     * 可查询
     */
    @Comment("可查询")
    @ColumnDefault("0")
    @Column(name = "queryable")
    private Boolean queryable;

    /**
     * 查询方式
     */
    @Comment("查询方式")
    @ColumnDefault("'NONE'")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "query_type", length = 10)
    private QueryTypeEnum queryType;

    /**
     * 显示类型
     */
    @Comment("显示类型")
    @ColumnDefault("'INPUT'")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "html_type", length = 15)
    private HtmlTypeEnum htmlType;

    /**
     * 可导出
     */
    @Comment("可导出")
    @ColumnDefault("0")
    @Column(name = "exportable")
    private Boolean exportable;

    /**
     * 默认值
     */
    @Comment("默认值")
    @Size(max = 500)
    @Column(name = "default_value", length = 500)
    private String defaultValue;

    @Transient
    private transient String getter;

    public boolean isPk() {
        return columnKey == ColumnKeyEnum.PRI;
    }

    public boolean isUnique() {
        return columnKey == ColumnKeyEnum.UNI;
    }

    public String quotesType() {
        if (StringUtil.containsAnyIgnoreCase(columnName, "tinyint", "smallint", "mediumint", "int", "integer", "year", "bigint", "float", "double", "decimal", "numeric", "bit")) {
            return "N";
        } else if (StringUtil.containsAnyIgnoreCase(columnName, "date", "time", "datetime", "timestamp", "enum", "set", "char", "varchar", "text", "tinytext", "mediumtext", "longtext")) {
            return "Y";
        }
        return "";
    }
}
