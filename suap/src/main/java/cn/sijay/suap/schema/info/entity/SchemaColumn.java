package cn.sijay.suap;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "COLUMNS", schema = "information_schema")
public class SchemaColumn {
    @EmbeddedId
    private ColumnId id;

    @Column(name = "ORDINAL_POSITION", columnDefinition = "int UNSIGNED not null")
    private Long ordinalPosition;

    @Size(max = 3)
    @NotNull
    @Column(name = "IS_NULLABLE", nullable = false, length = 3)
    private String isNullable;

    @Lob
    @Column(name = "DATA_TYPE")
    private String dataType;

    @Column(name = "CHARACTER_MAXIMUM_LENGTH")
    private Long characterMaximumLength;

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

}