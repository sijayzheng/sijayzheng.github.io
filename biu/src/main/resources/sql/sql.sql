create table gen_table
(
    id              bigint                              not null auto_increment comment 'id',
    table_name      varchar(200)             default '' not null comment '表名称',
    table_comment   varchar(500)             default '' not null comment '表描述',
    package_name    varchar(100)             default '' comment '生成包路径',
    module_name     varchar(30)              default '' comment '生成模块名',
    class_name      varchar(100)             default '' not null comment '类名称',
    class_comment   varchar(50)              default '' not null comment '类描述',
    gen_type        enum ('DIRECTORY','ZIP') default 'ZIP' comment '生成类型',
    template_type   enum ('LIST','TREE')     default 'LIST' comment '模板类型',
    author          varchar(50)              default '' comment '作者',
    parent_menu_id  bigint comment '父菜单编号',
    tree_key        varchar(20) comment '树节点标识',
    tree_label      varchar(20) comment '树节点标签',
    tree_parent_key varchar(20) comment '树父节点标识',
    primary key (id)
) comment ='代码生成表定义';
create table gen_column
(
    id                bigint                                                                                                                                                                                                                                                    not null auto_increment comment 'id',
    column_name       varchar(64)                                                                                                                                                                                                                                    default '' not null comment '字段名',
    table_id          bigint                                                                                                                                                                                                                                                    not null comment '表编号',
    column_comment    varchar(255)                                                                                                                                                                                                                                              not null comment '字段描述',
    column_key        enum ('MUL','NONE','PRI','UNI')                                                                                                                                                                                                                default 'NONE' comment '列类型',
    data_type         varchar(32)                                                                                                                                                                                                                                               not null comment '字段类型',
    java_type         enum ('BIG_DECIMAL','BOOLEAN','BYTE_ARRAY','CHARACTER','DATA_SCOPE_ENUM','DOUBLE','FLOAT','GENDER_TYPE','INTEGER','LIST','LIST_LONG','LOCAL_DATE','LOCAL_DATE_TIME','LOCAL_TIME','LONG','MENU_TYPE','MESSAGE_TYPE','SET','SHOW_TYPE','STRING') default 'STRING' comment 'java 属性类型',
    java_field        varchar(64)                                                                                                                                                                                                                                               not null comment 'java 属性名',
    sort              integer                                                                                                                                                                                                                                        default 0 comment '排序',
    nullable          bit                                                                                                                                                                                                                                            default 0 comment '可空',
    numeric_precision integer comment '数值长度',
    numeric_scale     integer comment '小数位数',
    length            bigint comment '文本长度',
    default_value     varchar(500) comment '默认值',
    listable          bit                                                                                                                                                                                                                                            default 0 comment '可列出',
    editable          bit                                                                                                                                                                                                                                            default 0 comment '可编辑',
    queryable         bit                                                                                                                                                                                                                                            default 0 comment '可查询',
    exportable        bit                                                                                                                                                                                                                                            default 0 comment '可导出',
    html_type         enum ('CHECKBOX','DATETIME_PICKER','DATE_PICKER','EDITOR','FILE_UPLOAD','IMAGE_UPLOAD','INPUT','INPUT_NUMBER','RADIO','SELECT','SWITCH','TEXTAREA','TIME_PICKER','TREE_SELECT')                                                                default 'INPUT' comment '显示类型',
    query_type        enum ('BETWEEN','EQUAL','LIKE','NONE')                                                                                                                                                                                                         default 'NONE' comment '查询方式',
    dict_type         varchar(200) comment '字典类型',
    primary key (id)
) comment ='代码生成列定义';

create table system_user
(
    id          bigint auto_increment primary key comment 'id',
    username    varchar(20) not null unique key comment '账号',
    dept_id     bigint comment '部门',
    real_name   varchar(50) comment '姓名',
    email       varchar(100) comment '邮箱',
    phone       varchar(11) comment '手机号码',
    gender      enum ('MALE','FEMALE','UNKNOWN') default 'UNKNOWN' comment '性别',
    avatar      bigint comment '头像',
    password    varchar(255) comment '密码',
    roles       json comment '角色',
    posts       json comment '岗位',
    modules     json comment '模块',
    enable      bit                              default 1 comment '启用',
    pwd_changed bit                              default 0 comment '密码是否更改',
    create_dept bigint comment '创建部门',
    create_by   bigint comment '创建者',
    create_time datetime comment '创建时间',
    update_by   bigint comment '更新者',
    update_time datetime comment '更新时间'
) comment '系统用户';
create table system_dept
(
    id          bigint auto_increment primary key comment 'id',
    name        varchar(50) not null comment '部门名称',
    parent_id   bigint comment '父部门id',
    ancestors   json comment '祖级列表',
    sort        int comment '排序',
    leader      bigint comment '负责人',
    phone       varchar(11) comment '联系电话',
    email       varchar(100) comment '邮箱',
    enable      bit default 1 comment '启用',
    create_dept bigint comment '创建部门',
    create_by   bigint comment '创建者',
    create_time datetime comment '创建时间',
    update_by   bigint comment '更新者',
    update_time datetime comment '更新时间'
) comment '系统部门';
create table system_config
(
    id          bigint auto_increment primary key comment 'id',
    name        varchar(100)  not null comment '名称',
    code        varchar(100)  not null unique key comment '编码',
    value       varchar(500)  not null comment '值',
    internal    bit default 0 null comment '内置',
    create_dept bigint comment '创建部门',
    create_by   bigint comment '创建者',
    create_time datetime comment '创建时间',
    update_by   bigint comment '更新者',
    update_time datetime comment '更新时间'
) comment '系统配置';
create table system_menu
(
    id            bigint auto_increment primary key comment 'id',
    parent_id     bigint default 0                     null comment '父菜单id',
    name          varchar(50)                          not null comment '菜单名称',
    perms_code    varchar(100)                         not null comment '权限编码',
    sort          int    default 0                     null comment '排序',
    path          varchar(200)                         null comment '路由地址',
    component     varchar(255)                         null comment '组件路径',
    type          enum ('BUTTON', 'DIRECTORY', 'MENU') not null comment '菜单类型',
    external_link bit    default 0                     null comment '是否外链',
    cacheable     bit    default 1                     null comment '可缓存',
    visible       bit    default 1                     null comment '可显示',
    enable        bit    default 1                     null comment '启用',
    icon          varchar(100)                         null comment '菜单图标',
    create_dept   bigint                               null comment '创建部门',
    create_by     bigint                               null comment '创建者',
    create_time   datetime                             null comment '创建时间',
    update_by     bigint                               null comment '更新者',
    update_time   datetime                             null comment '更新时间'
)
    comment '系统菜单';
create table system_role
(
    id                  bigint auto_increment primary key comment 'id',
    code                varchar(50)                                              not null unique key comment '编码',
    name                varchar(50)                                              not null comment '名称',
    sort                int                                        default 0     null comment '排序',
    enable              bit                                        default 1     null comment '启用',
    menus               json                                                     null comment '菜单',
    menu_check_strictly bit                                        default 1     null comment '菜单树关联显示',
    departments         json                                                     null comment '部门',
    dept_check_strictly bit                                        default 1     null comment '部门树关联显示',
    data_scope          enum ('ALL', 'DEPT', 'DEPT_UNDER', 'SELF') default 'ALL' null comment '数据权限范围',
    create_dept         bigint                                                   null comment '创建部门',
    create_by           bigint                                                   null comment '创建者',
    create_time         datetime                                                 null comment '创建时间',
    update_by           bigint                                                   null comment '更新者',
    update_time         datetime                                                 null comment '更新时间'
)
    comment '系统权限';
create table system_post
(
    id          bigint auto_increment primary key comment 'id',
    name        varchar(50)   not null comment '名称',
    code        varchar(50)   not null unique key comment '编码',
    dept_id     bigint        not null comment '部门id',
    sort        int default 0 null comment '排序',
    enable      bit default 1 null comment '启用',
    create_dept bigint        null comment '创建部门',
    create_by   bigint        null comment '创建者',
    create_time datetime      null comment '创建时间',
    update_by   bigint        null comment '更新者',
    update_time datetime      null comment '更新时间'
)
    comment '系统岗位';
create table system_module
(
    id          bigint auto_increment primary key comment 'id',
    code        varchar(50)   not null unique key comment '编码',
    name        varchar(50)   not null comment '名称',
    roles       json          null comment '角色',
    menus       json          null comment '菜单',
    enable      bit default 1 null comment '启用',
    create_dept bigint        null comment '创建部门',
    create_by   bigint        null comment '创建者',
    create_time datetime      null comment '创建时间',
    update_by   bigint        null comment '更新者',
    update_time datetime      null comment '更新时间'
)
    comment '系统模块';
create table user_avatar
(
    id     bigint not null auto_increment primary key comment 'id',
    avatar blob comment '头像'
) comment ='用户头像表';
create table system_message
(
    id          bigint auto_increment primary key comment 'id',
    title       varchar(50)                                                 not null comment '消息标题',
    type        enum ('ANNOUNCEMENT', 'MESSAGE', 'NOTICE') default 'NOTICE' not null comment '消息类型',
    content     varchar(5000)                                               null comment '内容',
    closed      bit                                        default 0        null comment '状态',
    publisher   bigint                                                      null comment '发布人',
    receiver    json                                                        null comment '接收人',
    create_dept bigint                                                      null comment '创建部门',
    create_by   bigint                                                      null comment '创建者',
    create_time datetime                                                    null comment '创建时间',
    update_by   bigint                                                      null comment '更新者',
    update_time datetime                                                    null comment '更新时间'
)
    comment '系统消息';
create table system_dict
(
    id          bigint auto_increment primary key comment 'id',
    code        varchar(100) not null comment '编码',
    name        varchar(100) not null comment '名称',
    create_dept bigint       null comment '创建部门',
    create_by   bigint       null comment '创建者',
    create_time datetime     null comment '创建时间',
    update_by   bigint       null comment '更新者',
    update_time datetime     null comment '更新时间'
)
    comment '系统字典';
create table system_dict_data
(
    id            bigint auto_increment primary key comment 'id',
    dict_id       bigint                                                                          not null comment '字典',
    label         varchar(100)                                                                    not null comment '标签',
    value         varchar(100)                                                                    not null comment '键值',
    sort          int                                                              default 0      null comment '排序',
    class_name    varchar(100)                                                                    null comment '样式属性',
    show_type     enum ('DANGER', 'INFO', 'NONE', 'PRIMARY', 'SUCCESS', 'WARNING') default 'NONE' null comment '表格回显样式',
    default_value bit                                                              default 0      null comment '默认值',
    create_dept   bigint                                                                          null comment '创建部门',
    create_by     bigint                                                                          null comment '创建者',
    create_time   datetime                                                                        null comment '创建时间',
    update_by     bigint                                                                          null comment '更新者',
    update_time   datetime                                                                        null comment '更新时间',
    index idx_system_dict_data_dict_id (dict_id)
)
    comment '系统字典数据';



create table file_storage
(
    id            bigint       not null auto_increment primary key comment 'id',
    name          varchar(255) not null default '' unique comment '文件名',
    original_name varchar(255) not null default '' comment '原名',
    suffix        varchar(10)  not null default '' comment '文件后缀名',
    url           varchar(255) not null default '' comment '文件访问地址',
    store_path    varchar(255) not null default '' comment '文件存储路径',
    create_dept   bigint       null comment '创建部门',
    create_by     bigint       null comment '创建者',
    create_time   datetime     null comment '创建时间',
    update_by     bigint       null comment '更新者',
    update_time   datetime     null comment '更新时间'
) comment ='文件存储表';

create table log_login
(
    id         bigint       not null auto_increment primary key comment 'id',
    user_id    bigint       not null comment '用户id',
    ip         varchar(128) null comment '登录ip',
    location   varchar(255) null comment '登录地点',
    browser    varchar(100) null comment '浏览器类型',
    os         varchar(50)  null comment '操作系统',
    successful bit      default 0 comment '登录状态',
    msg        varchar(255) null comment '提示消息',
    time       datetime default now() comment '时间',
    index idx_log_login_time (time),
    index idx_log_login_successful (successful)
) comment ='登录日志表';
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
    index idx_log_operate_status (status),
    index idx_log_operate_user_id (user_id),
    index idx_log_operate_time (time)
) comment ='操作日志表';