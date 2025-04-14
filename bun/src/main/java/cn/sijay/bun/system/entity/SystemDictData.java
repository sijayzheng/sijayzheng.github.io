package cn.sijay.bun.system.entity;

import cn.sijay.bun.common.enums.ShowType;
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
 * <strong>SystemDictData</strong>
 * <p>
 * 系统字典数据
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
@Comment("系统字典数据")
@Entity
@Table(name = "system_dict_data")
public class SystemDictData implements Serializable {
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
     * 字典
     */
    @ExcelProperty("字典")
    @NotNull
    @Column(name = "dict_id", nullable = false)
    private Long dictId;

    /**
     * 标签
     */
    @ExcelProperty("标签")
    @Size(max = 100)
    @NotNull
    @Column(name = "label", nullable = false, length = 100)
    private String label;

    /**
     * 键值
     */
    @ExcelProperty("键值")
    @Size(max = 100)
    @NotNull
    @Column(name = "value", nullable = false, length = 100)
    private String value;

    /**
     * 排序
     */
    @ExcelProperty("排序")
    @ColumnDefault("0")
    @Column(name = "sort")
    private Integer sort;

    /**
     * 样式属性
     */
    @ExcelProperty("样式属性")
    @Size(max = 100)
    @Column(name = "class_name", length = 100)
    private String className;

    /**
     * 表格回显样式
     */
    @ExcelProperty("表格回显样式")
    @ColumnDefault("'NONE'")
    @Column(name = "show_type")
    private ShowType showType;

    /**
     * 是否默认
     */
    @ExcelProperty("是否默认")
    @ColumnDefault("'0'")
    @Column(name = "default_value")
    private Boolean defaultValue;

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
