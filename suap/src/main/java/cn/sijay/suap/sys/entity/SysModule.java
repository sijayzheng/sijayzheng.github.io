package cn.sijay.suap.sys.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
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
 * <strong>SysModule</strong>
 * <p>
 * 模块
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
@Table(name = "sys_module")
public class SysModule implements Serializable {

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
     * 模块名称
     */
    @Size(max = 50, message = "字段[模块名称]最大长度为{max}")
    @NotNull
    @ExcelProperty(value = "模块名称", order = 1)
    @Schema(title = "模块名称")
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * 菜单
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @ExcelProperty(value = "菜单", order = 2)
    @Schema(title = "菜单")
    @Column(name = "menus")
    private List<Long> menus;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序", order = 3)
    @Schema(title = "排序")
    @Column(name = "sort")
    private Integer sort;

    /**
     * 是否启用
     */
    @ExcelProperty(value = "是否启用", order = 4)
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

}
