-- liquibase formatted sql
-- changeset Aleksa Krsmanovic:fill_subject_table

insert into `subject`(`id`, `name`, `ects`, `studiestype`)
values (9, 'PS', 6, 'UNDERGRADUATE')
