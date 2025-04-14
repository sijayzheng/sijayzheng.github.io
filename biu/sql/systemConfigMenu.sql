insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统配置', 'system:systemConfig:list', 1, 'systemConfig', 'system/systemConfig', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统配置查询', 'system:systemConfig:query', '1', '', 'BUTTON'),
(@parentId, '系统配置新增', 'system:systemConfig:add', '2', '', 'BUTTON'),
(@parentId, '系统配置修改', 'system:systemConfig:edit', '3', '', 'BUTTON'),
(@parentId, '系统配置删除', 'system:systemConfig:remove', '4', '', 'BUTTON'),
(@parentId, '系统配置导出', 'system:systemConfig:export', '5', '', 'BUTTON');
