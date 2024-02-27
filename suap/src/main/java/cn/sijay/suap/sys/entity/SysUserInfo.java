package cn.sijay.suap.sys.entity;

import cn.sijay.suap.core.annotation.ExcelProperty;
import cn.sijay.suap.core.base.BaseEntity;
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

/**
 * <p>
 * <em>SysUserInfo 用户信息</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user_info")
@Schema(name = "SysUserInfo", title = "用户信息", description = "用户信息")
public class SysUserInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户
     */
    @Schema(title = "用户")
    @ExcelProperty(value = "用户", sort = 0)
    @TableField("user_id")
    private Long userId;

    /**
     * 姓名
     */
    @Schema(title = "姓名")
    @ExcelProperty(value = "姓名", sort = 1)
    @Size(max = 50, message = "字段[姓名]最大长度为{max}")
    @TableField("name")
    private String name;

    /**
     * 性别
     */
    @Schema(title = "性别")
    @ExcelProperty(value = "性别", sort = 2)
    @Size(max = 1, message = "字段[性别]最大长度为{max}")
    @TableField("gender")
    private String gender;

    /**
     * 生日
     */
    @Schema(title = "生日")
    @ExcelProperty(value = "生日", sort = 3)
    @TableField("birthday")
    private LocalDate birthday;

    /**
     * 头像
     */
    @Schema(title = "头像")
    @ExcelProperty(value = "头像", sort = 4)
    @Size(max = 200, message = "字段[头像]最大长度为{max}")
    @TableField("avatar")
    private String avatar;

    /**
     * 省
     */
    @Schema(title = "省")
    @ExcelProperty(value = "省", sort = 5)
    @TableField("province")
    private Long province;

    /**
     * 市
     */
    @Schema(title = "市")
    @ExcelProperty(value = "市", sort = 6)
    @TableField("city")
    private Long city;

    /**
     * 区
     */
    @Schema(title = "区")
    @ExcelProperty(value = "区", sort = 7)
    @TableField("area")
    private Long area;

    /**
     * 详细地址
     */
    @Schema(title = "详细地址")
    @ExcelProperty(value = "详细地址", sort = 8)
    @Size(max = 200, message = "字段[详细地址]最大长度为{max}")
    @TableField("address")
    private String address;

    /**
     * 部门
     */
    @Schema(title = "部门")
    @ExcelProperty(value = "部门", sort = 9)
    @TableField("dept_id")
    private Long deptId;

    /**
     * 备注
     */
    @Schema(title = "备注")
    @ExcelProperty(value = "备注", sort = 10)
    @Size(max = 200, message = "字段[备注]最大长度为{max}")
    @TableField("remark")
    private String remark;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    @TableField("deleted")
    private YesOrNo deleted;

}
