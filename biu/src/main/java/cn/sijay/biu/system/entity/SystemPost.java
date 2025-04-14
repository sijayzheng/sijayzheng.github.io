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

/**
 * SystemPost
 * 系统岗位实体类
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
@Comment("系统岗位")
@Entity
@Table(name = "system_post")
public class SystemPost extends BaseEntity {

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
     * 编码
     */
    @ExcelProperty("编码")
    @Size(max = 50, message = "编码最大长度为{max}")
    @NotNull
    @Comment("编码")
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    /**
     * 部门id
     */
    @ExcelProperty("部门id")
    @NotNull
    @Comment("部门id")
    @Column(name = "dept_id", nullable = false, unique = true)
    private Long deptId;

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

}
