-- liquibase formatted sql
-- changeset Aleksa Krsmanovic:fill_department_table
insert into `department`(`id`, `name`, `shortName`)
values (1, 'Katedra za softversko inzenjerstvo', 'SILAB'),
       (2, 'Katedra za matematiku', 'MATH'),
       (3, 'Katedra za operaciona istrazivanja i statistiku', 'STAT'),
       (4, 'Katedra za menadzment ljudskih resursa', 'HR'),
       (7, 'Katedra za strane jezike', 'KZS'),
       (8, 'Katedra za komunikacije', 'KZK'),
       (23, 'Probni Department', 'PROBA'),
       (24, 'Proba', 'PROBAA');