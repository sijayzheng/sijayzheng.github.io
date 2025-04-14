package cn.sijay.suap.sys.repository;

import cn.sijay.suap.sys.entity.SysConfig;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * <strong>SysConfigRepository</strong>
 * <p>
 * 系统配置数据层
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Repository
public interface SysConfigRepository extends JpaRepositoryImplementation<SysConfig, Long> {
    SysConfig findByCode(String code);

    boolean existsByCode(String code);

    boolean existsByCodeAndId(String code, Long id);

}
