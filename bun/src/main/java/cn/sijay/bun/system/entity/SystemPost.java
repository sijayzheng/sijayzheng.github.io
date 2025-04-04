package cn.sijay.bun.system.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <strong>SystemPost</strong>
 * <p>
 * 系统岗位
 * </p>
 *
 * @author sijay
 * @since 2024-11-11
 */
@ExcelIgnoreUnannotated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Comment("系统岗位")
@Entity
@Table(name = "system_post")
public class SystemPost implements Serializable {
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
     * 部门id
     */
    @ExcelProperty("部门id")
    @NotNull
    @Column(name = "dept_id", nullable = false)
    private Long deptId;

    /**
     * 岗位编码
     */
    @ExcelProperty("岗位编码")
    @Size(max = 50)
    @NotNull
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    /**
     * 岗位名称
     */
    @ExcelProperty("岗位名称")
    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 排序
     */
    @ExcelProperty("排序")
    @ColumnDefault("0")
    @Column(name = "sort")
    private Integer sort;

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
