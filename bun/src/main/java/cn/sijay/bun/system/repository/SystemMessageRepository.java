package cn.sijay.bun.system.repository;

import cn.sijay.bun.system.entity.SystemMessage;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * <strong>SystemMessageRepository</strong>
 * <p>
 * 系统消息
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
public interface SystemMessageRepository extends JpaRepositoryImplementation<SystemMessage, Long> {

}
