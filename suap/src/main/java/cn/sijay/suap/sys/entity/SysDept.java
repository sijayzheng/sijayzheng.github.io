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
import java.util.List;

/**
 * <strong>SysDept</strong>
 * <p>
 * 部门
 * </p>
 *
 * @author sijay
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
@Table(name = "sys_dept")
public class SysDept implements Serializable {

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
     * 上级
     */
    @NotNull
    @ExcelProperty(value = "上级", order = 1)
    @Schema(title = "上级")
    @Column(name = "parent_id", nullable = false)
    private Long parentId;

    /**
     * 部门名称
     */
    @Size(max = 50, message = "字段[部门名称]最大长度为{max}")
    @NotNull
    @ExcelProperty(value = "部门名称", order = 2)
    @Schema(title = "部门名称")
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * 部门负责人
     */
    @ExcelProperty(value = "部门负责人", order = 3)
    @Schema(title = "部门负责人")
    @Column(name = "leader")
    private Long leader;

    /**
     * 部门电话
     */
    @Size(max = 11, message = "字段[部门电话]最大长度为{max}")
    @ExcelProperty(value = "部门电话", order = 4)
    @Schema(title = "部门电话")
    @Column(name = "phone", length = 11)
    private String phone;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序", order = 5)
    @Schema(title = "排序")
    @Column(name = "sort")
    private Integer sort;

    /**
     * 是否启用
     */
    @ExcelProperty(value = "是否启用", order = 6)
    @Schema(title = "是否启用")
    @Column(name = "enabled")
    private Boolean enabled;

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

    /**
     * 子节点
     */
    private transient List<SysDept> children;
}
