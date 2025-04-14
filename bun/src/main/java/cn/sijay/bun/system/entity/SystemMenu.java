package cn.sijay.bun.system.entity;

import cn.sijay.bun.common.enums.MenuType;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <strong>SystemMenu</strong>
 * <p>
 * 系统菜单
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
@ExcelIgnoreUnannotated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Comment("系统菜单")
@Entity
@Table(name = "system_menu")
public class SystemMenu implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 父菜单id
     */
    @ExcelProperty("父菜单id")
    @ColumnDefault("0")
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 菜单名称
     */
    @ExcelProperty("菜单名称")
    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 权限编码
     */
    @ExcelProperty("权限编码")
    @Size(max = 100)
    @NotNull
    @Column(name = "perms_code", nullable = false, length = 100)
    private String permsCode;

    /**
     * 排序
     */
    @ExcelProperty("排序")
    @ColumnDefault("0")
    @Column(name = "sort")
    private Integer sort;

    /**
     * 路由地址
     */
    @ExcelProperty("路由地址")
    @Size(max = 200)
    @Column(name = "path", length = 200)
    private String path;

    /**
     * 组件路径
     */
    @ExcelProperty("组件路径")
    @Size(max = 255)
    @Column(name = "component", length = 255)
    private String component;

    /**
     * 菜单类型
     */
    @ExcelProperty("菜单类型")
    @ColumnDefault("'DIRECTORY'")
    @Column(name = "type")
    private MenuType type;

    /**
     * 是否外链
     */
    @ExcelProperty("是否外链")
    @ColumnDefault("'0'")
    @Column(name = "external_link")
    private Boolean externalLink;

    /**
     * 可缓存
     */
    @ExcelProperty("可缓存")
    @ColumnDefault("'1'")
    @Column(name = "cacheable")
    private Boolean cacheable;

    /**
     * 可显示
     */
    @ExcelProperty("可显示")
    @ColumnDefault("'1'")
    @Column(name = "visible")
    private Boolean visible;

    /**
     * 启用
     */
    @ExcelProperty("启用")
    @ColumnDefault("'1'")
    @Column(name = "enable")
    private Boolean enable;

    /**
     * 菜单图标
     */
    @ExcelProperty("菜单图标")
    @Size(max = 100)
    @Column(name = "icon", length = 100)
    private String icon;

    /**
     * 创建部门
     */
    @Column(name = "create_dept")
    private Long createDept;

    /**
     * 创建者
     */
    @CreatedBy
    @Column(name = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_date")
    private LocalDateTime createDate;

    /**
     * 更新者
     */
    @LastModifiedBy
    @Column(name = "update_by")
    private Long updateBy;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime updateDate;

}
