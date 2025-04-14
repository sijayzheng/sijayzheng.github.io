package cn.sijay.biu.system.repository;

import cn.sijay.biu.system.entity.SystemMenu;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * SystemMenuRepository
 * 系统菜单数据访问层
 *
 * @author Sijay
 * @since 2025-04-14
 */
public interface SystemMenuRepository extends JpaRepositoryImplementation<SystemMenu, Long> {

}
