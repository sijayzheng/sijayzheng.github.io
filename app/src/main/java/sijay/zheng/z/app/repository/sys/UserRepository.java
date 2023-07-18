/*
 * Ownership belongs to Sijay Zheng
 */

/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app.repository.sys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sijay.zheng.z.app.entity.sys.SysUser;

/**
 * @author sijay
 * @date 2023/7/17 23:51
 */
@Repository
public interface UserRepository extends JpaRepository<SysUser, Long> {
    SysUser getSysUserByUserName(String userName);
}
