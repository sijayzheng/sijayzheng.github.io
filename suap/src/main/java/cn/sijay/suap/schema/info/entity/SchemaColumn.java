package cn.sijay.suap.schema.info.entity;

import cn.sijay.suap.core.enums.ColumnKey;
import cn.sijay.suap.gen.entity.GenTableColumn;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import java.util.Objects;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@ToString
@Immutable
@Table(name = "COLUMNS", schema = "information_schema")
public class SchemaColumn {
    @EmbeddedId
    private ColumnId id;

    @Column(name = "ORDINAL_POSITION", columnDefinition = "int UNSIGNED not null")
    private Integer ordinalPosition;

    @Size(max = 3)
    @NotNull
    @Column(name = "IS_NULLABLE", nullable = false, length = 3)
    private String isNullable;

    @Lob
    @Column(name = "DATA_TYPE")
    private String dataType;

    @Column(name = "CHARACTER_MAXIMUM_LENGTH")
    private Integer characterMaximumLength;

    @NotNull
    @Lob
    @Column(name = "COLUMN_TYPE", nullable = false)
    private String columnType;

    @NotNull
    @Lob
    @Column(name = "COLUMN_KEY", nullable = false)
    private ColumnKey columnKey;

    @NotNull
    @Lob
    @Column(name = "COLUMN_COMMENT", nullable = false)
    private String columnComment;

    public GenTableColumn toGenTableColumn() {
        GenTableColumn genTableColumn = new GenTableColumn();
        genTableColumn.setColumnName(id.getColumnName());
        genTableColumn.setColumnComment(columnComment);
        genTableColumn.setDataType(Objects.equals("tinyint(1)", columnType) ? "boolean" : dataType);
        genTableColumn.setLength(characterMaximumLength);
        genTableColumn.setColumnKey(columnKey);
        genTableColumn.setNullable(Objects.equals("YES", isNullable));
        genTableColumn.setSort(ordinalPosition);
        return genTableColumn;
    }
}