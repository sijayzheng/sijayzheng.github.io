package cn.sijay.bun.system.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <strong>SystemDept</strong>
 * <p>
 * 系统部门
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
@ExcelIgnoreUnannotated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Comment("系统部门")
@Entity
@Table(name = "system_dept")
public class SystemDept implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 父部门id
     */
    @ExcelProperty("父部门id")
    @ColumnDefault("0")
    @NotNull
    @Column(name = "parent_id", nullable = false)
    private Long parentId;

    /**
     * 祖级列表
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "ancestors")
    private List<Long> ancestors;

    /**
     * 部门名称
     */
    @ExcelProperty("部门名称")
    @Size(max = 30)
    @NotNull
    @Column(name = "dept_name", nullable = false, length = 30)
    private String deptName;

    /**
     * 排序
     */
    @ExcelProperty("排序")
    @ColumnDefault("0")
    @Column(name = "sort")
    private Integer sort;

    /**
     * 负责人
     */
    @ExcelProperty("负责人")
    @Column(name = "leader")
    private Long leader;

    /**
     * 联系电话
     */
    @ExcelProperty("联系电话")
    @Size(max = 11)
    @Column(name = "phone", length = 11)
    private String phone;

    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

    /**
     * 启用
     */
    @ExcelProperty("启用")
    @ColumnDefault("'1'")
    @Column(name = "enable")
    private Boolean enable;

    /**
     * 创建部门
     */
    @Column(name = "create_dept")
    private Long createDept;

    /**
     * 创建者
     */
    @CreatedBy
    @Column(name = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_date")
    private LocalDateTime createDate;

    /**
     * 更新者
     */
    @LastModifiedBy
    @Column(name = "update_by")
    private Long updateBy;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime updateDate;

}
