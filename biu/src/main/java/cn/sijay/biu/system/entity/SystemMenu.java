package cn.sijay.biu.system.entity;

import cn.sijay.biu.core.base.BaseEntity;
import cn.sijay.biu.core.enums.MenuEnum;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Comment;

/**
 * SystemMenu
 * 系统菜单实体类
 *
 * @author Sijay
 * @since 2025-04-14
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
public class SystemMenu extends BaseEntity {

    /**
     * 父菜单id
     */
    @ExcelProperty("父菜单id")
    @Comment("父菜单id")
    @Column(name = "parent_id", unique = true)
    private Long parentId;

    /**
     * 名称
     */
    @ExcelProperty("名称")
    @Size(max = 50, message = "名称最大长度为{max}")
    @NotNull
    @Comment("名称")
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    /**
     * 权限编码
     */
    @ExcelProperty("权限编码")
    @Size(max = 100, message = "权限编码最大长度为{max}")
    @NotNull
    @Comment("权限编码")
    @Column(name = "perms_code", nullable = false, unique = true, length = 100)
    private String permsCode;

    /**
     * 排序
     */
    @ExcelProperty("排序")
    @Comment("排序")
    @Column(name = "sort", unique = true)
    private Integer sort;

    /**
     * 路由地址
     */
    @ExcelProperty("路由地址")
    @Size(max = 200, message = "路由地址最大长度为{max}")
    @Comment("路由地址")
    @Column(name = "path", unique = true, length = 200)
    private String path;

    /**
     * 组件路径
     */
    @ExcelProperty("组件路径")
    @Size(max = 255, message = "组件路径最大长度为{max}")
    @Comment("组件路径")
    @Column(name = "component", unique = true, length = 255)
    private String component;

    /**
     * 菜单类型
     */
    @ExcelProperty("菜单类型")
    @Size(max = 9, message = "菜单类型最大长度为{max}")
    @NotNull
    @Comment("菜单类型")
    @Column(name = "type", nullable = false, unique = true, length = 9)
    private MenuEnum type;

    /**
     * 是否外链
     */
    @ExcelProperty("是否外链")
    @Comment("是否外链")
    @Column(name = "external_link", unique = true)
    private Boolean externalLink;

    /**
     * 可缓存
     */
    @ExcelProperty("可缓存")
    @Comment("可缓存")
    @Column(name = "cacheable", unique = true)
    private Boolean cacheable;

    /**
     * 可显示
     */
    @ExcelProperty("可显示")
    @Comment("可显示")
    @Column(name = "visible", unique = true)
    private Boolean visible;

    /**
     * 启用
     */
    @ExcelProperty("启用")
    @Comment("启用")
    @Column(name = "enable", unique = true)
    private Boolean enable;

    /**
     * 菜单图标
     */
    @ExcelProperty("菜单图标")
    @Size(max = 100, message = "菜单图标最大长度为{max}")
    @Comment("菜单图标")
    @Column(name = "icon", unique = true, length = 100)
    private String icon;

}
