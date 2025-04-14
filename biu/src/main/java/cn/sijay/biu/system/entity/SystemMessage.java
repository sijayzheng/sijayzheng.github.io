package cn.sijay.biu.system.entity;

import cn.sijay.biu.core.base.BaseEntity;
import cn.sijay.biu.core.enums.MessageEnum;
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
 * SystemMessage
 * 系统消息实体类
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
@Comment("系统消息")
@Entity
@Table(name = "system_message")
public class SystemMessage extends BaseEntity {

    /**
     * 消息标题
     */
    @ExcelProperty("消息标题")
    @Size(max = 50, message = "消息标题最大长度为{max}")
    @NotNull
    @Comment("消息标题")
    @Column(name = "title", nullable = false, unique = true, length = 50)
    private String title;

    /**
     * 消息类型
     */
    @ExcelProperty("消息类型")
    @Size(max = 12, message = "消息类型最大长度为{max}")
    @NotNull
    @Comment("消息类型")
    @Column(name = "type", nullable = false, unique = true, length = 12)
    private MessageEnum type;

    /**
     * 内容
     */
    @ExcelProperty("内容")
    @Size(max = 5000, message = "内容最大长度为{max}")
    @Comment("内容")
    @Column(name = "content", unique = true, length = 5000)
    private String content;

    /**
     * 状态
     */
    @ExcelProperty("状态")
    @Comment("状态")
    @Column(name = "closed", unique = true)
    private Boolean closed;

    /**
     * 发布人
     */
    @ExcelProperty("发布人")
    @Comment("发布人")
    @Column(name = "publisher", unique = true)
    private Long publisher;

    /**
     * 接收人
     */
    @ExcelProperty("接收人")
    @JdbcTypeCode(SqlTypes.JSON)
    @Comment("接收人")
    @Column(name = "receiver", unique = true)
    private List<Long> receiver;

}
