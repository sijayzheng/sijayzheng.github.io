package cn.sijay.bun.system.repository;

import cn.sijay.bun.system.entity.SystemRole;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * <strong>SystemRoleRepository</strong>
 * <p>
 * 系统权限
 * </p>
 *
 * @author sijay
 * @since 2024-11-11
 */
public interface SystemRoleRepository extends JpaRepositoryImplementation<SystemRole, Long> {

}
