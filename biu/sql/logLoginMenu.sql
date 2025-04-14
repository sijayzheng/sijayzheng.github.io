insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '登录日志', 'log:logLogin:list', 1, 'logLogin', 'log/logLogin', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '登录日志查询', 'log:logLogin:query', '1', '', 'BUTTON'),
(@parentId, '登录日志新增', 'log:logLogin:add', '2', '', 'BUTTON'),
(@parentId, '登录日志修改', 'log:logLogin:edit', '3', '', 'BUTTON'),
(@parentId, '登录日志删除', 'log:logLogin:remove', '4', '', 'BUTTON'),
(@parentId, '登录日志导出', 'log:logLogin:export', '5', '', 'BUTTON');
