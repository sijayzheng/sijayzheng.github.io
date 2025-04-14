package cn.sijay.suap.data.entity;

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

/**
 * <strong>DataDictData</strong>
 * <p>
 * 数据字典项
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
@Table(name = "data_dict_data")
public class DataDictData implements Serializable {

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
     * 字典类型编码
     */
    @Size(max = 100, message = "字段[字典类型编码]最大长度为{max}")
    @NotNull
    @ExcelProperty(value = "字典类型编码", order = 1)
    @Schema(title = "字典类型编码")
    @Column(name = "dict_type_code", length = 100, nullable = false)
    private String dictTypeCode;

    /**
     * 显示名称
     */
    @Size(max = 100, message = "字段[显示名称]最大长度为{max}")
    @NotNull
    @ExcelProperty(value = "显示名称", order = 2)
    @Schema(title = "显示名称")
    @Column(name = "label", length = 100, nullable = false)
    private String label;

    /**
     * 值
     */
    @Size(max = 50, message = "字段[值]最大长度为{max}")
    @NotNull
    @ExcelProperty(value = "值", order = 3)
    @Schema(title = "值")
    @Column(name = "value", length = 50, nullable = false)
    private String value;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序", order = 4)
    @Schema(title = "排序")
    @Column(name = "sort")
    private Integer sort;

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

}
