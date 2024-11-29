package cn.sijay.suap.gen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

@Getter
@Setter
@ToString
@Entity
@Immutable
@Table(name = "TABLES", schema = "information_schema")
public class SchemaTable {
    @EmbeddedId
    private TableId id;

    @Lob
    @Column(name = "TABLE_COMMENT")
    private String tableComment;

    public GenTable toGenTable() {
        GenTable genTable = new GenTable();
        genTable.setTableName(id.getTableName());
        genTable.setComment(tableComment);
        return genTable;
    }
}
