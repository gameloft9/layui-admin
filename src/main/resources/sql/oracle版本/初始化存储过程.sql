create or replace procedure prc_init_db
as

begin
   /*
   清空表
   */
   delete from   sys_access_permission_test;
   delete from   sys_menu_role_test;
   delete from   sys_menu_test;
   delete from   sys_oper_log_test;
   delete from   sys_organize_test;
   delete from   sys_role_test;
   delete from   sys_user_role_test;
   delete from   user_test;

   /*初始化数据-user_test*/
   insert into user_test(ID,LOGIN_NAME,PASSWORD,REAL_NAME,IS_FORBIDDEN,MOBILE,ICON,ORG_ID,ORG_NAME,CREATE_DATE,UPDATE_DATE)
values('123','gameloft9','7C4A8D09CA3762AF61E59520943DC26494F8941B','leiyao','0','13618629441',null,'gameloft9','总公司',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));

/*初始化数据-sys_access_permission_test*/
insert into sys_access_permission_test(ID,URL,ROLES,SORT,IS_DELETED,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values
('c8c4e92a1ece4da79ede1763faecdf77','sysUser/*','authc,roles[admin]','170','0','gameloft9',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_access_permission_test(ID,URL,ROLES,SORT,IS_DELETED,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('90762decb8874ba0955eb8cef21b6682','/menu/**','authc,roles[admin]','197','0','gameloft9',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_access_permission_test(ID,URL,ROLES,SORT,IS_DELETED,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('7ee176c20c904536862da37aacb0f7c8','/org/**','authc,roles[test]','123','0','gameloft9',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_access_permission_test(ID,URL,ROLES,SORT,IS_DELETED,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('7c1f21aa63cf4fd6a5be43072e81ab99','/test/**','authc,roleOr[test,admin]','135','0','gameloft9',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_access_permission_test(ID,URL,ROLES,SORT,IS_DELETED,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('7042e4eeebf5433388ddfdfb09764369','/log/**','authc,roleOr[test,admin]','419','0','gameloft9',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_access_permission_test(ID,URL,ROLES,SORT,IS_DELETED,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('6319f8a7688343139ac4ba0d40877e2c','/sys/**','authc,roleOr[test,admin]','201','0','gameloft9',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_access_permission_test(ID,URL,ROLES,SORT,IS_DELETED,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('6','/**/*.do','authc','999','0','admin',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),'admin',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_access_permission_test(ID,URL,ROLES,SORT,IS_DELETED,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('5','/getVCode','anon','4','0','admin',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),'admin',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_access_permission_test(ID,URL,ROLES,SORT,IS_DELETED,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('4','/role/**','authc,roles[admin]','5','0','admin',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),'admin',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_access_permission_test(ID,URL,ROLES,SORT,IS_DELETED,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('3','/login','anon','3','0','admin',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),'admin',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_access_permission_test(ID,URL,ROLES,SORT,IS_DELETED,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('2','/index','anon','2','0','admin',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),'admin',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_access_permission_test(ID,URL,ROLES,SORT,IS_DELETED,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('1','/','anon','1','0','admin',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),'admin',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));

/*初始化数据-sys_menu_role_test*/
insert into sys_menu_role_test(ID,MENU_ID,ROLE_ID,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values
('e92a68532e134b2e9b8af46a43ce61e0','1688998d013c4ebf95be6fd7e1fecb20','343859934ece44c988f53700fb34c80a',null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_menu_role_test(ID,MENU_ID,ROLE_ID,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('c89cc73ffa1b40da943cb542b9b9819f','882b26afbdbb4d63bcba0cd826a5b9c1','J/F9-+?',null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_menu_role_test(ID,MENU_ID,ROLE_ID,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('bd3b96f8742940e8acc628ff5c940465','b53bfdfe33444703aa76c2c1a1b8d820','J/F9-+?',null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_menu_role_test(ID,MENU_ID,ROLE_ID,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('asdfr456yhbv123','4a7f3cae133e4a51b8f35769b55163dd','J/F9-+?',null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_menu_role_test(ID,MENU_ID,ROLE_ID,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('874970f9c9b14021909485f201479373','1688998d013c4ebf95be6fd7e1fecb20','J/F9-+?',null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_menu_role_test(ID,MENU_ID,ROLE_ID,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('71fa522e4da04ab9abd6008d2cb1c070','26b3df2c6d464a0b89858eb896b849d2','J/F9-+?',null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_menu_role_test(ID,MENU_ID,ROLE_ID,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('6302f705514e4c839400f804ec29ec5c','89e9f1eb41a240fea361e0d188375884','343859934ece44c988f53700fb34c80a',null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_menu_role_test(ID,MENU_ID,ROLE_ID,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('5b79d6e436154cfb9533a531fb7eb744','882b26afbdbb4d63bcba0cd826a5b9c1','343859934ece44c988f53700fb34c80a',null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_menu_role_test(ID,MENU_ID,ROLE_ID,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('5905556900e741a89aafec25948b8fca','89e9f1eb41a240fea361e0d188375884','J/F9-+?',null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_menu_role_test(ID,MENU_ID,ROLE_ID,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('4523b393aae64771aac4f91c5623599b','244247008b53450fa16bc503bc861b7c','343859934ece44c988f53700fb34c80a',null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_menu_role_test(ID,MENU_ID,ROLE_ID,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('123456789ijhg','244247008b53450fa16bc503bc861b7c','J/F9-+?',null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));

/*初始化数据-sys_menu_test*/
insert into sys_menu_test(ID,TITLE,HREF,REQUEST_URL,ICON,CODE,TARGET,PARENT_ID,SORT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('b53bfdfe33444703aa76c2c1a1b8d820','角色管理','page/system/sysRole/allRoles.html','/role/**','icon-text','1-3',null,'244247008b53450fa16bc503bc861b7c','2','gameloft9',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_menu_test(ID,TITLE,HREF,REQUEST_URL,ICON,CODE,TARGET,PARENT_ID,SORT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('89e9f1eb41a240fea361e0d188375884','机构管理','page/system/sysOrg/allOrgs.html','/org/**','icon-text','1-2',null,'244247008b53450fa16bc503bc861b7c','3','gameloft9',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_menu_test(ID,TITLE,HREF,REQUEST_URL,ICON,CODE,TARGET,PARENT_ID,SORT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('882b26afbdbb4d63bcba0cd826a5b9c1','系统日志管理','page/system/sysLog/allLogs.html','/log/**','icon-text','1-4',null,'244247008b53450fa16bc503bc861b7c','5','gameloft9',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_menu_test(ID,TITLE,HREF,REQUEST_URL,ICON,CODE,TARGET,PARENT_ID,SORT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('4a7f3cae133e4a51b8f35769b55163dd','菜单管理','page/system/sysMenu/allMenus.html','/menu/**','icon-text','1-1',null,'244247008b53450fa16bc503bc861b7c','1','gameloft9',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_menu_test(ID,TITLE,HREF,REQUEST_URL,ICON,CODE,TARGET,PARENT_ID,SORT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('26b3df2c6d464a0b89858eb896b849d2','系统用户管理','page/system/sysUser/allUsers.html','/sysUser/**','icon-text','1-5',null,'244247008b53450fa16bc503bc861b7c','4','gameloft9',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_menu_test(ID,TITLE,HREF,REQUEST_URL,ICON,CODE,TARGET,PARENT_ID,SORT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('244247008b53450fa16bc503bc861b7c','系统管理','#','/sys/**','icon-text','1',null,null,'1','gameloft9',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_menu_test(ID,TITLE,HREF,REQUEST_URL,ICON,CODE,TARGET,PARENT_ID,SORT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values('1688998d013c4ebf95be6fd7e1fecb20','测试一级菜单','/memCard/index.do','test/*','icon-text','2',null,null,'2','gameloft9',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));

/*初始化数据-sys_organize_test*/
insert into sys_organize_test(ID,PARENT_ID,ORGANIZE_NAME,ORGANIZE_CODE,TREE_LEVEL,TREE_PATH,CREATE_DATE,UPDATE_DATE) values
('54fa67f3d443427c9f47432ead20f2f4','21a6ff8a6abf410eaf97981c1da039d8','湖北分公司','010002','2','21a6ff8a6abf410eaf97981c1da039d8#54fa67f3d443427c9f47432ead20f2f4',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_organize_test(ID,PARENT_ID,ORGANIZE_NAME,ORGANIZE_CODE,TREE_LEVEL,TREE_PATH,CREATE_DATE,UPDATE_DATE) values
('21a6ff8a6abf410eaf97981c1da039d8',null,'总公司','010001','1','21a6ff8a6abf410eaf97981c1da039d8',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));

/*初始化数据-sys_role_test*/
insert into sys_role_test(ID,ROLE_NAME,IS_SUPER,IS_DELETED,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values
('J/F9-+?','admin','1','0','v[l_4zL8',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),':9He2+GW',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));
insert into sys_role_test(ID,ROLE_NAME,IS_SUPER,IS_DELETED,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values
('343859934ece44c988f53700fb34c80a','test','0','0','gameloft9',to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'),null,to_date('2017-12-28 16:24:32','yyyy-MM-dd HH24:mi:ss'));

/*初始化数据-sys_user_role_test*/
insert into sys_user_role_test(ID,USER_ID,ROLE_ID) values
('2fb2a9fb965e462eb72c14361a83f006','123','J/F9-+?');
insert into sys_user_role_test(ID,USER_ID,ROLE_ID) values
('03f9afb1d42e49d6a026f537d9f033b6','9dbdc3a6cc444190bb5ac4e15df6234b','343859934ece44c988f53700fb34c80a');

end;






