package cn.sijay.suap.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * <em>LogLogin 登录日志</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Data
@Accessors(chain = true)
@TableName("log_login")
@Schema(name = "LogLogin", title = "登录日志", description = "登录日志")
public class LogLogin implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Schema(title = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户
     */
    @Schema(title = "用户")
    @TableField("user_id")
    private Long userId;

    /**
     * ip
     */
    @Schema(title = "ip")
    @Size(max = 50, message = "字段[ip]最大长度为{max}")
    @TableField("ip")
    private String ip;

    /**
     * 登录时间
     */
    @Schema(title = "登录时间")
    @TableField("login_time")
    private LocalDate loginTime;

}
