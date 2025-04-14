insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '操作日志', 'log:logOperate:list', 1, 'logOperate', 'log/logOperate', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '操作日志查询', 'log:logOperate:query', '1', '', 'BUTTON'),
(@parentId, '操作日志新增', 'log:logOperate:add', '2', '', 'BUTTON'),
(@parentId, '操作日志修改', 'log:logOperate:edit', '3', '', 'BUTTON'),
(@parentId, '操作日志删除', 'log:logOperate:remove', '4', '', 'BUTTON'),
(@parentId, '操作日志导出', 'log:logOperate:export', '5', '', 'BUTTON');
