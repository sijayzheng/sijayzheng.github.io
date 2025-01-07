package cn.sijay.demos.controller;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Comment("系统消息表")
@Entity
@Table(name = "system_message", schema = "test")
public class SystemMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = -2454746508536873657L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("id")
    @Column(name = "id", nullable = false)
    private Long id;

    @Comment("消息标题")
    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Comment("消息类型")
    @ColumnDefault("'NOTICE'")
    @Column(name = "type", nullable = false, length = 12)
    private String type;

    @Comment("内容")
    @Column(name = "content", length = 5000)
    private String content;

    @Comment("状态")
    @ColumnDefault("0")
    @Column(name = "closed")
    private Boolean closed;

    @Comment("发布人")
    @Column(name = "publisher")
    private Long publisher;

    @Comment("接收人")
    @Column(name = "receiver")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Long> receiver;

    @Comment("创建部门")
    @Column(name = "create_dept")
    private Long createDept;

    @Comment("创建者")
    @Column(name = "create_by")
    private Long createBy;

    @Comment("创建时间")
    @Column(name = "create_date")
    private Instant createDate;

    @Comment("更新者")
    @Column(name = "update_by")
    private Long updateBy;

    @Comment("更新时间")
    @Column(name = "update_date")
    private Instant updateDate;

}