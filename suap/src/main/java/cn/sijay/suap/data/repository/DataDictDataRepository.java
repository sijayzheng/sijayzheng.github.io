package cn.sijay.suap.data.repository;

import cn.sijay.suap.data.entity.DataDictData;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * <strong>DataDictDataRepository</strong>
 * <p>
 * 数据字典项数据层
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Repository
public interface DataDictDataRepository extends JpaRepositoryImplementation<DataDictData, Long> {

}
