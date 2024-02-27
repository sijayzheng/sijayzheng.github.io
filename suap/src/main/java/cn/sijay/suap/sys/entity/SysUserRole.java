package cn.sijay.suap.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * <em>SysUserRole 用户角色</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user_role")
@Schema(name = "SysUserRole", title = "用户角色", description = "用户角色")
public class SysUserRole implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色
     */
    @Schema(title = "角色")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 用户
     */
    @Schema(title = "用户")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

}
