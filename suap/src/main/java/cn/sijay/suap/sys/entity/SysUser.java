package cn.sijay.suap.sys.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <strong>SysUser</strong>
 * <p>
 * 用户
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ExcelIgnoreUnannotated
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "sys_user")
public class SysUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ExcelProperty(value = "主键", order = 0)
    @Schema(title = "主键")
    @Column(name = "id")
    private Long id;

    /**
     * 用户名
     */
    @Size(max = 50, message = "字段[用户名]最大长度为{max}")
    @NotNull
    @ExcelProperty(value = "用户名", order = 1)
    @Schema(title = "用户名")
    @Column(name = "username", length = 50, nullable = false)
    private String username;

    /**
     * 姓名
     */
    @Size(max = 50, message = "字段[姓名]最大长度为{max}")
    @NotNull
    @ExcelProperty(value = "姓名", order = 2)
    @Schema(title = "姓名")
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * 手机号
     */
    @Size(max = 11, message = "字段[手机号]最大长度为{max}")
    @ExcelProperty(value = "手机号", order = 3)
    @Schema(title = "手机号")
    @Column(name = "phone", length = 11)
    private String phone;

    /**
     * 部门
     */
    @ExcelProperty(value = "部门", order = 4)
    @Schema(title = "部门")
    @Column(name = "dept_id")
    private Long deptId;

    /**
     * 密码
     */
    @JsonIgnoreProperties
    @Size(max = 128, message = "字段[密码]最大长度为{max}")
    @NotNull
    @ExcelProperty(value = "密码", order = 5)
    @Schema(title = "密码")
    @Column(name = "password", length = 128, nullable = false)
    private String password;

    /**
     * 是否启用
     */
    @ExcelProperty(value = "是否启用", order = 6)
    @Schema(title = "是否启用")
    @Column(name = "enable")
    private Boolean enable;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序", order = 7)
    @Schema(title = "排序")
    @Column(name = "sort")
    private Integer sort;

    /**
     * 模块
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @ExcelProperty(value = "模块", order = 8)
    @Schema(title = "模块")
    @Column(name = "modules")
    private List<Long> modules;

    /**
     * 角色
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @ExcelProperty(value = "角色", order = 9)
    @Schema(title = "角色")
    @Column(name = "roles")
    private List<Long> roles;

    /**
     * 创建者
     */
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @CreatedBy
    @Column(name = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @CreatedDate
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @LastModifiedBy
    @Column(name = "update_by")
    private Long updateBy;

    /**
     * 更新时间
     */
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @LastModifiedDate
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Transient
    private transient List<String> permissions;
}
