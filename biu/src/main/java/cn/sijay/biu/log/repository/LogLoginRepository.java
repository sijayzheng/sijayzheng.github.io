package cn.sijay.biu.log.repository;

import cn.sijay.biu.log.entity.LogLogin;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * LogLoginRepository
 * 登录日志数据访问层
 *
 * @author Sijay
 * @since 2025-04-14
 */
public interface LogLoginRepository extends JpaRepositoryImplementation<LogLogin, Long> {

}
