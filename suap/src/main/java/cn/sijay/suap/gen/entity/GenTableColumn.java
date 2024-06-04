package cn.sijay.suap.gen.entity;

import cn.sijay.suap.core.enums.ColumnKey;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "gen_table_column")
public class GenTableColumn implements Serializable {
    @Serial
    private static final long serialVersionUID = 182559477709873458L;
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "table_id", nullable = false)
    private Long tableId;

    @Size(max = 50)
    @NotNull
    @Column(name = "column_name", nullable = false, length = 50)
    private String columnName;

    @Size(max = 200)
    @Column(name = "column_comment", length = 200)
    private String columnComment;

    @Size(max = 50)
    @NotNull
    @Column(name = "data_type", nullable = false, length = 50)
    private String dataType;

    @Column(name = "length")
    private Integer length;

    @Size(max = 100)
    @Column(name = "java_type", length = 100)
    private String javaType;

    @Size(max = 100)
    @Column(name = "field_name", length = 100)
    private String fieldName;

    @Size(max = 1)
    @Column(name = "column_type", length = 1)
    private ColumnKey columnKey;

    @Column(name = "nullable")
    private Boolean nullable;

    @Size(max = 20)
    @Column(name = "input_type", length = 20)
    private String inputType;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "addable")
    private Boolean addable;

    @Column(name = "editable")
    private Boolean editable;

    @Column(name = "queryable")
    private Boolean queryable;

    @Size(max = 20)
    @Column(name = "query_type", length = 20)
    private String queryType;

    @Column(name = "excel_column")
    private Boolean excelColumn;

    @Size(max = 200)
    @Column(name = "dict_type", length = 200)
    private String dictType;

    @Column(name = "sort")
    private Integer sort;

}