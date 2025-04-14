package cn.sijay.biu.system.entity;

import cn.sijay.biu.core.base.IdEntity;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Comment;

/**
 * SystemUserAvatar
 * 用户头像实体类
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
@Comment("用户头像")
@Entity
@Table(name = "system_user_avatar")
public class SystemUserAvatar extends IdEntity {

    /**
     * 头像
     */
    @Size(max = 65535, message = "头像最大长度为{max}")
    @Comment("头像")
    @Column(name = "avatar", unique = true, length = 65535)
    private byte[] avatar;

}
