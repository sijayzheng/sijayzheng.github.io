insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统字典', 'system:systemDict:list', 1, 'systemDict', 'system/systemDict', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统字典查询', 'system:systemDict:query', '1', '', 'BUTTON'),
(@parentId, '系统字典新增', 'system:systemDict:add', '2', '', 'BUTTON'),
(@parentId, '系统字典修改', 'system:systemDict:edit', '3', '', 'BUTTON'),
(@parentId, '系统字典删除', 'system:systemDict:remove', '4', '', 'BUTTON'),
(@parentId, '系统字典导出', 'system:systemDict:export', '5', '', 'BUTTON');
