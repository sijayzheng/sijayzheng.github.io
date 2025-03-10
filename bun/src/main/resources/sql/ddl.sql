drop table if exists gen_table;
create table gen_table
(
    id                 bigint       not null auto_increment primary key comment '编号id',
    table_name         varchar(200) not null default '' comment '表名称',
    table_comment      varchar(500) not null default '' comment '表描述',
    class_name         varchar(100) not null default '' comment '类名称',
    class_comment      varchar(50)  not null default '' comment '类描述',
    template_type      varchar(4)            default 'LIST' comment '模板类型',
    package_name       varchar(100)          default '' comment '生成包路径',
    module_name        varchar(30)           default '' comment '生成模块名',
    author             varchar(50)           default '' comment '作者',
    gen_type           varchar(10)           default 'ZIP' comment '生成类型',
    gen_path           varchar(200)          default '/' comment '生成路径',
    parent_menu_id     bigint                default null comment '父菜单编号',
    tree_parent_column bigint                default null comment '树表的父字段'
) comment = '代码生成表定义';
drop table if exists gen_column;
create table gen_column
(
    id                bigint       not null auto_increment primary key comment '编号id',
    table_id          bigint       not null comment '表编号',
    column_name       varchar(64)  not null default '' comment '字段名',
    column_comment    varchar(255) not null comment '字段描述',
    data_type         varchar(32)  not null comment '字段类型',
    sortField         int                   default 0 comment '排序',
    nullable          boolean               default false comment '可空',
    length            bigint comment '文本长度',
    numeric_precision int comment '数值长度',
    numeric_scale     int comment '小数位数',
    column_key        varchar(4)            default 'NONE' comment '列类型',
    java_field        varchar(64)  not null comment 'java 属性名',
    java_type         varchar(15)           default 'STRING' comment 'java 属性类型',
    dict_type         varchar(200) comment '字典类型',
    insertable        boolean               default false comment '可插入',
    editable          boolean               default false comment '可编辑',
    listable          boolean               default false comment '可列出',
    queryable         boolean               default false comment '可查询',
    query_type        varchar(10)           default 'NONE' comment '查询方式',
    html_type         varchar(15)           default 'INPUT' comment '显示类型',
    exportable        boolean               default false comment '可导出',
    default_value     varchar(500)          default null comment '默认值'
) comment = '代码生成字段定义';

drop table if exists system_menu;
create table system_menu
(
    id            bigint       not null auto_increment primary key comment 'id',
    parent_id     bigint                default 0 comment '父菜单id',
    name          varchar(50)  not null default '' unique comment '菜单名称',
    perms_code    varchar(100) not null default '' unique comment '权限编码',
    sortField     int                   default 0 comment '排序',
    path          varchar(200) null     default null comment '路由地址',
    component     varchar(255) null     default null comment '组件路径',
    type          varchar(10)           default 'DIRECTORY' comment '菜单类型',
    external_link boolean               default false comment '是否外链',
    cacheable     boolean               default true comment '可缓存',
    visible       boolean               default true comment '可显示',
    enable        boolean               default true comment '启用',
    icon          varchar(100) null     default null comment '菜单图标',
    create_dept   bigint comment '创建部门',
    create_by     bigint comment '创建者',
    create_date   datetime comment '创建时间',
    update_by     bigint comment '更新者',
    update_date   datetime comment '更新时间'
) comment ='系统菜单表';

drop table if exists system_role;
create table system_role
(
    id                  bigint      not null auto_increment primary key comment 'id',
    code                varchar(50) not null default '' unique comment '编码',
    name                varchar(50) not null default '' comment '名称',
    sortField           int                  default 0 comment '排序',
    enable              boolean              default true comment '启用',
    menu_check_strictly boolean              default true comment '菜单树选择项是否关联显示',
    menus               json comment '菜单',
    dept_check_strictly boolean              default true comment '部门树选择项是否关联显示',
    data_scope          varchar(10)          default 'ALL' comment '数据权限范围',
    departments         json comment '部门',
    create_dept         bigint comment '创建部门',
    create_by           bigint comment '创建者',
    create_date         datetime comment '创建时间',
    update_by           bigint comment '更新者',
    update_date         datetime comment '更新时间'
) comment ='系统权限表';

drop table if exists system_dept;
create table system_dept
(
    id          bigint      not null auto_increment primary key comment 'id',
    parent_id   bigint      not null default 0 comment '父部门id',
    ancestors   json comment '祖级列表',
    dept_name   varchar(30) not null unique comment '部门名称',
    sortField   int                  default 0 comment '排序',
    leader      bigint      null     default null comment '负责人',
    phone       varchar(11) null     default null comment '联系电话',
    email       varchar(50) null     default null comment '邮箱',
    enable      boolean              default true comment '启用',
    create_dept bigint comment '创建部门',
    create_by   bigint comment '创建者',
    create_date datetime comment '创建时间',
    update_by   bigint comment '更新者',
    update_date datetime comment '更新时间'
) comment ='系统部门表';

drop table if exists system_post;
create table system_post
(
    id          bigint      not null auto_increment primary key comment 'id',
    dept_id     bigint      not null comment '部门id',
    code        varchar(50) not null default '' unique comment '岗位编码',
    name        varchar(50) not null comment '岗位名称',
    sortField   int                  default 0 comment '排序',
    enable      boolean              default true comment '启用',
    create_dept bigint comment '创建部门',
    create_by   bigint comment '创建者',
    create_date datetime comment '创建时间',
    update_by   bigint comment '更新者',
    update_date datetime comment '更新时间'
) comment ='系统岗位表';

drop table if exists system_module;
create table system_module
(
    id          bigint      not null auto_increment primary key comment 'id',
    code        varchar(50) not null default '' unique comment '编码',
    name        varchar(50) not null default '' unique comment '名称',
    enable      boolean              default true comment '启用',
    menus       json comment '菜单',
    roles       json comment '角色',
    create_dept bigint comment '创建部门',
    create_by   bigint comment '创建者',
    create_date datetime comment '创建时间',
    update_by   bigint comment '更新者',
    update_date datetime comment '更新时间'
) comment ='系统模块表';

drop table if exists system_user;
create table system_user
(
    id          bigint       not null auto_increment primary key comment 'id',
    dept_id     bigint       null     default null comment '部门',
    user_name   varchar(30)  not null default '' unique comment '账号',
    real_name   varchar(30)  not null default '' comment '姓名',
    email       varchar(50)  null     default null comment '邮箱',
    phone       varchar(11)  null     default null comment '手机号码',
    gender      varchar(7)            default 'UNKNOWN' comment '性别',
    avatar      bigint       null     default null comment '头像',
    password    varchar(100) not null default '' comment '密码',
    roles       json comment '角色',
    posts       json comment '岗位',
    modules     json comment '模块',
    enable      boolean               default true comment '启用',
    pwd_changed boolean               default false comment '密码是否更改',
    create_dept bigint comment '创建部门',
    create_by   bigint comment '创建者',
    create_date datetime comment '创建时间',
    update_by   bigint comment '更新者',
    update_date datetime comment '更新时间'
) comment ='系统用户表';

drop table if exists user_avatar;
create table user_avatar
(
    id     bigint not null auto_increment primary key comment 'id',
    avatar blob comment '头像'
) comment ='用户头像表';

drop table if exists file_storage;
create table file_storage
(
    id            bigint       not null auto_increment primary key comment 'id',
    name          varchar(255) not null default '' unique comment '文件名',
    original_name varchar(255) not null default '' comment '原名',
    suffix        varchar(10)  not null default '' comment '文件后缀名',
    create_dept   bigint comment '创建部门',
    create_by     bigint comment '创建者',
    create_date   datetime comment '创建时间',
    update_by     bigint comment '更新者',
    update_date   datetime comment '更新时间'
) comment ='文件存储表';

drop table if exists system_config;
create table system_config
(
    id          bigint not null auto_increment primary key comment 'id',
    name        varchar(100) default '' comment '名称',
    code        varchar(100) default '' unique comment '编码',
    value       varchar(500) default '' comment '值',
    internal    boolean      default false comment '内置',
    create_dept bigint comment '创建部门',
    create_by   bigint comment '创建者',
    create_date datetime comment '创建时间',
    update_by   bigint comment '更新者',
    update_date datetime comment '更新时间'
) comment ='系统配置表';

drop table if exists system_dict;
create table system_dict
(
    id          bigint       not null auto_increment primary key comment 'id',
    code        varchar(100) not null default '' unique comment '编码',
    name        varchar(100) not null default '' comment '字典名称',
    create_dept bigint comment '创建部门',
    create_by   bigint comment '创建者',
    create_date datetime comment '创建时间',
    update_by   bigint comment '更新者',
    update_date datetime comment '更新时间'
) comment ='系统字典表';

drop table if exists system_dict_data;
create table system_dict_data
(
    id            bigint       not null auto_increment primary key comment 'id',
    dict_id       bigint       not null comment '字典',
    label         varchar(100) not null unique comment '标签',
    value         varchar(100) not null comment '键值',
    sortField     int               default 0 comment '排序',
    class_name    varchar(100) null comment '样式属性',
    show_type     varchar(10)  null default 'NONE' comment '表格回显样式',
    default_value boolean           default false comment '是否默认',
    create_dept   bigint comment '创建部门',
    create_by     bigint comment '创建者',
    create_date   datetime comment '创建时间',
    update_by     bigint comment '更新者',
    update_date   datetime comment '更新时间',
    index idx_system_dict_data_dict_id (dict_id)
) comment ='系统字典数据表';

drop table if exists system_message;
create table system_message
(
    id          bigint        not null auto_increment primary key comment 'id',
    title       varchar(50)   not null comment '消息标题',
    type        varchar(12)   not null default 'NOTICE' comment '消息类型',
    content     varchar(5000) null comment '内容',
    closed      boolean                default false comment '状态',
    publisher   bigint        null comment '发布人',
    receiver    json comment '接收人',
    create_dept bigint comment '创建部门',
    create_by   bigint comment '创建者',
    create_date datetime comment '创建时间',
    update_by   bigint comment '更新者',
    update_date datetime comment '更新时间'
) comment ='系统消息表';

drop table if exists log_login;
create table log_login
(
    id          bigint       not null auto_increment primary key comment 'id',
    user_id     bigint       not null comment '用户id',
    ip          varchar(128) null comment '登录ip',
    location    varchar(255) null comment '登录地点',
    browser     varchar(100) null comment '浏览器类型',
    os          varchar(50)  null comment '操作系统',
    successful  boolean  default false comment '登录状态',
    msg         varchar(255) null comment '提示消息',
    time        datetime default now() comment '时间',
    create_dept bigint comment '创建部门',
    create_by   bigint comment '创建者',
    create_date datetime comment '创建时间',
    update_by   bigint comment '更新者',
    update_date datetime comment '更新时间',
    index idx_log_login_time (time),
    index idx_log_login_successful (successful)
) comment ='登录日志表';

drop table if exists log_operate;
create table log_operate
(
    id             bigint        not null auto_increment primary key comment 'id',
    user_id        bigint        not null comment '用户id',
    title          varchar(50)   null comment '模块标题',
    method         varchar(100)  null comment '方法名称',
    request_method varchar(10)   null comment '请求方式',
    url            varchar(255)  null comment '请求URL',
    ip             varchar(128)  null comment '主机地址',
    location       varchar(255)  null comment '操作地点',
    param          varchar(2000) null comment '请求参数',
    result         varchar(2000) null comment '返回参数',
    status         int      default 200 comment '操作状态',
    error_msg      varchar(2000) null comment '错误消息',
    time           datetime default now() comment '操作时间',
    cost_time      bigint   default 0 comment '消耗时间',
    create_dept    bigint comment '创建部门',
    create_by      bigint comment '创建者',
    create_date    datetime comment '创建时间',
    update_by      bigint comment '更新者',
    update_date    datetime comment '更新时间',
    index idx_log_operate_status (status),
    index idx_log_operate_user_id (user_id),
    index idx_log_operate_time (time)
) comment ='操作日志表';
