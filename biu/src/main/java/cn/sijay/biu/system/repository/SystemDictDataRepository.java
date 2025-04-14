package cn.sijay.biu.system.repository;

import cn.sijay.biu.system.entity.SystemDictData;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * SystemDictDataRepository
 * 系统字典数据数据访问层
 *
 * @author Sijay
 * @since 2025-04-14
 */
public interface SystemDictDataRepository extends JpaRepositoryImplementation<SystemDictData, Long> {

}
