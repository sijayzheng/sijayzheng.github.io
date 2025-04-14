package cn.sijay.suap.sys.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <strong>SysNotice</strong>
 * <p>
 * 通知公告
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
@Table(name = "sys_notice")
public class SysNotice implements Serializable {

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
     * 标题
     */
    @Size(max = 50, message = "字段[标题]最大长度为{max}")
    @NotNull
    @ExcelProperty(value = "标题", order = 1)
    @Schema(title = "标题")
    @Column(name = "title", length = 50, nullable = false)
    private String title;

    /**
     * 类型
     */
    @Size(max = 10, message = "字段[类型]最大长度为{max}")
    @ExcelProperty(value = "类型", order = 2)
    @Schema(title = "类型")
    @Column(name = "type", length = 10)
    private String type;

    /**
     * 状态
     */
    @Size(max = 10, message = "字段[状态]最大长度为{max}")
    @ExcelProperty(value = "状态", order = 3)
    @Schema(title = "状态")
    @Column(name = "status", length = 10)
    private String status;

    /**
     * 内容
     */
    @Size(max = 65535, message = "字段[内容]最大长度为{max}")
    @ExcelProperty(value = "内容", order = 4)
    @Schema(title = "内容")
    @Column(name = "content", length = 65535)
    private String content;

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

}
