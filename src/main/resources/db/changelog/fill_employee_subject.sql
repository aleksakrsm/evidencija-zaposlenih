-- liquibase formatted sql
-- changeset Aleksa Krsmanovic:fill_employee_subject_table

insert into `employeesubject`(`employee`, `subject`, `class_type`)
values (1, 1, 'LECTURES');