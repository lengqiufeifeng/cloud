-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.31 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2018-02-11 15:13:59
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping structure for table app_sys_user.ava_resource
DROP TABLE IF EXISTS `ava_resource`;
CREATE TABLE IF NOT EXISTS `ava_resource` (
  `resource_id` varchar(50) NOT NULL COMMENT '资源表主键',
  `resource_name` varchar(100) NOT NULL COMMENT '资源名称',
  `resource_url` varchar(200) NOT NULL COMMENT '资源菜单链接',
  `icon` varchar(1000) DEFAULT NULL COMMENT '资源图标链接',
  `resource_pid` varchar(1000) DEFAULT NULL COMMENT '父节点',
  `resource_key` varchar(50) DEFAULT '0' COMMENT '排序',
  `resource_type` varchar(2) DEFAULT NULL COMMENT '资源类型 0：目录 1：菜单 2：按钮',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `style` varchar(100) DEFAULT NULL COMMENT '样式',
  `create_by` varchar(50) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '最近修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

-- Data exporting was unselected.


-- Dumping structure for table app_sys_user.ava_role
DROP TABLE IF EXISTS `ava_role`;
CREATE TABLE IF NOT EXISTS `ava_role` (
  `role_id` varchar(50) NOT NULL COMMENT '角色表主键',
  `role_code` varchar(50) DEFAULT NULL COMMENT '角色代码',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`),
  KEY `role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- Data exporting was unselected.


-- Dumping structure for table app_sys_user.ava_role_resource
DROP TABLE IF EXISTS `ava_role_resource`;
CREATE TABLE IF NOT EXISTS `ava_role_resource` (
  `role_resource_id` varchar(50) NOT NULL COMMENT '角色权限表主键',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色表主键',
  `resource_id` varchar(50) DEFAULT NULL COMMENT '资源表主键',
  `create_by` varchar(50) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- Data exporting was unselected.


-- Dumping structure for table app_sys_user.ava_user_role
DROP TABLE IF EXISTS `ava_user_role`;
CREATE TABLE IF NOT EXISTS `ava_user_role` (
  `user_role_id` varchar(64) NOT NULL COMMENT '用户角色表主键',
  `user_id` varchar(50) NOT NULL COMMENT '用户表主键',
  `role_id` varchar(50) NOT NULL COMMENT '角色表主键',
  `create_by` varchar(50) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_role_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- Data exporting was unselected.


-- Dumping structure for table app_sys_user.sys_user
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user` (
  `user_id` varchar(50) NOT NULL COMMENT '用户表主键',
  `org_id` varchar(50) DEFAULT NULL COMMENT '组织机构表主键',
  `user_code` varchar(50) DEFAULT NULL COMMENT '用户登录ID',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `tel` varchar(50) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` varchar(50) DEFAULT NULL COMMENT '用户状态 1:生效 0:失效',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  KEY `user_code` (`user_code`),
  KEY `email` (`email`),
  KEY `tel` (`tel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- Data exporting was unselected.
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
