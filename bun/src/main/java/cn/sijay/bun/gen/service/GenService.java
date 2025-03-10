package cn.sijay.bun.gen.service;

import cn.sijay.bun.common.exception.BaseException;
import cn.sijay.bun.common.util.FileUtil;
import cn.sijay.bun.common.util.JSONUtil;
import cn.sijay.bun.common.util.StringUtil;
import cn.sijay.bun.gen.constants.GenConstants;
import cn.sijay.bun.gen.entity.GenColumn;
import cn.sijay.bun.gen.entity.GenTable;
import cn.sijay.bun.gen.enums.*;
import cn.sijay.bun.gen.properties.GenProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <strong>IGenService</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-11-05
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class GenService {
    private final GenTableService tableService;
    private final GenColumnService columnService;
    private final GenProperties genProperties;

    public void autoImport() {
        List<String> existTables = tableService.findAll().parallelStream().map(GenTable::getTableName).toList();
        List<GenTable> tables = tableService.getAllTable().stream()
                                            .filter(item -> !existTables.contains(item.getTableName()))
                                            .peek(this::initTable)
                                            .toList();
        for (GenTable table : tables) {
            GenTable save = tableService.save(table);
            System.out.println(JSONUtil.toJSONString(table));
            List<GenColumn> columns = columnService.getColumnByTableName(table.getTableName()).stream().peek(this::initColumn).toList();
            for (GenColumn column : columns) {
                column.setTableId(save.getId());
                columnService.save(column);
            }
        }
    }

    public void generate(Long tableId) {
        GenTable table = tableService.getById(tableId);
        table.setColumns(columnService.getColumnByTableId(tableId));
        initVelocity();
        VelocityContext context = prepareContext(table);
        // 获取模板列表
        List<String> templates = getTemplateList(table.getTemplateType());
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, StandardCharsets.UTF_8.name());
            tpl.merge(context, sw);
            try {
                String path = getGenPath(table, template);
                FileUtil.writeFile(path, sw.toString());
            } catch (Exception e) {
                throw new BaseException("渲染模板失败，表名：" + table.getTableName());
            }
        }
    }

    public GenTable getTableById(Long tableId) {
        return tableService.getById(tableId);
    }

    private void initTable(GenTable table) {
        table.setTableName(table.getTableName());
        table.setClassName(StringUtil.toUpperCamelCase(table.getTableName()));
        table.setClassComment(table.getTableComment().replaceAll("(.+)表$", "$1"));
        table.setTemplateType(genProperties.getTemplateType());
        table.setPackageName(genProperties.getPackageName());
        table.setModuleName(table.getTableName().split("_")[0]);
        table.setAuthor(genProperties.getAuthor());
        table.setGenType(genProperties.getGenType());
        table.setGenPath(genProperties.getGenPath());
        table.setParentMenuId(1L);
    }

    private void initColumn(GenColumn column) {
        column.setJavaField(StringUtil.toLowerCamelCase(column.getColumnName()));
        column.setJavaType(switch (column.getDataType()) {
            case "tinyint", "bit" -> JavaType.BOOLEAN;
            case "smallint", "mediumint", "int", "year" -> JavaType.INTEGER;
            case "bigint" -> JavaType.LONG;
            case "decimal" -> JavaType.BIG_DECIMAL;
            case "float" -> JavaType.FLOAT;
            case "double" -> JavaType.DOUBLE;
            case "date" -> JavaType.LOCAL_DATE;
            case "datetime", "timestamp" -> JavaType.LOCAL_DATE_TIME;
            case "time" -> JavaType.LOCAL_TIME;
            case "char" -> JavaType.CHARACTER;
            case "binary", "varbinary", "blob" -> JavaType.BYTE_ARRAY;
            case "json" -> JavaType.LIST_LONG;
            case "set" -> JavaType.SET;
            default -> JavaType.STRING;
        });
        if (GenConstants.AUTO_FIELD.contains(column.getColumnName())) {
            column.setQueryable(false);
            column.setListable(false);
            column.setEditable(false);
            column.setInsertable(false);
            column.setExportable(false);
        } else {
            column.setQueryable(canQuery(column));
            column.setExportable(canQuery(column));
            if (column.getQueryable()) {
                column.setQueryType(switch (column.getJavaType()) {
                    case BOOLEAN, INTEGER, LONG, BIG_DECIMAL, FLOAT, DOUBLE, DATA_SCOPE_ENUM, GENDER_TYPE, MENU_TYPE, MESSAGE_TYPE, SHOW_TYPE ->
                        QueryType.EQUAL;
                    case LOCAL_DATE, LOCAL_DATE_TIME, LOCAL_TIME -> QueryType.BETWEEN;
                    case CHARACTER, STRING -> QueryType.LIKE;
                    default -> null;
                });
            }
            column.setListable(true);
            column.setEditable(!ColumnKeyType.PRI.equals(column.getColumnKey()));
            column.setInsertable(!ColumnKeyType.PRI.equals(column.getColumnKey()));
        }
        column.setHtmlType(switch (column.getJavaType()) {
            case BOOLEAN -> HtmlType.SWITCH;
            case INTEGER, LONG, BIG_DECIMAL, FLOAT, DOUBLE -> HtmlType.INPUT_NUMBER;
            case BYTE_ARRAY, CHARACTER, STRING -> HtmlType.INPUT;
            case LOCAL_DATE -> HtmlType.DATE_PICKER;
            case LOCAL_DATE_TIME -> HtmlType.DATETIME_PICKER;
            case LOCAL_TIME -> HtmlType.TIME_PICKER;
            case LIST, LIST_LONG, SET, DATA_SCOPE_ENUM, GENDER_TYPE, MENU_TYPE, MESSAGE_TYPE, SHOW_TYPE -> HtmlType.SELECT;
        });
    }

    private Boolean canQuery(GenColumn column) {
        return !(ColumnKeyType.PRI.equals(column.getColumnKey()) ||
            column.getJavaType() == JavaType.BYTE_ARRAY ||
            column.getJavaType() == JavaType.LIST ||
            column.getJavaType() == JavaType.LIST_LONG ||
            column.getJavaType() == JavaType.SET);
    }

    private void initVelocity() {
        Properties p = new Properties();
        try {
            // 加载classpath目录下的vm文件
            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, StandardCharsets.UTF_8.name());
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new BaseException("Velocity引擎初始化失败");
        }
    }

    private VelocityContext prepareContext(GenTable genTable) {
        TemplateType templateType = genTable.getTemplateType();
        VelocityContext context = new VelocityContext();
        context.put("tableName", genTable.getTableName());
        context.put("tableComment", genTable.getTableComment());
        context.put("className", genTable.getClassName());
        context.put("lowerName", StringUtil.toLowerCamelCase(genTable.getClassName()));
        context.put("classComment", genTable.getClassComment());
        context.put("templateType", genTable.getTemplateType());
        context.put("packageName", genTable.getPackageName());
        context.put("moduleName", genTable.getModuleName());
        context.put("author", genTable.getAuthor());
        context.put("genType", genTable.getGenType());
        context.put("genPath", genTable.getGenPath());
        context.put("parentMenuId", genTable.getParentMenuId());
        context.put("date", "2024-11-11");
        List<GenColumn> columns = genTable.getColumns();
        context.put("treeParentColumn", columns.stream().filter(item -> Objects.equals(genTable.getTreeParentColumn(), item.getId()))
                                               .findFirst().orElse(new GenColumn()).getColumnName());
        Set<String> imports = columns.parallelStream().map(GenColumn::getJavaType).map(JavaType::getPackageName).filter(StringUtils::isNotBlank)
                                     .collect(Collectors.toSet());
        context.put("columns", columns);
        context.put("imports", imports);
        if (TemplateType.TREE.equals(templateType)) {
            columnService.getById(genTable.getTreeParentColumn());
        }
        return context;
    }

    private List<String> getTemplateList(TemplateType templateType) {
        List<String> templates = new ArrayList<>();
        templates.add("vm/entity.java.vm");
        templates.add("vm/repository.java.vm");
        templates.add("vm/service.java.vm");
        templates.add("vm/controller.java.vm");
        templates.add("vm/sql.vm");
        templates.add("vm/api.ts.vm");
        if (TemplateType.LIST.equals(templateType)) {
            templates.add("vm/index.vue.vm");
        } else if (TemplateType.TREE.equals(templateType)) {
            templates.add("vm/index-tree.vue.vm");
        }
        return templates;
    }

    private String getGenPath(GenTable table, String template) {
        String genPath = table.getGenPath();
        if (StringUtils.equals(genPath, "/")) {
            return System.getProperty("user.dir") + File.separator + getFileName(template, table);
        }
        return genPath + File.separator + getFileName(template, table);
    }

    private String getFileName(String template, GenTable genTable) {
        // 文件名称
        String fileName = "";
        // 包路径
        String packageName = genTable.getPackageName();
        // 模块名
        String moduleName = genTable.getModuleName();
        // 大写类名
        String className = genTable.getClassName();
        // 业务名称
        String businessName = StringUtil.toLowerCamelCase(className);
        String javaPath = "/src/main/java/" + StringUtils.replace(packageName, ".", "/") + "/" + moduleName;
        String vuePath = "ui/src";
        if (template.contains("entity.java.vm")) {
            fileName = String.join(File.separator, javaPath, "entity", className + ".java");
        } else if (template.contains("repository.java.vm")) {
            fileName = String.join(File.separator, javaPath, "repository", className + "Repository.java");
        } else if (template.contains("service.java.vm")) {
            fileName = String.join(File.separator, javaPath, "service", className + "Service.java");
        } else if (template.contains("controller.java.vm")) {
            fileName = String.join(File.separator, javaPath, "controller", className + "Controller.java");
        } else if (template.contains("sql.vm")) {
            fileName = String.join(File.separator, "sql", className + ".sql");
        } else if (template.contains("api.ts.vm")) {
            fileName = String.join(File.separator, vuePath, "api", moduleName, businessName + "Api.ts");
        } else if (template.contains("vue.vm")) {
            fileName = String.join(File.separator, vuePath, "views", moduleName, className + ".vue");
        }
        return fileName;
    }

}
