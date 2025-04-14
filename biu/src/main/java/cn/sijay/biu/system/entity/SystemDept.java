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
 * SystemDept
 * 系统部门实体类
 *
 * @author Sijay
 * @since 2025-04-09
 */
@ExcelIgnoreUnannotated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Comment("系统部门")
@Entity
@Table(name = "system_dept")
public class SystemDept extends BaseEntity {

    /**
     * 部门名称
     */
    @ExcelProperty("部门名称")
    @Size(max = 50, message = "部门名称最大长度为{max}")
    @NotNull
    @Comment("部门名称")
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    /**
     * 父部门id
     */
    @ExcelProperty("父部门id")
    @Comment("父部门id")
    @Column(name = "parent_id", unique = true)
    private Long parentId;

    /**
     * 祖级列表
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Comment("祖级列表")
    @Column(name = "ancestors", unique = true)
    private List<Long> ancestors;

    /**
     * 排序
     */
    @ExcelProperty("排序")
    @Comment("排序")
    @Column(name = "sort", unique = true)
    private Integer sort;

    /**
     * 负责人
     */
    @ExcelProperty("负责人")
    @Comment("负责人")
    @Column(name = "leader", unique = true)
    private Long leader;

    /**
     * 联系电话
     */
    @ExcelProperty("联系电话")
    @Size(max = 11, message = "联系电话最大长度为{max}")
    @Comment("联系电话")
    @Column(name = "phone", unique = true, length = 11)
    private String phone;

    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    @Size(max = 100, message = "邮箱最大长度为{max}")
    @Comment("邮箱")
    @Column(name = "email", unique = true, length = 100)
    private String email;

    /**
     * 启用
     */
    @Comment("启用")
    @Column(name = "enable", unique = true)
    private Boolean enable;

}
