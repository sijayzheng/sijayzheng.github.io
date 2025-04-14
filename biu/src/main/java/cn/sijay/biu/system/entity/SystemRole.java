package cn.sijay.biu.system.entity;

import cn.sijay.biu.core.base.BaseEntity;
import cn.sijay.biu.core.enums.DataScopeEnum;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

/**
 * SystemRole
 * 系统权限实体类
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
@Comment("系统权限")
@Entity
@Table(name = "system_role")
public class SystemRole extends BaseEntity {

    /**
     * 编码
     */
    @ExcelProperty("编码")
    @Size(max = 50, message = "编码最大长度为{max}")
    @NotNull
    @Comment("编码")
    @Column(name = "code", nullable = false, length = 50)
    private String code;

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
     * 排序
     */
    @ExcelProperty("排序")
    @Comment("排序")
    @Column(name = "sort", unique = true)
    private Integer sort;

    /**
     * 启用
     */
    @ExcelProperty("启用")
    @Comment("启用")
    @Column(name = "enable", unique = true)
    private Boolean enable;

    /**
     * 菜单
     */
    @ExcelProperty("菜单")
    @JdbcTypeCode(SqlTypes.JSON)
    @Comment("菜单")
    @Column(name = "menus", unique = true)
    private List<Long> menus;

    /**
     * 菜单树关联显示
     */
    @ExcelProperty("菜单树关联显示")
    @Comment("菜单树关联显示")
    @Column(name = "menu_check_strictly", unique = true)
    private Boolean menuCheckStrictly;

    /**
     * 部门
     */
    @ExcelProperty("部门")
    @JdbcTypeCode(SqlTypes.JSON)
    @Comment("部门")
    @Column(name = "departments", unique = true)
    private List<Long> departments;

    /**
     * 部门树关联显示
     */
    @ExcelProperty("部门树关联显示")
    @Comment("部门树关联显示")
    @Column(name = "dept_check_strictly", unique = true)
    private Boolean deptCheckStrictly;

    /**
     * 数据权限范围
     */
    @ExcelProperty("数据权限范围")
    @Size(max = 10, message = "数据权限范围最大长度为{max}")
    @Comment("数据权限范围")
    @Column(name = "data_scope", unique = true, length = 10)
    private DataScopeEnum dataScope;

}
