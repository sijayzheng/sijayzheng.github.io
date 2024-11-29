drop table if exists gen_table;
create table gen_table
(
    id           bigint      not null auto_increment comment '主键',
    table_name   varchar(50) not null comment '表名',
    comment      varchar(200) default null comment '表描述',
    class_name   varchar(100) default '' comment '实体类名',
    template     varchar(10)  default 'list' comment '模板类型',
    package_name varchar(100) default 'cn.sijay.suap' comment '包路径',
    module_name  varchar(30)  default '' comment '模块名',
    author       varchar(50)  default 'sijay' comment '作者',
    menu_id      bigint       default null comment '所属菜单',
    primary key (id)
) comment ='表信息';

drop table if exists gen_table_column;
create table gen_table_column
(
    id             bigint      not null auto_increment comment '主键',
    table_id       bigint      not null comment '表',
    column_name    varchar(50) not null comment '列名',
    column_comment varchar(200) default null comment '列描述',
    data_type      varchar(50) not null comment '数据库类型',
    length         int          default null comment '长度',
    java_type      varchar(100) default '' comment 'Java类型',
    field_name     varchar(100) default '' comment 'Java属性',
    column_key     varchar(5)   default '' comment '列类型',
    addable        boolean      default true comment '可添加',
    editable       boolean      default true comment '可编辑',
    visible        boolean      default true comment '可显示',
    queryable      boolean      default true comment '可查询',
    query_type     varchar(20)  default 'like' comment '查询方式',
    nullable       boolean      default true comment '是否可为空',
    input_type     varchar(20)  default 'text_input' comment '输入类型',
    dict_type      varchar(100) default '' comment '字典类型',
    excel_column   boolean      default false comment '是否是导入导出字段',
    sort           int          default null comment '排序',
    primary key (id)
) comment ='列信息';

drop table if exists sys_config;
create table sys_config
(
    id          bigint      not null auto_increment comment '主键',
    name        varchar(50) not null comment '配置名称',
    code        varchar(50) not null comment '配置编码',
    value       varchar(50) not null comment '配置值',
    sort        int      default 0 comment '排序',
    create_by   bigint   default 1 comment '创建者',
    create_time datetime default current_timestamp comment '创建时间',
    update_by   bigint   default null comment '更新者',
    update_time datetime default null comment '更新时间',
    primary key (id),
    unique key uk_code (code)
) comment ='系统配置';
insert into sys_config (id, name, code, value, sort)
values (1, '初始密码', 'init.password', '123456', 0);

drop table if exists sys_dept;
create table sys_dept
(
    id          bigint      not null auto_increment comment '主键',
    parent_id   bigint      not null default 0 comment '上级',
    name        varchar(50) not null comment '部门名称',
    leader      bigint               default null comment '部门负责人',
    phone       varchar(11)          default null comment '部门电话',
    sort        int                  default 0 comment '排序',
    enabled     boolean              default true comment '是否启用',
    create_by   bigint               default 1 comment '创建者',
    create_time datetime             default current_timestamp comment '创建时间',
    update_by   bigint               default null comment '更新者',
    update_time datetime             default null comment '更新时间',
    primary key (id)
) comment ='部门表';
insert into sys_dept (id, parent_id, name)
values (1, 0, '总公司');

drop table if exists sys_menu;
create table sys_menu
(
    id          bigint       not null auto_increment comment '主键',
    parent_id   bigint       not null default 0 comment '上级',
    type        varchar(10)  not null default 'DIRECTORY' comment '菜单类型',
    name        varchar(50)  not null comment '图标',
    icon        varchar(100) null comment '菜单名称',
    sort        int                   default 0 comment '排序',
    path        varchar(200)          default '' comment '路由地址',
    component   varchar(255)          default '' comment '组件路径',
    perms       varchar(100)          default '' comment '权限标识',
    link        boolean               default false comment '是否为外链',
    visible     boolean               default true comment '显示状态',
    enabled     boolean               default true comment '是否启用',
    create_by   bigint                default 1 comment '创建者',
    create_time datetime              default current_timestamp comment '创建时间',
    update_by   bigint                default null comment '更新者',
    update_time datetime              default null comment '更新时间',
    primary key (id)
) comment ='菜单表';
insert into sys_menu (id, parent_id, name, type, path, component, perms, sort)
values (1, 0, '系统管理', 'DIRECTORY', 'sys', null, null, 1),
       (2, 0, '数据管理', 'DIRECTORY', 'data', null, null, 2),
       (3, 0, '系统工具', 'DIRECTORY', 'tool', null, null, 3),
       (101, 1, '系统配置', 'MENU', 'sysConfig', 'sys/sysConfig', 'sys:sysConfig:list', 1),
       (102, 1, '菜单管理', 'MENU', 'sysMenu', 'sys/sysMenu', 'sys:sysMenu:list', 2),
       (103, 1, '模块管理', 'MENU', 'sysModule', 'sys/sysModule', 'sys:sysModule:list', 3),
       (104, 1, '部门管理', 'MENU', 'sysDept', 'sys/sysDept', 'sys:sysDept:list', 4),
       (105, 1, '角色管理', 'MENU', 'sysRole', 'sys/sysRole', 'sys:sysRole:list', 5),
       (106, 1, '岗位管理', 'MENU', 'sysPost', 'sys/sysPost', 'sys:sysPost:list', 6),
       (107, 1, '用户管理', 'MENU', 'sysUser', 'sys/sysUser', 'sys:sysUser:list', 7),
       (108, 1, '通知公告', 'MENU', 'sysNotice', 'sys/sysNotice', 'sys:sysNotice:list', 8),
       (201, 2, '数据字典类型', 'MENU', 'dataDictType', 'data/dataDictType', 'data:dataDictType:list', 9),
       (202, 2, '数据字典项', 'MENU', 'dataDictData', 'data/dataDictData', 'data:dataDictData:list', 10),
       (301, 3, '代码生成', 'MENU', 'gen', 'gen/index', '', 11),
       (10101, 101, '系统配置查询', 'BUTTON', null, null, 'sys:sysConfig:list', 0),
       (10102, 101, '系统配置新增', 'BUTTON', null, null, 'sys:sysConfig:add', 1),
       (10103, 101, '系统配置修改', 'BUTTON', null, null, 'sys:sysConfig:edit', 2),
       (10104, 101, '系统配置删除', 'BUTTON', null, null, 'sys:sysConfig:remove', 3),
       (10105, 101, '系统配置导入', 'BUTTON', null, null, 'sys:sysConfig:import', 4),
       (10106, 101, '系统配置导出', 'BUTTON', null, null, 'sys:sysConfig:export', 5),
       (10201, 102, '菜单查询', 'BUTTON', null, null, 'sys:sysMenu:list', 0),
       (10202, 102, '菜单新增', 'BUTTON', null, null, 'sys:sysMenu:add', 1),
       (10203, 102, '菜单修改', 'BUTTON', null, null, 'sys:sysMenu:edit', 2),
       (10204, 102, '菜单删除', 'BUTTON', null, null, 'sys:sysMenu:remove', 3),
       (10205, 102, '菜单导入', 'BUTTON', null, null, 'sys:sysMenu:import', 4),
       (10206, 102, '菜单导出', 'BUTTON', null, null, 'sys:sysMenu:export', 5),
       (10301, 103, '模块查询', 'BUTTON', null, null, 'sys:sysModule:list', 0),
       (10302, 103, '模块新增', 'BUTTON', null, null, 'sys:sysModule:add', 1),
       (10303, 103, '模块修改', 'BUTTON', null, null, 'sys:sysModule:edit', 2),
       (10304, 103, '模块删除', 'BUTTON', null, null, 'sys:sysModule:remove', 3),
       (10305, 103, '模块导入', 'BUTTON', null, null, 'sys:sysModule:import', 4),
       (10306, 103, '模块导出', 'BUTTON', null, null, 'sys:sysModule:export', 5),
       (10401, 104, '部门查询', 'BUTTON', null, null, 'sys:sysDept:list', 0),
       (10402, 104, '部门新增', 'BUTTON', null, null, 'sys:sysDept:add', 1),
       (10403, 104, '部门修改', 'BUTTON', null, null, 'sys:sysDept:edit', 2),
       (10404, 104, '部门删除', 'BUTTON', null, null, 'sys:sysDept:remove', 3),
       (10405, 104, '部门导入', 'BUTTON', null, null, 'sys:sysDept:import', 4),
       (10406, 104, '部门导出', 'BUTTON', null, null, 'sys:sysDept:export', 5),
       (10501, 105, '角色查询', 'BUTTON', null, null, 'sys:sysRole:list', 0),
       (10502, 105, '角色新增', 'BUTTON', null, null, 'sys:sysRole:add', 1),
       (10503, 105, '角色修改', 'BUTTON', null, null, 'sys:sysRole:edit', 2),
       (10504, 105, '角色删除', 'BUTTON', null, null, 'sys:sysRole:remove', 3),
       (10505, 105, '角色导入', 'BUTTON', null, null, 'sys:sysRole:import', 4),
       (10506, 105, '角色导出', 'BUTTON', null, null, 'sys:sysRole:export', 5),
       (10601, 106, '岗位查询', 'BUTTON', null, null, 'sys:sysPost:list', 0),
       (10602, 106, '岗位新增', 'BUTTON', null, null, 'sys:sysPost:add', 1),
       (10603, 106, '岗位修改', 'BUTTON', null, null, 'sys:sysPost:edit', 2),
       (10604, 106, '岗位删除', 'BUTTON', null, null, 'sys:sysPost:remove', 3),
       (10605, 106, '岗位导入', 'BUTTON', null, null, 'sys:sysPost:import', 4),
       (10606, 106, '岗位导出', 'BUTTON', null, null, 'sys:sysPost:export', 5),
       (10701, 107, '用户查询', 'BUTTON', null, null, 'sys:sysUser:list', 0),
       (10702, 107, '用户新增', 'BUTTON', null, null, 'sys:sysUser:add', 1),
       (10703, 107, '用户修改', 'BUTTON', null, null, 'sys:sysUser:edit', 2),
       (10704, 107, '用户删除', 'BUTTON', null, null, 'sys:sysUser:remove', 3),
       (10705, 107, '用户导入', 'BUTTON', null, null, 'sys:sysUser:import', 4),
       (10706, 107, '用户导出', 'BUTTON', null, null, 'sys:sysUser:export', 5),
       (10801, 108, '通知公告查询', 'BUTTON', null, null, 'sys:sysNotice:list', 0),
       (10802, 108, '通知公告新增', 'BUTTON', null, null, 'sys:sysNotice:add', 1),
       (10803, 108, '通知公告修改', 'BUTTON', null, null, 'sys:sysNotice:edit', 2),
       (10804, 108, '通知公告删除', 'BUTTON', null, null, 'sys:sysNotice:remove', 3),
       (10805, 108, '通知公告导入', 'BUTTON', null, null, 'sys:sysNotice:import', 4),
       (10806, 108, '通知公告导出', 'BUTTON', null, null, 'sys:sysNotice:export', 5),
       (20101, 201, '数据字典类型查询', 'BUTTON', null, null, 'data:dataDictType:list', 0),
       (20102, 201, '数据字典类型新增', 'BUTTON', null, null, 'data:dataDictType:add', 1),
       (20103, 201, '数据字典类型修改', 'BUTTON', null, null, 'data:dataDictType:edit', 2),
       (20104, 201, '数据字典类型删除', 'BUTTON', null, null, 'data:dataDictType:remove', 3),
       (20105, 201, '数据字典类型导入', 'BUTTON', null, null, 'data:dataDictType:import', 4),
       (20106, 201, '数据字典类型导出', 'BUTTON', null, null, 'data:dataDictType:export', 5),
       (20201, 202, '数据字典项查询', 'BUTTON', null, null, 'data:dataDictData:list', 0),
       (20202, 202, '数据字典项新增', 'BUTTON', null, null, 'data:dataDictData:add', 1),
       (20203, 202, '数据字典项修改', 'BUTTON', null, null, 'data:dataDictData:edit', 2),
       (20204, 202, '数据字典项删除', 'BUTTON', null, null, 'data:dataDictData:remove', 3),
       (20205, 202, '数据字典项导入', 'BUTTON', null, null, 'data:dataDictData:import', 4),
       (20206, 202, '数据字典项导出', 'BUTTON', null, null, 'data:dataDictData:export', 5);

drop table if exists sys_module;
create table sys_module
(
    id          bigint      not null auto_increment comment '主键',
    name        varchar(50) not null comment '模块名称',
    menus       json     default null comment '菜单',
    sort        int      default 0 comment '排序',
    enabled     boolean  default true comment '是否启用',
    create_by   bigint   default 1 comment '创建者',
    create_time datetime default current_timestamp comment '创建时间',
    update_by   bigint   default null comment '更新者',
    update_time datetime default null comment '更新时间',
    primary key (id)
) comment ='模块表';

drop table if exists sys_notice;
create table sys_notice
(
    id          bigint      not null auto_increment comment '主键',
    title       varchar(50) not null comment '标题',
    type        varchar(10) default '0' comment '类型',
    status      varchar(10) default '0' comment '状态',
    content     text comment '内容',
    create_by   bigint      default 1 comment '创建者',
    create_time datetime    default current_timestamp comment '创建时间',
    update_by   bigint      default null comment '更新者',
    update_time datetime    default null comment '更新时间',
    primary key (id)
) comment ='通知公告';

drop table if exists sys_role;
create table sys_role
(
    id          bigint      not null auto_increment comment '主键',
    name        varchar(50) not null comment '角色名称',
    code        varchar(50) not null comment '角色编码',
    sort        int      default 0 comment '排序',
    enabled     boolean  default true comment '是否启用',
    menus       json     default null comment '菜单',
    create_by   bigint   default 1 comment '创建者',
    create_time datetime default current_timestamp comment '创建时间',
    update_by   bigint   default null comment '更新者',
    update_time datetime default null comment '更新时间',
    primary key (id),
    unique key uk_code (code)
) comment ='角色表';
insert into sys_role (id, name, code, enabled, sort, menus)
values (1, '超级管理员', 'su', 1, 0, '[]');

drop table if exists sys_user;
create table sys_user
(
    id          bigint       not null auto_increment comment '主键',
    username    varchar(50)  not null comment '用户名',
    name        varchar(50)  not null comment '姓名',
    phone       varchar(11) default null comment '手机号',
    dept_id     bigint      default null comment '部门',
    password    varchar(128) not null comment '密码',
    enable      boolean     default true comment '是否启用',
    sort        int         default 9 comment '排序',
    posts       json        default null comment '岗位',
    roles       json        default null comment '角色',
    create_by   bigint      default 1 comment '创建者',
    create_time datetime    default current_timestamp comment '创建时间',
    update_by   bigint      default null comment '更新者',
    update_time datetime    default null comment '更新时间',
    primary key (id),
    unique key uk_username (username),
    unique key uk_phone (phone)
) comment ='用户表';
insert into sys_user (id, username, phone, password, name, dept_id, enable, sort, posts, roles)
values (1, 'su', '15566668888', '123456', '张三', 1, 1, 999, '[]', '[]');

drop table if exists data_dict_type;
create table data_dict_type
(
    id          bigint       not null auto_increment comment '主键',
    name        varchar(100) not null comment '名称',
    code        varchar(100) not null comment '编码',
    sort        int      default 0 comment '排序',
    create_by   bigint   default 1 comment '创建者',
    create_time datetime default current_timestamp comment '创建时间',
    update_by   bigint   default null comment '更新者',
    update_time datetime default null comment '更新时间',
    primary key (id),
    unique key uk_code (code)
) comment '数据字典类型表';

drop table if exists data_dict_data;
create table data_dict_data
(
    id             bigint       not null auto_increment comment '主键',
    dict_type_code varchar(100) not null comment '字典类型编码',
    label          varchar(100) not null comment '显示名称',
    value          varchar(50)  not null comment '值',
    sort           int      default 0 comment '排序',
    create_by      bigint   default 1 comment '创建者',
    create_time    datetime default current_timestamp comment '创建时间',
    update_by      bigint   default null comment '更新者',
    update_time    datetime default null comment '更新时间',
    primary key (id)
) comment '数据字典项';


-- drop table if exists data_division;
-- create table data_division
-- (
--     id          bigint      not null auto_increment comment '主键',
--     name        varchar(50) not null comment '名称',
--     code        int         not null comment '编码',
--     parent_id   bigint      not null comment '父级id',
--     level       int         not null comment '级别',
--     create_by   bigint   default 1 comment '创建者',
--     create_time datetime default current_timestamp comment '创建时间',
--     update_by   bigint   default null comment '更新者',
--     update_time datetime default null comment '更新时间',
--     primary key (id),
--     unique key uk_code (code)
-- ) comment '行政区划表';
