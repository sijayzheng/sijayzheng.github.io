package cn.sijay.suap.gen.service;

import cn.sijay.suap.core.base.BaseException;
import cn.sijay.suap.core.base.BaseService;
import cn.sijay.suap.core.constant.Constants;
import cn.sijay.suap.core.constant.ExceptionConstant;
import cn.sijay.suap.core.entity.PageQuery;
import cn.sijay.suap.core.enums.GenFileType;
import cn.sijay.suap.core.enums.JavaType;
import cn.sijay.suap.core.utils.FileUtil;
import cn.sijay.suap.core.utils.StringUtil;
import cn.sijay.suap.core.utils.VelocityUtil;
import cn.sijay.suap.gen.entity.GenTable;
import cn.sijay.suap.gen.entity.GenTableColumn;
import cn.sijay.suap.gen.entity.SchemaColumn;
import cn.sijay.suap.gen.entity.SchemaTable;
import cn.sijay.suap.gen.repository.GenTableColumnRepository;
import cn.sijay.suap.gen.repository.GenTableRepository;
import cn.sijay.suap.gen.repository.SchemaColumnRepository;
import cn.sijay.suap.gen.repository.SchemaTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * <em>GenService</em>
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class GenService implements BaseService<GenTable> {
    private final GenTableColumnRepository genTableColumnRepository;
    private final GenTableRepository genTableRepository;
    private final SchemaTableRepository schemaTableRepository;
    private final SchemaColumnRepository schemaColumnRepository;

    /**
     * 分页查询
     *
     * @param entity    实体类
     * @param pageQuery 分页查询条件
     * @return 分页查询结果
     */
    private Page<GenTable> page(GenTable entity, PageQuery pageQuery) {
        return genTableRepository.findAll(buildSpecification(entity), pageQuery.build());
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(GenTable genTable) {
        genTableRepository.save(genTable);
        Long genTableId = genTable.getId();
        List<GenTableColumn> fields = genTable.getFields()
                                              .stream()
                                              .peek(item -> item.setTableId(genTableId))
                                              .toList();
        genTableColumnRepository.saveAll(fields);
    }

    /**
     * 删除
     *
     * @param ids id
     */
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            List<Long> list = ids.stream()
                                 .filter(id -> !genTableRepository.existsById(id))
                                 .collect(Collectors.toList());
            if (CollectionUtils.isEmpty(list)) {
                genTableColumnRepository.deleteByTableIdIn(list);
                genTableRepository.deleteAllById(ids);
            } else {
                throw new BaseException(ExceptionConstant.ENTITY_NOT_FOUND, list);
            }
        } else {
            throw new BaseException(ExceptionConstant.REQUEST_PARAM_ERROR);
        }
    }

    private List<GenTable> listAllTable() {
        List<String> list = genTableRepository.findAll()
                                              .stream()
                                              .map(GenTable::getTableName)
                                              .toList();
        return schemaTableRepository.findAllTable()
                                    .stream()
                                    .map(SchemaTable::toGenTable)
                                    .filter(item -> !item.getTableName()
                                                         .startsWith("gen_"))
                                    .filter(item -> !list.contains(item.getTableName()))
                                    .map(this::initTable)
                                    .collect(Collectors.toList());
    }

    private GenTable getTableDetail(Long id) {
        GenTable table = genTableRepository.getReferenceById(id);
        table.setFields(genTableColumnRepository.findAllByTableId((id)));
        return table;
    }

    private Map<String, String> preview(Long id) {
        GenTable genTable = genTableRepository.getReferenceById(id);
        setExtAttr(genTable);
        VelocityUtil.initVelocity();
        VelocityContext context = prepareContext(genTable);
        String dir = genTable.getTemplate();
        return Arrays.stream(GenFileType.values())
                     .map(GenFileType::getFileName)
                     .collect(Collectors.toMap(template -> template,
                         template -> render(dir + File.separator + template, context)));

    }

    public void generate(List<Long> ids) {
        List<GenTable> list;
        if (CollectionUtils.isNotEmpty(ids)) {
            list = genTableRepository.findAllById(ids);
        } else {
            list = genTableRepository.findAll();
        }
        for (GenTable genTable : list) {
            genToDir(genTable);
        }
    }

    private void genToDir(GenTable genTable) {
        String genPath = System.getProperty("user.dir");
        setExtAttr(genTable);
        VelocityUtil.initVelocity();
        VelocityContext context = prepareContext(genTable);
        final String packageDir = genTable.getPackageName()
                                          .replace(".", File.separator);
        final String moduleName = genTable.getModuleName();
        final String className = genTable.getClassName();
        final String javaPath = FileUtil.concatPath(genPath, "src", "main", "java", packageDir, moduleName);
        for (GenFileType fileType : GenFileType.values()) {
            String path = switch (fileType) {
                case ENTITY -> FileUtil.concatPath(javaPath, "entity", className + ".java");
                case CONTROLLER -> FileUtil.concatPath(javaPath, "controller", className + "Controller.java");
                case SERVICE -> FileUtil.concatPath(javaPath, "service", "I" + className + "Service.java");
                case SERVICE_IMPL -> FileUtil.concatPath(javaPath, "service", "impl", className + "ServiceImpl.java");
                case MAPPER -> FileUtil.concatPath(javaPath, "mapper", className + "Mapper.java");
                case MAPPER_XML -> FileUtil.concatPath(genPath, "src", "main", "resources", "moduleName", className + "Mapper.xml");
                case VUE -> FileUtil.concatPath(genPath, "ui", "src", "views", moduleName, genTable.getBusinessName() + ".vue");
                case JS -> FileUtil.concatPath(genPath, "ui", "src", "api", moduleName, genTable.getBusinessName() + ".js");
                case SQL -> FileUtil.concatPath(genPath, "sql", genTable.getBusinessName() + ".sql");
            };
            FileUtil.mkDirOrTouch(path);
            try (FileOutputStream fos = new FileOutputStream(path)) {
                String template = fileType.getFileName() + ".vm";
                if (fileType == GenFileType.VUE && "tree".equals(genTable.getTemplate())) {
                    template = "tree/" + fileType.getFileName() + ".vm";
                }
                fos.write(render(template, context).getBytes(StandardCharsets.UTF_8));
                log.info("代码生成成功：{}", path);
            } catch (FileNotFoundException e) {
                throw new BaseException(ExceptionConstant.FILE_NOT_FOUND, path);
            } catch (IOException e) {
                throw new BaseException("渲染模板失败，表名：" + context.get("tableName"));
            }
        }
    }

    private void setExtAttr(GenTable genTable) {
        List<GenTableColumn> fields = genTableColumnRepository.findAllByTableId(genTable.getId());
        Set<String> imports = fields.stream()
                                    .map(field -> JavaType.getFullName(field.getJavaType()))
                                    .filter(StringUtil::isNotBlank)
                                    .collect(Collectors.toSet());
        genTable.setImports(imports);
        genTable.setFields(fields);
        genTable.setBusinessName(StringUtil.toLowerCamelCase(genTable.getClassName()));
    }

    private VelocityContext prepareContext(GenTable genTable) {
        VelocityContext context = new VelocityContext();
        context.put("tableName", genTable.getTableName());
        context.put("tableComment", genTable.getComment());
        context.put("className", genTable.getClassName());
        context.put("packageName", genTable.getPackageName());
        context.put("businessName", genTable.getBusinessName());
        context.put("functionName", genTable.getComment()
                                            .replaceAll("表$", ""));
        context.put("author", genTable.getAuthor());
        context.put("moduleName", genTable.getModuleName());
        context.put("menuId", genTable.getMenuId());
        GenTableColumn pk = genTable.getFields()
                                    .stream()
                                    .filter(item -> item.getColumnKey()
                                                        .equals("PRI"))
                                    .findFirst()
                                    .orElse(new GenTableColumn());
        context.put("primaryKey", pk);
        context.put("unique", genTable.getFields()
                                      .stream()
                                      .anyMatch(item -> item.getColumnKey()
                                                            .equals("UNI")));
        context.put("isTree", "tree".equals(genTable.getTemplate()));
//        context.put("createTime", LocalDate.now().toString());
        context.put("createTime", "2024-07-18");
        context.put("imports", genTable.getImports());
        context.put("fields", genTable.getFields());
        return context;
    }

    private String render(String template, VelocityContext context) {
        StringWriter sw = new StringWriter();
        Template tpl = Velocity.getTemplate("/templates/" + template, Constants.UTF8);
        tpl.merge(context, sw);
        return sw.toString();
    }

    @Transactional(rollbackFor = Exception.class)
    public void autoImport() {
        List<GenTable> list = listAllTable()
            .stream()
            .peek(table -> initTable(table).setFields(listColumnByTableName(table.getTableName())))
            .toList();
        for (GenTable genTable : list) {
            GenTable save = genTableRepository.save(genTable);
            Long tableId = save.getId();
            genTableColumnRepository.saveAll(genTable.getFields()
                                                     .stream()
                                                     .peek(item -> item.setTableId(tableId))
                                                     .toList());
        }
    }

    private GenTable initTable(GenTable table) {
        table.setClassName(StringUtil.toUpperCamelCase(table.getTableName()));
        table.setModuleName(table.getTableName()
                                 .split("_")[0]);
        table.setPackageName("cn.sijay.suap");
        table.setAuthor("sijay");
        table.setMenuId(1L);
        table.setTemplate("list");
        table.setBusinessName(StringUtil.toLowerCamelCase(table.getTableName()));
        return table;
    }

    private List<GenTableColumn> listColumnByTableName(String tableName) {
        return initFields(schemaColumnRepository.findAllByTableName(tableName)
                                                .stream()
                                                .map(SchemaColumn::toGenTableColumn)
                                                .toList());
    }

    private List<GenTableColumn> initFields(List<GenTableColumn> columns) {
        return columns.stream()
                      .peek(item -> {
                          item.setFieldName(StringUtil.toLowerCamelCase(item.getColumnName()));
                          item.setJavaTypeEnum(switch (item.getDataType()) {
                              case "bigint" -> JavaType.Long;
                              case "datetime" -> JavaType.LocalDateTime;
                              case "date" -> JavaType.LocalDate;
                              case "time" -> JavaType.LocalTime;
                              case "int" -> JavaType.Integer;
                              case "decimal" -> JavaType.BigDecimal;
                              case "double" -> JavaType.Double;
                              case "boolean" -> JavaType.Boolean;
                              case "json" -> JavaType.List;
                              default -> JavaType.String;
                          });
                          item.setJavaType(item.getJavaTypeEnum()
                                               .name());
                          if (List.of("create_by", "create_time", "update_by", "update_time")
                                  .contains(item.getColumnName())) {
                              item.setVisible(false);
                              item.setAddable(false);
                              item.setEditable(false);
                              item.setQueryable(false);
                              item.setExcelColumn(false);
                              item.setQueryType("");
                              item.setInputType("");
                          } else {
                              item.setVisible(true);
                              item.setAddable(!"PRI".equals(item.getColumnKey()));
                              item.setEditable(true);
                              item.setQueryable(!"PRI".equals(item.getColumnKey()));
                              item.setExcelColumn(true);
                              item.setQueryType(switch (item.getJavaTypeEnum()) {
                                  case JavaType, OperateType, Long, Integer, Byte, BigDecimal, Double, GenType, GenderType, TemplateType, Boolean ->
                                      "equal";
                                  case List, Set -> "in";
                                  case LocalDateTime, LocalDate, LocalTime -> "between";
                                  default -> "like";
                              });
                              item.setInputType(switch (item.getJavaTypeEnum()) {
                                  case Long, Integer, Byte, BigDecimal, Double -> "number_input";
                                  case JavaType, OperateType, QueryType, List, Set -> "select";
                                  case GenType, GenderType, TemplateType -> "radio";
                                  case LocalDateTime -> "datetime_pick";
                                  case LocalDate -> "date_pick";
                                  case LocalTime -> "time_pick";
                                  case Boolean -> "switch";
                                  default -> "input";
                              });
                          }
                      })
                      .toList();
    }

}
