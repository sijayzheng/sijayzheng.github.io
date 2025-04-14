package cn.sijay.bun.system.entity;

import cn.sijay.bun.common.enums.DataScopeEnum;
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
 * <strong>SystemRole</strong>
 * <p>
 * 系统权限
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
@Comment("系统权限")
@Entity
@Table(name = "system_role")
public class SystemRole implements Serializable {
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
     * 编码
     */
    @ExcelProperty("编码")
    @Size(max = 50)
    @NotNull
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    /**
     * 名称
     */
    @ExcelProperty("名称")
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
     * 菜单树选择项是否关联显示
     */
    @ExcelProperty("菜单树选择项是否关联显示")
    @ColumnDefault("'1'")
    @Column(name = "menu_check_strictly")
    private Boolean menuCheckStrictly;

    /**
     * 菜单
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "menus")
    private List<Long> menus;

    /**
     * 部门树选择项是否关联显示
     */
    @ExcelProperty("部门树选择项是否关联显示")
    @ColumnDefault("'1'")
    @Column(name = "dept_check_strictly")
    private Boolean deptCheckStrictly;

    /**
     * 数据权限范围
     */
    @ExcelProperty("数据权限范围")
    @ColumnDefault("'ALL'")
    @Column(name = "data_scope")
    private DataScopeEnum dataScope;

    /**
     * 部门
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "departments")
    private List<Long> departments;

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
