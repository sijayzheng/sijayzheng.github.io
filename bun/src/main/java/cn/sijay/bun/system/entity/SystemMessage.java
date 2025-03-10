package cn.sijay.bun.system.entity;

import cn.sijay.bun.common.enums.MessageType;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <strong>SystemMessage</strong>
 * <p>
 * 系统消息
 * </p>
 *
 * @author sijay
 * @since 2024-11-11
 */
@ExcelIgnoreUnannotated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Comment("系统消息")
@Entity
@Table(name = "system_message")
public class SystemMessage implements Serializable {
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
     * 消息标题
     */
    @ExcelProperty("消息标题")
    @Size(max = 50)
    @NotNull
    @Column(name = "title", nullable = false, length = 50)
    private String title;

    /**
     * 消息类型
     */
    @ExcelProperty("消息类型")
    @ColumnDefault("'NOTICE'")
    @NotNull
    @Column(name = "type", nullable = false)
    private MessageType type;

    /**
     * 内容
     */
    @ExcelProperty("内容")
    @Size(max = 5000)
    @Column(name = "content", length = 5000)
    private String content;

    /**
     * 状态
     */
    @ExcelProperty("状态")
    @ColumnDefault("'0'")
    @Column(name = "closed")
    private Boolean closed;

    /**
     * 发布人
     */
    @ExcelProperty("发布人")
    @Column(name = "publisher")
    private Long publisher;

    /**
     * 接收人
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "receiver")
    private List<Long> receiver;

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
