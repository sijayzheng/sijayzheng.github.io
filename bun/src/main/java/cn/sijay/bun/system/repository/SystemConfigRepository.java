package cn.sijay.bun.system.repository;

import cn.sijay.bun.system.entity.SystemConfig;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * <strong>SystemConfigRepository</strong>
 * <p>
 * 系统配置
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
public interface SystemConfigRepository extends JpaRepositoryImplementation<SystemConfig, Long> {

}
