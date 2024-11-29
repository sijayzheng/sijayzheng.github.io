package cn.sijay.suap.sys.repository;

import cn.sijay.suap.sys.entity.SysModule;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * <strong>SysModuleRepository</strong>
 * <p>
 * 模块数据层
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Repository
public interface SysModuleRepository extends JpaRepositoryImplementation<SysModule, Long> {

}
