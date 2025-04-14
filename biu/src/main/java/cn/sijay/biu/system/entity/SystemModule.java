package cn.sijay.biu.system.entity;

import cn.sijay.biu.core.base.BaseEntity;
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
 * SystemModule
 * 系统模块实体类
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
@Comment("系统模块")
@Entity
@Table(name = "system_module")
public class SystemModule extends BaseEntity {

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
     * 角色
     */
    @ExcelProperty("角色")
    @JdbcTypeCode(SqlTypes.JSON)
    @Comment("角色")
    @Column(name = "roles", unique = true)
    private List<Long> roles;

    /**
     * 菜单
     */
    @ExcelProperty("菜单")
    @JdbcTypeCode(SqlTypes.JSON)
    @Comment("菜单")
    @Column(name = "menus", unique = true)
    private List<Long> menus;

    /**
     * 启用
     */
    @ExcelProperty("启用")
    @Comment("启用")
    @Column(name = "enable", unique = true)
    private Boolean enable;

}
