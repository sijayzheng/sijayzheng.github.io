package cn.sijay.suap.sys.repository;

import cn.sijay.suap.sys.entity.SysRole;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * <strong>SysRoleRepository</strong>
 * <p>
 * 角色数据层
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Repository
public interface SysRoleRepository extends JpaRepositoryImplementation<SysRole, Long> {

    boolean existsByCode(String code);

    boolean existsByCodeAndId(String code, Long id);

}
