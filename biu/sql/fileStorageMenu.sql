insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '文件存储', 'file:fileStorage:list', 1, 'fileStorage', 'file/fileStorage', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '文件存储查询', 'file:fileStorage:query', '1', '', 'BUTTON'),
(@parentId, '文件存储新增', 'file:fileStorage:add', '2', '', 'BUTTON'),
(@parentId, '文件存储修改', 'file:fileStorage:edit', '3', '', 'BUTTON'),
(@parentId, '文件存储删除', 'file:fileStorage:remove', '4', '', 'BUTTON'),
(@parentId, '文件存储导出', 'file:fileStorage:export', '5', '', 'BUTTON');
