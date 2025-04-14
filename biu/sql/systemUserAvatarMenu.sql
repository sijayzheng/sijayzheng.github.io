insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '用户头像', 'system:systemUserAvatar:list', 1, 'systemUserAvatar', 'system/systemUserAvatar', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '用户头像查询', 'system:systemUserAvatar:query', '1', '', 'BUTTON'),
(@parentId, '用户头像新增', 'system:systemUserAvatar:add', '2', '', 'BUTTON'),
(@parentId, '用户头像修改', 'system:systemUserAvatar:edit', '3', '', 'BUTTON'),
(@parentId, '用户头像删除', 'system:systemUserAvatar:remove', '4', '', 'BUTTON'),
(@parentId, '用户头像导出', 'system:systemUserAvatar:export', '5', '', 'BUTTON');
