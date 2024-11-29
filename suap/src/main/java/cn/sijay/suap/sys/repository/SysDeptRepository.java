package cn.sijay.suap.sys.repository;

import cn.sijay.suap.sys.entity.SysDept;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <strong>SysDeptRepository</strong>
 * <p>
 * 部门数据层
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Repository
public interface SysDeptRepository extends JpaRepositoryImplementation<SysDept, Long> {

    boolean existsByParentIdIn(List<Long> parentIds);

}
