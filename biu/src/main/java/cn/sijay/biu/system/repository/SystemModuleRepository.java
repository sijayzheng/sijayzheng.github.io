package cn.sijay.biu.system.repository;

import cn.sijay.biu.system.entity.SystemModule;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * SystemModuleRepository
 * 系统模块数据访问层
 *
 * @author Sijay
 * @since 2025-04-14
 */
public interface SystemModuleRepository extends JpaRepositoryImplementation<SystemModule, Long> {

}
