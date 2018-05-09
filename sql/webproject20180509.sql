/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : webproject

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2018-05-09 19:00:46
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
  `order` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('4', '0', '员工信息管理', 'admin/employee/employee', '&#xe612;', '2018-04-29 23:28:28', '2018-05-08 16:16:28', '6');
INSERT INTO `permission` VALUES ('5', '0', '手机档案信息管理', 'admin/phone/phoneinfo', '&#xe60a;', '2018-05-01 21:43:37', '2018-05-08 16:16:08', '1');
INSERT INTO `permission` VALUES ('6', '0', '二手手机维修管理', 'admin/repair/repair', '&#xe631;', '2018-05-02 18:50:04', '2018-05-08 16:16:18', '2');
INSERT INTO `permission` VALUES ('7', '0', '库存管理', 'admin/stock/stock', '&#xe7a0;', '2018-05-05 15:33:26', '2018-05-08 16:16:23', '3');
INSERT INTO `permission` VALUES ('8', '0', '销售管理', 'admin/sell/sell', '&#xe65e;', '2018-05-05 16:17:09', '2018-05-08 16:16:26', '4');
INSERT INTO `permission` VALUES ('9', '0', '历史记录查询', null, '&#xe63c;', '2018-05-06 16:38:08', '2018-05-08 16:16:27', '5');
INSERT INTO `permission` VALUES ('10', '9', '回收记录查询', 'admin/history/recycleHistory', null, '2018-05-06 16:38:29', '2018-05-06 17:52:11', null);
INSERT INTO `permission` VALUES ('11', '9', '销售记录查询', 'admin/history/sellHistory', null, '2018-05-06 16:39:02', '2018-05-06 16:39:16', null);
INSERT INTO `permission` VALUES ('12', '0', '前台图片管理', 'admin/picture/frontPicture', '&#xe64a;', '2018-05-07 20:36:47', '2018-05-08 16:16:29', '7');

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

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
INSERT INTO `phone_color` VALUES ('15', '白色', '5');
INSERT INTO `phone_color` VALUES ('16', '红色', '5');
INSERT INTO `phone_color` VALUES ('17', '磨砂黑', '5');
INSERT INTO `phone_color` VALUES ('18', '亮黑色', '5');
INSERT INTO `phone_color` VALUES ('19', '白色', '6');
INSERT INTO `phone_color` VALUES ('20', '黑色', '6');
INSERT INTO `phone_color` VALUES ('21', '磨砂黑', '6');
INSERT INTO `phone_color` VALUES ('22', '亮黑色', '6');
INSERT INTO `phone_color` VALUES ('23', '银黑色', '6');
INSERT INTO `phone_color` VALUES ('25', '白色', '7');
INSERT INTO `phone_color` VALUES ('26', '蓝色', '10');
INSERT INTO `phone_color` VALUES ('27', '摩卡金', '11');
INSERT INTO `phone_color` VALUES ('28', '黑色', '12');

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
  `img` varchar(100) DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`phone_id`),
  UNIQUE KEY `IMEI` (`IMEI`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='二手手机档案信息表';

-- ----------------------------
-- Records of phone_info
-- ----------------------------
INSERT INTO `phone_info` VALUES ('10', '13413453452345345', '1', '1', '1', '2', '八成新', '1', '屏幕', '3', '2000.00', '3000.00', null, '2yqVk9ru1.jpg');
INSERT INTO `phone_info` VALUES ('11', '1345234523452343', '4', '7', '25', '15', '九成新', '1', '无', '3', '3500.00', '4500.00', null, 'SzXeX0uF2.jpg');
INSERT INTO `phone_info` VALUES ('12', '3123412341234213', '5', '11', '27', '17', '九成新', '1', '无', '3', '2000.00', '3000.00', null, 'eD6vp97q3.jpg');
INSERT INTO `phone_info` VALUES ('13', '12334145324523433', '7', '12', '28', '18', '八成新', '1', '屏幕', '3', '2000.00', '3000.00', null, 'RTbDQD4F4.jpg');
INSERT INTO `phone_info` VALUES ('14', '5463563452134242', '8', '10', '26', '16', '一成新', '1', '无', '3', '100.00', '200.00', null, 'ZjJGIABU5.jpg');
INSERT INTO `phone_info` VALUES ('15', '5422634553242345', '1', '1', '1', '1', '九成新', '1', '无', '3', '2000.00', '2500.00', null, 'FBhJGTYf7.jpg');

-- ----------------------------
-- Table structure for phone_memory
-- ----------------------------
DROP TABLE IF EXISTS `phone_memory`;
CREATE TABLE `phone_memory` (
  `memory_id` int(11) NOT NULL AUTO_INCREMENT,
  `memory_name` varchar(30) NOT NULL,
  `model_id` int(11) NOT NULL,
  PRIMARY KEY (`memory_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phone_memory
-- ----------------------------
INSERT INTO `phone_memory` VALUES ('1', '32GB', '1');
INSERT INTO `phone_memory` VALUES ('2', '64GB', '1');
INSERT INTO `phone_memory` VALUES ('3', '64GB', '2');
INSERT INTO `phone_memory` VALUES ('4', '128GB', '2');
INSERT INTO `phone_memory` VALUES ('5', '64GB', '3');
INSERT INTO `phone_memory` VALUES ('6', '128GB', '3');
INSERT INTO `phone_memory` VALUES ('9', '64GB', '5');
INSERT INTO `phone_memory` VALUES ('10', '128GB', '5');
INSERT INTO `phone_memory` VALUES ('11', '64GB', '6');
INSERT INTO `phone_memory` VALUES ('12', '128GB', '6');
INSERT INTO `phone_memory` VALUES ('14', '64GB', '7');
INSERT INTO `phone_memory` VALUES ('15', '128GB', '7');
INSERT INTO `phone_memory` VALUES ('16', '1GB', '10');
INSERT INTO `phone_memory` VALUES ('17', '64GB', '11');
INSERT INTO `phone_memory` VALUES ('18', '64GB', '12');

-- ----------------------------
-- Table structure for phone_model
-- ----------------------------
DROP TABLE IF EXISTS `phone_model`;
CREATE TABLE `phone_model` (
  `model_id` int(11) NOT NULL AUTO_INCREMENT,
  `model_name` varchar(30) NOT NULL,
  `brand_id` int(11) NOT NULL,
  `img` varchar(100) DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`model_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phone_model
-- ----------------------------
INSERT INTO `phone_model` VALUES ('1', 'iphone 6', '1', 'aclgNuAjg.jpg');
INSERT INTO `phone_model` VALUES ('2', 'iphone 6s', '1', 'vuTjFe7as.jpg');
INSERT INTO `phone_model` VALUES ('3', 'iphone 7 ', '1', 'gz9wkm9H7.jpg');
INSERT INTO `phone_model` VALUES ('5', 'iphone 8', '1', '6uPEPsMO8.jpg');
INSERT INTO `phone_model` VALUES ('6', 'iphone x', '1', 'emYOoTkex.jpg');
INSERT INTO `phone_model` VALUES ('7', 'NOTE7', '4', 'TXB6I23B7.jpg');
INSERT INTO `phone_model` VALUES ('8', 'NOTE5', '4', 'tQoAw7Xu5.jpg');
INSERT INTO `phone_model` VALUES ('10', 'E72', '8', 'eiuyHcJT2.jpg');
INSERT INTO `phone_model` VALUES ('11', 'Mate 10 pro', '5', 'nYNolf7P.jpg');
INSERT INTO `phone_model` VALUES ('12', 'MotoZ2', '7', 'dzRTc6fo2.jpg');

-- ----------------------------
-- Table structure for recycle_log
-- ----------------------------
DROP TABLE IF EXISTS `recycle_log`;
CREATE TABLE `recycle_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial_number` char(20) NOT NULL COMMENT '流水号',
  `imei` varchar(17) NOT NULL COMMENT '手机编号',
  `recycle_date` datetime NOT NULL COMMENT '回收日期',
  `brand_name` varchar(20) NOT NULL COMMENT '品牌名',
  `model_name` varchar(20) NOT NULL COMMENT '型号名',
  `color_name` varchar(20) NOT NULL COMMENT '颜色名',
  `memory_name` varchar(20) NOT NULL COMMENT '内存名',
  `recycle_price` decimal(10,0) NOT NULL COMMENT '回收价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recycle_log
-- ----------------------------
INSERT INTO `recycle_log` VALUES ('6', 'H7725142949517805295', '13413453452345345', '2018-05-09 18:36:44', '苹果', 'iphone 6', '白色', '64GB', '2000');
INSERT INTO `recycle_log` VALUES ('7', 'H6901277808506453614', '1345234523452343', '2018-05-09 18:39:01', '三星', 'NOTE7', '白色', '128GB', '3500');
INSERT INTO `recycle_log` VALUES ('8', 'H3140516086735144495', '3123412341234213', '2018-05-09 18:41:40', '华为', 'Mate 10 pro', '摩卡金', '64GB', '2000');
INSERT INTO `recycle_log` VALUES ('9', 'H4733093210748709801', '12334145324523433', '2018-05-09 18:43:30', '摩托罗拉', 'MotoZ2', '黑色', '64GB', '2000');
INSERT INTO `recycle_log` VALUES ('10', 'H1200723548923848487', '5463563452134242', '2018-05-09 18:45:06', '诺基亚', 'E72', '蓝色', '1GB', '100');
INSERT INTO `recycle_log` VALUES ('11', 'H6377009144020346921', '5422634553242345', '2018-05-09 18:50:35', '苹果', 'iphone 6', '白色', '32GB', '2000');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair` (
  `repair_order_number` char(10) NOT NULL COMMENT '维修单号',
  `IMEI` varchar(17) NOT NULL COMMENT '手机编号',
  `maintenance_project` varchar(50) NOT NULL COMMENT '维修项目',
  `amount_complained` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '投入金额',
  PRIMARY KEY (`repair_order_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维修情况表';

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES ('1863848477', '4t234523412341234', '听筒', '100');
INSERT INTO `repair` VALUES ('5257432805', '413423412341234', '屏幕', '200');
INSERT INTO `repair` VALUES ('7004860106', '12334145324523433', '屏幕', '100');
INSERT INTO `repair` VALUES ('7647960567', '321354658786712', 'HOME键', '500');
INSERT INTO `repair` VALUES ('8516705168', '13413453452345345', '屏幕', '300');

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
INSERT INTO `role_permission` VALUES ('2', '7');
INSERT INTO `role_permission` VALUES ('1', '7');
INSERT INTO `role_permission` VALUES ('1', '8');
INSERT INTO `role_permission` VALUES ('2', '8');
INSERT INTO `role_permission` VALUES ('1', '9');
INSERT INTO `role_permission` VALUES ('1', '10');
INSERT INTO `role_permission` VALUES ('1', '11');
INSERT INTO `role_permission` VALUES ('2', '9');
INSERT INTO `role_permission` VALUES ('2', '10');
INSERT INTO `role_permission` VALUES ('2', '11');
INSERT INTO `role_permission` VALUES ('1', '12');
INSERT INTO `role_permission` VALUES ('2', '12');

-- ----------------------------
-- Table structure for selling_detail
-- ----------------------------
DROP TABLE IF EXISTS `selling_detail`;
CREATE TABLE `selling_detail` (
  `selling_id` char(20) NOT NULL COMMENT '销售明细编号',
  `imei` varchar(17) NOT NULL DEFAULT '' COMMENT '手机imei',
  `serial_number` char(20) NOT NULL COMMENT '销售单编号',
  `transaction_price` decimal(10,2) NOT NULL COMMENT '手机交易价',
  PRIMARY KEY (`selling_id`),
  KEY `serial_number` (`serial_number`),
  KEY `phone_id` (`imei`),
  CONSTRAINT `selling_detail_ibfk_2` FOREIGN KEY (`serial_number`) REFERENCES `sell_slip` (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='销售明细表';

-- ----------------------------
-- Records of selling_detail
-- ----------------------------

-- ----------------------------
-- Table structure for sell_log
-- ----------------------------
DROP TABLE IF EXISTS `sell_log`;
CREATE TABLE `sell_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `selling_id` char(20) NOT NULL COMMENT '销售明细编号',
  `imei` varchar(17) NOT NULL COMMENT '手机编号',
  `operator_name` varchar(20) NOT NULL COMMENT '经办人Id',
  `sale_date` datetime NOT NULL COMMENT '销售日期',
  `brand_name` varchar(20) NOT NULL COMMENT '品牌名',
  `model_name` varchar(20) NOT NULL COMMENT '型号名',
  `color_name` varchar(20) NOT NULL COMMENT '颜色名',
  `memory_name` varchar(20) NOT NULL COMMENT '内存名',
  `transaction_price` decimal(10,0) NOT NULL COMMENT '成交价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='销售日志';

-- ----------------------------
-- Records of sell_log
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='库存信息表';

-- ----------------------------
-- Records of stock_info
-- ----------------------------
INSERT INTO `stock_info` VALUES ('22', '4', '25', '7', '15', '1');
INSERT INTO `stock_info` VALUES ('26', '1', '1', '1', '2', '1');
INSERT INTO `stock_info` VALUES ('27', '5', '27', '11', '17', '1');
INSERT INTO `stock_info` VALUES ('28', '7', '28', '12', '18', '1');
INSERT INTO `stock_info` VALUES ('29', '8', '26', '10', '16', '1');
INSERT INTO `stock_info` VALUES ('30', '1', '1', '1', '1', '1');

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
