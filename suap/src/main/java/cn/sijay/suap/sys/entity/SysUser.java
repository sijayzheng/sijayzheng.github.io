package cn.sijay.suap.sys.entity;

import cn.sijay.suap.core.annotation.ExcelProperty;
import cn.sijay.suap.core.annotation.QueryColumn;
import cn.sijay.suap.core.base.BaseEntity;
import cn.sijay.suap.core.enums.QueryType;
import cn.sijay.suap.core.enums.YesOrNo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * <em>SysUser 登录用户</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
@Schema(name = "SysUser", title = "登录用户", description = "登录用户")
public class SysUser extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @Schema(title = "用户名")
    @ExcelProperty(value = "用户名", sort = 0)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[用户名]最大长度为{max}")
    @TableField("username")
    private String username;

    /**
     * 手机号
     */
    @Schema(title = "手机号")
    @ExcelProperty(value = "手机号", sort = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 11, message = "字段[手机号]最大长度为{max}")
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @Schema(title = "邮箱")
    @ExcelProperty(value = "邮箱", sort = 2)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 100, message = "字段[邮箱]最大长度为{max}")
    @TableField("email")
    private String email;

    /**
     * 密码
     */
    @Schema(title = "密码")
    @ExcelProperty(value = "密码", sort = 3)
    @Size(max = 128, message = "字段[密码]最大长度为{max}")
    @TableField("password")
    private String password;

    /**
     * 最后登录时间
     */
    @Schema(title = "最后登录时间")
    @ExcelProperty(value = "最后登录时间", sort = 4)
    @TableField("last_login_time")
    private LocalDate lastLoginTime;

    /**
     * 是否启用
     */
    @Schema(title = "是否启用")
    @ExcelProperty(value = "是否启用", sort = 5)
    @TableField("enable")
    private YesOrNo enable;

    /**
     * 排序
     */
    @Schema(title = "排序")
    @ExcelProperty(value = "排序", sort = 6)
    @TableField("sort")
    private Integer sort;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    @TableField("deleted")
    private YesOrNo deleted;

    @TableField(exist = false)
    private LocalDateTime lastLoginTimeStart;

    @TableField(exist = false)
    private LocalDateTime lastLoginTimeEnd;
    @TableField(exist = false)
    private LocalDateTime MenuPermission;
    @TableField(exist = false)
    private LocalDateTime RolePermission;

}
