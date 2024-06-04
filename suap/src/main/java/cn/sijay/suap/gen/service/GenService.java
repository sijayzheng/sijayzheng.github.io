//package cn.sijay.suap.gen.service;
//
//import cn.sijay.suap.core.entity.Ids;
//import cn.sijay.suap.core.entity.Option;
//import cn.sijay.suap.core.entity.PageQuery;
//import cn.sijay.suap.core.entity.Pair;
//import cn.sijay.suap.core.enums.*;
//import cn.sijay.suap.core.exception.BaseException;
//import cn.sijay.suap.core.properties.ConfigProperties;
//import cn.sijay.suap.core.properties.GenProperties;
//import cn.sijay.suap.core.util.*;
//import cn.sijay.suap.data.service.IDataDictService;
//import cn.sijay.suap.gen.entity.GenTable;
//import cn.sijay.suap.gen.entity.GenTableColumn;
//
//import cn.sijay.suap.gen.repository.GenTableColumnRepository;
//import cn.sijay.suap.gen.repository.GenTableRepository;
//import cn.sijay.suap.schema.info.entity.SchemaTable;
//import cn.sijay.suap.schema.info.entity.TableId;
//import cn.sijay.suap.schema.info.repository.SchemaColumnRepository;
//import cn.sijay.suap.schema.info.repository.SchemaTableRepository;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.ObjectUtils;
//import org.apache.velocity.VelocityContext;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.*;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.nio.charset.StandardCharsets;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * <p>
// * <em>GenService</em>
// * </p>
// *
// * @author Sijay
// * @since 2024/1/11 17:35
// */
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class GenService {
//    private final GenProperties genProperties;
//    private final ConfigProperties configProperties;
//    private final GenTableColumnRepository genTableColumnRepository;
//    private final GenTableRepository genTableRepository;
//    private final SchemaTableRepository schemaTableRepository;
//    private final SchemaColumnRepository schemaColumnRepository;
//
//    public HashMap<String, String> preview(long id) {
//        GenTable genTable = genTableService.getById(id);
//        setExtAttr(genTable);
//        VelocityUtil.initVelocity();
//        VelocityContext context = VelocityUtil.prepareContext(genTable);
//        HashMap<String, String> result = new HashMap<>();
//        genFileTypeMap().forEach((template, genFileType) -> result.put(template.replace(".vm", ""), VelocityUtil.render(template, context)));
//        genTable.setCreated(YesOrNo.Y);
//        genTableService.update(genTable);
//        return result;
//    }
//
//    public void genToDir(GenTable genTable) {
//        setExtAttr(genTable);
//        VelocityUtil.initVelocity();
//        VelocityContext context = VelocityUtil.prepareContext(genTable);
//        genFileTypeMap().forEach((template, genFileType) -> {
//            String path = VelocityUtil.getGenPath(genTable, template.split("\\.")[0], genFileType, genProperties.getPath());
//            FileUtil.mkDirOrTouch(path);
//            try (FileOutputStream fos = new FileOutputStream(path)) {
//                fos.write(VelocityUtil.render(template, context).getBytes(StandardCharsets.UTF_8));
//            } catch (FileNotFoundException e) {
//                throw new BaseException(ExceptionEnum.FILE_NOT_FOUND, path);
//            } catch (IOException e) {
//                throw new BaseException("渲染模板失败，表名：" + context.get("tableName"));
//            }
//        });
//        genTable.setCreated(YesOrNo.Y);
//        genTableService.update(genTable);
//    }
//
//    public List<Option<Long>> getTableData(String tableName) {
//        List<Option<Long>> options;
//        String className = "cn.sijay.suap." + tableName.split("_")[0] + ".service.impl." + StringUtil.toUpperCamelCase(tableName) + "ServiceImpl";
//        try {
//            Class<?> clazz = Class.forName(className);
//            System.out.println(clazz);
//            options = (List<Option<Long>>) clazz.getDeclaredMethod("getOptionData").invoke(SpringUtil.getBean(clazz));
//        } catch (ClassNotFoundException e) {
//            throw new BaseException(ExceptionEnum.REFLECT_CLASS_NOT_FOUND_ERROR, className);
//        } catch (NoSuchMethodException e) {
//            throw new BaseException(ExceptionEnum.REFLECT_METHOD_NOT_FOUND_ERROR, className, "getOptionData");
//        } catch (InvocationTargetException | IllegalAccessException e) {
//            throw new BaseException(ExceptionEnum.REFLECT_METHOD_INVOKE_ERROR, className, "getOptionData");
//        } catch (Exception e) {
//            throw new BaseException("获取选项数据失败");
//        }
//        return options;
//    }
//
//    @Transactional
//    public Pair<String, Long> save(GenTable genTable) {
//        boolean a = genTableService.save(genTable);
//        List<GenTableColumn> fields = genTable.getFields();
//        Long genTableId = genTable.getId();
//        fields = fields.stream().peek(item -> item.setTableId(genTableId)).toList();
//        boolean b = genTableColumnService.saveBatch(fields);
//        return new Pair<>(a && b ? "表信息新增成功" : "表信息新增失败", genTableId);
//    }
//
//    public void download(Long id, HttpServletResponse response) {
//        GenTable genTable = genTableService.getById(id);
//        genToDir(genTable);
//        File file = FileUtil.zipFolder(genProperties.getPath(), configProperties.getTempFolder(), genTable.getComment());
//        try (FileInputStream fileInputStream = new FileInputStream(file)) {
//            ResponseUtil.setDownloadFileName(response, genTable.getComment() + ".zip");
//            response.getOutputStream().write(fileInputStream.readAllBytes());
//        } catch (FileNotFoundException e) {
//            throw new BaseException(ExceptionEnum.FILE_NOT_FOUND, file.getName());
//        } catch (IOException e) {
//            throw new BaseException(ExceptionEnum.IO_ERROR, file.getName());
//        }
//        new File(genProperties.getPath()).deleteOnExit();
//    }
//
//    @Transactional
//    public Pair<String, Long> update(GenTable genTable) {
//        boolean a = genTableService.update(genTable);
//        List<GenTableColumn> fields = genTable.getFields();
//        Long genTableId = genTable.getId();
//        boolean d = genTableColumnService.removeBatchByTableId(genTableId);
//        fields = fields.stream().peek(item -> item.setTableId(genTableId)).toList();
//        boolean b = genTableColumnService.saveBatch(fields);
//        return new Pair<>(a && d && b ? "表信息更新成功" : "表信息更新失败", genTableId);
//    }
//
//    public List<GenTableColumn> listColumnByTableId(Long tableId) {
//        GenTable genTable = genTableService.getById(tableId);
//        String superClass = genTable.getSuperClass();
//        return initFields(genTableColumnService.listColumnByTableId(tableId), getSuperClassFields(superClass));
//    }
//
//    public List<GenTableColumn> listTableColumns(String tableName, String superClass) {
//        return initFields(genTableColumnService.listTableColumns(tableName), getSuperClassFields(superClass));
//    }
//
//    private List<GenTableColumn> initFields(List<GenTableColumn> columns, List<String> superClassFields) {
//        List<GenTableColumn> list = columns.stream().peek(item -> {
//            item.setFieldName(StringUtil.toLowerCamelCase(item.getName()));
//            item.setJavaType(switch (item.getType()) {
//                case "bigint" -> JavaType.Long;
//                case "datetime" -> JavaType.DATE_TIME;
//                case "date" -> JavaType.DATE;
//                case "time" -> JavaType.TIME;
//                case "int" -> JavaType.Integer;
//                case "decimal" -> JavaType.BIG_DECIMAL;
//                case "double" -> JavaType.DOUBLE;
//                default -> JavaType.STRING;
//            });
//            if ("bigint".equals(item.getType()) || "int".equals(item.getType())) {
//                item.setLength(null);
//                item.setScale(null);
//            }
//            item.setRequired(YesOrNo.isYes(item.getNullable()) ? YesOrNo.N : YesOrNo.Y);
//        }).collect(Collectors.toList());
//        if (CollectionUtils.isNotEmpty(superClassFields)) {
//            list = list.stream().peek(item -> {
//                item.setQueryable(YesOrNo.N);
//                if (superClassFields.contains(item.getFieldName())) {
//                    item.setSuperColumn(YesOrNo.Y);
//                    item.setEditable(YesOrNo.N);
//                    item.setVisible(YesOrNo.N);
//                    item.setDisable(true);
//                } else if ("deleted".equals(item.getFieldName())) {
//                    item.setSuperColumn(YesOrNo.N);
//                    item.setEditable(YesOrNo.N);
//                    item.setVisible(YesOrNo.N);
//                    item.setDisable(true);
//                } else {
//                    item.setSuperColumn(YesOrNo.N);
//                    item.setEditable(YesOrNo.Y);
//                    item.setVisible(YesOrNo.Y);
//                    item.setDisable(false);
//                    item.setInputType(getInputType(item.getJavaType()));
//                    item.setExcelColumn(YesOrNo.Y);
//                }
//            }).toList();
//        } else {
//            list = list.stream().peek(item -> {
//                item.setInputType(getInputType(item.getJavaType()));
//            }).toList();
//        }
//        return list;
//    }
//
//    private InputType getInputType(JavaType javaType) {
//        if (javaType == JavaType.Long || javaType == JavaType.Integer || javaType == JavaType.Byte || javaType == JavaType.BIG_DECIMAL || javaType == JavaType.DOUBLE) {
//            return InputType.NUMBER_INPUT;
//        } else if (javaType == JavaType.DATA_SOURCE || javaType == JavaType.GEN_TYPE || javaType == JavaType.JAVA_TYPE || javaType == JavaType.OPERATE_TYPE || javaType == JavaType.QUERY_TYPE || javaType == JavaType.INPUT_TYPE || javaType == JavaType.SUPER_CLASS_ENUM) {
//            return InputType.SELECTOR;
//        } else if (javaType == JavaType.GENDER_TYPE || javaType == JavaType.TEMPLATE_TYPE) {
//            return InputType.RADIO;
//        } else if (javaType == JavaType.DATE_TIME) {
//            return InputType.DATETIME_PICK;
//        } else if (javaType == JavaType.DATE) {
//            return InputType.DATE_PICK;
//        } else if (javaType == JavaType.TIME) {
//            return InputType.TIME_PICK;
//        } else if (javaType == JavaType.YES_OR_NO) {
//            return InputType.SWITCH;
//        } else {
//            return InputType.TEXT_INPUT;
//        }
//    }
//
//    private void setOptions(GenTableColumn item) {
//        Map<String, String> optionsMap = new HashMap<>();
//        if (DataSource.DICT == item.getDataSource()) {
//            //字典
//            optionsMap.put(item.getFieldName() + "Options", "commonApi.listDictDataOptions('" + item.getData() + "')");// JsonUtil.toJsonString(dataDictService.getOptions(item.getData()));
//        } else if (DataSource.ENUM == item.getDataSource()) {
//            //枚举
//            optionsMap.put(item.getFieldName() + "Options", "commonApi.listEnumDataOptions('" + item.getData() + "')");// JsonUtil.toJsonString(ReflectUtil.enumToOption(item.getData())));
//        } else if (DataSource.SELF == item.getDataSource() || DataSource.TABLE == item.getDataSource()) {
//            //表数据
//            optionsMap.put(item.getFieldName() + "Options", "commonApi.listTableDataOptions('" + item.getData() + "')");// JsonUtil.toJsonString(getTableData(item.getData())));
//        }
//        item.setOptionsMap(optionsMap);
//    }
//
//    private List<String> getSuperClassFields(String superClass) {
//        if (StringUtil.isNotBlank(superClass)) {
//            try {
//                return Arrays.stream(Class.forName(superClass).getDeclaredFields())
//                             .filter(field -> field.getAnnotation(TableField.class) != null || field.getAnnotation(TableId.class) != null)
//                             .map(Field::getName).collect(Collectors.toList());
//            } catch (ClassNotFoundException e) {
//                throw new BaseException(ExceptionEnum.REFLECT_CLASS_NOT_FOUND_ERROR, superClass);
//            }
//        }
//        return null;
//    }
//
//    private Map<String, GenFileType> genFileTypeMap() {
//        Map<String, GenFileType> map = new HashMap<>();
//        map.put("entity.java.vm", GenFileType.JAVA);
//        map.put("controller.java.vm", GenFileType.JAVA);
//        map.put("service.java.vm", GenFileType.JAVA);
//        map.put("service_impl.java.vm", GenFileType.JAVA);
//        map.put("repository.java.vm", GenFileType.JAVA);
//        map.put("mapper.xml.vm", GenFileType.XML);
//        map.put("vue.vm", GenFileType.VUE);
//        map.put("js.vm", GenFileType.JS);
//        return map;
//    }
//
//    private void setExtAttr(GenTable genTable) {
//        genTable.setLowerName(StringUtil.toLowerCamelCase(genTable.getTableName()));
//        List<GenTableColumn> fields = genTableColumnService.listColumnByTableId(genTable.getId()).stream().peek(item -> {
//            item.setGetMethodName("get" + StringUtil.toUpperCamelCase(item.getName()));
//            if (item.getBoolQueryable() || item.getBoolEditable()) {
//                setOptions(item);
//            }
//        }).toList();
//        Set<String> imports = new HashSet<>(fields.stream().map(field -> field.getJavaType().getPkg()).filter(StringUtil::isNotBlank).toList());
//        genTable.setImports(imports);
//        genTable.setFields(fields);
//    }
//
//    public void generate(Long id) {
//        if (Objects.isNull(id)) {
//            for (GenTable genTable : genTableService.list()) {
//                genToDir(genTable);
//            }
//        } else {
//            genToDir(genTableService.getById(id));
//        }
//    }
//
//
//
//    /**
//     * 分页查询
//     *
//     * @param entity    实体类
//     * @param pageQuery 分页查询条件
//     * @return 分页查询结果
//     */
//    public Page<GenTable> page(GenTable entity, PageQuery pageQuery) {
//        return page(pageQuery.build(), buildQueryWrapper(entity));
//    }
//
//    /**
//     * 删除
//     *
//     * @param ids id
//     * @return 删除结果，true为成功，false为失败
//     */
//    @Transactional(rollbackFor = {Exception.class})
//    public boolean remove(Ids<Long> ids) {
//        if (ObjectUtils.isNotEmpty(ids.getId())) {
//            if (exists(ids.getId())) {
//                return genTableColumnService.removeBatchByTableId(ids.getId()) && removeById(ids.getId());
//            } else {
//                throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, ids.getId());
//            }
//        } else if (CollectionUtils.isNotEmpty(ids.getIds())) {
//            List<Long> list = ids.getIds().stream().filter(id -> !exists(id)).collect(Collectors.toList());
//            if (CollectionUtils.isEmpty(list)) {
//                var b = true;
//                for (Long id : list) {
//                    b = b && genTableColumnService.removeBatchByTableId(id);
//                }
//                return b && removeByIds(ids.getIds());
//            } else {
//                throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, list);
//            }
//        } else {
//            throw new BaseException(ExceptionEnum.REQUEST_PARAM_ERROR);
//        }
//    }
//
//    public List<GenTable> listAllTable() {
//        List<String> list = list().stream().map(GenTable::getTableName).toList();
//        return baseMapper.listAllTable().stream().filter(item -> !list.contains(item.getTableName()))
//                         .peek(item -> {
//                             item.setClassName(StringUtil.toUpperCamelCase(item.getTableName()));
//                             item.setModule(item.getTableName().split("_")[0]);
//                             item.setLowerName(StringUtil.toLowerCamelCase(item.getClassName()));
//                         }).collect(Collectors.toList());
//    }
//
//    /**
//     * 更新
//     *
//     * @param entity 实体类
//     * @return 更新结果，true为成功，false为失败
//     */
//    @Transactional(rollbackFor = {Exception.class})
//    public boolean update(GenTable entity) {
//        if (exists(entity.getId())) {
//            GenTable genTable = getById(entity.getId());
//            BeanUtil.copyEntityProperties(entity, genTable);
//            return updateById(genTable);
//        }
//        return false;
//    }
//
//    boolean exists(Long id) {
//        return exists(new LambdaQueryWrapper<GenTable>().eq(GenTable::getId, id));
//    }
//
//    LambdaQueryWrapper<GenTable> buildQueryWrapper(GenTable entity) {
//        LambdaQueryWrapper<GenTable> wrapper = new LambdaQueryWrapper<>();
//        wrapper.like(StringUtil.isNotBlank(entity.getTableName()), GenTable::getTableName, entity.getTableName());
//        wrapper.like(StringUtil.isNotBlank(entity.getComment()), GenTable::getComment, entity.getComment());
//        return wrapper;
//    }
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void removeBatchByTableId(Long genTableId) {
//        List<GenTableColumn> list = listColumnByTableId(genTableId);
//        repository.deleteAll(list);
//    }
//
//    @Override
//    public List<GenTableColumn> listTableColumns(String tableName) {
//        return baseMapper.listTableColumns(tableName);
//    }
//
//    @Override
//    public List<GenTableColumn> listColumnByTableId(Long tableId) {
//        return list(new LambdaQueryWrapper<GenTableColumn>().eq(GenTableColumn::getTableId, tableId).orderByAsc(GenTableColumn::getSort));
//    }
//}
