package cn.sijay.biu.system.repository;

import cn.sijay.biu.system.entity.SystemRole;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * SystemRoleRepository
 * 系统权限数据访问层
 *
 * @author Sijay
 * @since 2025-04-14
 */
public interface SystemRoleRepository extends JpaRepositoryImplementation<SystemRole, Long> {

}
