-- liquibase formatted sql
-- changeset Aleksa Krsmanovic:fill_users_table

insert into `_user`(`id`, `firstname`, `lastname`, `username`, `email`, `password`, `role`)
values (5, 'bobi', 'krsmanovic', 'b', 'bobi.krsma@gmail.com',
        '$2a$10$khsi0DsGneC0EjhlH5UC.eP6nDp5JoUCWsJXEA7AYB5.ArQCfKcRO', 'USER'),
       (16, 'Aleksa', 'Krsmanvic', 'a', 'aleksa.krsma@gmail.com',
        '$2a$10$rCxf3/1rPBGLUrfCRkBB9ujLLkQAdfBsv7l7astxdBkcrtYa17AgK', 'USER'),
       (17, 'Aleksa', 'Krsmanovic', 'm', 'aleksa.krsma@gmail.com',
        '$2a$10$31bpQNgRrfHEgov9cIyvxeYWaYr3qvbHu6iNceKQwLp7UQBc9c7F6', 'USER'),
       (19, 'Aleksandra', 'Nikolendzic', 'alek', 'anikolendzic@gmail.com',
        '$2a$10$iAkOpX/8GAkZ9WVFuhXMw.ezccm9JlkMBiG.XPOOgdU1IiNZjWPZq', 'USER'),
       (20, 'Admin', 'Admin', 'admin', 'a1@a1.com', '$2a$10$VOKhhTvdTuJseyLXLpGtEuypk9K8WDLjFOg1lT9BviXTdHBaNN33a',
        'ADMIN'),
       (21, 'Tanja', 'Krsmanovic', 'tanja', 'aleksa.krsma@gmail.com',
        '$2a$10$iGKjGlBhvaSHoMPt4lSkW.P9WxBYgaf5Z20DYfCFL1Nk13ahfy5me', 'USER');
