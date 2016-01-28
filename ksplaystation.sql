/*
MySQL Data Transfer
Source Host: localhost
Source Database: ksplaystation
Target Host: localhost
Target Database: ksplaystation
Date: 9/23/2015 7:15:32 PM
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for device
-- ----------------------------
CREATE TABLE `device` (
  `device_id` int(11) NOT NULL AUTO_INCREMENT,
  `device_name` varchar(200) NOT NULL,
  `device_hour_price` double(5,2) NOT NULL,
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for last_update
-- ----------------------------
CREATE TABLE `last_update` (
  `last_update_id` int(11) NOT NULL AUTO_INCREMENT,
  `last_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`last_update_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for room
-- ----------------------------
CREATE TABLE `room` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(100) NOT NULL,
  `room_description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for room_device
-- ----------------------------
CREATE TABLE `room_device` (
  `room_device_id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) NOT NULL,
  `device_id` int(11) NOT NULL,
  `active` int(1) NOT NULL,
  PRIMARY KEY (`room_device_id`),
  KEY `room_id` (`room_id`),
  KEY `device_id` (`device_id`),
  CONSTRAINT `room_device_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`),
  CONSTRAINT `room_device_ibfk_2` FOREIGN KEY (`device_id`) REFERENCES `device` (`device_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for room_service
-- ----------------------------
CREATE TABLE `room_service` (
  `room_service_id` int(11) NOT NULL AUTO_INCREMENT,
  `room_work_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `called_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`room_service_id`),
  KEY `room_work_id` (`room_work_id`),
  KEY `service_id` (`service_id`),
  CONSTRAINT `room_service_ibfk_1` FOREIGN KEY (`room_work_id`) REFERENCES `room_work` (`room_work_id`),
  CONSTRAINT `room_service_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `service` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for room_work
-- ----------------------------
CREATE TABLE `room_work` (
  `room_work_id` int(11) NOT NULL AUTO_INCREMENT,
  `room_device_id` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `client_name` varchar(500) DEFAULT NULL,
  `all_price` double(5,2) NOT NULL,
  `discount_amount` double(5,2) DEFAULT NULL,
  `discount_reason` text,
  `game_price` double(5,2) NOT NULL,
  `total_price` double(5,2) NOT NULL,
  PRIMARY KEY (`room_work_id`),
  KEY `room_device_id` (`room_device_id`),
  CONSTRAINT `room_work_ibfk_1` FOREIGN KEY (`room_device_id`) REFERENCES `room_device` (`room_device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for service
-- ----------------------------
CREATE TABLE `service` (
  `service_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(200) NOT NULL,
  `service_price` double(5,2) NOT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `user_password` varchar(100) NOT NULL,
  `user_type` int(1) NOT NULL,
  `user_full_name` varchar(200) NOT NULL,
  `user_mobile` varchar(20) NOT NULL,
  `user_phone` varchar(20) DEFAULT NULL,
  `user_address` varchar(500) DEFAULT NULL,
  `user_email` varchar(200) DEFAULT NULL,
  `user_gender` int(1) DEFAULT NULL,
  `user_national_number` int(50) DEFAULT NULL,
  `user_birthdate` date DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `device` VALUES ('1', 'ps4 single', '25.00');
INSERT INTO `device` VALUES ('2', 'ps4 multi', '30.00');
INSERT INTO `device` VALUES ('3', 'ps3 single', '20.00');
INSERT INTO `device` VALUES ('4', 'ps3 multi', '25.00');
INSERT INTO `last_update` VALUES ('1', '2015-09-23 19:13:45');
INSERT INTO `room` VALUES ('1', '1', 'ps3');
INSERT INTO `room` VALUES ('2', '2', 'ps3\r\n');
INSERT INTO `room` VALUES ('3', '3', 'ps4');
INSERT INTO `room_device` VALUES ('1', '1', '4', '1');
INSERT INTO `room_device` VALUES ('2', '2', '3', '1');
INSERT INTO `room_device` VALUES ('3', '3', '1', '1');
INSERT INTO `room_device` VALUES ('4', '3', '2', '1');
INSERT INTO `room_device` VALUES ('5', '1', '3', '1');
INSERT INTO `user` VALUES ('1', 'admin', 'fUb40grLuxWS1swgYAFCvw==', '1', 'Mohamed Salah', '01204439179', null, null, null, '1', null, null);
