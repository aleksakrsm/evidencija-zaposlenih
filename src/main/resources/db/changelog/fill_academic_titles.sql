-- liquibase formatted sql
-- changeset Aleksa Krsmanovic:fill_academic_title_table

insert into `academictitle`(`id`, `name`)
values (1, 'redovni profesor'),
       (2, 'vanredni profesor'),
       (3, 'docent'),
       (4, 'asistent'),
       (5, 'asistent sa doktoratom'),
       (6, 'saradnik');