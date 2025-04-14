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
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

/**
 * SystemConfig
 * 系统配置实体类
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
@Comment("系统配置")
@Entity
@Table(name = "system_config")
public class SystemConfig extends BaseEntity {

    /**
     * 名称
     */
    @ExcelProperty("名称")
    @Size(max = 100, message = "名称最大长度为{max}")
    @NotNull
    @Comment("名称")
    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    /**
     * 编码
     */
    @ExcelProperty("编码")
    @Size(max = 100, message = "编码最大长度为{max}")
    @NotNull
    @Comment("编码")
    @Column(name = "code", nullable = false, length = 100)
    private String code;

    /**
     * 值
     */
    @ExcelProperty("值")
    @Size(max = 500, message = "值最大长度为{max}")
    @NotNull
    @Comment("值")
    @Column(name = "value", nullable = false, unique = true, length = 500)
    private String value;

    /**
     * 内置
     */
    @ExcelProperty("内置")
    @ColumnDefault("b'0'")
    @Comment("内置")
    @Column(name = "internal", unique = true)
    private Boolean internal;

}
