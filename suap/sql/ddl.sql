drop table if exists log_login;
create table log_login
(
    id         bigint      not null auto_increment primary key comment 'id',
    user_id    bigint      not null comment '用户',
    ip         varchar(50) not null comment 'ip',
    login_time datetime    not null comment '登录时间'
) comment '登录日志';
drop table if exists log_business;
create table log_business
(
    id               bigint not null auto_increment primary key comment 'id',
    user_id          bigint        default null comment '用户',
    ip               varchar(50)   default '' comment 'ip',
    method           varchar(200)  default '' comment '方法',
    params           varchar(2000) default '' comment '参数',
    request_type     varchar(10)   default '' comment '请求类型',
    request_url      varchar(100)  default '' comment '请求url',
    return_result    varchar(2000) default '' comment '返回信息',
    business_name    varchar(50)   default '' comment '业务名称',
    operation_type   varchar(50)   default '' comment '操作类型',
    operation_result varchar(4)    default '' comment '操作结果',
    error_message    varchar(2000) default '' comment '错误信息',
    operation_time   datetime      default now() comment '操作时间'
) comment '业务日志';

drop table if exists gen_table;
create table gen_table
(
    id           bigint      not null auto_increment primary key comment 'id',
    table_name   varchar(50) not null comment '表名',
    comment      varchar(200) default null comment '表备注',
    class_name   varchar(100) default '' comment '实体类名称',
    template     varchar(1)   default 'L' comment '模板类型',
    package_name varchar(100) default 'cn.sijay.suap' comment '包路径',
    module       varchar(30)  default '' comment '模块名',
    author       varchar(50)  default 'Sijay' comment '作者',
    gen_type     varchar(1)   default 'Z' comment '生成方式',
    super_class  varchar(100) default '' comment '父类',
    menu_id      bigint       default null comment '所属菜单',
    created      varchar(1)   default 'N' comment '是否已生成',
    creator      bigint       default 1 comment '创建者',
    create_time  datetime     default now() comment '创建时间',
    updater      bigint       default null comment '更新者',
    update_time  datetime     default null comment '更新时间',
    version      bigint       default 1 comment '版本号'
) comment '表信息';
drop table if exists gen_table_column;
create table gen_table_column
(
    id           bigint      not null auto_increment primary key comment 'id',
    table_id     bigint      not null comment '表',
    name         varchar(50) not null comment '列名',
    comment      varchar(200) default null comment '列注释',
    type         varchar(50) not null comment '列类型',
    length       int          default null comment '长度',
    scale        int          default null comment '小数位',
    field_name   varchar(100) default '' comment '实体类名称',
    java_type    varchar(100) default '' comment '实体类类型',
    input_type   varchar(5)   default 'TI' comment '输入类型',
    visible      varchar(1)   default 'Y' comment '是否显示',
    editable     varchar(1)   default 'Y' comment '是否可编辑',
    required     varchar(1)   default 'N' comment '是否必填',
    queryable    varchar(1)   default 'N' comment '是否可查询',
    query_type   varchar(20)  default 'EQ' comment '查询方式',
    sort         int          default null comment '排序',
    primary_key  varchar(1)   default 'N' comment '是否主键',
    nullable     varchar(1)   default 'Y' comment '是否可为空',
    super_column varchar(1)   default 'N' comment '是否父类字段',
    unique_key   varchar(1)   default 'N' comment '是否唯一',
    excel_column varchar(1)   default 'N' comment '是否是导入导出字段',
    data_source  varchar(1)   default '' comment '数据来源',
    data         varchar(200) default '' comment '数据',
    creator      bigint       default 1 comment '创建者',
    create_time  datetime     default now() comment '创建时间',
    updater      bigint       default null comment '更新者',
    update_time  datetime     default null comment '更新时间',
    version      bigint       default 1 comment '版本号'
) comment '列信息';

drop table if exists sys_menu;
create table sys_menu
(
    id          bigint      not null auto_increment primary key comment 'id',
    parent_id   bigint      not null default 0 comment '上级',
    name        varchar(50) not null comment '菜单名称',
    type        char(1)              default '' comment '菜单类型',
    path        varchar(200)         default null comment '路径',
    component   varchar(255)         default null comment '组件路径',
    query_param varchar(255)         default null comment '路由参数',
    perms       varchar(100)         default null comment '权限标识',
    icon        varchar(100)         default null comment '图标',
    sort        int                  default 0 comment '排序',
    link        varchar(1)           default 'N' comment '是否为外链',
    cache       varchar(1)           default 'Y' comment '是否缓存',
    visible     varchar(1)           default 'Y' comment '显示状态',
    enabled     varchar(1)           default 'Y' comment '是否启用',
    creator     bigint               default 1 comment '创建者',
    create_time datetime             default now() comment '创建时间',
    updater     bigint               default null comment '更新者',
    update_time datetime             default null comment '更新时间',
    deleted     varchar(1)           default 'N' comment '是否删除',
    version     bigint               default 1 comment '版本号'
) comment '菜单信息';
drop table if exists sys_role;
create table sys_role
(
    id          bigint      not null auto_increment primary key comment 'id',
    name        varchar(50) not null comment '角色名称',
    code        varchar(50) not null unique comment '角色编码',
    sort        int        default 0 comment '排序',
    creator     bigint     default 1 comment '创建者',
    create_time datetime   default now() comment '创建时间',
    updater     bigint     default null comment '更新者',
    update_time datetime   default null comment '更新时间',
    deleted     varchar(1) default 'N' comment '是否删除',
    version     bigint     default 1 comment '版本号'
) comment '角色信息';
drop table if exists sys_role_menu;
create table sys_role_menu
(
    role_id bigint not null comment '角色',
    menu_id bigint not null comment '菜单',
    primary key (role_id, menu_id) using btree comment '角色菜单复合主键'
) comment '角色菜单';
drop table if exists sys_dept;
create table sys_dept
(
    id          bigint      not null auto_increment primary key comment 'id',
    parent_id   bigint      not null default 0 comment '上级',
    name        varchar(50) not null comment '部门名称',
    leader      bigint               default null comment '部门负责人',
    phone       varchar(11)          default null comment '部门电话',
    sort        int                  default 0 comment '排序',
    creator     bigint               default 1 comment '创建者',
    create_time datetime             default now() comment '创建时间',
    updater     bigint               default null comment '更新者',
    update_time datetime             default null comment '更新时间',
    deleted     varchar(1)           default 'N' comment '是否删除',
    version     bigint               default 1 comment '版本号'
) comment '部门信息';
drop table if exists sys_user;
create table sys_user
(
    id              bigint       not null auto_increment primary key comment 'id',
    username        varchar(50)  not null unique comment '用户名',
    phone           varchar(11)  default null unique comment '手机号',
    email           varchar(100) default null unique comment '邮箱',
    password        varchar(128) not null comment '密码',
    last_login_time datetime     default null comment '最后登录时间',
    enable          varchar(1)   default 'Y' comment '是否启用',
    sort            int          default 0 comment '排序',
    creator         bigint       default 1 comment '创建者',
    create_time     datetime     default now() comment '创建时间',
    updater         bigint       default null comment '更新者',
    update_time     datetime     default null comment '更新时间',
    deleted         varchar(1)   default 'N' comment '是否删除',
    version         bigint       default 1 comment '版本号'
) comment '登录用户';
drop table if exists sys_user_info;
create table sys_user_info
(
    id          bigint      not null auto_increment primary key comment 'id',
    user_id     bigint      not null comment '用户',
    name        varchar(50) not null comment '姓名',
    gender      varchar(1)   default 'U' comment '性别',
    birthday    date         default null comment '生日',
    avatar      varchar(200) default null comment '头像',
    province    bigint       default null comment '省',
    city        bigint       default null comment '市',
    area        bigint       default null comment '区',
    address     varchar(200) default null comment '详细地址',
    dept_id     bigint       default null comment '部门',
    remark      varchar(200) default null comment '备注',
    creator     bigint       default 1 comment '创建者',
    create_time datetime     default now() comment '创建时间',
    updater     bigint       default null comment '更新者',
    update_time datetime     default null comment '更新时间',
    deleted     varchar(1)   default 'N' comment '是否删除',
    version     bigint       default 1 comment '版本号'
) comment '用户信息';
drop table if exists sys_user_role;
create table sys_user_role
(
    role_id bigint not null comment '角色',
    user_id bigint not null comment '用户',
    primary key (role_id, user_id) using btree comment '角色用户复合主键'
) comment '用户角色';
drop table if exists sys_post;
create table sys_post
(
    id          bigint      not null auto_increment primary key comment 'id',
    name        varchar(50) not null comment '岗位名称',
    code        varchar(50) not null unique comment '岗位编码',
    sort        int        default 0 comment '排序',
    creator     bigint     default 1 comment '创建者',
    create_time datetime   default now() comment '创建时间',
    updater     bigint     default null comment '更新者',
    update_time datetime   default null comment '更新时间',
    deleted     varchar(1) default 'N' comment '是否删除',
    version     bigint     default 1 comment '版本号'
) comment '岗位信息';
drop table if exists sys_user_post;
create table sys_user_post
(
    user_id bigint not null comment '用户',
    post_id bigint not null comment '岗位',
    primary key (user_id, post_id) using btree comment '用户岗位复合主键'
) comment '用户岗位';
drop table if exists sys_notice;
create table sys_notice
(
    id          bigint       not null auto_increment primary key comment 'id',
    title       varchar(50)  not null comment '标题',
    content     varchar(500) not null comment '内容',
    type        varchar(10)  not null comment '类型',
    status      varchar(10)  not null comment '状态',
    creator     bigint     default 1 comment '创建者',
    create_time datetime   default now() comment '创建时间',
    updater     bigint     default null comment '更新者',
    update_time datetime   default null comment '更新时间',
    deleted     varchar(1) default 'N' comment '是否删除',
    version     bigint     default 1 comment '版本号'
) comment '通知公告';
drop table if exists sys_config;
create table sys_config
(
    id          bigint      not null auto_increment primary key comment 'id',
    name        varchar(50) not null comment '配置名称',
    code        varchar(50) not null unique comment '配置编码',
    value       varchar(50) not null comment '配置值',
    sort        int        default 0 comment '排序',
    creator     bigint     default 1 comment '创建者',
    create_time datetime   default now() comment '创建时间',
    updater     bigint     default null comment '更新者',
    update_time datetime   default null comment '更新时间',
    deleted     varchar(1) default 'N' comment '是否删除',
    version     bigint     default 1 comment '版本号'
) comment '系统配置';

drop table if exists data_dict;
create table data_dict
(
    id          bigint      not null auto_increment primary key comment 'id',
    parent_id   bigint      not null default 0 comment '上级',
    name        varchar(50) not null comment '字典名称',
    code        varchar(50) not null unique comment '字典编码',
    value       varchar(50)          default '' comment '字典值',
    sort        int                  default 0 comment '排序',
    creator     bigint               default 1 comment '创建者',
    create_time datetime             default now() comment '创建时间',
    updater     bigint               default null comment '更新者',
    update_time datetime             default null comment '更新时间',
    deleted     varchar(1)           default 'N' comment '是否删除',
    version     bigint               default 1 comment '版本号'
) comment '数据字典';
drop table if exists data_region;
create table data_region
(
    id          bigint      not null auto_increment primary key comment 'id',
    parent_id   bigint      not null default 0 comment '上级',
    name        varchar(50) not null comment '名称',
    code        varchar(50) not null unique comment '编码',
    level       int                  default 0 comment '级别',
    sort        int                  default 0 comment '排序',
    creator     bigint               default 1 comment '创建者',
    create_time datetime             default now() comment '创建时间',
    updater     bigint               default null comment '更新者',
    update_time datetime             default null comment '更新时间',
    deleted     varchar(1)           default 'N' comment '是否删除',
    version     bigint               default 1 comment '版本号'
) comment '行政区划数据';



