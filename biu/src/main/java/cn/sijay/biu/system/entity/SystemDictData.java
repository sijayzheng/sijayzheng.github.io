package cn.sijay.biu.system.entity;

import cn.sijay.biu.core.base.BaseEntity;
import cn.sijay.biu.core.enums.ShowEnum;
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
 * SystemDictData
 * 系统字典数据实体类
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
@Comment("系统字典数据")
@Entity
@Table(name = "system_dict_data")
public class SystemDictData extends BaseEntity {

    /**
     * 字典
     */
    @ExcelProperty("字典")
    @NotNull
    @Comment("字典")
    @Column(name = "dict_id", nullable = false, unique = true)
    private Long dictId;

    /**
     * 标签
     */
    @ExcelProperty("标签")
    @Size(max = 100, message = "标签最大长度为{max}")
    @NotNull
    @Comment("标签")
    @Column(name = "label", nullable = false, unique = true, length = 100)
    private String label;

    /**
     * 键值
     */
    @ExcelProperty("键值")
    @Size(max = 100, message = "键值最大长度为{max}")
    @NotNull
    @Comment("键值")
    @Column(name = "value", nullable = false, unique = true, length = 100)
    private String value;

    /**
     * 排序
     */
    @ExcelProperty("排序")
    @Comment("排序")
    @Column(name = "sort", unique = true)
    private Integer sort;

    /**
     * 样式属性
     */
    @ExcelProperty("样式属性")
    @Size(max = 100, message = "样式属性最大长度为{max}")
    @Comment("样式属性")
    @Column(name = "class_name", unique = true, length = 100)
    private String className;

    /**
     * 表格回显样式
     */
    @ExcelProperty("表格回显样式")
    @Size(max = 7, message = "表格回显样式最大长度为{max}")
    @Comment("表格回显样式")
    @Column(name = "show_type", unique = true, length = 7)
    private ShowEnum showType;

    /**
     * 默认值
     */
    @ExcelProperty("默认值")
    @Comment("默认值")
    @Column(name = "default_value", unique = true)
    private Boolean defaultValue;

}
