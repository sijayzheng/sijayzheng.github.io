package sijay.zheng.z.app.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sijay
 * @date 2023/7/17 23:51
 */
@Repository
public interface UserRepository extends JpaRepository<SysUser, Long> {
    SysUser getSysUserByUserName(String userName);
}
