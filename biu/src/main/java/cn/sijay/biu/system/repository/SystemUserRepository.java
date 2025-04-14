package cn.sijay.biu.system.repository;

import cn.sijay.biu.system.entity.SystemUser;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * SystemUserRepository
 * 系统用户数据访问层
 *
 * @author Sijay
 * @since 2025-04-09
 */
public interface SystemUserRepository extends JpaRepositoryImplementation<SystemUser, Long> {

    SystemUser findByUsername(String username);
}
