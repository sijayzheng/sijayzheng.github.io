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
 * <strong>SysMenu</strong>
 * <p>
 * 菜单
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
@Table(name = "sys_menu")
public class SysMenu implements Serializable {

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
     * 菜单类型
     */
    @Size(max = 10, message = "字段[菜单类型]最大长度为{max}")
    @NotNull
    @ExcelProperty(value = "菜单类型", order = 2)
    @Schema(title = "菜单类型")
    @Column(name = "type", length = 10, nullable = false)
    private String type;

    /**
     * 菜单名称
     */
    @Size(max = 50, message = "字段[菜单名称]最大长度为{max}")
    @NotNull
    @ExcelProperty(value = "菜单名称", order = 3)
    @Schema(title = "菜单名称")
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * 图标
     */
    @Size(max = 100, message = "字段[图标]最大长度为{max}")
    @ExcelProperty(value = "图标", order = 4)
    @Schema(title = "图标")
    @Column(name = "icon", length = 100)
    private String icon;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序", order = 5)
    @Schema(title = "排序")
    @Column(name = "sort")
    private Integer sort;

    /**
     * 路由地址
     */
    @Size(max = 200, message = "字段[路由地址]最大长度为{max}")
    @ExcelProperty(value = "路由地址", order = 6)
    @Schema(title = "路由地址")
    @Column(name = "path", length = 200)
    private String path;

    /**
     * 组件路径
     */
    @Size(max = 255, message = "字段[组件路径]最大长度为{max}")
    @ExcelProperty(value = "组件路径", order = 7)
    @Schema(title = "组件路径")
    @Column(name = "component", length = 255)
    private String component;

    /**
     * 权限标识
     */
    @Size(max = 100, message = "字段[权限标识]最大长度为{max}")
    @ExcelProperty(value = "权限标识", order = 8)
    @Schema(title = "权限标识")
    @Column(name = "perms", length = 100)
    private String perms;

    /**
     * 是否为外链
     */
    @ExcelProperty(value = "是否为外链", order = 9)
    @Schema(title = "是否为外链")
    @Column(name = "link")
    private Boolean link;

    /**
     * 显示状态
     */
    @ExcelProperty(value = "显示状态", order = 11)
    @Schema(title = "显示状态")
    @Column(name = "visible")
    private Boolean visible;

    /**
     * 是否启用
     */
    @ExcelProperty(value = "是否启用", order = 12)
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
    private transient List<SysMenu> children;
}
