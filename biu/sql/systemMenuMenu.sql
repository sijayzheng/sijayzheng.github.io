insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统菜单', 'system:systemMenu:list', 1, 'systemMenu', 'system/systemMenu', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统菜单查询', 'system:systemMenu:query', '1', '', 'BUTTON'),
(@parentId, '系统菜单新增', 'system:systemMenu:add', '2', '', 'BUTTON'),
(@parentId, '系统菜单修改', 'system:systemMenu:edit', '3', '', 'BUTTON'),
(@parentId, '系统菜单删除', 'system:systemMenu:remove', '4', '', 'BUTTON'),
(@parentId, '系统菜单导出', 'system:systemMenu:export', '5', '', 'BUTTON');
