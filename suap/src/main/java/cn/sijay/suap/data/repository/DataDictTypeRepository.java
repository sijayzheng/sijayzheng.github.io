package cn.sijay.suap.data.repository;

import cn.sijay.suap.data.entity.DataDictType;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * <strong>DataDictTypeRepository</strong>
 * <p>
 * 数据字典类型数据层
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Repository
public interface DataDictTypeRepository extends JpaRepositoryImplementation<DataDictType, Long> {

    boolean existsByCode(String code);

    boolean existsByCodeAndId(String code, Long id);

}
