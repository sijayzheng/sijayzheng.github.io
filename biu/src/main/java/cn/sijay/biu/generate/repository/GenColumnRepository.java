package cn.sijay.biu.generate.repository;

import cn.sijay.biu.generate.entity.GenColumn;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface GenColumnRepository extends JpaRepositoryImplementation<GenColumn, Long> {
    void deleteByTableIdIn(Collection<Long> tableIds);

    List<GenColumn> findAllByTableId(@NotNull Long tableId);

    @Query(value = """
            select ?3+ordinal_position as id,
            column_name,
            column_comment,
            data_type,
            ordinal_position as sort,
            is_nullable='yes' as nullable,
            character_maximum_length as length,
            numeric_precision,
            numeric_scale,
            if(column_key='','NONE',column_key) as column_key,
            ifnull(column_default,'') as default_value,
            '' as table_id,
            '' as java_field,
            'STRING' as java_type,
            '' as dict_type,
            false as editable,
            false as listable,
            false as queryable,
            'NONE' as query_type,
            'INPUT' as html_type,
            false as exportable
            from information_schema.columns
            where table_schema=?1 and table_name=?2
            order by ordinal_position
            """, nativeQuery = true)
    List<GenColumn> findAllByTableName(String schema, String tableName, long i);

    void deleteByTableId(Long tableId);
}
