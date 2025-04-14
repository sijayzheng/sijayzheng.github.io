package cn.sijay.suap.sys.repository;

import cn.sijay.suap.sys.entity.SysUser;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * <strong>SysUserRepository</strong>
 * <p>
 * 用户数据层
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Repository
public interface SysUserRepository extends JpaRepositoryImplementation<SysUser, Long> {

    boolean existsByUsername(String username);

    boolean existsByUsernameAndId(String username, Long id);

    boolean existsByPhone(String phone);

    boolean existsByPhoneAndId(String phone, Long id);

    SysUser findByUsername(String username);
}
