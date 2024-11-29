package cn.sijay.suap.gen.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@Embeddable
public class ColumnId implements Serializable {
    @Serial
    private static final long serialVersionUID = -4891461892287134215L;

    @NotNull
    @Column(name = "TABLE_SCHEMA", nullable = false, length = 64)
    private String tableSchema;

    @NotNull
    @Column(name = "TABLE_NAME", nullable = false, length = 64)
    private String tableName;

    @Column(name = "COLUMN_NAME", length = 64)
    private String columnName;

    @Override
    public int hashCode() {
        return Objects.hash(tableSchema, tableName, columnName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        ColumnId entity = (ColumnId) o;
        return Objects.equals(this.tableSchema, entity.tableSchema) &&
            Objects.equals(this.tableName, entity.tableName) &&
            Objects.equals(this.columnName, entity.columnName);
    }

}
