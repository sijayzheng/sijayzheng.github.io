package cn.sijay.biu.system.repository;

import cn.sijay.biu.system.entity.SystemUserAvatar;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * SystemUserAvatarRepository
 * 用户头像数据访问层
 *
 * @author Sijay
 * @since 2025-04-14
 */
public interface SystemUserAvatarRepository extends JpaRepositoryImplementation<SystemUserAvatar, Long> {

}
