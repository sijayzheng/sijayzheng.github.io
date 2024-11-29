/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `data_dict_data`
(
    `id`           bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `dict_type_id` bigint      NOT NULL COMMENT '字典类型',
    `label`        varchar(50) NOT NULL COMMENT '显示名称',
    `value`        varchar(50) NOT NULL COMMENT '值',
    `sort`         int      DEFAULT '0' COMMENT '排序',
    `create_by`    bigint   DEFAULT '1' COMMENT '创建者',
    `create_time`  datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`    bigint   DEFAULT NULL COMMENT '更新者',
    `update_time`  datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 75
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='数据字典项';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `data_dict_data`
VALUES (1, 1, '男', 'MALE', 1, NULL, NULL, NULL, NULL),
       (2, 1, '女', 'FEMALE', 2, NULL, NULL, NULL, NULL),
       (3, 1, '未知', 'UNKNOWN', 3, NULL, NULL, NULL, NULL),
       (4, 2, '项目中', 'PROJECT', 1, NULL, NULL, NULL, NULL),
       (5, 2, '压缩包', 'ZIP', 2, NULL, NULL, NULL, NULL),
       (6, 2, '自定义位置', 'CUSTOM', 3, NULL, NULL, NULL, NULL),
       (7, 3, '文本输入框', 'INPUT', 1, NULL, NULL, NULL, NULL),
       (8, 3, '数值输入框', 'NUMBER_INPUT', 2, NULL, NULL, NULL, NULL),
       (9, 3, '文本块', 'TEXTAREA', 3, NULL, NULL, NULL, NULL),
       (10, 3, '下拉框', 'SELECT', 4, NULL, NULL, NULL, NULL),
       (11, 3, '树形下拉框', 'TREE_SELECT', 5, NULL, NULL, NULL, NULL),
       (12, 3, '复选框', 'CHECKBOX', 6, NULL, NULL, NULL, NULL),
       (13, 3, '单选框', 'RADIO', 7, NULL, NULL, NULL, NULL),
       (14, 3, '日期时间选择器', 'DATETIME_PICK', 8, NULL, NULL, NULL, NULL),
       (15, 3, '日期选择器', 'DATE_PICK', 9, NULL, NULL, NULL, NULL),
       (16, 3, '时间选择器', 'TIME_PICK', 10, NULL, NULL, NULL, NULL),
       (17, 3, '开关', 'SWITCH', 11, NULL, NULL, NULL, NULL),
       (18, 4, '布尔型', 'Boolean', 1, NULL, NULL, NULL, NULL),
       (19, 4, '字节型', 'Byte', 2, NULL, NULL, NULL, NULL),
       (20, 4, '字符型', 'Character', 3, NULL, NULL, NULL, NULL),
       (21, 4, '浮点型', 'Double', 4, NULL, NULL, NULL, NULL),
       (22, 4, '整型', 'Integer', 5, NULL, NULL, NULL, NULL),
       (23, 4, '长整型', 'Long', 6, NULL, NULL, NULL, NULL),
       (24, 4, '对象', 'Object', 7, NULL, NULL, NULL, NULL),
       (25, 4, '字符串', 'String', 8, NULL, NULL, NULL, NULL),
       (26, 4, '大数值', 'BigDecimal', 9, NULL, NULL, NULL, NULL),
       (27, 4, '日期', 'LocalDate', 10, NULL, NULL, NULL, NULL),
       (28, 4, '日期时间', 'LocalDateTime', 11, NULL, NULL, NULL, NULL),
       (29, 4, '时间', 'LocalTime', 12, NULL, NULL, NULL, NULL),
       (30, 4, '列表', 'List', 13, NULL, NULL, NULL, NULL),
       (31, 4, '映射', 'Map', 14, NULL, NULL, NULL, NULL),
       (32, 4, '集', 'Set', 15, NULL, NULL, NULL, NULL),
       (33, 4, '菜单类型', 'MenuType', 16, NULL, NULL, NULL, NULL),
       (34, 4, '操作类型', 'OperateType', 17, NULL, NULL, NULL, NULL),
       (35, 4, '查询类型', 'QueryType', 18, NULL, NULL, NULL, NULL),
       (36, 4, '生成方式', 'GenType', 19, NULL, NULL, NULL, NULL),
       (37, 4, '输入方式', 'InputType', 20, NULL, NULL, NULL, NULL),
       (38, 4, 'Java数据类型', 'JavaType', 21, NULL, NULL, NULL, NULL),
       (39, 4, '前端类型', 'TemplateType', 22, NULL, NULL, NULL, NULL),
       (40, 4, '回显样式', 'DisplayType', 23, NULL, NULL, NULL, NULL),
       (41, 4, '性别', 'GenderType', 24, NULL, NULL, NULL, NULL),
       (42, 4, '公告类型', 'NoticeType', 25, NULL, NULL, NULL, NULL),
       (43, 5, '目录', 'DIRECTORY', 1, NULL, NULL, NULL, NULL),
       (44, 5, '菜单', 'MENU', 2, NULL, NULL, NULL, NULL),
       (45, 5, '按钮', 'BUTTON', 3, NULL, NULL, NULL, NULL),
       (46, 6, '公告', 'A', 1, NULL, NULL, NULL, NULL),
       (47, 6, '通知', 'B', 2, NULL, NULL, NULL, NULL),
       (48, 7, '其它', 'OTHER', 1, NULL, NULL, NULL, NULL),
       (49, 7, '新增', 'CREATE', 2, NULL, NULL, NULL, NULL),
       (50, 7, '批量新增', 'BATCH_CREATE', 3, NULL, NULL, NULL, NULL),
       (51, 7, '修改', 'MODIFY', 4, NULL, NULL, NULL, NULL),
       (52, 7, '删除', 'REMOVE', 5, NULL, NULL, NULL, NULL),
       (53, 7, '批量删除', 'BATCH_REMOVE', 6, NULL, NULL, NULL, NULL),
       (54, 7, '授权', 'AUTHORIZE', 7, NULL, NULL, NULL, NULL),
       (55, 7, '导出', 'EXPORT', 8, NULL, NULL, NULL, NULL),
       (56, 7, '导入', 'IMPORT', 9, NULL, NULL, NULL, NULL),
       (57, 7, '强退', 'FORCE_OUT', 10, NULL, NULL, NULL, NULL),
       (58, 7, '生成代码', 'GENERATE_CODE', 11, NULL, NULL, NULL, NULL),
       (59, 7, '清空数据', 'DROP', 12, NULL, NULL, NULL, NULL),
       (60, 7, '查询', 'SELECT', 13, NULL, NULL, NULL, NULL),
       (61, 7, '下载', 'DOWNLOAD', 14, NULL, NULL, NULL, NULL),
       (62, 7, '登录', 'LOGIN', 15, NULL, NULL, NULL, NULL),
       (63, 7, '登出', 'LOGOUT', 16, NULL, NULL, NULL, NULL),
       (64, 7, '新增或修改', 'SAVE', 17, NULL, NULL, NULL, NULL),
       (65, 8, '包含', 'LIKE', 1, NULL, NULL, NULL, NULL),
       (66, 8, '等于', 'EQUAL', 2, NULL, NULL, NULL, NULL),
       (67, 8, '大于', 'GREATER_THAN', 3, NULL, NULL, NULL, NULL),
       (68, 8, '大于等于', 'GREATER_OR_EQUAL', 4, NULL, NULL, NULL, NULL),
       (69, 8, '小于', 'LESS_THAN', 5, NULL, NULL, NULL, NULL),
       (70, 8, '小于等于', 'LESS_OR_EQUAL', 6, NULL, NULL, NULL, NULL),
       (71, 8, '在范围内', 'BETWEEN', 7, NULL, NULL, NULL, NULL),
       (72, 8, '在列表内', 'IN', 8, NULL, NULL, NULL, NULL),
       (73, 9, '列表', 'LIST', 1, NULL, NULL, NULL, NULL),
       (74, 9, '树表', 'TREE', 2, NULL, NULL, NULL, NULL);
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `data_dict_type`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(50) NOT NULL COMMENT '名称',
    `code`        varchar(50) NOT NULL COMMENT '编码',
    `sort`        int        DEFAULT '0' COMMENT '排序',
    `editable`    tinyint(1) DEFAULT '1' COMMENT '是否可编辑',
    `create_by`   bigint     DEFAULT '1' COMMENT '创建者',
    `create_time` datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint     DEFAULT NULL COMMENT '更新者',
    `update_time` datetime   DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code` (`code`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='数据字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `data_dict_type`
VALUES (1, '性别', 'GenderType', 1, 0, NULL, NULL, NULL, NULL),
       (2, '生成方式', 'GenType', 2, 0, NULL, NULL, NULL, NULL),
       (3, '输入方式', 'InputType', 3, 0, NULL, NULL, NULL, NULL),
       (4, 'Java类型', 'JavaType', 4, 0, NULL, NULL, NULL, NULL),
       (5, '菜单类型', 'MenuType', 5, 0, NULL, NULL, NULL, NULL),
       (6, '公告类型', 'NoticeType', 6, 0, NULL, NULL, NULL, NULL),
       (7, '操作类型', 'OperateType', 7, 0, NULL, NULL, NULL, NULL),
       (8, '查询类型', 'QueryType', 8, 0, NULL, NULL, NULL, NULL),
       (9, '模板类型', 'TemplateType', 9, 0, NULL, NULL, NULL, NULL);
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `data_division`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(50) NOT NULL COMMENT '名称',
    `code`        varchar(6)  NOT NULL COMMENT '编码',
    `parent_id`   bigint      NOT NULL COMMENT '父级id',
    `level`       int         NOT NULL COMMENT '级别',
    `create_by`   bigint   DEFAULT '1' COMMENT '创建者',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint   DEFAULT NULL COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code` (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='行政区划表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table`
(
    `id`           bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `table_name`   varchar(50) NOT NULL COMMENT '表名',
    `comment`      varchar(200) DEFAULT NULL COMMENT '表备注',
    `package_name` varchar(100) DEFAULT 'cn.sijay.suap' COMMENT '包路径',
    `module_name`  varchar(30)  DEFAULT '' COMMENT '模块名',
    `class_name`   varchar(100) DEFAULT '' COMMENT '实体类名',
    `template`     varchar(10)  DEFAULT 'list' COMMENT '模板类型',
    `author`       varchar(50)  DEFAULT 'sijay' COMMENT '作者',
    `gen_type`     varchar(10)  DEFAULT 'zip' COMMENT '生成方式',
    `gen_path`     varchar(10)  DEFAULT '/' COMMENT '生成路径',
    `menu_id`      bigint       DEFAULT NULL COMMENT '所属菜单',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='表信息';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `gen_table`
VALUES (1, 'data_dict_data', '数据字典项', 'cn.sijay.suap', 'data', 'DataDictData', 'LIST', 'sijay', 'ZIP', '/', 1),
       (2, 'data_dict_type', '数据字典类型表', 'cn.sijay.suap', 'data', 'DataDictType', 'LIST', 'sijay', 'ZIP', '/', 1),
       (3, 'data_division', '行政区划表', 'cn.sijay.suap', 'data', 'DataDivision', 'TREE', 'sijay', 'ZIP', '/', 1),
       (4, 'sys_config', '系统配置', 'cn.sijay.suap', 'sys', 'SysConfig', 'LIST', 'sijay', 'ZIP', '/', 1),
       (5, 'sys_dept', '部门表', 'cn.sijay.suap', 'sys', 'SysDept', 'TREE', 'sijay', 'ZIP', '/', 1),
       (6, 'sys_menu', '菜单表', 'cn.sijay.suap', 'sys', 'SysMenu', 'TREE', 'sijay', 'ZIP', '/', 1),
       (7, 'sys_module', '模块表', 'cn.sijay.suap', 'sys', 'SysModule', 'LIST', 'sijay', 'ZIP', '/', 1),
       (8, 'sys_notice', '通知公告', 'cn.sijay.suap', 'sys', 'SysNotice', 'LIST', 'sijay', 'ZIP', '/', 1),
       (9, 'sys_post', '岗位表', 'cn.sijay.suap', 'sys', 'SysPost', 'LIST', 'sijay', 'ZIP', '/', 1),
       (10, 'sys_role', '角色表', 'cn.sijay.suap', 'sys', 'SysRole', 'LIST', 'sijay', 'ZIP', '/', 1),
       (11, 'sys_user', '用户表', 'cn.sijay.suap', 'sys', 'SysUser', 'LIST', 'sijay', 'ZIP', '/', 1);
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table_column`
(
    `id`             bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `table_id`       bigint      NOT NULL COMMENT '表',
    `column_name`    varchar(50) NOT NULL COMMENT '列名',
    `column_comment` varchar(200) DEFAULT NULL COMMENT '列注释',
    `data_type`      varchar(50) NOT NULL COMMENT '列类型',
    `length`         int          DEFAULT NULL COMMENT '长度',
    `java_type`      varchar(100) DEFAULT '' COMMENT '实体类类型',
    `field_name`     varchar(100) DEFAULT '' COMMENT '实体类名称',
    `column_key`     varchar(5)   DEFAULT '' COMMENT '列类型',
    `nullable`       tinyint(1)   DEFAULT '1' COMMENT '是否可为空',
    `input_type`     varchar(20)  DEFAULT 'text_input' COMMENT '输入类型',
    `visible`        tinyint(1)   DEFAULT '1' COMMENT '是否显示',
    `addable`        tinyint(1)   DEFAULT '1' COMMENT '是否可添加',
    `editable`       tinyint(1)   DEFAULT '1' COMMENT '是否可编辑',
    `queryable`      tinyint(1)   DEFAULT '1' COMMENT '是否可查询',
    `query_type`     varchar(20)  DEFAULT 'like' COMMENT '查询方式',
    `excel_column`   tinyint(1)   DEFAULT '0' COMMENT '是否是导入导出字段',
    `sort`           int          DEFAULT NULL COMMENT '排序',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 126
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='列信息';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `gen_table_column`
VALUES (1, 1, 'id', '主键', 'bigint', NULL, 'Long', 'id', 'PRI', 0, 'NUMBER_INPUT', 1, 0, 0, 0, 'EQUAL', 0, 1),
       (2, 1, 'dict_type_id', '字典类型', 'bigint', NULL, 'Long', 'dictTypeId', '', 0, 'NUMBER_INPUT', 1, 1, 1, 1, 'EQUAL', 1, 2),
       (3, 1, 'label', '显示名称', 'varchar', 50, 'String', 'label', '', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 3),
       (4, 1, 'value', '值', 'varchar', 50, 'String', 'value', '', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 4),
       (5, 1, 'sort', '排序', 'int', NULL, 'Integer', 'sort', '', 1, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 5),
       (6, 1, 'create_by', '创建者', 'bigint', NULL, 'Long', 'createBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 6),
       (7, 1, 'create_time', '创建时间', 'datetime', NULL, 'LocalDateTime', 'createTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 7),
       (8, 1, 'update_by', '更新者', 'bigint', NULL, 'Long', 'updateBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 8),
       (9, 1, 'update_time', '更新时间', 'datetime', NULL, 'LocalDateTime', 'updateTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 9),
       (10, 2, 'id', '主键', 'bigint', NULL, 'Long', 'id', 'PRI', 0, 'NUMBER_INPUT', 1, 0, 0, 0, 'EQUAL', 0, 1),
       (11, 2, 'name', '名称', 'varchar', 50, 'String', 'name', '', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 2),
       (12, 2, 'code', '编码', 'varchar', 50, 'String', 'code', 'UNI', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 3),
       (13, 2, 'sort', '排序', 'int', NULL, 'Integer', 'sort', '', 1, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 4),
       (14, 2, 'editable', '是否可编辑', 'boolean', NULL, 'Boolean', 'editable', '', 1, 'SWITCH', 1, 1, 0, 0, 'EQUAL', 1, 5),
       (15, 2, 'create_by', '创建者', 'bigint', NULL, 'Long', 'createBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 6),
       (16, 2, 'create_time', '创建时间', 'datetime', NULL, 'LocalDateTime', 'createTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 7),
       (17, 2, 'update_by', '更新者', 'bigint', NULL, 'Long', 'updateBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 8),
       (18, 2, 'update_time', '更新时间', 'datetime', NULL, 'LocalDateTime', 'updateTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 9),
       (19, 3, 'id', '主键', 'bigint', NULL, 'Long', 'id', 'PRI', 0, 'NUMBER_INPUT', 1, 0, 0, 0, 'EQUAL', 0, 1),
       (20, 3, 'name', '名称', 'varchar', 50, 'String', 'name', '', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 2),
       (21, 3, 'code', '编码', 'varchar', 6, 'String', 'code', 'UNI', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 3),
       (22, 3, 'parent_id', '上级', 'bigint', NULL, 'Long', 'parentId', '', 0, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 4),
       (23, 3, 'level', '级别', 'int', NULL, 'Integer', 'level', '', 0, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 5),
       (24, 3, 'create_by', '创建者', 'bigint', NULL, 'Long', 'createBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 6),
       (25, 3, 'create_time', '创建时间', 'datetime', NULL, 'LocalDateTime', 'createTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 7),
       (26, 3, 'update_by', '更新者', 'bigint', NULL, 'Long', 'updateBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 8),
       (27, 3, 'update_time', '更新时间', 'datetime', NULL, 'LocalDateTime', 'updateTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 9),
       (28, 4, 'id', '主键', 'bigint', NULL, 'Long', 'id', 'PRI', 0, 'NUMBER_INPUT', 1, 0, 0, 0, 'EQUAL', 0, 1),
       (29, 4, 'config_name', '配置名称', 'varchar', 50, 'String', 'configName', '', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 2),
       (30, 4, 'config_code', '配置编码', 'varchar', 50, 'String', 'configCode', 'UNI', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 3),
       (31, 4, 'config_value', '配置值', 'varchar', 50, 'String', 'configValue', '', 0, 'INPUT', 1, 1, 1, 0, 'LIKE', 1, 4),
       (32, 4, 'internal', '内置配置', 'boolean', NULL, 'Boolean', 'internal', '', 1, 'SWITCH', 1, 1, 0, 0, 'EQUAL', 1, 5),
       (33, 4, 'sort', '排序', 'int', NULL, 'Integer', 'sort', '', 1, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 6),
       (34, 4, 'create_by', '创建者', 'bigint', NULL, 'Long', 'createBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 7),
       (35, 4, 'create_time', '创建时间', 'datetime', NULL, 'LocalDateTime', 'createTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 8),
       (36, 4, 'update_by', '更新者', 'bigint', NULL, 'Long', 'updateBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 9),
       (37, 4, 'update_time', '更新时间', 'datetime', NULL, 'LocalDateTime', 'updateTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 10),
       (38, 5, 'id', '主键', 'bigint', NULL, 'Long', 'id', 'PRI', 0, 'NUMBER_INPUT', 1, 0, 0, 0, 'EQUAL', 0, 1),
       (39, 5, 'parent_id', '上级', 'bigint', NULL, 'Long', 'parentId', '', 0, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 2),
       (40, 5, 'dept_name', '部门名称', 'varchar', 50, 'String', 'deptName', '', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 3),
       (41, 5, 'leader', '部门负责人', 'bigint', NULL, 'Long', 'leader', '', 1, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 4),
       (42, 5, 'phone', '部门电话', 'varchar', 11, 'String', 'phone', '', 1, 'INPUT', 1, 1, 1, 0, 'LIKE', 1, 5),
       (43, 5, 'sort', '排序', 'int', NULL, 'Integer', 'sort', '', 1, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 6),
       (44, 5, 'enabled', '是否启用', 'boolean', NULL, 'Boolean', 'enabled', '', 1, 'SWITCH', 1, 1, 1, 0, 'EQUAL', 1, 7),
       (45, 5, 'create_by', '创建者', 'bigint', NULL, 'Long', 'createBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 8),
       (46, 5, 'create_time', '创建时间', 'datetime', NULL, 'LocalDateTime', 'createTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 9),
       (47, 5, 'update_by', '更新者', 'bigint', NULL, 'Long', 'updateBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 10),
       (48, 5, 'update_time', '更新时间', 'datetime', NULL, 'LocalDateTime', 'updateTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 11),
       (49, 6, 'id', '主键', 'bigint', NULL, 'Long', 'id', 'PRI', 0, 'NUMBER_INPUT', 1, 0, 0, 0, 'EQUAL', 0, 1),
       (50, 6, 'parent_id', '上级', 'bigint', NULL, 'Long', 'parentId', '', 0, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 2),
       (51, 6, 'menu_name', '菜单名称', 'varchar', 50, 'String', 'menuName', '', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 3),
       (52, 6, 'menu_type', '菜单类型', 'varchar', 10, 'MenuType', 'menuType', '', 1, 'RADIO', 1, 1, 1, 0, 'LIKE', 1, 4),
       (53, 6, 'path', '路径', 'varchar', 200, 'String', 'path', '', 1, 'INPUT', 1, 1, 1, 0, 'LIKE', 1, 5),
       (54, 6, 'component', '组件路径', 'varchar', 255, 'String', 'component', '', 1, 'INPUT', 1, 1, 1, 0, 'LIKE', 1, 6),
       (55, 6, 'perms', '权限标识', 'varchar', 100, 'String', 'perms', '', 1, 'INPUT', 1, 1, 1, 0, 'LIKE', 1, 7),
       (56, 6, 'sort', '排序', 'int', NULL, 'Integer', 'sort', '', 1, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 8),
       (57, 6, 'link', '是否为外链', 'boolean', NULL, 'Boolean', 'link', '', 1, 'SWITCH', 1, 1, 1, 0, 'EQUAL', 1, 9),
       (59, 6, 'visible', '显示状态', 'boolean', NULL, 'Boolean', 'visible', '', 1, 'SWITCH', 1, 1, 1, 0, 'EQUAL', 1, 11),
       (60, 6, 'enabled', '是否启用', 'boolean', NULL, 'Boolean', 'enabled', '', 1, 'SWITCH', 1, 1, 1, 0, 'EQUAL', 1, 12),
       (61, 6, 'create_by', '创建者', 'bigint', NULL, 'Long', 'createBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 13),
       (62, 6, 'create_time', '创建时间', 'datetime', NULL, 'LocalDateTime', 'createTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 14),
       (63, 6, 'update_by', '更新者', 'bigint', NULL, 'Long', 'updateBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 15),
       (64, 6, 'update_time', '更新时间', 'datetime', NULL, 'LocalDateTime', 'updateTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 16),
       (65, 7, 'id', '主键', 'bigint', NULL, 'Long', 'id', 'PRI', 0, 'NUMBER_INPUT', 1, 0, 0, 0, 'EQUAL', 0, 1),
       (66, 7, 'module_name', '模块名称', 'varchar', 50, 'String', 'moduleName', '', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 2),
       (67, 7, 'menus', '菜单', 'json', NULL, 'List', 'menus', '', 1, 'SELECT', 1, 1, 1, 0, 'IN', 1, 3),
       (68, 7, 'sort', '排序', 'int', NULL, 'Integer', 'sort', '', 1, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 4),
       (69, 7, 'enabled', '是否启用', 'boolean', NULL, 'Boolean', 'enabled', '', 1, 'SWITCH', 1, 1, 1, 0, 'EQUAL', 1, 5),
       (70, 7, 'create_by', '创建者', 'bigint', NULL, 'Long', 'createBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 6),
       (71, 7, 'create_time', '创建时间', 'datetime', NULL, 'LocalDateTime', 'createTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 7),
       (72, 7, 'update_by', '更新者', 'bigint', NULL, 'Long', 'updateBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 8),
       (73, 7, 'update_time', '更新时间', 'datetime', NULL, 'LocalDateTime', 'updateTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 9),
       (74, 8, 'id', '主键', 'bigint', NULL, 'Long', 'id', 'PRI', 0, 'NUMBER_INPUT', 1, 0, 0, 0, 'EQUAL', 0, 1),
       (75, 8, 'title', '标题', 'varchar', 50, 'String', 'title', '', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 2),
       (76, 8, 'content', '内容', 'varchar', 500, 'String', 'content', '', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 3),
       (77, 8, 'type', '类型', 'varchar', 10, 'NoticeType', 'type', '', 0, 'SELECT', 1, 1, 1, 1, 'EQUAL', 1, 4),
       (78, 8, 'status', '状态', 'varchar', 10, 'String', 'status', '', 0, 'INPUT', 1, 1, 1, 0, 'LIKE', 1, 5),
       (79, 8, 'create_by', '创建者', 'bigint', NULL, 'Long', 'createBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 6),
       (80, 8, 'create_time', '创建时间', 'datetime', NULL, 'LocalDateTime', 'createTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 7),
       (81, 8, 'update_by', '更新者', 'bigint', NULL, 'Long', 'updateBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 8),
       (82, 8, 'update_time', '更新时间', 'datetime', NULL, 'LocalDateTime', 'updateTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 9),
       (83, 9, 'id', '主键', 'bigint', NULL, 'Long', 'id', 'PRI', 0, 'NUMBER_INPUT', 1, 0, 0, 0, 'EQUAL', 0, 1),
       (84, 9, 'post_name', '岗位名称', 'varchar', 50, 'String', 'postName', '', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 2),
       (85, 9, 'post_code', '岗位编码', 'varchar', 50, 'String', 'postCode', 'UNI', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 3),
       (86, 9, 'sort', '排序', 'int', NULL, 'Integer', 'sort', '', 1, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 4),
       (87, 9, 'enabled', '是否启用', 'boolean', NULL, 'Boolean', 'enabled', '', 1, 'SWITCH', 1, 1, 1, 0, 'EQUAL', 1, 5),
       (88, 9, 'create_by', '创建者', 'bigint', NULL, 'Long', 'createBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 6),
       (89, 9, 'create_time', '创建时间', 'datetime', NULL, 'LocalDateTime', 'createTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 7),
       (90, 9, 'update_by', '更新者', 'bigint', NULL, 'Long', 'updateBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 8),
       (91, 9, 'update_time', '更新时间', 'datetime', NULL, 'LocalDateTime', 'updateTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 9),
       (92, 10, 'id', '主键', 'bigint', NULL, 'Long', 'id', 'PRI', 0, 'NUMBER_INPUT', 1, 0, 0, 0, 'EQUAL', 0, 1),
       (93, 10, 'role_name', '角色名称', 'varchar', 50, 'String', 'roleName', '', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 2),
       (94, 10, 'role_code', '角色编码', 'varchar', 50, 'String', 'roleCode', 'UNI', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 3),
       (95, 10, 'enabled', '是否启用', 'boolean', NULL, 'Boolean', 'enabled', '', 1, 'SWITCH', 1, 1, 1, 0, 'EQUAL', 1, 4),
       (96, 10, 'sort', '排序', 'int', NULL, 'Integer', 'sort', '', 1, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 5),
       (97, 10, 'menus', '菜单', 'json', NULL, 'List', 'menus', '', 1, 'SELECT', 1, 1, 1, 0, 'IN', 1, 6),
       (98, 10, 'create_by', '创建者', 'bigint', NULL, 'Long', 'createBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 7),
       (99, 10, 'create_time', '创建时间', 'datetime', NULL, 'LocalDateTime', 'createTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 8),
       (100, 10, 'update_by', '更新者', 'bigint', NULL, 'Long', 'updateBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 9),
       (101, 10, 'update_time', '更新时间', 'datetime', NULL, 'LocalDateTime', 'updateTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 10),
       (102, 11, 'id', '主键', 'bigint', NULL, 'Long', 'id', 'PRI', 0, 'NUMBER_INPUT', 1, 0, 0, 0, 'EQUAL', 0, 1),
       (103, 11, 'username', '用户名', 'varchar', 50, 'String', 'username', 'UNI', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 2),
       (104, 11, 'phone', '手机号', 'varchar', 11, 'String', 'phone', 'UNI', 1, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 3),
       (105, 11, 'email', '邮箱', 'varchar', 100, 'String', 'email', 'UNI', 1, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 4),
       (106, 11, 'password', '密码', 'varchar', 128, 'String', 'password', '', 0, 'INPUT', 1, 1, 1, 0, 'LIKE', 1, 5),
       (107, 11, 'name', '姓名', 'varchar', 50, 'String', 'name', '', 0, 'INPUT', 1, 1, 1, 1, 'LIKE', 1, 6),
       (108, 11, 'gender', '性别', 'varchar', 10, 'GenderType', 'gender', '', 1, 'RADIO', 1, 1, 1, 0, 'LIKE', 1, 7),
       (109, 11, 'birthday', '生日', 'date', NULL, 'LocalDate', 'birthday', '', 1, 'DATE_PICK', 1, 1, 1, 0, 'BETWEEN', 1, 8),
       (110, 11, 'avatar', '头像', 'varchar', 200, 'String', 'avatar', '', 1, 'INPUT', 1, 1, 1, 0, 'LIKE', 1, 9),
       (111, 11, 'province', '省', 'bigint', NULL, 'Long', 'province', '', 1, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 10),
       (112, 11, 'city', '市', 'bigint', NULL, 'Long', 'city', '', 1, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 11),
       (113, 11, 'area', '区', 'bigint', NULL, 'Long', 'area', '', 1, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 12),
       (114, 11, 'address', '详细地址', 'varchar', 200, 'String', 'address', '', 1, 'INPUT', 1, 1, 1, 0, 'LIKE', 1, 13),
       (115, 11, 'dept_id', '部门', 'bigint', NULL, 'Long', 'deptId', '', 1, 'NUMBER_INPUT', 1, 1, 1, 1, 'EQUAL', 1, 14),
       (116, 11, 'remark', '备注', 'varchar', 200, 'String', 'remark', '', 1, 'INPUT', 1, 1, 1, 0, 'LIKE', 1, 15),
       (118, 11, 'enable', '是否启用', 'boolean', NULL, 'Boolean', 'enable', '', 1, 'SWITCH', 1, 1, 1, 0, 'EQUAL', 1, 17),
       (119, 11, 'sort', '排序', 'int', NULL, 'Integer', 'sort', '', 1, 'NUMBER_INPUT', 1, 1, 1, 0, 'EQUAL', 1, 18),
       (120, 11, 'posts', '岗位', 'json', NULL, 'List', 'posts', '', 1, 'SELECT', 1, 1, 1, 0, 'IN', 1, 19),
       (121, 11, 'roles', '角色', 'json', NULL, 'List', 'roles', '', 1, 'SELECT', 1, 1, 1, 0, 'IN', 1, 20),
       (122, 11, 'create_by', '创建者', 'bigint', NULL, 'Long', 'createBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 21),
       (123, 11, 'create_time', '创建时间', 'datetime', NULL, 'LocalDateTime', 'createTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 22),
       (124, 11, 'update_by', '更新者', 'bigint', NULL, 'Long', 'updateBy', '', 1, 'NUMBER_INPUT', 0, 0, 0, 0, 'EQUAL', 0, 23),
       (125, 11, 'update_time', '更新时间', 'datetime', NULL, 'LocalDateTime', 'updateTime', '', 1, 'DATETIME_PICK', 0, 0, 0, 0, 'BETWEEN', 0, 24);
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_config`
(
    `id`           bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `config_name`  varchar(50) NOT NULL COMMENT '配置名称',
    `config_code`  varchar(50) NOT NULL COMMENT '配置编码',
    `config_value` varchar(50) NOT NULL COMMENT '配置值',
    `internal`     tinyint(1) DEFAULT '0' COMMENT '内置配置',
    `sort`         int        DEFAULT '0' COMMENT '排序',
    `create_by`    bigint     DEFAULT '1' COMMENT '创建者',
    `create_time`  datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`    bigint     DEFAULT NULL COMMENT '更新者',
    `update_time`  datetime   DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code` (`config_code`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='系统配置';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `sys_config`
VALUES (1, '初始密码', 'init.password', '1234567', 1, 0, 1, '2024-06-09 14:22:23', 2, '2024-06-18 17:27:27');
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dept`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `parent_id`   bigint      NOT NULL DEFAULT '0' COMMENT '上级',
    `dept_name`   varchar(50) NOT NULL COMMENT '部门名称',
    `leader`      bigint               DEFAULT NULL COMMENT '部门负责人',
    `phone`       varchar(11)          DEFAULT NULL COMMENT '部门电话',
    `sort`        int                  DEFAULT '0' COMMENT '排序',
    `enabled`     tinyint(1)           DEFAULT '1' COMMENT '是否启用',
    `create_by`   bigint               DEFAULT '1' COMMENT '创建者',
    `create_time` datetime             DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint               DEFAULT NULL COMMENT '更新者',
    `update_time` datetime             DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `sys_dept`
VALUES (1, 0, '总公司', NULL, NULL, 0, 1, 1, '2024-06-17 16:03:17', NULL, NULL),
       (2, 1, '一部', 0, NULL, 0, 1, 1, '2024-06-17 16:03:17', NULL, NULL),
       (3, 1, '二部', NULL, NULL, 1, 1, 1, '2024-06-17 16:03:17', NULL, NULL),
       (4, 1, '三部', NULL, NULL, 2, 1, 1, '2024-06-17 16:03:17', NULL, NULL);
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `parent_id`   bigint      NOT NULL DEFAULT '0' COMMENT '上级',
    `menu_name`   varchar(50) NOT NULL COMMENT '菜单名称',
    `menu_type`   varchar(10)          DEFAULT 'D' COMMENT '菜单类型',
    `path`        varchar(200)         DEFAULT NULL COMMENT '路径',
    `component`   varchar(255)         DEFAULT NULL COMMENT '组件路径',
    `perms`       varchar(100)         DEFAULT NULL COMMENT '权限标识',
    `sort`        int                  DEFAULT '0' COMMENT '排序',
    `link`        tinyint(1)           DEFAULT '0' COMMENT '是否为外链',
    `visible`     tinyint(1)           DEFAULT '1' COMMENT '显示状态',
    `enabled`     tinyint(1)           DEFAULT '1' COMMENT '是否启用',
    `create_by`   bigint               DEFAULT '1' COMMENT '创建者',
    `create_time` datetime             DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint               DEFAULT NULL COMMENT '更新者',
    `update_time` datetime             DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 20207
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `sys_menu`
VALUES (1, 0, '系统管理', 'DIRECTORY', 'sys', NULL, NULL, 1, 0, 1, 1, 1, 1, '2024-06-25 23:05:06', NULL, NULL),
       (2, 0, '数据管理', 'DIRECTORY', 'data', NULL, NULL, 2, 0, 1, 1, 1, 1, '2024-06-25 23:05:06', NULL, NULL),
       (3, 0, '系统工具', 'DIRECTORY', 'tool', NULL, NULL, 3, 0, 1, 1, 1, 1, '2024-06-25 23:05:06', NULL, NULL),
       (101, 1, '系统配置', 'MENU', 'sysConfig', 'sys/sysConfig', 'sys:sysConfig:list', 1, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (102, 1, '菜单管理', 'MENU', 'sysMenu', 'sys/sysMenu', 'sys:sysMenu:list', 2, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (103, 1, '模块管理', 'MENU', 'sysModule', 'sys/sysModule', 'sys:sysModule:list', 3, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (104, 1, '部门管理', 'MENU', 'sysDept', 'sys/sysDept', 'sys:sysDept:list', 4, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (105, 1, '角色管理', 'MENU', 'sysRole', 'sys/sysRole', 'sys:sysRole:list', 5, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (106, 1, '岗位管理', 'MENU', 'sysPost', 'sys/sysPost', 'sys:sysPost:list', 6, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (107, 1, '用户管理', 'MENU', 'sysUser', 'sys/sysUser', 'sys:sysUser:list', 7, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (108, 1, '通知公告', 'MENU', 'sysNotice', 'sys/sysNotice', 'sys:sysNotice:list', 8, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (201, 2, '数据字典类型', 'MENU', 'dataDictType', 'data/dataDictType', 'data:dataDictType:list', 9, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL,
        NULL),
       (202, 2, '数据字典项', 'MENU', 'dataDictData', 'data/dataDictData', 'data:dataDictData:list', 10, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL,
        NULL),
       (301, 3, '代码生成', 'MENU', 'gen', 'gen/index', '', 11, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10101, 101, '系统配置查询', 'BUTTON', NULL, NULL, 'sys:sysConfig:list', 0, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10102, 101, '系统配置新增', 'BUTTON', NULL, NULL, 'sys:sysConfig:add', 1, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10103, 101, '系统配置修改', 'BUTTON', NULL, NULL, 'sys:sysConfig:edit', 2, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10104, 101, '系统配置删除', 'BUTTON', NULL, NULL, 'sys:sysConfig:remove', 3, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10105, 101, '系统配置导入', 'BUTTON', NULL, NULL, 'sys:sysConfig:import', 4, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10106, 101, '系统配置导出', 'BUTTON', NULL, NULL, 'sys:sysConfig:export', 5, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10201, 102, '菜单查询', 'BUTTON', NULL, NULL, 'sys:sysMenu:list', 0, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10202, 102, '菜单新增', 'BUTTON', NULL, NULL, 'sys:sysMenu:add', 1, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10203, 102, '菜单修改', 'BUTTON', NULL, NULL, 'sys:sysMenu:edit', 2, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10204, 102, '菜单删除', 'BUTTON', NULL, NULL, 'sys:sysMenu:remove', 3, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10205, 102, '菜单导入', 'BUTTON', NULL, NULL, 'sys:sysMenu:import', 4, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10206, 102, '菜单导出', 'BUTTON', NULL, NULL, 'sys:sysMenu:export', 5, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10301, 103, '模块查询', 'BUTTON', NULL, NULL, 'sys:sysModule:list', 0, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10302, 103, '模块新增', 'BUTTON', NULL, NULL, 'sys:sysModule:add', 1, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10303, 103, '模块修改', 'BUTTON', NULL, NULL, 'sys:sysModule:edit', 2, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10304, 103, '模块删除', 'BUTTON', NULL, NULL, 'sys:sysModule:remove', 3, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10305, 103, '模块导入', 'BUTTON', NULL, NULL, 'sys:sysModule:import', 4, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10306, 103, '模块导出', 'BUTTON', NULL, NULL, 'sys:sysModule:export', 5, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10401, 104, '部门查询', 'BUTTON', NULL, NULL, 'sys:sysDept:list', 0, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10402, 104, '部门新增', 'BUTTON', NULL, NULL, 'sys:sysDept:add', 1, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10403, 104, '部门修改', 'BUTTON', NULL, NULL, 'sys:sysDept:edit', 2, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10404, 104, '部门删除', 'BUTTON', NULL, NULL, 'sys:sysDept:remove', 3, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10405, 104, '部门导入', 'BUTTON', NULL, NULL, 'sys:sysDept:import', 4, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10406, 104, '部门导出', 'BUTTON', NULL, NULL, 'sys:sysDept:export', 5, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10501, 105, '角色查询', 'BUTTON', NULL, NULL, 'sys:sysRole:list', 0, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10502, 105, '角色新增', 'BUTTON', NULL, NULL, 'sys:sysRole:add', 1, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10503, 105, '角色修改', 'BUTTON', NULL, NULL, 'sys:sysRole:edit', 2, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10504, 105, '角色删除', 'BUTTON', NULL, NULL, 'sys:sysRole:remove', 3, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10505, 105, '角色导入', 'BUTTON', NULL, NULL, 'sys:sysRole:import', 4, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10506, 105, '角色导出', 'BUTTON', NULL, NULL, 'sys:sysRole:export', 5, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10601, 106, '岗位查询', 'BUTTON', NULL, NULL, 'sys:sysPost:list', 0, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10602, 106, '岗位新增', 'BUTTON', NULL, NULL, 'sys:sysPost:add', 1, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10603, 106, '岗位修改', 'BUTTON', NULL, NULL, 'sys:sysPost:edit', 2, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10604, 106, '岗位删除', 'BUTTON', NULL, NULL, 'sys:sysPost:remove', 3, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10605, 106, '岗位导入', 'BUTTON', NULL, NULL, 'sys:sysPost:import', 4, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10606, 106, '岗位导出', 'BUTTON', NULL, NULL, 'sys:sysPost:export', 5, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10701, 107, '用户查询', 'BUTTON', NULL, NULL, 'sys:sysUser:list', 0, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10702, 107, '用户新增', 'BUTTON', NULL, NULL, 'sys:sysUser:add', 1, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10703, 107, '用户修改', 'BUTTON', NULL, NULL, 'sys:sysUser:edit', 2, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10704, 107, '用户删除', 'BUTTON', NULL, NULL, 'sys:sysUser:remove', 3, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10705, 107, '用户导入', 'BUTTON', NULL, NULL, 'sys:sysUser:import', 4, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10706, 107, '用户导出', 'BUTTON', NULL, NULL, 'sys:sysUser:export', 5, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10801, 108, '通知公告查询', 'BUTTON', NULL, NULL, 'sys:sysNotice:list', 0, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10802, 108, '通知公告新增', 'BUTTON', NULL, NULL, 'sys:sysNotice:add', 1, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10803, 108, '通知公告修改', 'BUTTON', NULL, NULL, 'sys:sysNotice:edit', 2, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10804, 108, '通知公告删除', 'BUTTON', NULL, NULL, 'sys:sysNotice:remove', 3, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10805, 108, '通知公告导入', 'BUTTON', NULL, NULL, 'sys:sysNotice:import', 4, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (10806, 108, '通知公告导出', 'BUTTON', NULL, NULL, 'sys:sysNotice:export', 5, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (20101, 201, '数据字典类型查询', 'BUTTON', NULL, NULL, 'data:dataDictType:list', 0, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (20102, 201, '数据字典类型新增', 'BUTTON', NULL, NULL, 'data:dataDictType:add', 1, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (20103, 201, '数据字典类型修改', 'BUTTON', NULL, NULL, 'data:dataDictType:edit', 2, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (20104, 201, '数据字典类型删除', 'BUTTON', NULL, NULL, 'data:dataDictType:remove', 3, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (20105, 201, '数据字典类型导入', 'BUTTON', NULL, NULL, 'data:dataDictType:import', 4, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (20106, 201, '数据字典类型导出', 'BUTTON', NULL, NULL, 'data:dataDictType:export', 5, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (20201, 202, '数据字典项查询', 'BUTTON', NULL, NULL, 'data:dataDictData:list', 0, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (20202, 202, '数据字典项新增', 'BUTTON', NULL, NULL, 'data:dataDictData:add', 1, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (20203, 202, '数据字典项修改', 'BUTTON', NULL, NULL, 'data:dataDictData:edit', 2, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (20204, 202, '数据字典项删除', 'BUTTON', NULL, NULL, 'data:dataDictData:remove', 3, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (20205, 202, '数据字典项导入', 'BUTTON', NULL, NULL, 'data:dataDictData:import', 4, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL),
       (20206, 202, '数据字典项导出', 'BUTTON', NULL, NULL, 'data:dataDictData:export', 5, 0, 1, 1, 1, 1, '2024-06-25 16:00:12', NULL, NULL);
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_module`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `module_name` varchar(50) NOT NULL COMMENT '模块名称',
    `menus`       json       DEFAULT NULL COMMENT '菜单',
    `sort`        int        DEFAULT '0' COMMENT '排序',
    `enabled`     tinyint(1) DEFAULT '1' COMMENT '是否启用',
    `create_by`   bigint     DEFAULT '1' COMMENT '创建者',
    `create_time` datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint     DEFAULT NULL COMMENT '更新者',
    `update_time` datetime   DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='模块表';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `sys_module`
VALUES (1, '后台管理系统', '[
    1,
    2,
    3,
    101,
    102,
    103,
    104,
    105,
    106,
    107,
    108,
    201,
    202,
    301
]', 0, 1, 1, '2024-06-25 16:30:49', NULL, NULL),
       (2, '项目管理系统', '[]', 1, 1, 1, '2024-06-25 16:35:17', NULL, NULL),
       (3, '周报管理系统', '[]', 2, 1, 1, '2024-06-25 16:35:17', NULL, NULL);
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_notice`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title`       varchar(50)  NOT NULL COMMENT '标题',
    `content`     varchar(500) NOT NULL COMMENT '内容',
    `type`        varchar(10)  NOT NULL COMMENT '类型',
    `status`      varchar(10)  NOT NULL COMMENT '状态',
    `create_by`   bigint   DEFAULT '1' COMMENT '创建者',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint   DEFAULT NULL COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='通知公告';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `sys_notice`
VALUES (1, '更新通知', '系统于今日更新', 'A', '1', 1, '2024-06-17 16:03:17', NULL, NULL);
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_post`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `post_name`   varchar(50) NOT NULL COMMENT '岗位名称',
    `post_code`   varchar(50) NOT NULL COMMENT '岗位编码',
    `sort`        int        DEFAULT '0' COMMENT '排序',
    `enabled`     tinyint(1) DEFAULT '1' COMMENT '是否启用',
    `create_by`   bigint     DEFAULT '1' COMMENT '创建者',
    `create_time` datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint     DEFAULT NULL COMMENT '更新者',
    `update_time` datetime   DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code` (`post_code`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='岗位表';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `sys_post`
VALUES (1, '开发', 'dev', 0, 1, 1, '2024-06-17 16:03:17', NULL, NULL),
       (2, '测试', 'test', 1, 1, 1, '2024-07-02 17:14:53', NULL, NULL);
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_name`   varchar(50) NOT NULL COMMENT '角色名称',
    `role_code`   varchar(50) NOT NULL COMMENT '角色编码',
    `enabled`     tinyint(1) DEFAULT '1' COMMENT '是否启用',
    `sort`        int        DEFAULT '0' COMMENT '排序',
    `menus`       json       DEFAULT NULL COMMENT '菜单',
    `create_by`   bigint     DEFAULT '1' COMMENT '创建者',
    `create_time` datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint     DEFAULT NULL COMMENT '更新者',
    `update_time` datetime   DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code` (`role_code`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `sys_role`
VALUES (1, '超级管理员', 'su', 1, 0, '[
    1,
    2,
    3,
    4,
    5,
    6,
    7,
    8,
    9,
    10,
    11,
    12,
    13,
    14,
    15,
    16,
    17
]', 1, '2024-06-17 16:03:17', NULL, NULL);
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username`    varchar(50)  NOT NULL COMMENT '用户名',
    `phone`       varchar(11)  DEFAULT NULL COMMENT '手机号',
    `email`       varchar(100) DEFAULT NULL COMMENT '邮箱',
    `password`    varchar(128) NOT NULL COMMENT '密码',
    `name`        varchar(50)  NOT NULL COMMENT '姓名',
    `gender`      varchar(10)  DEFAULT 'UNKNOWN' COMMENT '性别',
    `birthday`    date         DEFAULT NULL COMMENT '生日',
    `avatar`      varchar(200) DEFAULT NULL COMMENT '头像',
    `province`    bigint       DEFAULT NULL COMMENT '省',
    `city`        bigint       DEFAULT NULL COMMENT '市',
    `area`        bigint       DEFAULT NULL COMMENT '区',
    `address`     varchar(200) DEFAULT NULL COMMENT '详细地址',
    `dept_id`     bigint       DEFAULT NULL COMMENT '部门',
    `remark`      varchar(200) DEFAULT NULL COMMENT '备注',
    `enable`      tinyint(1)   DEFAULT '1' COMMENT '是否启用',
    `sort`        int          DEFAULT '0' COMMENT '排序',
    `posts`       json         DEFAULT NULL COMMENT '岗位',
    `roles`       json         DEFAULT NULL COMMENT '角色',
    `create_by`   bigint       DEFAULT '1' COMMENT '创建者',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint       DEFAULT NULL COMMENT '更新者',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`),
    UNIQUE KEY `uk_phone` (`phone`),
    UNIQUE KEY `uk_email` (`email`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `sys_user`
VALUES (1, 'su', '15566668888', '15566668888@163.com', '$2a$10$JzqkUrKInLJu0d1hnpajpuT3Tof2WoEG63LshE5uBkAFKfGIprb5O', '张三', 'UNKNOWN',
        '2024-06-12', NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, '[1, 2, 5]', '[1, 5, 9, 10]', 1, '2024-06-17 16:03:28', NULL, NULL);
