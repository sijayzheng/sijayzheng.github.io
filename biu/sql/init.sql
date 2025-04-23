insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统用户', 'system:systemUser:list', 1, 'systemUser', 'system/systemUser', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统用户查询', 'system:systemUser:query', '1', '', 'BUTTON'),
       (@parentId, '系统用户新增', 'system:systemUser:add', '2', '', 'BUTTON'),
       (@parentId, '系统用户修改', 'system:systemUser:edit', '3', '', 'BUTTON'),
       (@parentId, '系统用户删除', 'system:systemUser:remove', '4', '', 'BUTTON'),
       (@parentId, '系统用户导出', 'system:systemUser:export', '5', '', 'BUTTON');

insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统部门', 'system:systemDept:list', 1, 'systemDept', 'system/systemDept', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统部门查询', 'system:systemDept:query', '1', '', 'BUTTON'),
       (@parentId, '系统部门新增', 'system:systemDept:add', '2', '', 'BUTTON'),
       (@parentId, '系统部门修改', 'system:systemDept:edit', '3', '', 'BUTTON'),
       (@parentId, '系统部门删除', 'system:systemDept:remove', '4', '', 'BUTTON'),
       (@parentId, '系统部门导出', 'system:systemDept:export', '5', '', 'BUTTON');



insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '文件存储', 'file:fileStorage:list', 1, 'fileStorage', 'file/fileStorage', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '文件存储查询', 'file:fileStorage:query', '1', '', 'BUTTON'),
       (@parentId, '文件存储新增', 'file:fileStorage:add', '2', '', 'BUTTON'),
       (@parentId, '文件存储修改', 'file:fileStorage:edit', '3', '', 'BUTTON'),
       (@parentId, '文件存储删除', 'file:fileStorage:remove', '4', '', 'BUTTON'),
       (@parentId, '文件存储导出', 'file:fileStorage:export', '5', '', 'BUTTON');
insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '登录日志', 'log:logLogin:list', 1, 'logLogin', 'log/logLogin', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '登录日志查询', 'log:logLogin:query', '1', '', 'BUTTON'),
       (@parentId, '登录日志新增', 'log:logLogin:add', '2', '', 'BUTTON'),
       (@parentId, '登录日志修改', 'log:logLogin:edit', '3', '', 'BUTTON'),
       (@parentId, '登录日志删除', 'log:logLogin:remove', '4', '', 'BUTTON'),
       (@parentId, '登录日志导出', 'log:logLogin:export', '5', '', 'BUTTON');
insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '操作日志', 'log:logOperate:list', 1, 'logOperate', 'log/logOperate', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '操作日志查询', 'log:logOperate:query', '1', '', 'BUTTON'),
       (@parentId, '操作日志新增', 'log:logOperate:add', '2', '', 'BUTTON'),
       (@parentId, '操作日志修改', 'log:logOperate:edit', '3', '', 'BUTTON'),
       (@parentId, '操作日志删除', 'log:logOperate:remove', '4', '', 'BUTTON'),
       (@parentId, '操作日志导出', 'log:logOperate:export', '5', '', 'BUTTON');
insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统配置', 'system:systemConfig:list', 1, 'systemConfig', 'system/systemConfig', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统配置查询', 'system:systemConfig:query', '1', '', 'BUTTON'),
       (@parentId, '系统配置新增', 'system:systemConfig:add', '2', '', 'BUTTON'),
       (@parentId, '系统配置修改', 'system:systemConfig:edit', '3', '', 'BUTTON'),
       (@parentId, '系统配置删除', 'system:systemConfig:remove', '4', '', 'BUTTON'),
       (@parentId, '系统配置导出', 'system:systemConfig:export', '5', '', 'BUTTON');
insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统字典数据', 'system:systemDictData:list', 1, 'systemDictData', 'system/systemDictData', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统字典数据查询', 'system:systemDictData:query', '1', '', 'BUTTON'),
       (@parentId, '系统字典数据新增', 'system:systemDictData:add', '2', '', 'BUTTON'),
       (@parentId, '系统字典数据修改', 'system:systemDictData:edit', '3', '', 'BUTTON'),
       (@parentId, '系统字典数据删除', 'system:systemDictData:remove', '4', '', 'BUTTON'),
       (@parentId, '系统字典数据导出', 'system:systemDictData:export', '5', '', 'BUTTON');
insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统字典', 'system:systemDict:list', 1, 'systemDict', 'system/systemDict', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统字典查询', 'system:systemDict:query', '1', '', 'BUTTON'),
       (@parentId, '系统字典新增', 'system:systemDict:add', '2', '', 'BUTTON'),
       (@parentId, '系统字典修改', 'system:systemDict:edit', '3', '', 'BUTTON'),
       (@parentId, '系统字典删除', 'system:systemDict:remove', '4', '', 'BUTTON'),
       (@parentId, '系统字典导出', 'system:systemDict:export', '5', '', 'BUTTON');
insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统菜单', 'system:systemMenu:list', 1, 'systemMenu', 'system/systemMenu', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统菜单查询', 'system:systemMenu:query', '1', '', 'BUTTON'),
       (@parentId, '系统菜单新增', 'system:systemMenu:add', '2', '', 'BUTTON'),
       (@parentId, '系统菜单修改', 'system:systemMenu:edit', '3', '', 'BUTTON'),
       (@parentId, '系统菜单删除', 'system:systemMenu:remove', '4', '', 'BUTTON'),
       (@parentId, '系统菜单导出', 'system:systemMenu:export', '5', '', 'BUTTON');
insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统消息', 'system:systemMessage:list', 1, 'systemMessage', 'system/systemMessage', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统消息查询', 'system:systemMessage:query', '1', '', 'BUTTON'),
       (@parentId, '系统消息新增', 'system:systemMessage:add', '2', '', 'BUTTON'),
       (@parentId, '系统消息修改', 'system:systemMessage:edit', '3', '', 'BUTTON'),
       (@parentId, '系统消息删除', 'system:systemMessage:remove', '4', '', 'BUTTON'),
       (@parentId, '系统消息导出', 'system:systemMessage:export', '5', '', 'BUTTON');
insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统模块', 'system:systemModule:list', 1, 'systemModule', 'system/systemModule', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统模块查询', 'system:systemModule:query', '1', '', 'BUTTON'),
       (@parentId, '系统模块新增', 'system:systemModule:add', '2', '', 'BUTTON'),
       (@parentId, '系统模块修改', 'system:systemModule:edit', '3', '', 'BUTTON'),
       (@parentId, '系统模块删除', 'system:systemModule:remove', '4', '', 'BUTTON'),
       (@parentId, '系统模块导出', 'system:systemModule:export', '5', '', 'BUTTON');
insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统岗位', 'system:systemPost:list', 1, 'systemPost', 'system/systemPost', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统岗位查询', 'system:systemPost:query', '1', '', 'BUTTON'),
       (@parentId, '系统岗位新增', 'system:systemPost:add', '2', '', 'BUTTON'),
       (@parentId, '系统岗位修改', 'system:systemPost:edit', '3', '', 'BUTTON'),
       (@parentId, '系统岗位删除', 'system:systemPost:remove', '4', '', 'BUTTON'),
       (@parentId, '系统岗位导出', 'system:systemPost:export', '5', '', 'BUTTON');
insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '系统权限', 'system:systemRole:list', 1, 'systemRole', 'system/systemRole', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '系统权限查询', 'system:systemRole:query', '1', '', 'BUTTON'),
       (@parentId, '系统权限新增', 'system:systemRole:add', '2', '', 'BUTTON'),
       (@parentId, '系统权限修改', 'system:systemRole:edit', '3', '', 'BUTTON'),
       (@parentId, '系统权限删除', 'system:systemRole:remove', '4', '', 'BUTTON'),
       (@parentId, '系统权限导出', 'system:systemRole:export', '5', '', 'BUTTON');
insert into system_menu(parent_id, name, perms_code, sort, path, component, type)
values (1, '用户头像', 'system:systemUserAvatar:list', 1, 'systemUserAvatar', 'system/systemUserAvatar', 'MENU');

SELECT @parentId := LAST_INSERT_ID();

insert into system_menu(parent_id, name, perms_code, sort, icon, type)
values (@parentId, '用户头像查询', 'system:systemUserAvatar:query', '1', '', 'BUTTON'),
       (@parentId, '用户头像新增', 'system:systemUserAvatar:add', '2', '', 'BUTTON'),
       (@parentId, '用户头像修改', 'system:systemUserAvatar:edit', '3', '', 'BUTTON'),
       (@parentId, '用户头像删除', 'system:systemUserAvatar:remove', '4', '', 'BUTTON'),
       (@parentId, '用户头像导出', 'system:systemUserAvatar:export', '5', '', 'BUTTON');