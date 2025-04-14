package cn.sijay.bun;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * <strong>GenTest</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-01
 */
@SpringBootTest(classes = BunApplication.class)
public class GenTest {
//    @Autowired
//    private GenService genService;
//    @Autowired
//    private GenTableService tableService;
//    @Autowired
//    private GenColumnService columnService;
//
//    @Test
//    public void test() {
//        List<String> existTables = tableService.findAll().parallelStream().map(GenTable::getTableName).toList();
//        List<GenTable> tables = tableService.getAllTable().stream()
//                                            .filter(item -> !existTables.contains(item.getTableName()))
//                                            .toList();
//        for (GenTable table : tables) {
//            System.out.println(JSONUtil.toJSONString(table));
//            List<GenColumn> columns = columnService.getColumnByTableName(table.getTableName());
//            for (GenColumn column : columns) {
//                System.out.println(JSONUtil.toJSONString(column));
//            }
//        }
//    }
//
//    @Test
//    public void autoImport() {
//        genService.autoImport();
//    }
//
//    @Test
//    public void generate() {
//        for (long i = 4; i < 15; i++) {
//            genService.generate(i);
//        }
//    }
}
