package cn.sijay.biu.system.repository;

import cn.sijay.biu.system.entity.SystemMessage;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * SystemMessageRepository
 * 系统消息数据访问层
 *
 * @author Sijay
 * @since 2025-04-14
 */
public interface SystemMessageRepository extends JpaRepositoryImplementation<SystemMessage, Long> {

}
