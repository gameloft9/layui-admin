/*
 Navicat Premium Data Transfer

 Source Server         : ccx
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : layui

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 12/06/2018 17:23:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_access_permission_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_access_permission_test`;
CREATE TABLE `sys_access_permission_test`  (
  `ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问权限表',
  `URL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问链接',
  `ROLES` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色列表用,分割',
  `SORT` int(11) NULL DEFAULT NULL COMMENT '排序号',
  `IS_DELETED` int(2) NULL DEFAULT NULL COMMENT '是否删除',
  `CREATE_USER` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `UPDATE_USER` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `uidx_request_url`(`URL`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_access_permission_test
-- ----------------------------
INSERT INTO `sys_access_permission_test` VALUES ('1', '/', 'anon', 1, 0, 'admin', '2017-12-25 16:41:02', 'admin', '2017-12-25 16:41:02');
INSERT INTO `sys_access_permission_test` VALUES ('2', '/index', 'anon', 2, 0, 'admin', '2017-12-25 16:41:08', 'admin', '2017-12-25 16:41:08');
INSERT INTO `sys_access_permission_test` VALUES ('3', '/login', 'anon', 3, 0, 'admin', '2017-12-25 16:41:08', 'admin', '2017-12-25 16:41:08');
INSERT INTO `sys_access_permission_test` VALUES ('4', '/role/*', 'authc,roles[admin]', 5, 0, 'admin', '2017-12-25 16:41:08', 'admin', '2017-12-25 16:41:08');
INSERT INTO `sys_access_permission_test` VALUES ('5', '/getVCode', 'anon', 4, 0, 'admin', '2017-12-25 16:41:09', 'admin', '2017-12-25 16:41:09');
INSERT INTO `sys_access_permission_test` VALUES ('6', '/**/*.do', 'authc', 999, 0, 'admin', '2017-12-25 16:41:09', 'admin', '2017-12-25 16:41:09');
INSERT INTO `sys_access_permission_test` VALUES ('6319f8a7688343139ac4ba0d40877e2c', 'sys/*', 'authc,roles[test,admin]', 201, 0, 'gameloft9', '2017-12-28 11:04:58', NULL, '2017-12-28 11:04:58');
INSERT INTO `sys_access_permission_test` VALUES ('7042e4eeebf5433388ddfdfb09764369', 'log/*', 'authc,roles[test,admin]', 419, 0, 'gameloft9', '2017-12-27 17:04:10', NULL, '2017-12-27 17:04:10');
INSERT INTO `sys_access_permission_test` VALUES ('7c1f21aa63cf4fd6a5be43072e81ab99', 'test/*', 'authc,roles[test,admin]', 135, 0, 'gameloft9', '2017-12-28 11:05:10', NULL, '2017-12-28 11:05:10');
INSERT INTO `sys_access_permission_test` VALUES ('7ee176c20c904536862da37aacb0f7c8', 'org/*', 'authc,roles[test]', 123, 0, 'gameloft9', '2017-12-27 17:04:18', NULL, '2017-12-27 17:04:18');
INSERT INTO `sys_access_permission_test` VALUES ('8e68507e882c4165b06a4a9147d14a34', 'role/*', 'authc,roles[admin]', 669, 0, 'gameloft9', '2017-12-27 17:04:04', NULL, '2017-12-27 17:04:04');
INSERT INTO `sys_access_permission_test` VALUES ('90762decb8874ba0955eb8cef21b6682', 'menu/*', 'authc,roles[admin]', 197, 0, 'gameloft9', '2017-12-28 11:02:26', NULL, '2017-12-28 11:02:26');
INSERT INTO `sys_access_permission_test` VALUES ('c8c4e92a1ece4da79ede1763faecdf77', 'sysUser/*', 'authc,roles[admin]', 170, 0, 'gameloft9', '2017-12-27 17:05:49', NULL, '2017-12-27 17:05:49');

-- ----------------------------
-- Table structure for sys_menu_role_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role_test`;
CREATE TABLE `sys_menu_role_test`  (
  `ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MENU_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单id',
  `ROLE_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `CREATE_USER` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `UPDATE_USER` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `uidx_menu_role`(`MENU_ID`, `ROLE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu_role_test
-- ----------------------------
INSERT INTO `sys_menu_role_test` VALUES ('123456789ijhg', '244247008b53450fa16bc503bc861b7c', 'J/F9-+?', NULL, '2017-12-26 09:46:03', NULL, '2017-12-26 09:46:03');
INSERT INTO `sys_menu_role_test` VALUES ('4523b393aae64771aac4f91c5623599b', '244247008b53450fa16bc503bc861b7c', '343859934ece44c988f53700fb34c80a', NULL, '2018-01-11 14:47:33', NULL, '2018-01-11 14:47:33');
INSERT INTO `sys_menu_role_test` VALUES ('5905556900e741a89aafec25948b8fca', '89e9f1eb41a240fea361e0d188375884', 'J/F9-+?', NULL, '2017-12-27 17:04:18', NULL, '2017-12-27 17:04:18');
INSERT INTO `sys_menu_role_test` VALUES ('5b79d6e436154cfb9533a531fb7eb744', '882b26afbdbb4d63bcba0cd826a5b9c1', '343859934ece44c988f53700fb34c80a', NULL, '2018-01-11 14:47:16', NULL, '2018-01-11 14:47:16');
INSERT INTO `sys_menu_role_test` VALUES ('6302f705514e4c839400f804ec29ec5c', '89e9f1eb41a240fea361e0d188375884', '343859934ece44c988f53700fb34c80a', NULL, '2018-01-11 14:46:55', NULL, '2018-01-11 14:46:55');
INSERT INTO `sys_menu_role_test` VALUES ('71fa522e4da04ab9abd6008d2cb1c070', '26b3df2c6d464a0b89858eb896b849d2', 'J/F9-+?', NULL, '2017-12-27 17:05:49', NULL, '2017-12-27 17:05:49');
INSERT INTO `sys_menu_role_test` VALUES ('874970f9c9b14021909485f201479373', '1688998d013c4ebf95be6fd7e1fecb20', 'J/F9-+?', NULL, '2017-12-28 11:05:10', NULL, '2017-12-28 11:05:10');
INSERT INTO `sys_menu_role_test` VALUES ('asdfr456yhbv123', '4a7f3cae133e4a51b8f35769b55163dd', 'J/F9-+?', NULL, '2017-12-26 09:46:29', NULL, '2017-12-26 09:46:29');
INSERT INTO `sys_menu_role_test` VALUES ('bd3b96f8742940e8acc628ff5c940465', 'b53bfdfe33444703aa76c2c1a1b8d820', 'J/F9-+?', NULL, '2017-12-27 17:04:03', NULL, '2017-12-27 17:04:03');
INSERT INTO `sys_menu_role_test` VALUES ('c89cc73ffa1b40da943cb542b9b9819f', '882b26afbdbb4d63bcba0cd826a5b9c1', 'J/F9-+?', NULL, '2017-12-27 17:04:10', NULL, '2017-12-27 17:04:10');
INSERT INTO `sys_menu_role_test` VALUES ('e92a68532e134b2e9b8af46a43ce61e0', '1688998d013c4ebf95be6fd7e1fecb20', '343859934ece44c988f53700fb34c80a', NULL, '2018-01-11 14:47:42', NULL, '2018-01-11 14:47:42');

-- ----------------------------
-- Table structure for sys_menu_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_test`;
CREATE TABLE `sys_menu_test`  (
  `ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单表',
  `TITLE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `HREF` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问链接',
  `REQUEST_URL` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '后台请求url',
  `ICON` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `CODE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `TARGET` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标窗口,_blank则直接跳转，否则在子页面打开',
  `PARENT_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父菜单',
  `SORT` int(2) NULL DEFAULT NULL COMMENT '排序号',
  `CREATE_USER` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `UPDATE_USER` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `uidx_title`(`TITLE`) USING BTREE,
  UNIQUE INDEX `uidx_menu_req_url`(`REQUEST_URL`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu_test
-- ----------------------------
INSERT INTO `sys_menu_test` VALUES ('1688998d013c4ebf95be6fd7e1fecb20', '测试一级菜单', '/memCard/index.do', 'test/*', 'icon-text', '2', NULL, NULL, 2, 'gameloft9', '2017-12-27 17:09:17', NULL, '2017-12-27 17:09:17');
INSERT INTO `sys_menu_test` VALUES ('244247008b53450fa16bc503bc861b7c', '系统管理', '#', 'sys/*', 'icon-text', '1', NULL, NULL, 1, 'gameloft9', '2017-12-25 19:31:22', NULL, '2017-12-25 19:31:22');
INSERT INTO `sys_menu_test` VALUES ('26b3df2c6d464a0b89858eb896b849d2', '系统用户管理', 'page/system/sysUser/allUsers.html', 'sysUser/*', 'icon-text', '1-5', NULL, '244247008b53450fa16bc503bc861b7c', 4, 'gameloft9', '2017-12-27 17:05:40', NULL, '2017-12-27 17:05:40');
INSERT INTO `sys_menu_test` VALUES ('4a7f3cae133e4a51b8f35769b55163dd', '菜单管理', 'page/system/sysMenu/allMenus.html', 'menu/*', 'icon-text', '1-1', NULL, '244247008b53450fa16bc503bc861b7c', 1, 'gameloft9', '2017-12-25 19:32:25', NULL, '2017-12-25 19:32:25');
INSERT INTO `sys_menu_test` VALUES ('882b26afbdbb4d63bcba0cd826a5b9c1', '系统日志管理', 'page/system/sysLog/allLogs.html', 'log/*', 'icon-text', '1-4', NULL, '244247008b53450fa16bc503bc861b7c', 5, 'gameloft9', '2017-12-27 17:03:41', NULL, '2017-12-27 17:03:41');
INSERT INTO `sys_menu_test` VALUES ('89e9f1eb41a240fea361e0d188375884', '机构管理', 'page/system/sysOrg/allOrgs.html', 'org/*', 'icon-text', '1-2', NULL, '244247008b53450fa16bc503bc861b7c', 3, 'gameloft9', '2017-12-27 17:01:58', NULL, '2017-12-27 17:01:58');
INSERT INTO `sys_menu_test` VALUES ('b53bfdfe33444703aa76c2c1a1b8d820', '角色管理', 'page/system/sysRole/allRoles.html', 'role/*', 'icon-text', '1-3', NULL, '244247008b53450fa16bc503bc861b7c', 2, 'gameloft9', '2017-12-27 17:02:42', NULL, '2017-12-27 17:02:42');

-- ----------------------------
-- Table structure for sys_oper_log_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log_test`;
CREATE TABLE `sys_oper_log_test`  (
  `ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用日志表',
  `USER_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LOGIN_NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IP_ADDR` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `OPER_TYPE` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATE_DATE` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `MEMO` varchar(1500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_organize_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_organize_test`;
CREATE TABLE `sys_organize_test`  (
  `ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织机构表',
  `PARENT_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ORGANIZE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构名称',
  `ORGANIZE_CODE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构编码',
  `TREE_LEVEL` int(11) NOT NULL COMMENT '机构级别',
  `TREE_PATH` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构路径',
  `CREATE_DATE` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `uidx_org_name`(`ORGANIZE_NAME`) USING BTREE,
  UNIQUE INDEX `uidx_org_code`(`ORGANIZE_CODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_organize_test
-- ----------------------------
INSERT INTO `sys_organize_test` VALUES ('21a6ff8a6abf410eaf97981c1da039d8', NULL, '总公司', '010001', 1, '21a6ff8a6abf410eaf97981c1da039d8', '2017-12-27 17:06:04', '2017-12-27 17:06:04');
INSERT INTO `sys_organize_test` VALUES ('54fa67f3d443427c9f47432ead20f2f4', '21a6ff8a6abf410eaf97981c1da039d8', '湖北分公司', '010002', 2, '21a6ff8a6abf410eaf97981c1da039d8#54fa67f3d443427c9f47432ead20f2f4', '2017-12-27 17:06:22', '2017-12-27 17:06:22');

-- ----------------------------
-- Table structure for sys_role_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_test`;
CREATE TABLE `sys_role_test`  (
  `ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色表',
  `ROLE_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `IS_SUPER` int(2) NULL DEFAULT NULL COMMENT '是否是超级管理员',
  `IS_DELETED` int(2) NULL DEFAULT NULL COMMENT '是否删除',
  `CREATE_USER` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `UPDATE_USER` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_test
-- ----------------------------
INSERT INTO `sys_role_test` VALUES ('343859934ece44c988f53700fb34c80a', 'test', 0, 0, 'gameloft9', '2018-01-11 14:46:22', NULL, '2018-01-11 14:46:22');
INSERT INTO `sys_role_test` VALUES ('J/F9-+?', 'admin', 1, 0, 'v[l_4zL8', '2008-03-01 04:17:00', ':9He2+GW', '2002-09-27 03:13:16');

-- ----------------------------
-- Table structure for sys_user_role_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_test`;
CREATE TABLE `sys_user_role_test`  (
  `ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户角色表',
  `USER_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role_test
-- ----------------------------
INSERT INTO `sys_user_role_test` VALUES ('03f9afb1d42e49d6a026f537d9f033b6', '9dbdc3a6cc444190bb5ac4e15df6234b', '343859934ece44c988f53700fb34c80a');
INSERT INTO `sys_user_role_test` VALUES ('2fb2a9fb965e462eb72c14361a83f006', '123', 'J/F9-+?');

-- ----------------------------
-- Table structure for user_test
-- ----------------------------
DROP TABLE IF EXISTS `user_test`;
CREATE TABLE `user_test`  (
  `ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户测试表',
  `LOGIN_NAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PASSWORD` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REAL_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_FORBIDDEN` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MOBILE` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ICON` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ORG_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ORG_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATE_DATE` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `uidx_login_name`(`LOGIN_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_test
-- ----------------------------
INSERT INTO `user_test` VALUES ('123', 'gameloft9', '7C4A8D09CA3762AF61E59520943DC26494F8941B', 'leiyao', '0', '13618629441', NULL, 'gameloft9', '总公司', '2017-12-28 16:24:32', '2017-12-28 16:32:57');

SET FOREIGN_KEY_CHECKS = 1;
