insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统字典数据', 'system:systemDictData:list', 1, 'systemDictData', 'system/systemDictData', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统字典数据查询', 'system:systemDictData:query', '1', '', 'BUTTON'),
(@parentId, '系统字典数据新增', 'system:systemDictData:add', '2', '', 'BUTTON'),
(@parentId, '系统字典数据修改', 'system:systemDictData:edit', '3', '', 'BUTTON'),
(@parentId, '系统字典数据删除', 'system:systemDictData:remove', '4', '', 'BUTTON'),
(@parentId, '系统字典数据导出', 'system:systemDictData:export', '5', '', 'BUTTON');
