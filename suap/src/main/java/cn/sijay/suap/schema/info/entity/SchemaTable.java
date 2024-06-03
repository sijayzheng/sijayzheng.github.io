package cn.sijay.suap.schema.info.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
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
@jakarta.persistence.Table(name = "TABLES", schema = "information_schema")
public class Table {
    @EmbeddedId
    private TableId id;

    @Lob
    @Column(name = "TABLE_COMMENT")
    private String tableComment;

}