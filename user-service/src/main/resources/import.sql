insert into user (id, username, password, enabled, name, last_name, email, attempts) values (1, 'admin', '$2a$10$umyl1P7haiUwNo2yNhiNVOGGqpmUOfJEaM1bWdY0yYPCByswJJy3G', 1, 'Juan', 'Pedraza', 'user1@prueba.com', 0);
insert into user (id, username, password, enabled, name, last_name, email, attempts) values (2, 'user1', '$2a$10$A6YyQWrXwy3VZss/gCHg4OOWc4YMZgJuC4nXEl9CETqRuGDDcAExi', 1, 'Pedro', 'Paez', 'user2@prueba.com', 0);
insert into user (id, username, password, enabled, name, last_name, email, attempts) values (3, 'user2', '$2a$10$YAyUX0Zgdit5bkvTmCBrweOblDH7Cumd2XzvgAEzymBgYo36fO0Jq', 1, 'Andres', 'Pinto', 'user3@prueba.com', 0);
insert into user (id, username, password, enabled, name, last_name, email, attempts) values (4, 'user3', '$2a$10$r3z.mbmcfnzEogs9T7EHZOkf7TipOu0m5QtQbYmPUBpyWLhfQtGUi', 1, 'Carlos', 'Pino', 'user4@prueba.com', 0);
insert into user (id, username, password, enabled, name, last_name, email, attempts) values (5, 'user4', '$2a$10$IzW8dswXAd2oF5Dd68dOceujQPqA6QKdIPgdGN5bNHQ/11DrSFOg2', 1, 'Jean', 'Pantoja', 'user5@prueba.com', 0);

insert into role (id, name) values (1, 'ROLE_ADMIN');
insert into role (id, name) values (2, 'ROLE_USER');

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (1, 2);
insert into user_role (user_id, role_id) values (2, 2);
