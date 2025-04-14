insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统岗位', 'system:systemPost:list', 1, 'systemPost', 'system/systemPost', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统岗位查询', 'system:systemPost:query', '1', '', 'BUTTON'),
(@parentId, '系统岗位新增', 'system:systemPost:add', '2', '', 'BUTTON'),
(@parentId, '系统岗位修改', 'system:systemPost:edit', '3', '', 'BUTTON'),
(@parentId, '系统岗位删除', 'system:systemPost:remove', '4', '', 'BUTTON'),
(@parentId, '系统岗位导出', 'system:systemPost:export', '5', '', 'BUTTON');
