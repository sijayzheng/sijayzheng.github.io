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
 * <em>SysUserPost 用户岗位</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user_post")
@Schema(name = "SysUserPost", title = "用户岗位", description = "用户岗位")
public class SysUserPost implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户
     */
    @Schema(title = "用户")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 岗位
     */
    @Schema(title = "岗位")
    @TableId(value = "post_id", type = IdType.AUTO)
    private Long postId;

}
