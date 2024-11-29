package cn.sijay.suap.gen.repository;

import cn.sijay.suap.gen.entity.GenTableColumn;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Collection;
import java.util.List;

public interface GenTableColumnRepository extends JpaRepositoryImplementation<GenTableColumn, Long> {
    void deleteByTableId(Long tableId);

    void deleteByTableIdIn(Collection<Long> tableIds);

    List<GenTableColumn> findAllByTableId(Long tableId);
}
