insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统模块', 'system:systemModule:list', 1, 'systemModule', 'system/systemModule', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统模块查询', 'system:systemModule:query', '1', '', 'BUTTON'),
(@parentId, '系统模块新增', 'system:systemModule:add', '2', '', 'BUTTON'),
(@parentId, '系统模块修改', 'system:systemModule:edit', '3', '', 'BUTTON'),
(@parentId, '系统模块删除', 'system:systemModule:remove', '4', '', 'BUTTON'),
(@parentId, '系统模块导出', 'system:systemModule:export', '5', '', 'BUTTON');
