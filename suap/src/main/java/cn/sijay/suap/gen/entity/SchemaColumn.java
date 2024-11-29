package cn.sijay.suap.gen.entity;

import java.util.Objects;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Immutable
@Table(name = "COLUMNS", schema = "information_schema")
public class SchemaColumn {
  @EmbeddedId
  private ColumnId id;

  @Column(name = "ORDINAL_POSITION", columnDefinition = "int UNSIGNED not null")
  private Integer ordinalPosition;

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
  private String columnKey;

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
