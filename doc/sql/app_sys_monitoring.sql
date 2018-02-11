-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.31 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2018-02-11 15:13:41
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping structure for table app_sys_monitoring.sys_log
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE IF NOT EXISTS `sys_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_name` varchar(100) DEFAULT NULL,
  `sys_code` varchar(100) DEFAULT NULL,
  `service_name` varchar(100) DEFAULT NULL COMMENT '提供服务应用名称',
  `service_code` varchar(100) DEFAULT NULL COMMENT '提供服务应用代码',
  `method_name` varchar(100) DEFAULT NULL,
  `request_data` varchar(2000) DEFAULT NULL,
  `response_data` varchar(10000) DEFAULT NULL,
  `log_type` tinyint(4) DEFAULT NULL COMMENT '-1:异常',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志记录表';

-- Data exporting was unselected.


-- Dumping structure for table app_sys_monitoring.sys_notification
DROP TABLE IF EXISTS `sys_notification`;
CREATE TABLE IF NOT EXISTS `sys_notification` (
  `nt_id` int(10) NOT NULL AUTO_INCREMENT,
  `sys_name` varchar(100) NOT NULL,
  `sys_code` varchar(100) NOT NULL,
  `service_name` varchar(100) NOT NULL COMMENT '提供服务应用名称',
  `service_code` varchar(100) NOT NULL COMMENT '提供服务应用代码',
  `nct_name` varchar(100) NOT NULL COMMENT '推送名称',
  `nct_type` tinyint(4) NOT NULL COMMENT '0 : 短信；1：email；2：微信；3：支付宝；4：钉钉；5：三方推送；6：自定义推送',
  `nct_uri` varchar(500) NOT NULL COMMENT '通知的地址',
  `nct_parameters` varchar(500) NOT NULL COMMENT '用于认证的参数采用 key=value&key1=value1 方式',
  `nct_status` tinyint(4) NOT NULL COMMENT '状态 0 ：禁用；1 可用',
  `update_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`nt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统业务状态通知';

-- Data exporting was unselected.
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
