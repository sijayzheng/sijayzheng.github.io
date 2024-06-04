package cn.sijay.suap.gen.entity;

import jakarta.persistence.*;
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
@Table(name = "gen_table")
public class GenTable implements Serializable {
    @Serial
    private static final long serialVersionUID = 8775451518640035693L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "table_name", nullable = false, length = 50)
    private String tableName;

    @Size(max = 200)
    @Column(name = "comment", length = 200)
    private String comment;

    @Size(max = 100)
    @Column(name = "package_name", length = 100)
    private String packageName;

    @Size(max = 30)
    @Column(name = "module_name", length = 30)
    private String moduleName;

    @Size(max = 100)
    @Column(name = "class_name", length = 100)
    private String className;

    @Size(max = 10)
    @Column(name = "template", length = 10)
    private String template;

    @Size(max = 50)
    @Column(name = "author", length = 50)
    private String author;

    @Size(max = 10)
    @Column(name = "gen_type", length = 10)
    private String genType;

    @Size(max = 10)
    @Column(name = "gen_path", length = 10)
    private String genPath;

    @Column(name = "menu_id")
    private Long menuId;

//    @OneToOne
//    @JoinColumn(name = "menu_id", insertable = false, updatable = false)
//    private SysMenu sysMenu;

}