package cn.sijay.biu.system.entity;

import cn.sijay.biu.core.base.BaseEntity;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

/**
 * SystemUser
 * 系统用户实体类
 *
 * @author Sijay
 * @since 2025-04-09
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
public class SystemUser extends BaseEntity {

    /**
     * 账号
     */
    @ExcelProperty("账号")
    @Size(max = 20, message = "账号最大长度为{max}")
    @NotNull
    @Comment("账号")
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    /**
     * 部门
     */
    @ExcelProperty("部门")
    @Comment("部门")
    @Column(name = "dept_id", unique = true)
    private Long deptId;

    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    @Size(max = 50, message = "姓名最大长度为{max}")
    @Comment("姓名")
    @Column(name = "real_name", unique = true, length = 50)
    private String realName;

    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    @Size(max = 100, message = "邮箱最大长度为{max}")
    @Comment("邮箱")
    @Column(name = "email", unique = true, length = 100)
    private String email;

    /**
     * 手机号码
     */
    @ExcelProperty("手机号码")
    @Size(max = 11, message = "手机号码最大长度为{max}")
    @Comment("手机号码")
    @Column(name = "phone", unique = true, length = 11)
    private String phone;

    /**
     * 性别
     */
    @ExcelProperty("性别")
    @Size(max = 7, message = "性别最大长度为{max}")
    @Comment("性别")
    @Column(name = "gender", unique = true, length = 7)
    private String gender;

    /**
     * 头像
     */
    @Comment("头像")
    @Column(name = "avatar", unique = true)
    private Long avatar;

    /**
     * 密码
     */
    @Size(max = 255, message = "密码最大长度为{max}")
    @Comment("密码")
    @Column(name = "password", unique = true, length = 255)
    private String password;

    /**
     * 角色
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Comment("角色")
    @Column(name = "roles", unique = true)
    private List<Long> roles;

    /**
     * 岗位
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Comment("岗位")
    @Column(name = "posts", unique = true)
    private List<Long> posts;

    /**
     * 模块
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Comment("模块")
    @Column(name = "modules", unique = true)
    private List<Long> modules;

    /**
     * 启用
     */
    @Comment("启用")
    @Column(name = "enable", unique = true)
    private Boolean enable;

    /**
     * 密码是否更改
     */
    @Comment("密码是否更改")
    @Column(name = "pwd_changed", unique = true)
    private Boolean pwdChanged;

}
