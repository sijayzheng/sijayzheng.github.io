package cn.sijay.biu.generate.service;

import cn.sijay.biu.core.base.BaseService;
import cn.sijay.biu.core.constant.CommonConstants;
import cn.sijay.biu.core.constant.SymbolConstants;
import cn.sijay.biu.core.entity.PageQuery;
import cn.sijay.biu.core.exception.ServiceException;
import cn.sijay.biu.core.util.CollectionUtil;
import cn.sijay.biu.core.util.FileUtil;
import cn.sijay.biu.core.util.NamingUtil;
import cn.sijay.biu.generate.entity.GenColumn;
import cn.sijay.biu.generate.entity.GenTable;
import cn.sijay.biu.generate.enums.HtmlTypeEnum;
import cn.sijay.biu.generate.enums.JavaTypeEnum;
import cn.sijay.biu.generate.enums.QueryTypeEnum;
import cn.sijay.biu.generate.enums.TemplateEnum;
import cn.sijay.biu.generate.properties.GenProperties;
import cn.sijay.biu.generate.repository.GenColumnRepository;
import cn.sijay.biu.generate.repository.GenTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * GenerateService
 *
 * @author Sijay
 * @since 2025-02-27
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GenService implements BaseService {

    private final GenTableRepository tableRepository;
    private final GenColumnRepository columnRepository;
    private final DataSourceProperties dataSourceProperties;
    private final GenProperties genProperties;

    List<String> NOT_ADD = List.of("create_dept", "create_by", "create_time", "deleted", "update_by", "update_time", "version");
    List<String> NOT_QUERY = List.of("id", "create_dept", "create_by", "create_time", "deleted", "update_by", "update_time", "remark", "version");
    Set<String> BASE_ENTITY_COLUMNS = Set.of("id", "create_dept", "create_by", "create_time", "update_by", "update_time");

    public Page<GenTable> page(GenTable genTable, PageQuery pageQuery) {
        return tableRepository.findAll(buildSpecification(genTable), pageQuery.build());
    }

    /**
     * 查询业务字段列表
     *
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
    public List<GenColumn> listColumnByTableId(Long tableId) {
        return columnRepository.findAllByTableId(tableId);
    }

    /**
     * 查询数据库列表
     *
     * @param tableName 表名
     * @return 包含分页结果的ResponseResult对象
     */
    public List<GenTable> selectTableList(String tableName) {
        String exists = tableRepository.findAll().stream().map(GenTable::getTableName).collect(Collectors.joining("','"));
        return tableRepository.findAllTable(dataSourceProperties.getName(), like(tableName), exists);
    }

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    public GenTable getById(Long id) {
        return tableRepository.getReferenceById(id);
    }

    /**
     * 修改业务
     *
     * @param genTable 业务信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateGenTable(GenTable genTable) {
        tableRepository.save(genTable);
        columnRepository.saveAll(genTable.getColumns());
    }

    /**
     * 删除业务对象
     *
     * @param ids 需要删除的数据ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteGenTableByIds(List<Long> ids) {
        tableRepository.deleteAllById(ids);
        columnRepository.deleteByTableIdIn(ids);
    }

    /**
     * 导入表结构
     *
     * @param tableNames 导入表列表
     */
    @Transactional
    public void importGenTable(List<String> tableNames) {
        List<GenTable> tableList = tableRepository.findAllTableInTableNames(dataSourceProperties.getName(), tableNames);
        Set<String> set = tableRepository.findAll().parallelStream().map(GenTable::getTableName).collect(Collectors.toSet());
        tableList.stream()
                 .filter(genTable -> !set.contains(genTable.getTableName()))
                 .forEach(genTable -> {
                     String tableName = genTable.getTableName();
                     log.info(tableName);
                     GenTable table = new GenTable();
                     table.setTableName(tableName);
                     table.setTableComment(genTable.getTableComment());
                     table.setTemplateType(genTable.getTemplateType());
                     table.setGenType(genTable.getGenType());
                     table.setClassName(NamingUtil.snakeToUpperCamel(genTable.getTableName()));
                     table.setModuleName(StringUtils.substringBefore(tableName, "_"));
                     table.setClassComment(RegExUtils.replaceAll(genTable.getTableComment(), "表$", ""));
                     table.setPackageName(genProperties.getPackageName());
                     table.setAuthor(genProperties.getAuthor());
                     tableRepository.saveAndFlush(table);
                     // 保存列信息
                     Long tableId = table.getId();
                     List<GenColumn> columns = listColumnByTableName(tableName)
                             .parallelStream()
                             .map(genColumn -> initColumn(tableId, genColumn))
                             .sorted(Comparator.comparing(GenColumn::getSort))
                             .toList();
                     columnRepository.saveAllAndFlush(columns);
                 });
    }

    /**
     * 预览代码
     *
     * @param tableId 表编号
     * @return 预览数据列表
     */
    public Map<String, String> previewCode(Long tableId) {
        return genCode(tableId);
    }

    /**
     * 生成代码（自定义路径）
     *
     * @param tableId 表名称
     */
    public void generatorCode(Long tableId) {
        genCode(tableId).forEach((path, content) -> FileUtil.writeUtf8String(content, FileUtil.concatPath(System.getProperty("user.dir"), path)));
    }

    /**
     * 生成代码（下载方式）
     *
     * @param tableId 表名称
     * @return 数据
     */
    public byte[] downloadCode(Long tableId) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); ZipOutputStream zip = new ZipOutputStream(outputStream)) {
            for (Map.Entry<String, String> entry : genCode(tableId).entrySet()) {
                zip.putNextEntry(new ZipEntry(entry.getKey()));
                zip.write(entry.getValue().getBytes(StandardCharsets.UTF_8));
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 同步数据库
     *
     * @param tableId 表名称
     */
    @Transactional
    public void syncDb(Long tableId) {
        GenTable table = tableRepository.getReferenceById(tableId);
        String tableName = table.getTableName();
        List<GenColumn> columns = listColumnByTableName(tableName);
        if (CollectionUtil.isEmpty(columns)) {
            throw new ServiceException("同步数据失败，原表结构不存在");
        }
        // 保存列信息
        columns = columns
                .parallelStream()
                .map(genColumn -> initColumn(tableId, genColumn))
                .sorted(Comparator.comparing(GenColumn::getSort))
                .toList();
        columnRepository.deleteByTableId(tableId);
        columnRepository.saveAll(columns);
    }

    /**
     * 批量生成代码（下载方式）
     *
     * @param tableIds 表ID数组
     * @return 数据
     */
    public byte[] downloadCode(List<Long> tableIds) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); ZipOutputStream zip = new ZipOutputStream(outputStream)) {
            for (Long tableId : tableIds) {
                for (Map.Entry<String, String> entry : genCode(tableId).entrySet()) {
                    zip.putNextEntry(new ZipEntry(entry.getKey()));
                    zip.write(entry.getValue().getBytes(StandardCharsets.UTF_8));
                }
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private GenColumn initColumn(Long tableId, GenColumn genColumn) {
        GenColumn column = new GenColumn();
        column.setColumnName(genColumn.getColumnName());
        column.setColumnComment(genColumn.getColumnComment());
        column.setDataType(genColumn.getDataType());
        column.setSort(genColumn.getSort());
        column.setNullable(genColumn.getNullable());
        column.setLength(genColumn.getLength());
        column.setNumericPrecision(genColumn.getNumericPrecision());
        column.setNumericScale(genColumn.getNumericScale());
        column.setColumnKey(genColumn.getColumnKey());
        column.setDefaultValue(genColumn.getDefaultValue());
        column.setTableId(tableId);
        column.setDictType(genColumn.getDictType());
        column.setJavaField(NamingUtil.snakeToCamel(column.getColumnName()));
        column.setJavaType(switch (column.getDataType()) {
            case "tinyint" -> column.getNumericPrecision() == 1 ? JavaTypeEnum.BOOLEAN : JavaTypeEnum.INTEGER;
            case "smallint", "mediumint", "int", "integer", "year" -> JavaTypeEnum.INTEGER;
            case "bigint" -> JavaTypeEnum.LONG;
            case "float" -> JavaTypeEnum.FLOAT;
            case "double" -> JavaTypeEnum.DOUBLE;
            case "decimal", "numeric" -> JavaTypeEnum.BIG_DECIMAL;
            case "date" -> JavaTypeEnum.LOCAL_DATE;
            case "time" -> JavaTypeEnum.LOCAL_TIME;
            case "datetime", "timestamp" -> JavaTypeEnum.LOCAL_DATE_TIME;
            case "bit" -> JavaTypeEnum.BOOLEAN;
            case "set" -> JavaTypeEnum.SET;
            case "json" -> JavaTypeEnum.LIST_LONG;
            case "binary", "varbinary", "tinyblob", "blob", "mediumblob", "longblob" -> JavaTypeEnum.BYTE_ARRAY;
            default -> JavaTypeEnum.STRING;
        });
        String columnName = column.getColumnName();
        column.setHtmlType(switch (column.getJavaType()) {
            case BOOLEAN -> HtmlTypeEnum.RADIO;
            case INTEGER, LONG, BIG_DECIMAL, FLOAT, DOUBLE -> HtmlTypeEnum.INPUT_NUMBER;
            case BYTE_ARRAY -> HtmlTypeEnum.FILE_UPLOAD;
            case LOCAL_DATE -> HtmlTypeEnum.DATE_PICKER;
            case LOCAL_DATE_TIME -> HtmlTypeEnum.DATETIME_PICKER;
            case LOCAL_TIME -> HtmlTypeEnum.TIME_PICKER;
            case CHARACTER, STRING -> (column.getLength() != null && column.getLength() > 500) || column.getDataType().contains("text")
                    ? HtmlTypeEnum.TEXTAREA : HtmlTypeEnum.INPUT;
            case LIST, LIST_LONG, SET, DATA_SCOPE_ENUM -> HtmlTypeEnum.SELECT;
            default -> HtmlTypeEnum.INPUT;
        });
        column.setExportable(false);
        column.setEditable(false);
        column.setListable(false);
        column.setQueryable(false);
        column.setQueryType(QueryTypeEnum.NONE);
        if (!NOT_ADD.contains(columnName)) {
            if (!column.isPk()) {
                column.setExportable(true);
                column.setEditable(true);
            }
            column.setListable(true);
        }
        if (!NOT_QUERY.contains(columnName) && !column.isPk()) {
            column.setQueryable(true);
            column.setQueryType(switch (column.getJavaType()) {
                case LOCAL_DATE, LOCAL_DATE_TIME, LOCAL_TIME -> QueryTypeEnum.BETWEEN;
                case CHARACTER, STRING -> QueryTypeEnum.LIKE;
                case BOOLEAN, DATA_SCOPE_ENUM, GENDER_TYPE, MENU_TYPE, MESSAGE_TYPE,
                     SHOW_TYPE -> QueryTypeEnum.EQUAL;
                default -> QueryTypeEnum.NONE;
            });
        }

        return column;
    }

    /**
     * 根据列类型获取字典组
     *
     * @param genTable 业务表对象
     * @return 返回字典组
     */
    private String getDicts(GenTable genTable) {
        List<GenColumn> columns = genTable.getColumns();
        Set<String> dicts = new HashSet<>();
//        for (GenColumn column : columns) {
//            if (!column.isSuperColumn() && StringUtil.isNotEmpty(column.getDictType()) &&
//                    List.of(HtmlTypeEnum.SELECT, HtmlTypeEnum.RADIO, HtmlTypeEnum.CHECKBOX).contains(column.getHtmlType())) {
//                dicts.add("'" + column.getDictType() + "'");
//            }
//        }
        return StringUtils.join(dicts, SymbolConstants.COMMA);
    }

    private Specification<GenTable> buildSpecification(GenTable genTable) {
        return Specification.allOf(
                like("tableName", genTable.getTableName()),
                like("tableComment", genTable.getTableComment())
        );
    }

    private Map<String, String> genCode(Long tableId) {
        try {
            Properties p = new Properties();
            // 加载classpath目录下的vm文件
            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, CommonConstants.UTF8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new ServiceException("初始化Velocity引擎错误");
        }
        Map<String, String> dataMap = new LinkedHashMap<>();
        GenTable table = tableRepository.getReferenceById(tableId);

        List<GenColumn> tableColumns = columnRepository.findAllByTableId(tableId);
        for (GenColumn column : tableColumns) {
            if (column.isPk()) {
                table.setPkColumn(column);
                break;
            }
        }
        if (Objects.isNull(table.getPkColumn())) {
            table.setPkColumn(tableColumns.getFirst());
        }
        Map<String, GenColumn> columnMap = tableColumns
                .parallelStream()
                .collect(Collectors.toMap(GenColumn::getColumnName, item -> item));
        String superEntityClass = null;
        if (columnMap.keySet().containsAll(BASE_ENTITY_COLUMNS)) {
            for (String column : BASE_ENTITY_COLUMNS) {
                columnMap.remove(column);
            }
            superEntityClass = "BaseEntity";
        } else if (columnMap.containsKey("id")) {
            columnMap.remove("id");
            superEntityClass = "IdEntity";
        }
        List<GenColumn> columns = columnMap.values().parallelStream()
                                           .peek(column -> column.setGetter("get" + StringUtils.capitalize(column.getJavaField())))
                                           .sorted(Comparator.comparing(GenColumn::getSort))
                                           .toList();
        table.setColumns(columns);
        Set<String> imports = columns.stream().map(column -> column.getJavaType().getPackageName())
                                     .filter(StringUtils::isNoneBlank)
                                     .collect(Collectors.toSet());
        if (superEntityClass != null) {
            imports.add("cn.sijay.biu.core.base." + superEntityClass);
        }
        TemplateEnum templateEnum = table.getTemplateType();
        VelocityContext context = new VelocityContext();
        context.put("superEntityClass", superEntityClass);
        context.put("template", templateEnum);
        context.put("tableName", table.getTableName());
        context.put("classComment", StringUtils.defaultIfBlank(table.getClassComment(), "XXX"));
        context.put("ClassName", table.getClassName());
        String businessName = StringUtils.uncapitalize(table.getClassName());
        context.put("className", businessName);
        context.put("moduleName", table.getModuleName());
        context.put("packageName", table.getPackageName());
        context.put("author", table.getAuthor());
        context.put("date", LocalDate.now().toString());
        context.put("pkColumn", table.getPkColumn());
        context.put("imports", imports);
        context.put("columns", table.getColumns());
        context.put("queryColumns", table.getColumns().stream().filter(item -> item.getQueryable() && item.getQueryType() != QueryTypeEnum.NONE)
                                         .toList());
        context.put("permPrefix", businessName + ":");
        context.put("dicts", getDicts(table));
        context.put("parentMenuId", table.getParentMenuId());

        if (templateEnum == TemplateEnum.TREE) {
            context.put("treeKey", table.getTreeKey());
            context.put("treeLabel", table.getTreeLabel());
            context.put("treeParentKey", table.getTreeParentKey());
        }

        // 获取模板列表
        List<String> templates = new ArrayList<>();
        templates.add("vm/entity.java.vm");
        templates.add("vm/repository.java.vm");
        templates.add("vm/service.java.vm");
        templates.add("vm/controller.java.vm");
        templates.add("vm/sql.vm");
        templates.add("vm/api.ts.vm");
        if (templateEnum == TemplateEnum.LIST) {
            templates.add("vm/index.vue.vm");
        } else if (templateEnum == TemplateEnum.TREE) {
            templates.add("vm/index-tree.vue.vm");
        }
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, CommonConstants.UTF8);
            tpl.merge(context, sw);
            // 文件名称
            String fileName = "";
            // 包路径
            String packageName = table.getPackageName();
            // 模块名
            String moduleName = table.getModuleName();
            // 大写类名
            String className = table.getClassName();
            // 业务名称
            String javaPath = FileUtil.concatPath("src", "main", "java", StringUtils.replace(packageName, ".", File.separator));
            if (template.contains("entity.java.vm")) {
                fileName = FileUtil.concatPath(javaPath, moduleName, "entity", className + ".java");
            } else if (template.contains("controller.java.vm")) {
                fileName = FileUtil.concatPath(javaPath, moduleName, "controller", className + "Controller.java");
            } else if (template.contains("service.java.vm")) {
                fileName = FileUtil.concatPath(javaPath, moduleName, "service", className + "Service.java");
            } else if (template.contains("repository.java.vm")) {
                fileName = FileUtil.concatPath(javaPath, moduleName, "repository", className + "Repository.java");
            } else if (template.contains("sql.vm")) {
                fileName = FileUtil.concatPath("sql", businessName + "Menu.sql");
            } else if (template.contains("api.ts.vm")) {
                fileName = FileUtil.concatPath("src", "main", "vue", "src", "api", moduleName, businessName + ".ts");
            } else if (template.contains("index.vue.vm") || template.contains("index-tree.vue.vm")) {
                fileName = FileUtil.concatPath("src", "main", "vue", "src", "views", moduleName, businessName + ".vue");
            }
            dataMap.put(fileName, sw.toString().replace("￥", "$"));
        }
        return dataMap;
    }

    /**
     * 查询数据库列表
     *
     * @param tableName 表名称
     * @return 数据库表集合
     */
    private List<GenColumn> listColumnByTableName(String tableName) {
        return columnRepository.findAllByTableName(dataSourceProperties.getName(), tableName, System.nanoTime());
    }

}
