package cn.sijay.bun.system.repository;

import cn.sijay.bun.system.entity.SystemUser;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * <strong>SystemUserRepository</strong>
 * <p>
 * 系统用户
 * </p>
 *
 * @author sijay
 * @since 2024-11-11
 */
public interface SystemUserRepository extends JpaRepositoryImplementation<SystemUser, Long> {

}
