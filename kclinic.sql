/*
MySQL Data Transfer
Source Host: localhost
Source Database: kclinic
Target Host: localhost
Target Database: kclinic
Date: 4/9/2016 12:44:09 PM
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for case_study
-- ----------------------------
CREATE TABLE `case_study` (
  `case_study_id` int(11) NOT NULL AUTO_INCREMENT,
  `case_study_name` longtext NOT NULL,
  PRIMARY KEY (`case_study_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for category
-- ----------------------------
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_title` longtext NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for concentration
-- ----------------------------
CREATE TABLE `concentration` (
  `concentration_id` int(11) NOT NULL AUTO_INCREMENT,
  `concentration_name` longtext NOT NULL,
  PRIMARY KEY (`concentration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for detection
-- ----------------------------
CREATE TABLE `detection` (
  `detection_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `note` longtext,
  `extra_fees` double DEFAULT NULL,
  `archive` int(11) NOT NULL,
  `archive_date` datetime DEFAULT NULL,
  `discount_amount` double DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`detection_id`),
  KEY `FK_bo5iulgwj0pj7erhg5hi3eqay` (`patient_id`),
  KEY `FK_mm5iw9s33wvpcdrfqje301fbp` (`user_id`),
  CONSTRAINT `FK_mm5iw9s33wvpcdrfqje301fbp` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_bo5iulgwj0pj7erhg5hi3eqay` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for detection_study
-- ----------------------------
CREATE TABLE `detection_study` (
  `detection_study_id` int(11) NOT NULL AUTO_INCREMENT,
  `case_study_id` int(11) NOT NULL,
  `detection_id` int(11) NOT NULL,
  PRIMARY KEY (`detection_study_id`),
  KEY `FK_ij8jmtep1842x2tl1jrfs3y85` (`case_study_id`),
  KEY `FK_3i04pm5rsah812j0l3d62m5si` (`detection_id`),
  CONSTRAINT `FK_3i04pm5rsah812j0l3d62m5si` FOREIGN KEY (`detection_id`) REFERENCES `detection` (`detection_id`),
  CONSTRAINT `FK_ij8jmtep1842x2tl1jrfs3y85` FOREIGN KEY (`case_study_id`) REFERENCES `case_study` (`case_study_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for dose
-- ----------------------------
CREATE TABLE `dose` (
  `dose_id` int(11) NOT NULL AUTO_INCREMENT,
  `dose_name` longtext NOT NULL,
  PRIMARY KEY (`dose_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for form
-- ----------------------------
CREATE TABLE `form` (
  `form_id` int(11) NOT NULL AUTO_INCREMENT,
  `form_name` longtext NOT NULL,
  PRIMARY KEY (`form_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for medication
-- ----------------------------
CREATE TABLE `medication` (
  `medication_id` int(11) NOT NULL AUTO_INCREMENT,
  `medication_name` longtext NOT NULL,
  PRIMARY KEY (`medication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for patient
-- ----------------------------
CREATE TABLE `patient` (
  `patient_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_full_name` varchar(200) NOT NULL,
  `patient_mobile` varchar(20) NOT NULL,
  `patient_phone` varchar(20) DEFAULT NULL,
  `patient_address` longtext,
  `patient_email` varchar(200) DEFAULT NULL,
  `patient_gender` int(11) DEFAULT NULL,
  `patient_national_number` int(11) DEFAULT NULL,
  `patient_birthdate` date DEFAULT NULL,
  `patient_job` longtext,
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for prescription
-- ----------------------------
CREATE TABLE `prescription` (
  `prescription_id` int(11) NOT NULL AUTO_INCREMENT,
  `concentration_id` int(11) NOT NULL,
  `detection_id` int(11) NOT NULL,
  `dose_id` int(11) NOT NULL,
  `form_id` int(11) NOT NULL,
  `medication_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `prescription_date` datetime NOT NULL,
  PRIMARY KEY (`prescription_id`),
  KEY `FK_mf47cp8hd8jux11rc2wmg85pl` (`concentration_id`),
  KEY `FK_aub003vqcfpml6hp53sivpqr2` (`detection_id`),
  KEY `FK_qlbow3kqlxqi86uylsesiq512` (`dose_id`),
  KEY `FK_jaqt6y7gchir3gwee1tuna4v1` (`form_id`),
  KEY `FK_qgh1ijqx3yousreaqjpvrqvon` (`medication_id`),
  KEY `FK_6t4gsq8e4105olu2l9ycwncwd` (`user_id`),
  CONSTRAINT `FK_6t4gsq8e4105olu2l9ycwncwd` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_aub003vqcfpml6hp53sivpqr2` FOREIGN KEY (`detection_id`) REFERENCES `detection` (`detection_id`),
  CONSTRAINT `FK_jaqt6y7gchir3gwee1tuna4v1` FOREIGN KEY (`form_id`) REFERENCES `form` (`form_id`),
  CONSTRAINT `FK_mf47cp8hd8jux11rc2wmg85pl` FOREIGN KEY (`concentration_id`) REFERENCES `concentration` (`concentration_id`),
  CONSTRAINT `FK_qgh1ijqx3yousreaqjpvrqvon` FOREIGN KEY (`medication_id`) REFERENCES `medication` (`medication_id`),
  CONSTRAINT `FK_qlbow3kqlxqi86uylsesiq512` FOREIGN KEY (`dose_id`) REFERENCES `dose` (`dose_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for price_list
-- ----------------------------
CREATE TABLE `price_list` (
  `price_list_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `price_type` longtext NOT NULL,
  `price_amount` double NOT NULL,
  PRIMARY KEY (`price_list_id`),
  KEY `FK_cnv18ww8ke4ugsaprbcys2i4w` (`category_id`),
  CONSTRAINT `FK_cnv18ww8ke4ugsaprbcys2i4w` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for quest
-- ----------------------------
CREATE TABLE `quest` (
  `quest_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `quest_name` longtext NOT NULL,
  `quest_type` int(11) NOT NULL,
  PRIMARY KEY (`quest_id`),
  KEY `FK_j324wgo6iadfsqms3nxywoey0` (`category_id`),
  CONSTRAINT `FK_j324wgo6iadfsqms3nxywoey0` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for quest_answer
-- ----------------------------
CREATE TABLE `quest_answer` (
  `quest_answer_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `quest_id` int(11) NOT NULL,
  `quest_answer_name` longtext NOT NULL,
  `quest_answer_note` longtext,
  PRIMARY KEY (`quest_answer_id`),
  KEY `FK_yfgkt56f0wunh314rmr3k294` (`patient_id`),
  KEY `FK_hcc8susgbiyxttda9kj1xuwj4` (`quest_id`),
  CONSTRAINT `FK_hcc8susgbiyxttda9kj1xuwj4` FOREIGN KEY (`quest_id`) REFERENCES `quest` (`quest_id`),
  CONSTRAINT `FK_yfgkt56f0wunh314rmr3k294` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
CREATE TABLE `reservation` (
  `reservation_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL,
  `patient_id` int(11) NOT NULL,
  `price_list_id` int(11) DEFAULT NULL,
  `reservation_type` int(11) NOT NULL,
  `reservation_date` datetime NOT NULL,
  `reservation_number` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `FK_thc94gafckgl8b61vm151iaoe` (`category_id`),
  KEY `FK_dxrtabmx0xevtol5j1n5kh9e9` (`patient_id`),
  KEY `FK_qxcavclmrt9rx1vemw5pg00e4` (`price_list_id`),
  KEY `FK_recqnfjcp370rygd9hjjxjtg` (`user_id`),
  CONSTRAINT `FK_recqnfjcp370rygd9hjjxjtg` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_dxrtabmx0xevtol5j1n5kh9e9` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`),
  CONSTRAINT `FK_qxcavclmrt9rx1vemw5pg00e4` FOREIGN KEY (`price_list_id`) REFERENCES `price_list` (`price_list_id`),
  CONSTRAINT `FK_thc94gafckgl8b61vm151iaoe` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for session
-- ----------------------------
CREATE TABLE `session` (
  `session_id` int(11) NOT NULL AUTO_INCREMENT,
  `detection_id` int(11) NOT NULL,
  `session_name` longtext NOT NULL,
  `session_date` datetime NOT NULL,
  `amount paid` double NOT NULL,
  `notes` longtext,
  PRIMARY KEY (`session_id`),
  KEY `FK_iwsobvvhne005jk8x3kfasi4l` (`detection_id`),
  CONSTRAINT `FK_iwsobvvhne005jk8x3kfasi4l` FOREIGN KEY (`detection_id`) REFERENCES `detection` (`detection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `user_password` varchar(100) NOT NULL,
  `user_type` int(11) NOT NULL,
  `user_full_name` varchar(200) NOT NULL,
  `user_mobile` varchar(20) NOT NULL,
  `user_phone` varchar(20) DEFAULT NULL,
  `user_address` longtext,
  `user_email` varchar(200) DEFAULT NULL,
  `user_gender` int(11) DEFAULT NULL,
  `user_national_number` int(11) DEFAULT NULL,
  `user_birthdate` date DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'Hldobowx0IYKMz7p9mSdAQ==', '1', 'Admin', '01203849997', null, null, null, '1', null, '1988-04-08');
INSERT INTO `user` VALUES ('2', 'nurse-1', '4ZmHKxUaKFYHPG6shskegQ==', '3', 'Nurse', '01203849997', '', '', '', '1', '0', null);
