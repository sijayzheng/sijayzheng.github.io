package cn.sijay.biu.system.repository;

import cn.sijay.biu.system.entity.SystemDept;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * SystemDeptRepository
 * 系统部门数据访问层
 *
 * @author Sijay
 * @since 2025-04-09
 */
public interface SystemDeptRepository extends JpaRepositoryImplementation<SystemDept, Long> {

}
