package cn.sijay.bun.system.entity;

import cn.sijay.bun.common.enums.GenderType;
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
 * <strong>SystemUser</strong>
 * <p>
 * 系统用户
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
@Comment("系统用户")
@Entity
@Table(name = "system_user")
public class SystemUser implements Serializable {
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
     * 部门
     */
    @ExcelProperty("部门")
    @Column(name = "dept_id")
    private Long deptId;

    /**
     * 账号
     */
    @ExcelProperty("账号")
    @Size(max = 30)
    @NotNull
    @Column(name = "user_name", nullable = false, length = 30)
    private String userName;

    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    @Size(max = 30)
    @NotNull
    @Column(name = "real_name", nullable = false, length = 30)
    private String realName;

    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

    /**
     * 手机号码
     */
    @ExcelProperty("手机号码")
    @Size(max = 11)
    @Column(name = "phone", length = 11)
    private String phone;

    /**
     * 性别
     */
    @ExcelProperty("性别")
    @ColumnDefault("'UNKNOWN'")
    @Column(name = "gender")
    private GenderType gender;

    /**
     * 头像
     */
    @ExcelProperty("头像")
    @Column(name = "avatar")
    private Long avatar;

    /**
     * 密码
     */
    @ExcelProperty("密码")
    @Size(max = 100)
    @NotNull
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    /**
     * 角色
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "roles")
    private List<Long> roles;

    /**
     * 岗位
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "posts")
    private List<Long> posts;

    /**
     * 模块
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "modules")
    private List<Long> modules;

    /**
     * 启用
     */
    @ExcelProperty("启用")
    @ColumnDefault("'1'")
    @Column(name = "enable")
    private Boolean enable;

    /**
     * 密码是否更改
     */
    @ExcelProperty("密码是否更改")
    @ColumnDefault("'0'")
    @Column(name = "pwd_changed")
    private Boolean pwdChanged;

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
