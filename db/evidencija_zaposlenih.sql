/*
SQLyog Community
MySQL - 8.0.39 : Database - fakultet_projekat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fakultet_projekat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `fakultet_projekat`;

/*Table structure for table `_user` */

DROP TABLE IF EXISTS `_user`;

CREATE TABLE `_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(40) NOT NULL,
  `lastname` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(200) NOT NULL,
  `role` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `_user` */

insert  into `_user`(`id`,`firstname`,`lastname`,`username`,`email`,`password`,`role`) values 
(5,'bobi','krsmanovic','b','bobi.krsma@gmail.com','$2a$10$khsi0DsGneC0EjhlH5UC.eP6nDp5JoUCWsJXEA7AYB5.ArQCfKcRO','USER'),
(16,'Aleksa','Krsmanvic','a','aleksa.krsma@gmail.com','$2a$10$rCxf3/1rPBGLUrfCRkBB9ujLLkQAdfBsv7l7astxdBkcrtYa17AgK','USER'),
(17,'Aleksa','Krsmanovic','m','aleksa.krsma@gmail.com','$2a$10$31bpQNgRrfHEgov9cIyvxeYWaYr3qvbHu6iNceKQwLp7UQBc9c7F6','USER'),
(19,'Aleksandra','Nikolendzic','alek','anikolendzic@gmail.com','$2a$10$iAkOpX/8GAkZ9WVFuhXMw.ezccm9JlkMBiG.XPOOgdU1IiNZjWPZq','USER'),
(20,'Admin','Admin','admin','a1@a1.com','$2a$10$VOKhhTvdTuJseyLXLpGtEuypk9K8WDLjFOg1lT9BviXTdHBaNN33a','ADMIN'),
(21,'Tanja','Krsmanovic','tanja','aleksa.krsma@gmail.com','$2a$10$iGKjGlBhvaSHoMPt4lSkW.P9WxBYgaf5Z20DYfCFL1Nk13ahfy5me','USER');

/*Table structure for table `academictitle` */

DROP TABLE IF EXISTS `academictitle`;

CREATE TABLE `academictitle` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `academictitle` */

insert  into `academictitle`(`id`,`name`) values 
(1,'redovni profesor'),
(2,'vanredni profesor'),
(3,'docent'),
(4,'asistent'),
(5,'asistent sa doktoratom'),
(6,'saradnik');

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `shortName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `department` */

insert  into `department`(`id`,`name`,`shortName`) values 
(1,'Katedra za softversko inzenjerstvo','SILAB'),
(2,'Katedra za matematiku','MATH'),
(3,'Katedra za operaciona istrazivanja i statistiku','STAT'),
(4,'Katedra za menadzment ljudskih resursa','HR'),
(7,'Katedra za strane jezike','KZS'),
(8,'Katedra za komunikacije','KZK'),
(23,'Probni Department','PROBA'),
(24,'Proba','PROBAA');

/*Table structure for table `educationtitle` */

DROP TABLE IF EXISTS `educationtitle`;

CREATE TABLE `educationtitle` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `educationtitle` */

insert  into `educationtitle`(`id`,`name`) values 
(1,'PhD'),
(2,'MSc'),
(3,'BSc');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `birthday` date NOT NULL,
  `department` bigint unsigned NOT NULL,
  `academicTitle` bigint unsigned NOT NULL,
  `educationTitle` bigint unsigned DEFAULT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `zaposleniKatedraFK` (`department`),
  KEY `zaposleniAkademskaTitulaFK` (`academicTitle`),
  KEY `zaposleniNivoObrazovanjaFK` (`educationTitle`),
  CONSTRAINT `zaposleniAkademskaTitulaFK` FOREIGN KEY (`academicTitle`) REFERENCES `academictitle` (`id`) ON UPDATE RESTRICT,
  CONSTRAINT `zaposleniKatedraFK` FOREIGN KEY (`department`) REFERENCES `department` (`id`) ON UPDATE RESTRICT,
  CONSTRAINT `zaposleniNivoObrazovanjaFK` FOREIGN KEY (`educationTitle`) REFERENCES `educationtitle` (`id`) ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `employee` */

insert  into `employee`(`id`,`firstname`,`lastname`,`birthday`,`department`,`academicTitle`,`educationTitle`,`status`) values 
(1,'Aleksa','Krsmanovic','1999-06-21',1,1,2,'ACTIVE'),
(17,'Roko','Todorovic','2022-11-30',2,3,2,'ACTIVE'),
(21,'Luka','Laban','1964-01-01',1,3,2,'ACTIVE'),
(24,'Maksim','Maksim','1930-01-01',4,6,2,'ACTIVE'),
(25,'Stefan','Lazarevic','2022-11-30',3,1,2,'INACTIVE'),
(27,'Dragan','Jovanovic','1994-05-14',4,6,1,'ACTIVE'),
(31,'Sinan','Sakic','1930-01-01',1,1,1,'INACTIVE'),
(36,'Tanja','Krsmanovic','1968-09-27',2,2,3,'ACTIVE'),
(37,'Luka','Boros','1999-01-02',4,6,3,'INACTIVE'),
(39,'Nikola','Vlatkovic','1934-03-03',4,6,1,'ACTIVE'),
(42,'Milan','Stepanovic','1975-03-23',1,3,2,'ACTIVE'),
(43,'Spiros','Papas','1999-12-03',3,2,2,'ACTIVE'),
(44,'Mihajlo','Marinkovic','1999-01-02',3,2,3,'ACTIVE'),
(45,'Dusan','Licina','1999-08-02',2,5,1,'ACTIVE'),
(48,'Preldzija','Dorna','1931-01-02',1,1,1,'ACTIVE'),
(49,'Petar','Milosevic','2002-12-03',1,5,1,'ACTIVE'),
(50,'Petar','Kocic','1964-10-02',2,6,1,'ACTIVE'),
(56,'Marko','Lazarevic','1994-07-05',2,1,1,'ACTIVE'),
(57,'Marko','Lazarevic','1999-06-02',2,1,1,'ACTIVE'),
(58,'Marko','Lazarevic','1999-09-04',2,1,1,'ACTIVE'),
(59,'Marko','Lazarevic','1999-06-03',2,1,2,'ACTIVE'),
(60,'Marko','Lazarevic','1999-02-02',2,1,2,'ACTIVE'),
(61,'Marko','Lazarevic','1999-01-01',2,1,1,'ACTIVE'),
(62,'Marko','Lazarevic','1999-02-03',2,1,2,'ACTIVE'),
(63,'Marko','Lazarevic','1999-01-01',2,1,2,'ACTIVE'),
(64,'Marko','Lazarevic','1977-01-02',2,2,2,'ACTIVE'),
(65,'Aleksa','Aleksic','1999-01-05',2,3,2,'INACTIVE'),
(66,'Aleksa','Aleksic','1999-12-01',2,3,2,'ACTIVE'),
(67,'Aleksa','Aleksic','1999-12-21',2,3,2,'INACTIVE'),
(68,'Aleksa','Krsmanovic','1999-06-21',1,6,3,'ACTIVE'),
(69,'Todor','Todorovic','1999-06-21',2,1,2,'ACTIVE'),
(70,'Aleksa','Krsmanovic','1991-06-21',1,4,3,'ACTIVE'),
(71,'Aleksas','Krsmanovic','1999-06-21',1,4,3,'ACTIVE'),
(72,'Aleksa','Krsmanovic','1999-06-21',1,4,3,'ACTIVE'),
(73,'Aleksaaa','Krsmanovic','1999-06-21',1,4,3,'ACTIVE'),
(74,'Preldzija','Prepelic','2000-02-02',1,1,1,'ACTIVE');

/*Table structure for table `employeeacademictitle` */

DROP TABLE IF EXISTS `employeeacademictitle`;

CREATE TABLE `employeeacademictitle` (
  `employee` bigint unsigned NOT NULL,
  `academicTitle` bigint unsigned NOT NULL,
  `begin_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`employee`,`academicTitle`,`begin_date`),
  KEY `academicTitle` (`academicTitle`),
  CONSTRAINT `employeeacademictitle_ibfk_1` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON UPDATE RESTRICT,
  CONSTRAINT `employeeacademictitle_ibfk_2` FOREIGN KEY (`academicTitle`) REFERENCES `academictitle` (`id`) ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `employeeacademictitle` */

insert  into `employeeacademictitle`(`employee`,`academicTitle`,`begin_date`,`end_date`) values 
(1,1,'2005-03-31','2024-04-11'),
(1,5,'2005-04-04','2005-04-04'),
(67,6,'2012-01-02','2013-01-01'),
(67,6,'2015-01-02','2016-01-01');

/*Table structure for table `employeesubject` */

DROP TABLE IF EXISTS `employeesubject`;

CREATE TABLE `employeesubject` (
  `employee` bigint unsigned NOT NULL,
  `subject` bigint unsigned NOT NULL,
  `class_type` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`employee`,`subject`),
  KEY `subject` (`subject`),
  CONSTRAINT `employeesubject_ibfk_1` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `employeesubject_ibfk_2` FOREIGN KEY (`subject`) REFERENCES `subject` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `employeesubject` */

insert  into `employeesubject`(`employee`,`subject`,`class_type`) values 
(1,1,'LECTURES'),
(1,4,'PRACTICALS'),
(24,1,'LECTURES'),
(27,5,'LECTURES'),
(36,5,'PRACTICALS'),
(45,4,'LECTURES'),
(56,1,'PRACTICALS');

/*Table structure for table `subject` */

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `ects` int unsigned NOT NULL,
  `studiestype` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `subject` */

insert  into `subject`(`id`,`name`,`ects`,`studiestype`) values 
(1,'NJT',5,'UNDERGRADUATE'),
(2,'NP',5,'UNDERGRADUATE'),
(3,'Vgrtv',10,'UNDERGRADUATE'),
(4,'Epos',3,'SPECIALIZED'),
(5,'Aaaa',5,'MASTER'),
(6,'Fmir',3,'MASTER'),
(7,'Marketing',5,'UNDERGRADUATE'),
(8,'Ekonomija',7,'SPECIALIZED');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
