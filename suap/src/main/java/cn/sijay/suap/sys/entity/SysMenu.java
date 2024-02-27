package cn.sijay.suap.sys.entity;

import cn.sijay.suap.core.annotation.ExcelProperty;
import cn.sijay.suap.core.annotation.QueryColumn;
import cn.sijay.suap.core.base.BaseEntity;
import cn.sijay.suap.core.enums.MenuType;
import cn.sijay.suap.core.enums.QueryType;
import cn.sijay.suap.core.enums.YesOrNo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.List;

/**
 * <p>
 * <em>SysMenu 菜单信息</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/02/04 15:17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
@Schema(name = "SysMenu", title = "菜单信息", description = "菜单信息")
public class SysMenu extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 上级
     */
    @Schema(title = "上级")
    @ExcelProperty(value = "上级", sort = 0)
    @TableField("parent_id")
    private Long parentId;

    /**
     * 菜单名称
     */
    @Schema(title = "菜单名称")
    @ExcelProperty(value = "菜单名称", sort = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[菜单名称]最大长度为{max}")
    @TableField("name")
    private String name;

    /**
     * 菜单类型
     */
    @Schema(title = "菜单类型")
    @ExcelProperty(value = "菜单类型", sort = 2)
    @TableField("type")
    private MenuType type;

    /**
     * 路径
     */
    @Schema(title = "路径")
    @ExcelProperty(value = "路径", sort = 3)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 200, message = "字段[路径]最大长度为{max}")
    @TableField("path")
    private String path;

    /**
     * 组件路径
     */
    @Schema(title = "组件路径")
    @ExcelProperty(value = "组件路径", sort = 4)
    @Size(max = 255, message = "字段[组件路径]最大长度为{max}")
    @TableField("component")
    private String component;

    /**
     * 路由参数
     */
    @Schema(title = "路由参数")
    @ExcelProperty(value = "路由参数", sort = 5)
    @Size(max = 255, message = "字段[路由参数]最大长度为{max}")
    @TableField("query_param")
    private String queryParam;

    /**
     * 权限标识
     */
    @Schema(title = "权限标识")
    @ExcelProperty(value = "权限标识", sort = 6)
    @Size(max = 100, message = "字段[权限标识]最大长度为{max}")
    @TableField("perms")
    private String perms;

    /**
     * 图标
     */
    @Schema(title = "图标")
    @ExcelProperty(value = "图标", sort = 7)
    @Size(max = 100, message = "字段[图标]最大长度为{max}")
    @TableField("icon")
    private String icon;

    /**
     * 排序
     */
    @Schema(title = "排序")
    @ExcelProperty(value = "排序", sort = 8)
    @TableField("sort")
    private Integer sort;

    /**
     * 是否为外链
     */
    @Schema(title = "是否为外链")
    @ExcelProperty(value = "是否为外链", sort = 9)
    @TableField("link")
    private YesOrNo link;

    /**
     * 是否缓存
     */
    @Schema(title = "是否缓存")
    @ExcelProperty(value = "是否缓存", sort = 10)
    @TableField("cache")
    private YesOrNo cache;

    /**
     * 显示状态
     */
    @Schema(title = "显示状态")
    @ExcelProperty(value = "显示状态", sort = 11)
    @TableField("visible")
    private YesOrNo visible;

    /**
     * 是否启用
     */
    @Schema(title = "是否启用")
    @ExcelProperty(value = "是否启用", sort = 12)
    @TableField("enabled")
    private YesOrNo enabled;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    @TableField("deleted")
    private YesOrNo deleted;

    @TableField(exist = false)
    private List<SysMenu> children;

    @TableField(exist = false)
    private String parentName;

}
