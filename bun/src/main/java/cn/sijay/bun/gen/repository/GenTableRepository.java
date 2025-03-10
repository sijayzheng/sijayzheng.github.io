package cn.sijay.bun.gen.repository;

import cn.sijay.bun.gen.entity.GenTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface GenTableRepository extends JpaRepositoryImplementation<GenTable, Long> {
    @Query(value = """
            select table_name, table_comment
                from information_schema.tables
                where table_schema = (select database())
                  and table_name != 'gen_column'
                  and table_name != 'gen_table'
                and TABLE_NAME not in (select table_name from gen_table)
        """, nativeQuery = true)
    List<TableInfo> listAllTable();

    default List<GenTable> getAllTable() {
        return listAllTable().stream().map(item -> {
            GenTable table = new GenTable();
            table.setTableName(item.getTableName());
            table.setTableComment(item.getTableComment());
            return table;
        }).toList();
    }

    interface TableInfo {
        /**
         * 表名称
         */
        String getTableName();

        /**
         * 表描述
         */
        String getTableComment();
    }
}
