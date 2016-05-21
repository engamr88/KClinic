/*
MySQL Data Transfer
Source Host: localhost
Source Database: kclinic
Target Host: localhost
Target Database: kclinic
Date: 4/18/2016 2:06:41 PM
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for case_study
-- ----------------------------
CREATE TABLE `case_study` (
  `case_study_id` int(11) NOT NULL AUTO_INCREMENT,
  `case_study_name` text NOT NULL,
  PRIMARY KEY (`case_study_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for category
-- ----------------------------
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_title` text NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for concentration
-- ----------------------------
CREATE TABLE `concentration` (
  `concentration_id` int(11) NOT NULL AUTO_INCREMENT,
  `concentration_name` text NOT NULL,
  PRIMARY KEY (`concentration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for detection
-- ----------------------------
CREATE TABLE `detection` (
  `detection_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `note` text,
  `extra_fees` double(10,2) DEFAULT NULL,
  `archive` int(1) NOT NULL DEFAULT '0',
  `archive_date` datetime DEFAULT NULL,
  `discount_amount` double(10,2) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`detection_id`),
  KEY `patient_id` (`patient_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `detection_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`),
  CONSTRAINT `detection_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for detection_study
-- ----------------------------
CREATE TABLE `detection_study` (
  `detection_study_id` int(11) NOT NULL AUTO_INCREMENT,
  `detection_id` int(11) NOT NULL,
  `case_study_id` int(11) NOT NULL,
  PRIMARY KEY (`detection_study_id`),
  KEY `detection_id` (`detection_id`),
  KEY `case_study_id` (`case_study_id`),
  CONSTRAINT `detection_study_ibfk_1` FOREIGN KEY (`detection_id`) REFERENCES `detection` (`detection_id`),
  CONSTRAINT `detection_study_ibfk_2` FOREIGN KEY (`case_study_id`) REFERENCES `case_study` (`case_study_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dose
-- ----------------------------
CREATE TABLE `dose` (
  `dose_id` int(11) NOT NULL AUTO_INCREMENT,
  `dose_name` text NOT NULL,
  PRIMARY KEY (`dose_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for form
-- ----------------------------
CREATE TABLE `form` (
  `form_id` int(11) NOT NULL AUTO_INCREMENT,
  `form_name` text NOT NULL,
  PRIMARY KEY (`form_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for medication
-- ----------------------------
CREATE TABLE `medication` (
  `medication_id` int(11) NOT NULL AUTO_INCREMENT,
  `medication_name` text NOT NULL,
  PRIMARY KEY (`medication_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for patient
-- ----------------------------
CREATE TABLE `patient` (
  `patient_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_full_name` varchar(200) NOT NULL,
  `patient_mobile` varchar(20) NOT NULL,
  `patient_phone` varchar(20) DEFAULT NULL,
  `patient_address` varchar(500) DEFAULT NULL,
  `patient_email` varchar(200) DEFAULT NULL,
  `patient_gender` int(1) DEFAULT NULL,
  `patient_national_number` int(50) DEFAULT NULL,
  `patient_birthdate` date DEFAULT NULL,
  `patient_job` text,
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for prescription
-- ----------------------------
CREATE TABLE `prescription` (
  `prescription_id` int(11) NOT NULL AUTO_INCREMENT,
  `prescription_date` datetime NOT NULL,
  `detection_id` int(11) NOT NULL,
  `medication_id` int(11) NOT NULL,
  `dose_id` int(11) NOT NULL,
  `form_id` int(11) NOT NULL,
  `concentration_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`prescription_id`),
  KEY `medication_id` (`medication_id`),
  KEY `dose_id` (`dose_id`),
  KEY `form_id` (`form_id`),
  KEY `concentration_id` (`concentration_id`),
  KEY `detection_id` (`detection_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `prescription_ibfk_2` FOREIGN KEY (`medication_id`) REFERENCES `medication` (`medication_id`),
  CONSTRAINT `prescription_ibfk_3` FOREIGN KEY (`dose_id`) REFERENCES `dose` (`dose_id`),
  CONSTRAINT `prescription_ibfk_4` FOREIGN KEY (`form_id`) REFERENCES `form` (`form_id`),
  CONSTRAINT `prescription_ibfk_5` FOREIGN KEY (`concentration_id`) REFERENCES `concentration` (`concentration_id`),
  CONSTRAINT `prescription_ibfk_6` FOREIGN KEY (`detection_id`) REFERENCES `detection` (`detection_id`),
  CONSTRAINT `prescription_ibfk_7` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for price_list
-- ----------------------------
CREATE TABLE `price_list` (
  `price_list_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `price_type` text NOT NULL,
  `price_amount` double(10,2) NOT NULL,
  PRIMARY KEY (`price_list_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `price_list_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for quest
-- ----------------------------
CREATE TABLE `quest` (
  `quest_id` int(11) NOT NULL AUTO_INCREMENT,
  `quest_name` text NOT NULL,
  `quest_type` int(1) NOT NULL DEFAULT '1',
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`quest_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `quest_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for quest_answer
-- ----------------------------
CREATE TABLE `quest_answer` (
  `quest_answer_id` int(11) NOT NULL AUTO_INCREMENT,
  `quest_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `quest_answer_name` text NOT NULL,
  `quest_answer_note` text,
  PRIMARY KEY (`quest_answer_id`),
  KEY `quest_id` (`quest_id`),
  KEY `patient_id` (`patient_id`),
  CONSTRAINT `quest_answer_ibfk_1` FOREIGN KEY (`quest_id`) REFERENCES `quest` (`quest_id`),
  CONSTRAINT `quest_answer_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
CREATE TABLE `reservation` (
  `reservation_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `reservation_type` int(1) NOT NULL DEFAULT '1' COMMENT '1- kashf  2- esteshara',
  `reservation_date` datetime NOT NULL,
  `reservation_number` int(11) NOT NULL,
  `price_list_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `patient_id` (`patient_id`),
  KEY `category_id` (`category_id`),
  KEY `price_list_id` (`price_list_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`),
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `reservation_ibfk_3` FOREIGN KEY (`price_list_id`) REFERENCES `price_list` (`price_list_id`),
  CONSTRAINT `reservation_ibfk_5` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for session
-- ----------------------------
CREATE TABLE `session` (
  `session_id` int(11) NOT NULL AUTO_INCREMENT,
  `session_name` text NOT NULL,
  `session_date` datetime NOT NULL,
  `amount paid` double(10,2) NOT NULL,
  `notes` text,
  `detection_id` int(11) NOT NULL,
  PRIMARY KEY (`session_id`),
  KEY `detection_id` (`detection_id`),
  CONSTRAINT `session_ibfk_1` FOREIGN KEY (`detection_id`) REFERENCES `detection` (`detection_id`)
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `case_study` VALUES ('1', 'case 1');
INSERT INTO `category` VALUES ('2', 'كشف أسنان');
INSERT INTO `dose` VALUES ('2', 'dose-1');
INSERT INTO `form` VALUES ('2', 'form-2');
INSERT INTO `medication` VALUES ('2', 'med-2');
INSERT INTO `patient` VALUES ('1', 'محمد عبد السميع', '01203849997', '', '', '', '1', '0', null, null);
INSERT INTO `price_list` VALUES ('1', '2', 'حشو عادي', '160.00');
INSERT INTO `price_list` VALUES ('2', '2', 'كشف', '35.00');
INSERT INTO `price_list` VALUES ('3', '2', 'إستشارة', '10.00');
INSERT INTO `quest` VALUES ('2', 'ضغط', '1', '2');
INSERT INTO `quest` VALUES ('3', 'سكر', '1', '2');
INSERT INTO `quest` VALUES ('4', 'تفاصيل اخري', '2', '2');
INSERT INTO `reservation` VALUES ('7', '1', '1', '2016-03-20 19:40:37', '1', '1', '2', '2');
INSERT INTO `user` VALUES ('1', 'admin', 'fUb40grLuxWS1swgYAFCvw==', '1', 'Dr.', '', null, null, null, '1', null, null);
INSERT INTO `user` VALUES ('2', 'doc-1', '4ZmHKxUaKFYHPG6shskegQ==', '2', 'doc-1', '01203849979', '', '', '', '1', '0', null);
INSERT INTO `user` VALUES ('3', 'nurse-1', '4ZmHKxUaKFYHPG6shskegQ==', '3', 'nurse 1', '01203849997', '', '', '', '1', '0', null);
