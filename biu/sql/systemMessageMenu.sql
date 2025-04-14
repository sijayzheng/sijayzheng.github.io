insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统消息', 'system:systemMessage:list', 1, 'systemMessage', 'system/systemMessage', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统消息查询', 'system:systemMessage:query', '1', '', 'BUTTON'),
(@parentId, '系统消息新增', 'system:systemMessage:add', '2', '', 'BUTTON'),
(@parentId, '系统消息修改', 'system:systemMessage:edit', '3', '', 'BUTTON'),
(@parentId, '系统消息删除', 'system:systemMessage:remove', '4', '', 'BUTTON'),
(@parentId, '系统消息导出', 'system:systemMessage:export', '5', '', 'BUTTON');
