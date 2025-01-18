-- liquibase formatted sql
-- changeset Aleksa Krsmanovic:fill_education_title_table
insert into `educationtitle`(`id`, `name`)
values (1, 'PhD'),
       (2, 'MSc'),
       (3, 'BSc');