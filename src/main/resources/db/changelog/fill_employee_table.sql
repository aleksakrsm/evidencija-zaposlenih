-- liquibase formatted sql
-- changeset Aleksa Krsmanovic:fill_employee_table
insert into `employee`(`id`, `firstname`, `lastname`, `birthday`, `department`, `academicTitle`, `educationTitle`,
                       `status`)
values (1, 'Aleksa', 'Krsmanovic', '1999-06-21', 1, 1, 2, 'ACTIVE');