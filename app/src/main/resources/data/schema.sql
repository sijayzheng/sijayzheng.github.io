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