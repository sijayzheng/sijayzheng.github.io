package cn.sijay.suap.sys.repository;

import cn.sijay.suap.sys.entity.SysMenu;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <strong>SysMenuRepository</strong>
 * <p>
 * 菜单数据层
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Repository
public interface SysMenuRepository extends JpaRepositoryImplementation<SysMenu, Long> {

    boolean existsByParentIdIn(List<Long> parentIds);

}
