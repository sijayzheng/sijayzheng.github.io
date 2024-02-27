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
 * <em>LogBusiness 业务日志</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Data
@Accessors(chain = true)
@TableName("log_business")
@Schema(name = "LogBusiness", title = "业务日志", description = "业务日志")
public class LogBusiness implements Serializable {

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
     * 方法
     */
    @Schema(title = "方法")
    @Size(max = 200, message = "字段[方法]最大长度为{max}")
    @TableField("method")
    private String method;

    /**
     * 参数
     */
    @Schema(title = "参数")
    @Size(max = 2000, message = "字段[参数]最大长度为{max}")
    @TableField("params")
    private String params;

    /**
     * 请求类型
     */
    @Schema(title = "请求类型")
    @Size(max = 10, message = "字段[请求类型]最大长度为{max}")
    @TableField("request_type")
    private String requestType;

    /**
     * 请求url
     */
    @Schema(title = "请求url")
    @Size(max = 100, message = "字段[请求url]最大长度为{max}")
    @TableField("request_url")
    private String requestUrl;

    /**
     * 返回信息
     */
    @Schema(title = "返回信息")
    @Size(max = 2000, message = "字段[返回信息]最大长度为{max}")
    @TableField("return_result")
    private String returnResult;

    /**
     * 业务名称
     */
    @Schema(title = "业务名称")
    @Size(max = 50, message = "字段[业务名称]最大长度为{max}")
    @TableField("business_name")
    private String businessName;

    /**
     * 操作类型
     */
    @Schema(title = "操作类型")
    @Size(max = 50, message = "字段[操作类型]最大长度为{max}")
    @TableField("operation_type")
    private String operationType;

    /**
     * 操作结果
     */
    @Schema(title = "操作结果")
    @Size(max = 4, message = "字段[操作结果]最大长度为{max}")
    @TableField("operation_result")
    private String operationResult;

    /**
     * 错误信息
     */
    @Schema(title = "错误信息")
    @Size(max = 2000, message = "字段[错误信息]最大长度为{max}")
    @TableField("error_message")
    private String errorMessage;

    /**
     * 操作时间
     */
    @Schema(title = "操作时间")
    @TableField("operation_time")
    private LocalDate operationTime;

}
