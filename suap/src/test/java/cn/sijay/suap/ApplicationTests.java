package cn.sijay.suap;

import cn.sijay.suap.gen.repository.GenTableColumnRepository;
import cn.sijay.suap.gen.repository.GenTableRepository;
import cn.sijay.suap.schema.info.entity.SchemaColumn;
import cn.sijay.suap.schema.info.entity.SchemaTable;
import cn.sijay.suap.schema.info.repository.SchemaColumnRepository;
import cn.sijay.suap.schema.info.repository.SchemaTableRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private GenTableRepository genTableRepository;
    @Autowired
    private GenTableColumnRepository genTableColumnRepository;
    @Autowired
    private SchemaTableRepository schemaTableRepository;
    @Autowired
    private SchemaColumnRepository schemaColumnRepository;

    @Test
    void test() {
        for (SchemaTable schemaTable : schemaTableRepository.findAllTable()) {
            String tableName = schemaTable.getId().getTableName();
            System.out.println(schemaTable);
            for (SchemaColumn schemaColumn : schemaColumnRepository.findAllByTableName(tableName)) {
                System.out.println(schemaColumn);
            }
        }
    }

}
