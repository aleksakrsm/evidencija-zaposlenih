-- liquibase formatted sql

-- changeset Aleksa Krsmanovic:DDL

USE `fakultet_projekat`;

CREATE TABLE `_user`
(
    `id`        bigint unsigned NOT NULL AUTO_INCREMENT,
    `firstname` varchar(40)  NOT NULL,
    `lastname`  varchar(40)  NOT NULL,
    `username`  varchar(40)  NOT NULL,
    `email`     varchar(40)  NOT NULL,
    `password`  varchar(200) NOT NULL,
    `role`      varchar(40)  NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `academictitle`
(
    `id`   bigint unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `department`
(
    `id`        bigint unsigned NOT NULL AUTO_INCREMENT,
    `name`      varchar(100) NOT NULL,
    `shortName` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `educationtitle`
(
    `id`   bigint unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(30) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `employee`
(
    `id`             bigint unsigned NOT NULL AUTO_INCREMENT,
    `firstname`      varchar(30) NOT NULL,
    `lastname`       varchar(30) NOT NULL,
    `birthday`       date        NOT NULL,
    `department`     bigint unsigned NOT NULL,
    `academicTitle`  bigint unsigned NOT NULL,
    `educationTitle` bigint unsigned DEFAULT NULL,
    `status`         varchar(10) NOT NULL,
    PRIMARY KEY (`id`),
    KEY              `zaposleniKatedraFK` (`department`),
    KEY              `zaposleniAkademskaTitulaFK` (`academicTitle`),
    KEY              `zaposleniNivoObrazovanjaFK` (`educationTitle`),
    CONSTRAINT `zaposleniAkademskaTitulaFK` FOREIGN KEY (`academicTitle`) REFERENCES `academictitle` (`id`) ON UPDATE RESTRICT,
    CONSTRAINT `zaposleniKatedraFK` FOREIGN KEY (`department`) REFERENCES `department` (`id`) ON UPDATE RESTRICT,
    CONSTRAINT `zaposleniNivoObrazovanjaFK` FOREIGN KEY (`educationTitle`) REFERENCES `educationtitle` (`id`) ON UPDATE SET NULL
);

CREATE TABLE `employeeacademictitle`
(
    `employee`      bigint unsigned NOT NULL,
    `academicTitle` bigint unsigned NOT NULL,
    `begin_date`    date NOT NULL,
    `end_date`      date DEFAULT NULL,
    PRIMARY KEY (`employee`, `academicTitle`, `begin_date`),
    KEY             `academicTitle` (`academicTitle`),
    CONSTRAINT `employeeacademictitle_ibfk_1` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON UPDATE RESTRICT,
    CONSTRAINT `employeeacademictitle_ibfk_2` FOREIGN KEY (`academicTitle`) REFERENCES `academictitle` (`id`) ON UPDATE RESTRICT
);

CREATE TABLE `subject`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `name`        varchar(60)                                                  NOT NULL,
    `ects`        int unsigned NOT NULL,
    `studiestype` varchar(20) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `employeesubject`
(
    `employee`   bigint unsigned NOT NULL,
    `subject`    bigint unsigned NOT NULL,
    `class_type` varchar(35) NOT NULL,
    PRIMARY KEY (`employee`, `subject`),
    KEY          `subject` (`subject`),
    CONSTRAINT `employeesubject_ibfk_1` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `employeesubject_ibfk_2` FOREIGN KEY (`subject`) REFERENCES `subject` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);