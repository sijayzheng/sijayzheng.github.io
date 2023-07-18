drop table if exists province;
create table province
(
    id       int not null,
    code     varchar(12) default null comment '行政区划代码',
    name     varchar(32) default null comment '名称',
    province varchar(32) default null comment '省/直辖市',
    city     varchar(32) default null comment '市',
    area     varchar(32) default null comment '区',
    town     varchar(32) default null comment '城镇地区',
    primary key (id)
);

-- ry-vue.sys_config definition

CREATE TABLE sys_config
(
    config_id    bigint NOT NULL COMMENT '参数主键',
    config_name  varchar(100) DEFAULT '' COMMENT '参数名称',
    config_key   varchar(100) DEFAULT '' COMMENT '参数键名',
    config_value varchar(500) DEFAULT '' COMMENT '参数键值',
    remark       varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (config_id)
);


-- ry-vue.sys_dict definition

CREATE TABLE sys_dict
(
    id         bigint NOT NULL COMMENT '字典编码',
    dict_sort  int          DEFAULT '0' COMMENT '字典排序',
    dict_label varchar(100) DEFAULT '' COMMENT '字典标签',
    dict_value varchar(100) DEFAULT '' COMMENT '字典键值',
    parent_id  bigint       DEFAULT '0' COMMENT '字典类型',
    css_class  varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
    list_class varchar(100) DEFAULT NULL COMMENT '表格回显样式',
    is_default char(1)      DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
    status     char(1)      DEFAULT '0' COMMENT '状态（0正常 1停用）',
    remark     varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (id)
);


-- ry-vue.sys_menu definition

CREATE TABLE sys_menu
(
    menu_id     bigint      NOT NULL COMMENT '菜单ID',
    menu_name   varchar(50) NOT NULL COMMENT '菜单名称',
    parent_id   bigint       DEFAULT '0' COMMENT '父菜单ID',
    order_num   int          DEFAULT '0' COMMENT '显示顺序',
    path        varchar(200) DEFAULT '' COMMENT '路由地址',
    component   varchar(255) DEFAULT NULL COMMENT '组件路径',
    query_param varchar(255) DEFAULT NULL COMMENT '路由参数',
    is_frame    int          DEFAULT '1' COMMENT '是否为外链（0是 1否）',
    is_cache    int          DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
    menu_type   char(1)      DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
    visible     char(1)      DEFAULT '0' COMMENT '显示状态（0显示 1隐藏）',
    status      char(1)      DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
    perms       varchar(100) DEFAULT NULL COMMENT '权限标识',
    icon        varchar(100) DEFAULT '#' COMMENT '菜单图标',
    PRIMARY KEY (menu_id)
);


-- ry-vue.sys_user definition

CREATE TABLE sys_user
(
    user_id   bigint      NOT NULL COMMENT '用户ID',
    user_name varchar(30) NOT NULL COMMENT '用户账号',
    password  varchar(100) DEFAULT '' COMMENT '密码',
    PRIMARY KEY (user_id)
);
