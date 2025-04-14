package cn.sijay.biu.log.repository;

import cn.sijay.biu.log.entity.LogOperate;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * LogOperateRepository
 * 操作日志数据访问层
 *
 * @author Sijay
 * @since 2025-04-14
 */
public interface LogOperateRepository extends JpaRepositoryImplementation<LogOperate, Long> {

}
