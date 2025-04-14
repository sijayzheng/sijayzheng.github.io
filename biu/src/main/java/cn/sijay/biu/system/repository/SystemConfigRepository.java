package cn.sijay.biu.system.repository;

import cn.sijay.biu.system.entity.SystemConfig;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * SystemConfigRepository
 * 系统配置数据访问层
 *
 * @author Sijay
 * @since 2025-04-14
 */
public interface SystemConfigRepository extends JpaRepositoryImplementation<SystemConfig, Long> {

}
