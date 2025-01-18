-- liquibase formatted sql
-- changeset Aleksa Krsmanovic:fill_employee_academic_title_table

insert into `employeeacademictitle`(`employee`, `academicTitle`, `begin_date`, `end_date`)
values (1, 1, '2005-03-31', '2024-04-11');