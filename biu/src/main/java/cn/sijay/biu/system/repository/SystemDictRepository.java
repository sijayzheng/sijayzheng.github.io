package cn.sijay.biu.system.repository;

import cn.sijay.biu.system.entity.SystemDict;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * SystemDictRepository
 * 系统字典数据访问层
 *
 * @author Sijay
 * @since 2025-04-14
 */
public interface SystemDictRepository extends JpaRepositoryImplementation<SystemDict, Long> {

}
