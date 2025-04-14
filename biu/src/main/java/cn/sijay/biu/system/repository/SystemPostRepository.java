package cn.sijay.biu.system.repository;

import cn.sijay.biu.system.entity.SystemPost;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * SystemPostRepository
 * 系统岗位数据访问层
 *
 * @author Sijay
 * @since 2025-04-14
 */
public interface SystemPostRepository extends JpaRepositoryImplementation<SystemPost, Long> {

}
