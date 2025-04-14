package cn.sijay.biu.generate.repository;

import cn.sijay.biu.generate.entity.GenTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenTableRepository extends JpaRepositoryImplementation<GenTable, Long> {
    /**
     * 查询表信息
     *
     * @param schema    架构
     * @param tableName 表名称
     * @param exists    已存在的表
     * @return 表信息
     */
    @Query(value = """
            select row_number() over () as id,
            table_name,
            table_comment,
            '' as class_name,
            '' as class_comment,
            'LIST' as template_type,
            '' as package_name,
            '' as module_name,
            '' as author,
            'ZIP' as gen_type,
            1 as parent_menu_id,
            '' as tree_key,
            '' as tree_label,
            '' as tree_parent_key
            from information_schema.TABLES
            where TABLE_SCHEMA = ?1 and TABLE_NAME not like 'gen_%' and TABLE_NAME like ?2 and TABLE_NAME not in (?3)
            """, nativeQuery = true)
    List<GenTable> findAllTable(String schema, String tableName, String exists);

    /**
     * 查询表信息
     *
     * @param schema 架构
     * @param names  表名
     * @return 表信息
     */
    @Query(value = """
            select row_number() over () as id,
            table_name,
            table_comment,
            '' as class_name,
            '' as class_comment,
            'LIST' as template_type,
            '' as package_name,
            '' as module_name,
            '' as author,
            'ZIP' as gen_type,
            '' as parent_menu_id,
            '' as tree_key,
            '' as tree_label,
            '' as tree_parent_key
            from information_schema.TABLES
            where TABLE_SCHEMA = ?1 and TABLE_NAME not like 'gen_%' and TABLE_NAME in (?2)
            """, nativeQuery = true)
    List<GenTable> findAllTableInTableNames(String schema, List<String> names);

}
