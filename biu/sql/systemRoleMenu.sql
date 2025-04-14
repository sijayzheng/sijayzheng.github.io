insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统权限', 'system:systemRole:list', 1, 'systemRole', 'system/systemRole', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统权限查询', 'system:systemRole:query', '1', '', 'BUTTON'),
(@parentId, '系统权限新增', 'system:systemRole:add', '2', '', 'BUTTON'),
(@parentId, '系统权限修改', 'system:systemRole:edit', '3', '', 'BUTTON'),
(@parentId, '系统权限删除', 'system:systemRole:remove', '4', '', 'BUTTON'),
(@parentId, '系统权限导出', 'system:systemRole:export', '5', '', 'BUTTON');
