/*
Navicat MySQL Data Transfer

Source Server         : StuInfo
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : stuinfo

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-12-09 17:33:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `adminuser`
-- ----------------------------
DROP TABLE IF EXISTS `adminuser`;
CREATE TABLE `adminuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET armscii8 DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `adminrole` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adminuser
-- ----------------------------
INSERT INTO `adminuser` VALUES ('1', 'admin', '123456', '3', '2018-10-19 00:00:00', '2018-10-29 20:02:22');
INSERT INTO `adminuser` VALUES ('2', 'chencc', '123456', '3', '2018-10-29 17:45:21', '2018-10-29 17:45:21');

-- ----------------------------
-- Table structure for `coursedetail`
-- ----------------------------
DROP TABLE IF EXISTS `coursedetail`;
CREATE TABLE `coursedetail` (
  `id` varchar(45) NOT NULL,
  `subjectid` int(11) DEFAULT NULL,
  `teachercourseid` bigint(15) DEFAULT NULL,
  `teacherid` int(15) DEFAULT NULL,
  `coursecontent` varchar(111) DEFAULT NULL,
  `courseresource` varchar(88) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `subjectid` (`subjectid`),
  KEY `teacherid` (`teacherid`),
  KEY `teachecourseid` (`teachercourseid`),
  CONSTRAINT `coursedetail_ibfk_1` FOREIGN KEY (`subjectid`) REFERENCES `stusubject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `coursedetail_ibfk_2` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `coursedetail_ibfk_3` FOREIGN KEY (`teachercourseid`) REFERENCES `teachcourse` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coursedetail
-- ----------------------------
INSERT INTO `coursedetail` VALUES ('201812081613515520001', '13', '15442418186072', '2014001123', '东方早报的或许你真的', 'file/courseresource/1544256831539.zip', '2018-12-08 16:13:51');
INSERT INTO `coursedetail` VALUES ('201812081618195110001', '6', '15440857052940', '2014001123', '提任何人好像', 'file/courseresource/1544257099420.rar', '2018-12-08 16:18:19');
INSERT INTO `coursedetail` VALUES ('201812081646002300001', '6', '15440857052940', '2014001123', 'xtnhdxnxd政府的不会自动发你还不晓得你的感叹女孩突然想到', 'file/courseresource/1544258760153.rar', '2018-12-08 16:46:00');
INSERT INTO `coursedetail` VALUES ('201812081913208840001', '10', '15440857052941', '2014001123', '的系统恢复你的想法你家媳妇的那就非常的', 'file/courseresource/1544267600805.zip', '2018-12-08 19:13:20');
INSERT INTO `coursedetail` VALUES ('201812081950386850001', '6', '15440857052940', '2014001123', '达芙妮女鞋达芙妮答复', 'file/courseresource/1544269838670.zip', '2018-12-08 19:50:38');

-- ----------------------------
-- Table structure for `stugrade`
-- ----------------------------
DROP TABLE IF EXISTS `stugrade`;
CREATE TABLE `stugrade` (
  `id` varchar(45) NOT NULL,
  `stuid` varchar(45) NOT NULL,
  `subjectid` int(11) NOT NULL,
  `subjectscore` varchar(45) NOT NULL,
  `examtype` varchar(45) DEFAULT NULL,
  `examteam` varchar(45) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `subjectid` (`subjectid`),
  KEY `stugrade_ibfk_1` (`stuid`),
  CONSTRAINT `stugrade_ibfk_2` FOREIGN KEY (`subjectid`) REFERENCES `stusubject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stugrade_ibfk_3` FOREIGN KEY (`stuid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stugrade
-- ----------------------------
INSERT INTO `stugrade` VALUES ('15443235031041496215', '2019012345', '13', '45', '首修', '大三上学期', '2018-12-09 10:45:03');

-- ----------------------------
-- Table structure for `stusubject`
-- ----------------------------
DROP TABLE IF EXISTS `stusubject`;
CREATE TABLE `stusubject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subjectname` varchar(45) NOT NULL,
  `credit` int(11) NOT NULL,
  `subjectype` varchar(45) NOT NULL,
  `subjectproperty` varchar(45) DEFAULT NULL,
  `courseteam` varchar(45) NOT NULL,
  `coursetime` int(11) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stusubject
-- ----------------------------
INSERT INTO `stusubject` VALUES ('1', '高等数学1', '4', '专业基础课', '必修', '大一上学期', '60', '2018-11-18 19:59:58');
INSERT INTO `stusubject` VALUES ('2', '高等数学2', '4', '专业基础课', '必修', '大一下学期', '60', '2018-11-19 13:58:18');
INSERT INTO `stusubject` VALUES ('3', '大学物理1', '4', '专业基础课', '必修', '大一上学期', '60', '2018-11-19 15:25:24');
INSERT INTO `stusubject` VALUES ('4', '大学物理2', '4', '专业基础课', '必修', '大一下学期', '60', '2018-11-19 15:25:49');
INSERT INTO `stusubject` VALUES ('5', '程序设计基础（C语言）', '4', '专业基础课', '必修', '大一上学期', '60', '2018-11-19 15:27:58');
INSERT INTO `stusubject` VALUES ('6', '专业导论（网络）', '3', '专业基础课', '必修', '大一上学期', '56', '2018-11-19 16:46:08');
INSERT INTO `stusubject` VALUES ('7', '大学英语1', '4', '专业基础课', '必修', '大一上学期', '60', '2018-11-19 16:46:49');
INSERT INTO `stusubject` VALUES ('8', '大学英语2', '4', '专业基础课', '必修', '大一下学期', '60', '2018-11-19 16:47:18');
INSERT INTO `stusubject` VALUES ('9', '信息技术应用基础 ', '3', '专业基础课', '必修', '大一上学期', '60', '2018-11-19 16:47:36');
INSERT INTO `stusubject` VALUES ('10', '线性代数与空间解析几何A', '4', '专业基础课', '必修', '大一上学期', '60', '2018-11-19 16:48:03');
INSERT INTO `stusubject` VALUES ('11', '中国近代史纲要', '3', '专业基础课', '必修', '大一上学期', '56', '2018-11-19 16:49:53');
INSERT INTO `stusubject` VALUES ('12', '思想道德修养与法律基础', '3', '专业基础课', '必修', '大一上学期', '60', '2018-11-19 16:50:23');
INSERT INTO `stusubject` VALUES ('13', 'JAVA程序设计基础', '4', '专业基础课', '必修', '大三上学期', '56', '2018-12-04 17:00:33');
INSERT INTO `stusubject` VALUES ('14', '网页设计基础', '3', '专业基础课', '必修', '大一下学期', '56', '2018-12-04 17:02:56');
INSERT INTO `stusubject` VALUES ('15', 'C#程序设计', '3', '专业基础课', '必修', '大一下学期', '56', '2018-12-04 17:16:15');
INSERT INTO `stusubject` VALUES ('16', '计算机网络信息安全', '1', '专业方向课', '选修', '大三下学期', '56', '2018-12-04 18:23:59');

-- ----------------------------
-- Table structure for `subjectproperty`
-- ----------------------------
DROP TABLE IF EXISTS `subjectproperty`;
CREATE TABLE `subjectproperty` (
  `id` int(11) NOT NULL,
  `subjectproperty` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subjectproperty
-- ----------------------------
INSERT INTO `subjectproperty` VALUES ('1', '必修');
INSERT INTO `subjectproperty` VALUES ('2', '选修');
INSERT INTO `subjectproperty` VALUES ('3', '任选');
INSERT INTO `subjectproperty` VALUES ('4', '限选');

-- ----------------------------
-- Table structure for `teachcourse`
-- ----------------------------
DROP TABLE IF EXISTS `teachcourse`;
CREATE TABLE `teachcourse` (
  `id` bigint(15) NOT NULL,
  `teacherid` int(15) DEFAULT NULL,
  `subjectid` int(11) DEFAULT NULL,
  `courseteam` varchar(45) DEFAULT NULL,
  `classofteach` varchar(45) DEFAULT NULL,
  `coursestatus` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `teacherid` (`teacherid`),
  KEY `subjectid` (`subjectid`),
  CONSTRAINT `teachcourse_ibfk_1` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `teachcourse_ibfk_2` FOREIGN KEY (`subjectid`) REFERENCES `stusubject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teachcourse
-- ----------------------------
INSERT INTO `teachcourse` VALUES ('4', '2014001123', '3', '大一第二学期', '电气工程及其自动化1501', '0', '2018-12-05 17:33:56');
INSERT INTO `teachcourse` VALUES ('5', '2014001123', '5', '大一第二学期', '电气工程及其自动化1501', '0', '2018-12-05 17:39:22');
INSERT INTO `teachcourse` VALUES ('201902365', '2012112233', '2', '大一第二学期', '网络工程1501', '0', '2018-12-02 16:22:44');
INSERT INTO `teachcourse` VALUES ('1234567890', '2014001123', '4', '大一第二学期', '机械工程1501', '0', '2018-12-06 11:44:55');
INSERT INTO `teachcourse` VALUES ('2018120000', '2014001123', '9', '大一第二学期', '机械工程1501', '1', '2018-12-06 12:24:35');
INSERT INTO `teachcourse` VALUES ('2018120001', '2014001123', '11', '大一第二学期', '机械工程1501', '0', '2018-12-06 13:43:30');
INSERT INTO `teachcourse` VALUES ('2018120002', '2014001123', '7', '大一第二学期', '机械工程1501', '0', '2018-12-06 12:04:16');
INSERT INTO `teachcourse` VALUES ('2018120500', '2014001123', '3', '大一第二学期', '机械工程1501', '0', '2018-12-05 18:04:09');
INSERT INTO `teachcourse` VALUES ('2018120501', '2014001123', '2', '大一第二学期', '电气工程及其自动化1501', '0', '2018-12-05 23:22:26');
INSERT INTO `teachcourse` VALUES ('1544085468757', '2014001123', '8', '大一第二学期', '电气工程及其自动化1501', '0', '2018-12-06 16:37:48');
INSERT INTO `teachcourse` VALUES ('15440857052940', '2014001123', '6', '大一第二学期', '电气工程及其自动化1501', '1', '2018-12-06 16:41:45');
INSERT INTO `teachcourse` VALUES ('15440857052941', '2014001123', '10', '大一第二学期', '电气工程及其自动化1501', '1', '2018-12-06 16:41:57');
INSERT INTO `teachcourse` VALUES ('15442418186072', '2014001123', '13', '大三上学期', '电气工程及其自动化1506', '1', '2018-12-08 12:06:59');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(15) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `realname` varchar(45) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `identityid` varchar(45) DEFAULT NULL,
  `incollege` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('2012112145', '123456', '张人硕', '1', '4364574568679876', '艺术学院', '2018-12-04 18:38:55');
INSERT INTO `teacher` VALUES ('2012112233', '476584', '肖龙飞', '1', '321323198505060723', '商学院', '2018-12-01 12:27:17');
INSERT INTO `teacher` VALUES ('2012112234', '476585', '肖龙太', '1', '321323198505060099', '商学院', '2018-12-02 12:27:17');
INSERT INTO `teacher` VALUES ('2012112235', '476586', '肖龙格', '1', '321323198505060022', '计算软件工程学院', '2018-12-03 12:27:17');
INSERT INTO `teacher` VALUES ('2012112236', '476587', '肖龙风', '1', '321323198505060267', '计算机软件工程学院', '2018-12-04 12:27:16');
INSERT INTO `teacher` VALUES ('2012112237', '476588', '肖龙', '1', '321323198505060742', '计算机软件工程学院', '2018-12-05 12:27:16');
INSERT INTO `teacher` VALUES ('2012112238', '476589', '肖龙将', '1', '321323198505060785', '机械与电气工程学院', '2018-12-06 12:27:16');
INSERT INTO `teacher` VALUES ('2012112239', '476590', '肖龙开', '1', '321323198505060459', '艺术学院', '2018-12-07 12:27:16');
INSERT INTO `teacher` VALUES ('2012112240', '476591', '肖龙都', '1', '321323198505060078', '艺术学院', '2018-12-08 12:27:17');
INSERT INTO `teacher` VALUES ('2012112241', '476592', '肖龙为', '1', '321323198505060347', '艺术学院', '2018-12-09 12:27:16');
INSERT INTO `teacher` VALUES ('2012112242', '476593', '肖龙连', '1', '321323198505061235', '商学院', '2018-12-10 12:27:17');
INSERT INTO `teacher` VALUES ('2012112243', '476594', '肖龙安', '1', '321323198505060002', '商学院', '2018-12-11 12:27:17');
INSERT INTO `teacher` VALUES ('2014001123', '123456', '陈胜利', '1', '321323198505060001', '机械与电气工程学院', '2018-11-27 21:11:40');
INSERT INTO `teacher` VALUES ('2014234589', '123456', '王东来', '1', '321323198505060000', '艺术学院', '2018-12-01 12:28:15');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `stuclass` varchar(45) DEFAULT NULL,
  `stugrade` varchar(45) DEFAULT NULL,
  `incollege` varchar(45) DEFAULT NULL,
  `identityid` varchar(45) DEFAULT NULL,
  `politicsstatus` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `borndate` date DEFAULT NULL,
  `realname` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `auditstatus` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `identityid_UNIQUE` (`identityid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2019011250', '02331X', '通信工程1501', '2019', '计算机软件工程学院', '32433319970202331X', '1', '0', '1997-09-24', '朱艳雯', '重庆市,市辖区,合川区', '1', '2018-11-20 13:22:57');
INSERT INTO `user` VALUES ('2019012345', '021234', '网络工程1501', '2019', '计算机软件工程学院', '324333199803131234', '1', '1', '1997-02-02', '陈晨辰', '江苏省,南京市,雨花台区', '1', '2018-11-29 15:11:35');
INSERT INTO `user` VALUES ('2019011270', '123456', '通信工程1501', '2019', '计算机软件工程学院', '324333199709141236', '1', '0', '1997-09-24', '朱艳雯', '重庆市,市辖区,合川区', '1', '2018-11-20 07:22:57');
INSERT INTO `user` VALUES ('2019011271', '123456', '电气工程及其自动化1502', '2019', '机械与电气工程学院', '324333199708143695', '1', '1', '1997-09-25', '陈菲菲', '重庆市,市辖区,合川区', '1', '2018-11-21 05:22:57');
INSERT INTO `user` VALUES ('2019011272', '123456', '通信工程1503', '2019', '计算机软件工程学院', '324333199706147823', '1', '1', '1997-09-26', '张丹丹', '重庆市,市辖区,合川区', '1', '2018-11-22 19:22:57');
INSERT INTO `user` VALUES ('2019011273', '123456', '电气工程及其自动化1504', '2019', '机械与电气工程学院', '324333199703149637', '1', '1', '1997-09-27', '朱三方', '重庆市,市辖区,合川区', '1', '2018-11-23 18:22:57');
INSERT INTO `user` VALUES ('2019011274', '123456', '通信工程1505', '2019', '计算机软件工程学院', '324333199701141214', '1', '1', '1997-09-28', '乐坛人', '重庆市,市辖区,合川区', '1', '2018-11-24 17:22:57');
INSERT INTO `user` VALUES ('2019011275', '123456', '电气工程及其自动化1506', '2019', '机械与电气工程学院', '324333199706142580', '1', '1', '1997-09-29', '过度', '重庆市,市辖区,合川区', '1', '2018-11-25 16:22:57');
INSERT INTO `user` VALUES ('2019011276', '123456', '通信工程1507', '2019', '计算机软件工程学院', '324333199707145460', '1', '1', '1997-09-30', '地方', '重庆市,市辖区,合川区', '1', '2018-11-26 15:22:57');
INSERT INTO `user` VALUES ('2019011277', '123456', '电气工程及其自动化1501', '2019', '机械与电气工程学院', '324333199706140314', '1', '0', '1997-10-01', '的废墟', '重庆市,市辖区,合川区', '1', '2018-11-27 20:22:57');
INSERT INTO `user` VALUES ('2019011278', '123456', '通信工程1509', '2019', '计算机软件工程学院', '324333199706143218', '1', '0', '1997-10-02', '宋祖儿', '重庆市,市辖区,合川区', '1', '2018-12-02 18:38:11');
INSERT INTO `user` VALUES ('2019011279', '123456', '通信工程1510', '2019', '计算机软件工程学院', '324333199806196386', '1', '0', '1997-10-03', '信不过', '重庆市,市辖区,合川区', '1', '2018-11-29 10:22:58');
INSERT INTO `user` VALUES ('2019011280', '123456', '电气工程及其自动化1501', '2019', '机械与电气工程学院', '324333199606122581', '1', '0', '1997-10-04', '下班', '重庆市,市辖区,合川区', '1', '2018-11-30 06:22:59');
INSERT INTO `user` VALUES ('2019011281', '123456', '通信工程1512', '2019', '计算机软件工程学院', '324333199705111476', '1', '0', '1997-10-05', '小白', '重庆市,市辖区,合川区', '1', '2018-12-01 13:22:57');
INSERT INTO `user` VALUES ('2019011282', '261473', '机械工程1501', '2016', '机械与电气工程学院', '324333199806261473', '0', '1', '1998-06-26', '白天都', '重庆市,市辖区,合川区', null, '2018-12-02 17:29:15');
INSERT INTO `user` VALUES ('2019024568', '161234', '软件工程1501', '2019', '计算机软件工程学院', '324333199703161234', '1', '0', '1997-09-24', '谢彤', '江苏省,宿迁市,宿城区', '1', '2018-11-29 15:11:41');
INSERT INTO `user` VALUES ('2019024569', '123456', '网络工程1501', '20015', '计算机软件工程学院', '324333199502021234', '1', '1', '2018-12-02', '董书', '江苏省,宿迁市,宿城区', '1', '2018-12-02 15:34:21');
