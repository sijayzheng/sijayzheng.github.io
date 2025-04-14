package cn.sijay.biu.core.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * BaseEntity
 *
 * @author Sijay
 * @since 2025-03-07
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity extends IdEntity {

    /**
     * 创建部门
     */
    @Comment("创建部门")
    @Column(name = "create_dept")
    private Long createDept;
    /**
     * 创建者
     */
    @CreatedBy
    @Comment("创建者")
    @Column(name = "create_by")
    private Long createBy;
    /**
     * 创建时间
     */
    @CreatedDate
    @Comment("创建时间")
    @Column(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 更新者
     */
    @LastModifiedBy
    @Comment("更新者")
    @Column(name = "update_by")
    private Long updateBy;
    /**
     * 更新时间
     */
    @LastModifiedDate
    @Comment("更新时间")
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @PrePersist
    public void onUpdate() {
        this.createDept = 444L;//LoginHelper.getDeptId();
    }

}
