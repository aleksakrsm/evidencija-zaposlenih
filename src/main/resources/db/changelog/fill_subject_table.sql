-- liquibase formatted sql
-- changeset Aleksa Krsmanovic:fill_subject_table

insert into `subject`(`id`, `name`, `ects`, `studiestype`)
values (1, 'NJT', 5, 'UNDERGRADUATE'),
       (2, 'NP', 5, 'UNDERGRADUATE'),
       (3, 'Vgrtv', 10, 'UNDERGRADUATE'),
       (4, 'Epos', 3, 'SPECIALIZED'),
       (5, 'Aaaa', 5, 'MASTER'),
       (6, 'Fmir', 3, 'MASTER'),
       (7, 'Marketing', 5, 'UNDERGRADUATE'),
       (8, 'Ekonomija', 7, 'SPECIALIZED');