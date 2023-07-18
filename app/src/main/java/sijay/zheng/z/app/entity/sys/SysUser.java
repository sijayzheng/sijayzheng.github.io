/*
 * Ownership belongs to Sijay Zheng
 */

/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app.entity.sys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author sijay
 * @date 2023/7/17 23:51
 */
@Table(name = "sys_user")
@Data
@Entity
public class SysUser {
    // 用户ID
    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;
    // 用户账号
    @Column(name = "user_name", nullable = false, length = 30, unique = true)
    private String userName;
    // 密码
    @Column(name = "password", nullable = false, length = 100)
    private String password;
}
