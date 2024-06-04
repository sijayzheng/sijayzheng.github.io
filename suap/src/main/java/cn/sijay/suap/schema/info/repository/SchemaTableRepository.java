package cn.sijay.suap.schema.info.repository;

import cn.sijay.suap.schema.info.entity.SchemaTable;
import cn.sijay.suap.schema.info.entity.TableId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchemaTableRepository extends JpaRepository<SchemaTable, TableId> {

    @Query(value = "select * from information_schema.tables where table_schema = (select database())", nativeQuery = true)
    List<SchemaTable> findAllTable();
}