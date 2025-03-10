package cn.sijay.bun.gen.repository;

import cn.sijay.bun.gen.entity.GenColumn;
import cn.sijay.bun.gen.enums.ColumnKeyType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface GenColumnRepository extends JpaRepositoryImplementation<GenColumn, Long> {
    @Query(value = """
           select column_name,
                       ordinal_position,
                       is_nullable,
                       data_type,
                       character_maximum_length,
                       numeric_precision,
                       numeric_scale,
                       column_key,
                       column_comment,
                       column_default
                from information_schema.columns
                where table_schema = (select database())
                  and table_name = ?1
                order by ordinal_position
        """, nativeQuery = true)
    List<ColumnInfo> getColumnInfoByTableName(String tableName);

    default List<GenColumn> getColumnByTableName(String tableName) {
        return getColumnInfoByTableName(tableName).stream().map(item -> {
            GenColumn genColumn = new GenColumn();
            genColumn.setColumnName(item.getColumnName());
            genColumn.setColumnComment(item.getColumnComment());
            genColumn.setDataType(item.getDataType());
            genColumn.setSort(item.getOrdinalPosition());
            genColumn.setNullable("yes".equalsIgnoreCase(item.getIsNullable()));
            genColumn.setLength(item.getCharacterMaximumLength());
            genColumn.setNumericPrecision(item.getNumericPrecision());
            genColumn.setNumericScale(item.getNumericScale());
            if (StringUtils.isNoneBlank(item.getColumnKey())) {
                genColumn.setColumnKey(ColumnKeyType.valueOf(item.getColumnKey()));
            }
            genColumn.setDefaultValue(item.getColumnDefault());
            return genColumn;
        }).toList();
    }

    interface ColumnInfo {
        /**
         * 列名
         */
        String getColumnName();

        /**
         * 列注释
         */
        String getColumnComment();

        /**
         * 字段类型
         */
        String getDataType();

        /**
         * 排序
         */
        Integer getOrdinalPosition();

        /**
         * 可空
         */
        String getIsNullable();

        /**
         * 文本长度
         */
        Long getCharacterMaximumLength();

        /**
         * 数值长度
         */
        Integer getNumericPrecision();

        /**
         * 小数位数
         */
        Integer getNumericScale();

        /**
         * 列类型
         */
        String getColumnKey();

        /**
         * 默认值
         */
        String getColumnDefault();

    }
}
