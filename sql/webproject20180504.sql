/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : webproject

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2018-05-04 18:35:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT '0',
  `permission_name` varchar(30) NOT NULL,
  `permission_value` varchar(100) DEFAULT NULL,
  `permission_icon` varchar(10) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('4', '0', '员工信息管理', 'admin/employee/employee', '&#xe612;', '2018-04-29 23:28:28', '2018-05-01 16:00:08');
INSERT INTO `permission` VALUES ('5', '0', '二手档案信息管理', 'admin/phone/phoneinfo', '&#xe60a;', '2018-05-01 21:43:37', '2018-05-01 23:17:04');
INSERT INTO `permission` VALUES ('6', '0', '二手手机维修管理', 'admin/repair/repair', '&#xe631;', '2018-05-02 18:50:04', null);

-- ----------------------------
-- Table structure for phone_brand
-- ----------------------------
DROP TABLE IF EXISTS `phone_brand`;
CREATE TABLE `phone_brand` (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(30) NOT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phone_brand
-- ----------------------------
INSERT INTO `phone_brand` VALUES ('1', '苹果');
INSERT INTO `phone_brand` VALUES ('4', '三星');
INSERT INTO `phone_brand` VALUES ('5', '华为');
INSERT INTO `phone_brand` VALUES ('7', '摩托罗拉');
INSERT INTO `phone_brand` VALUES ('8', '诺基亚');

-- ----------------------------
-- Table structure for phone_color
-- ----------------------------
DROP TABLE IF EXISTS `phone_color`;
CREATE TABLE `phone_color` (
  `color_id` int(11) NOT NULL AUTO_INCREMENT,
  `color_name` varchar(30) NOT NULL,
  `model_id` int(11) NOT NULL,
  PRIMARY KEY (`color_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phone_color
-- ----------------------------
INSERT INTO `phone_color` VALUES ('1', '白色', '1');
INSERT INTO `phone_color` VALUES ('2', '黑色', '1');
INSERT INTO `phone_color` VALUES ('3', '白色', '2');
INSERT INTO `phone_color` VALUES ('4', '银黑色', '2');
INSERT INTO `phone_color` VALUES ('5', '亮黑色', '2');
INSERT INTO `phone_color` VALUES ('6', '白色', '3');
INSERT INTO `phone_color` VALUES ('7', '亮黑色', '3');
INSERT INTO `phone_color` VALUES ('8', '银黑色', '3');
INSERT INTO `phone_color` VALUES ('9', '磨砂黑', '3');
INSERT INTO `phone_color` VALUES ('10', '土豪金', '3');
INSERT INTO `phone_color` VALUES ('11', '白色', '4');
INSERT INTO `phone_color` VALUES ('12', '亮黑色', '4');
INSERT INTO `phone_color` VALUES ('13', '玫瑰色', '4');
INSERT INTO `phone_color` VALUES ('14', '磨砂黑', '4');
INSERT INTO `phone_color` VALUES ('15', '白色', '5');
INSERT INTO `phone_color` VALUES ('16', '红色', '5');
INSERT INTO `phone_color` VALUES ('17', '磨砂黑', '5');
INSERT INTO `phone_color` VALUES ('18', '亮黑色', '5');
INSERT INTO `phone_color` VALUES ('19', '白色', '6');
INSERT INTO `phone_color` VALUES ('20', '黑色', '6');
INSERT INTO `phone_color` VALUES ('21', '磨砂黑', '6');
INSERT INTO `phone_color` VALUES ('22', '亮黑色', '6');
INSERT INTO `phone_color` VALUES ('23', '银黑色', '6');
INSERT INTO `phone_color` VALUES ('24', '白色', '9');

-- ----------------------------
-- Table structure for phone_info
-- ----------------------------
DROP TABLE IF EXISTS `phone_info`;
CREATE TABLE `phone_info` (
  `phone_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '手机主键',
  `IMEI` varchar(17) NOT NULL COMMENT '手机编号',
  `brand_id` int(11) NOT NULL COMMENT '品牌',
  `model_id` int(11) NOT NULL COMMENT '型号',
  `color_id` int(11) NOT NULL COMMENT '颜色',
  `memory_id` int(11) NOT NULL COMMENT '内存',
  `degree` char(6) NOT NULL COMMENT '损坏程度',
  `protection` char(2) NOT NULL COMMENT '是否在保',
  `damaged_part` varchar(50) NOT NULL COMMENT '损坏部位',
  `state` char(8) NOT NULL COMMENT '状态 1：待翻新 2：翻新中 3：已上架 4：已下架',
  `recovery_price` decimal(10,2) NOT NULL COMMENT '回收价',
  `reference_selling_price` decimal(10,2) NOT NULL COMMENT '参考销售价',
  `selling_id` char(20) DEFAULT NULL COMMENT '销售明细编号',
  PRIMARY KEY (`phone_id`),
  UNIQUE KEY `IMEI` (`IMEI`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='二手手机档案信息表';

-- ----------------------------
-- Records of phone_info
-- ----------------------------
INSERT INTO `phone_info` VALUES ('1', '321354658786712', '1', '3', '10', '6', '八成新', '1', 'HOME键损坏', '1', '3500.00', '4000.00', null);
INSERT INTO `phone_info` VALUES ('2', '6543157676874654', '1', '2', '4', '4', '九成新', '1', '屏幕有一点裂痕', '1', '2000.00', '2500.00', null);

-- ----------------------------
-- Table structure for phone_memory
-- ----------------------------
DROP TABLE IF EXISTS `phone_memory`;
CREATE TABLE `phone_memory` (
  `memory_id` int(11) NOT NULL AUTO_INCREMENT,
  `memory_name` varchar(30) NOT NULL,
  `model_id` int(11) NOT NULL,
  PRIMARY KEY (`memory_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phone_memory
-- ----------------------------
INSERT INTO `phone_memory` VALUES ('1', '32GB', '1');
INSERT INTO `phone_memory` VALUES ('2', '64GB', '1');
INSERT INTO `phone_memory` VALUES ('3', '64GB', '2');
INSERT INTO `phone_memory` VALUES ('4', '128GB', '2');
INSERT INTO `phone_memory` VALUES ('5', '64GB', '3');
INSERT INTO `phone_memory` VALUES ('6', '128GB', '3');
INSERT INTO `phone_memory` VALUES ('7', '64GB', '4');
INSERT INTO `phone_memory` VALUES ('8', '128GB', '4');
INSERT INTO `phone_memory` VALUES ('9', '64GB', '5');
INSERT INTO `phone_memory` VALUES ('10', '128GB', '5');
INSERT INTO `phone_memory` VALUES ('11', '64GB', '6');
INSERT INTO `phone_memory` VALUES ('12', '128GB', '6');
INSERT INTO `phone_memory` VALUES ('13', '64GB', '9');

-- ----------------------------
-- Table structure for phone_model
-- ----------------------------
DROP TABLE IF EXISTS `phone_model`;
CREATE TABLE `phone_model` (
  `model_id` int(11) NOT NULL AUTO_INCREMENT,
  `model_name` varchar(30) NOT NULL,
  `brand_id` int(11) NOT NULL,
  PRIMARY KEY (`model_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phone_model
-- ----------------------------
INSERT INTO `phone_model` VALUES ('1', 'iphone 6', '1');
INSERT INTO `phone_model` VALUES ('2', 'iphone 6s', '1');
INSERT INTO `phone_model` VALUES ('3', 'iphone 7 ', '1');
INSERT INTO `phone_model` VALUES ('4', 'iphone 7s', '1');
INSERT INTO `phone_model` VALUES ('5', 'iphone 8', '1');
INSERT INTO `phone_model` VALUES ('6', 'iphone x', '1');
INSERT INTO `phone_model` VALUES ('7', 'NOTE7', '4');
INSERT INTO `phone_model` VALUES ('8', 'NOTE5', '4');
INSERT INTO `phone_model` VALUES ('9', 'NOTE6', '4');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair` (
  `repair_order_number` char(10) NOT NULL COMMENT '维修单号',
  `IMEI` varchar(15) NOT NULL COMMENT '手机编号',
  `maintenance_project` varchar(50) NOT NULL COMMENT '维修项目',
  `amount_complained` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '投入金额',
  PRIMARY KEY (`repair_order_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维修情况表';

-- ----------------------------
-- Records of repair
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '2018-04-28 18:23:33');
INSERT INTO `role` VALUES ('2', '管理员', '2018-04-30 00:47:50');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '4');
INSERT INTO `role_permission` VALUES ('1', '5');
INSERT INTO `role_permission` VALUES ('2', '5');
INSERT INTO `role_permission` VALUES ('1', '6');
INSERT INTO `role_permission` VALUES ('2', '6');

-- ----------------------------
-- Table structure for selling_detail
-- ----------------------------
DROP TABLE IF EXISTS `selling_detail`;
CREATE TABLE `selling_detail` (
  `selling_id` char(20) NOT NULL,
  `phone_id` int(11) NOT NULL,
  `serial_number` char(20) NOT NULL,
  `transaction_price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`selling_id`),
  KEY `serial_number` (`serial_number`),
  KEY `phone_id` (`phone_id`),
  CONSTRAINT `selling_detail_ibfk_2` FOREIGN KEY (`serial_number`) REFERENCES `sell_slip` (`serial_number`),
  CONSTRAINT `selling_detail_ibfk_3` FOREIGN KEY (`phone_id`) REFERENCES `phone_info` (`phone_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='销售明细表';

-- ----------------------------
-- Records of selling_detail
-- ----------------------------

-- ----------------------------
-- Table structure for sell_slip
-- ----------------------------
DROP TABLE IF EXISTS `sell_slip`;
CREATE TABLE `sell_slip` (
  `serial_number` varchar(20) NOT NULL COMMENT '销售单号',
  `operator_id` int(11) NOT NULL COMMENT '经办人编号',
  `sale_date` datetime NOT NULL COMMENT '销售日期',
  `sale_number` int(11) NOT NULL DEFAULT '0' COMMENT '销售数量',
  `total_price` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '总价',
  PRIMARY KEY (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sell_slip
-- ----------------------------

-- ----------------------------
-- Table structure for stock_info
-- ----------------------------
DROP TABLE IF EXISTS `stock_info`;
CREATE TABLE `stock_info` (
  `stock_id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_id` int(11) NOT NULL COMMENT '品牌id',
  `color_id` int(11) NOT NULL COMMENT '颜色id',
  `model_id` int(11) NOT NULL COMMENT '型号id',
  `memory_id` int(11) NOT NULL COMMENT '内存id',
  `number` int(11) NOT NULL DEFAULT '1' COMMENT '数量',
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='库存信息表';

-- ----------------------------
-- Records of stock_info
-- ----------------------------
INSERT INTO `stock_info` VALUES ('8', '1', '4', '2', '4', '1');
INSERT INTO `stock_info` VALUES ('9', '1', '10', '3', '6', '1');
INSERT INTO `stock_info` VALUES ('16', '1', '5', '2', '4', '0');
INSERT INTO `stock_info` VALUES ('17', '1', '3', '2', '4', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `real_name` varchar(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '2018-04-28 18:23:19', '2018-05-02 00:07:03');
INSERT INTO `user` VALUES ('2', 'employee1', 'e10adc3949ba59abbe56e057f20f883e', '张四', '2018-05-01 17:25:03', '2018-05-01 17:40:37');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '2');
