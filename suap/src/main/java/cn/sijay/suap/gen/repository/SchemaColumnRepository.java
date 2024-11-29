package cn.sijay.suap.gen.repository;

import cn.sijay.suap.gen.entity.ColumnId;
import cn.sijay.suap.gen.entity.SchemaColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchemaColumnRepository extends JpaRepository<SchemaColumn, ColumnId> {

    @Query(value = "select * from information_schema.columns where table_schema=(select database()) and table_name=?1 order by ordinal_position", nativeQuery = true)
    List<SchemaColumn> findAllByTableName(String tableName);
}
