/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : db_legao

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-03-06 22:11:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for legao_classroom
-- ----------------------------
DROP TABLE IF EXISTS `legao_classroom`;
CREATE TABLE `legao_classroom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '课室',
  `ageArea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '年龄段',
  `responsePersonName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '负责人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of legao_classroom
-- ----------------------------
INSERT INTO `legao_classroom` VALUES ('2', 'A101', '3-4', 'Jaden');
INSERT INTO `legao_classroom` VALUES ('3', 'A102', '5-6', 'Jaden');

-- ----------------------------
-- Table structure for legao_classtimepack
-- ----------------------------
DROP TABLE IF EXISTS `legao_classtimepack`;
CREATE TABLE `legao_classtimepack` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `classTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课时数',
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '价格',
  `priority` int(11) DEFAULT NULL COMMENT '优先级',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='课时包表';

-- ----------------------------
-- Records of legao_classtimepack
-- ----------------------------
INSERT INTO `legao_classtimepack` VALUES ('1', '大课时包', '100', '12000', '100', '最好');
INSERT INTO `legao_classtimepack` VALUES ('3', '一年课时', '40', '8000', '3', '江北店');
INSERT INTO `legao_classtimepack` VALUES ('4', '123', '123', '123', '123', 'asd');

-- ----------------------------
-- Table structure for legao_contract
-- ----------------------------
DROP TABLE IF EXISTS `legao_contract`;
CREATE TABLE `legao_contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `contractCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '合同编号',
  `studentId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生',
  `signDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '签约日期',
  `classPackageId` int(11) DEFAULT NULL COMMENT '课时包，关联课时包表id',
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '价格',
  `discount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '折扣',
  `depositId` int(11) DEFAULT NULL COMMENT '订金id,关联订金表id',
  `actPay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '实付金额',
  `payType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付方式',
  `presentationClassTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '赠送课时',
  `startTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '起始日期',
  `endTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '终止日期',
  `belongOne` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业绩所属',
  `memberCarId` int(11) DEFAULT NULL COMMENT '会员卡id，关联会员卡表id',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `classTime` varchar(11) DEFAULT NULL COMMENT '课时',
  `remainClassTime` varchar(11) DEFAULT NULL COMMENT '剩余课时',
  `schoolAreaId` int(11) DEFAULT NULL COMMENT '校区id关联到校区表',
  `totalClassTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '总课时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='合同表';

-- ----------------------------
-- Records of legao_contract
-- ----------------------------
INSERT INTO `legao_contract` VALUES ('15', '123', '2', '2019-03-06 20:23:42', '3', '8000', '无', '0', '231', '123', '123', '2019-03-06 20:23:42', '2019-03-06 20:23:42', '无', null, '无', '40', '163.0', '1', '163.0');
INSERT INTO `legao_contract` VALUES ('16', '123', '1', '2019-03-06 20:23:36', '1', '12000', '无', '0', '213', '213', '231', '2019-03-06 20:23:36', '2019-03-06 20:23:36', '无', null, '无', '100', '331.0', '1', '331.0');

-- ----------------------------
-- Table structure for legao_course
-- ----------------------------
DROP TABLE IF EXISTS `legao_course`;
CREATE TABLE `legao_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `ageArea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '年龄段',
  `teacherTools` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教具',
  `courseTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课时',
  `seriesID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of legao_course
-- ----------------------------
INSERT INTO `legao_course` VALUES ('1', '我的房子', '3-4', '9090', '1', '5');
INSERT INTO `legao_course` VALUES ('2', '空中花园', '3-4', '9090', '1', '5');
INSERT INTO `legao_course` VALUES ('3', '风扇', '7-8', '9686', '1', '7');
INSERT INTO `legao_course` VALUES ('4', '四驱车', '7-8', '9686', '1', '7');

-- ----------------------------
-- Table structure for legao_date
-- ----------------------------
DROP TABLE IF EXISTS `legao_date`;
CREATE TABLE `legao_date` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `timeSection` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '时间段',
  `status` int(11) DEFAULT NULL COMMENT '1表示启用中，2表示停用',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `priority` int(11) DEFAULT NULL COMMENT '优先级，取值为数字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of legao_date
-- ----------------------------
INSERT INTO `legao_date` VALUES ('2', '09:30 - 10:30', '1', '1个小时', '3');
INSERT INTO `legao_date` VALUES ('3', '11:00 - 12:00', '1', '1个小时', '4');
INSERT INTO `legao_date` VALUES ('4', '09:30 - 11:30', '1', '2小时', '2');

-- ----------------------------
-- Table structure for legao_deposit
-- ----------------------------
DROP TABLE IF EXISTS `legao_deposit`;
CREATE TABLE `legao_deposit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentName` varchar(255) DEFAULT NULL COMMENT '学生',
  `adviser` varchar(255) DEFAULT NULL COMMENT '顾问',
  `moneyAmount` varchar(255) DEFAULT NULL COMMENT '金额',
  `payType` varchar(255) DEFAULT NULL COMMENT '支付方式',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='订金表';

-- ----------------------------
-- Records of legao_deposit
-- ----------------------------

-- ----------------------------
-- Table structure for legao_employ
-- ----------------------------
DROP TABLE IF EXISTS `legao_employ`;
CREATE TABLE `legao_employ` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `telphone` varchar(11) DEFAULT NULL,
  `birthday` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `entryDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '入职日期',
  `jobID` varchar(11) DEFAULT NULL COMMENT '关联legao_job表',
  `basicSalary` varchar(255) DEFAULT NULL COMMENT '基本工资',
  `fullWork` varchar(2) DEFAULT NULL COMMENT '是否全勤',
  `eatAllow` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '餐补',
  `classPay` varchar(255) DEFAULT NULL COMMENT '课时费',
  `allClassTime` varchar(11) DEFAULT NULL COMMENT '总课时',
  `extraPay` varchar(255) DEFAULT NULL COMMENT '提成',
  `area` varchar(255) DEFAULT NULL COMMENT '校区',
  `status` int(11) DEFAULT NULL COMMENT '1表在职，2表离职，3表其他',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of legao_employ
-- ----------------------------
INSERT INTO `legao_employ` VALUES ('3', 'Jaden', '男', '15602338269', '1995-05-11 00:00:00', '2017-07-01 00:00:00', '1', '3000', '是', '300', '5', '0', '无', '东湖校区', '1');
INSERT INTO `legao_employ` VALUES ('4', '王五', '男', '15622187742', '2019-03-03 00:00:00', '2019-03-03 00:00:00', '1', '1345', '是', '0', '无', '123123', '无', '东湖校区', '1');
INSERT INTO `legao_employ` VALUES ('5', '李逵', '男', '15622187742', '2019-03-03 00:00:00', '2019-03-03 00:00:00', '1', '123', '是', '123', '123打算是范德萨发', '123', '312', '东湖校区', '1');
INSERT INTO `legao_employ` VALUES ('6', '23', '男', '15622187742', '2019-03-03 00:00:00', '2019-03-03 00:00:00', '11', '123', '是', '0', '无', '123', '无', '东湖校区', '1');
INSERT INTO `legao_employ` VALUES ('7', '阿萨德', '男', '15622187742', '2019-03-06 00:00:00', '2019-03-06 00:00:00', '1', '312', '是', '0', '无', '0', '无', '东湖校区', '1');

-- ----------------------------
-- Table structure for legao_follow
-- ----------------------------
DROP TABLE IF EXISTS `legao_follow`;
CREATE TABLE `legao_follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生名',
  `advisor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '顾问',
  `date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `mode` varchar(255) DEFAULT NULL COMMENT '跟进方式',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of legao_follow
-- ----------------------------
INSERT INTO `legao_follow` VALUES ('1', '小明', '张三', '2019-02-28 23:25:52', '电话', '电话');

-- ----------------------------
-- Table structure for legao_job
-- ----------------------------
DROP TABLE IF EXISTS `legao_job`;
CREATE TABLE `legao_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of legao_job
-- ----------------------------
INSERT INTO `legao_job` VALUES ('1', '教师', '负责中心授课', '2019-02-28 15:17:48');
INSERT INTO `legao_job` VALUES ('11', '顾问', '负责中心销售', '2019-02-28 15:18:04');
INSERT INTO `legao_job` VALUES ('12', '店长', '负责中心管理', '2019-02-28 15:18:17');
INSERT INTO `legao_job` VALUES ('13', '前台', '负责前台接待', '2019-02-28 15:18:32');

-- ----------------------------
-- Table structure for legao_membercard
-- ----------------------------
DROP TABLE IF EXISTS `legao_membercard`;
CREATE TABLE `legao_membercard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `memberCardCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '会员卡编号',
  `carType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '会员卡类型',
  `studentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生',
  `classTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='会员卡表';

-- ----------------------------
-- Records of legao_membercard
-- ----------------------------
INSERT INTO `legao_membercard` VALUES ('1', 'HC123455', '金卡', '张三', '10');

-- ----------------------------
-- Table structure for legao_relation
-- ----------------------------
DROP TABLE IF EXISTS `legao_relation`;
CREATE TABLE `legao_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scheduleID` int(11) DEFAULT NULL COMMENT '排课id',
  `studentID` int(11) DEFAULT NULL COMMENT '学生id',
  `signInStatus` int(11) DEFAULT NULL COMMENT '签到状态：1准时，2未签到，3请假',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='签到表';

-- ----------------------------
-- Records of legao_relation
-- ----------------------------
INSERT INTO `legao_relation` VALUES ('1', '1', '1', '1');
INSERT INTO `legao_relation` VALUES ('7', '1', '1', '2');
INSERT INTO `legao_relation` VALUES ('8', '1', '2', '2');
INSERT INTO `legao_relation` VALUES ('9', '1', '3', '2');
INSERT INTO `legao_relation` VALUES ('12', '4', '1', '2');
INSERT INTO `legao_relation` VALUES ('13', '4', '2', '2');
INSERT INTO `legao_relation` VALUES ('14', '4', '3', '2');
INSERT INTO `legao_relation` VALUES ('15', '5', '1', '2');
INSERT INTO `legao_relation` VALUES ('16', '5', '2', '2');
INSERT INTO `legao_relation` VALUES ('17', '5', '3', '2');

-- ----------------------------
-- Table structure for legao_schedule
-- ----------------------------
DROP TABLE IF EXISTS `legao_schedule`;
CREATE TABLE `legao_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `schoolAreaID` int(11) DEFAULT NULL COMMENT '校区id',
  `dateID` int(11) DEFAULT NULL COMMENT '时间段id',
  `courseID` int(11) DEFAULT NULL COMMENT '课程id',
  `classroomID` int(11) DEFAULT NULL COMMENT '课室id',
  `seriesID` int(11) DEFAULT NULL COMMENT '课程系列id',
  `courseDate` date DEFAULT NULL COMMENT '排课日期',
  `courseWeek` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '本节课在本周周几，周日是1，以此类推',
  `teacherID` int(255) DEFAULT NULL COMMENT '老师id，关联到员工表的老师',
  `weekOfYear` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of legao_schedule
-- ----------------------------
INSERT INTO `legao_schedule` VALUES ('1', '1', '4', '1', '2', '5', '2019-03-03', '1', '4', '10');
INSERT INTO `legao_schedule` VALUES ('4', '6', '3', '4', '3', '7', '2019-03-04', '2', '5', '10');
INSERT INTO `legao_schedule` VALUES ('5', '6', '4', '1', '3', '5', '2019-03-04', '2', '5', '10');

-- ----------------------------
-- Table structure for legao_schoolarea
-- ----------------------------
DROP TABLE IF EXISTS `legao_schoolarea`;
CREATE TABLE `legao_schoolarea` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '校区名',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `responPersonName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '负责人姓名',
  `telphone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of legao_schoolarea
-- ----------------------------
INSERT INTO `legao_schoolarea` VALUES ('1', '东湖校区', '东平颐景花园', 'Jojo', '15622176645');
INSERT INTO `legao_schoolarea` VALUES ('6', '江北校区', '江北华辉铭铸', 'Jojo', '15622187742');

-- ----------------------------
-- Table structure for legao_series
-- ----------------------------
DROP TABLE IF EXISTS `legao_series`;
CREATE TABLE `legao_series` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `teacherTools` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教具',
  `courseTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课时',
  `ageArea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '年龄段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of legao_series
-- ----------------------------
INSERT INTO `legao_series` VALUES ('5', '我爱我家', '9090', '12', '1岁-2岁');
INSERT INTO `legao_series` VALUES ('7', '动力机械', '9686', '12', '7-8');

-- ----------------------------
-- Table structure for legao_student
-- ----------------------------
DROP TABLE IF EXISTS `legao_student`;
CREATE TABLE `legao_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '昵称',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '姓名',
  `parentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '家长姓名',
  `parentRelat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '家长关系',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '性别',
  `telphone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '手机号码',
  `weChatID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '微信号',
  `education` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `birthday` timestamp NULL DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '录入日期',
  `sparePhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '备用电话',
  `markPeople` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '市场人员',
  `advisor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '顾问',
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '地区',
  `willDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '意向时间',
  `baseSituation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '基本情况',
  `status` int(11) DEFAULT NULL COMMENT '学生状态：1代表新客户，2代表试听客户，3代表号码无效客户，4其他',
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '学生来源',
  `importanceGrade` int(11) DEFAULT NULL COMMENT '重要程度：1代表重点，2代表优质，3会员，4一般，5放弃，6未知',
  `teacherName` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '老师姓名',
  `courseID` int(11) DEFAULT NULL COMMENT '学生选择的课程系列id',
  `schoolAreaID` int(11) DEFAULT NULL COMMENT '校区id,管理到legao_schoolArea表',
  `followID` int(11) DEFAULT NULL COMMENT '跟进人员id，关联到legao_followPeople',
  `stuAge` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of legao_student
-- ----------------------------
INSERT INTO `legao_student` VALUES ('5', '无', 'asd', '无', '无', '女', '15622187742', '无', '无', '2019-03-06 00:00:00', '2019-03-06 21:52:44', '无', '无', '0', '无', '2019-03-06 00:00:00', '无', '4', '无', '6', 'Jaden', null, '1', '0', '0岁0个月');
INSERT INTO `legao_student` VALUES ('6', '无', '13', '无', '无', '男', '15622187742', '无', '无', '2019-03-06 00:00:00', '2019-03-06 22:02:11', '无', '无', '0', '无', '2019-03-06 00:00:00', '无', '4', '无', '6', 'Jaden', null, '1', '0', '0岁0个月');

-- ----------------------------
-- Table structure for legao_user
-- ----------------------------
DROP TABLE IF EXISTS `legao_user`;
CREATE TABLE `legao_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `selectStudentFields` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '学生模块选择了哪些字段显示',
  `selectEmployFields` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `selectContractFields` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of legao_user
-- ----------------------------
INSERT INTO `legao_user` VALUES ('14', 'admin', 'e2687a7e9df77a71a4d9382066b3d787', '1', '2019-03-04 21:42:37', '[0,1,2,9,10,14,17]', '[0,1,2,5,6,9,10]', '[1,2,3,9,15,16]');
INSERT INTO `legao_user` VALUES ('34', 'Jaden', 'b3447b91e4460915799bc9f28d57d2ab', '1', '2019-03-04 21:37:16', null, null, null);
INSERT INTO `legao_user` VALUES ('36', 'dzx', 'e2687a7e9df77a71a4d9382066b3d787', '1', '2019-03-06 20:37:34', null, '[4,5,6,7,8,9,10]', '[12,14,15,16,17,18]');
INSERT INTO `legao_user` VALUES ('37', 'dzx1', 'e2687a7e9df77a71a4d9382066b3d787', '1', '2019-03-06 22:04:09', '[15,16,17,18,19,20,21,22]', null, '[12,14,15,16,17,18]');
