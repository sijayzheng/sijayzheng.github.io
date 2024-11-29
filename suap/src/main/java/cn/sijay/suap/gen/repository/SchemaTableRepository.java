package cn.sijay.suap.gen.repository;

import cn.sijay.suap.gen.entity.SchemaTable;
import cn.sijay.suap.gen.entity.TableId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchemaTableRepository extends JpaRepository<SchemaTable, TableId> {

    @Query(value = "select * from information_schema.tables where table_schema = (select database())", nativeQuery = true)
    List<SchemaTable> findAllTable();
}
