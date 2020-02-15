insert into user (id, username, password, enabled, name, last_name, email) values (1, 'user1', '12345', 1, 'Juan', 'Pedraza', 'user1@prueba.com');
insert into user (id, username, password, enabled, name, last_name, email) values (2, 'user2', '12345', 1, 'Pedro', 'Paez', 'user2@prueba.com');
insert into user (id, username, password, enabled, name, last_name, email) values (3, 'user3', '12345', 1, 'Andres', 'Pinto', 'user3@prueba.com');
insert into user (id, username, password, enabled, name, last_name, email) values (4, 'user4', '12345', 1, 'Carlos', 'Pino', 'user4@prueba.com');
insert into user (id, username, password, enabled, name, last_name, email) values (5, 'user5', '12345', 1, 'Jean', 'Pantoja', 'user5@prueba.com');

insert into role (id, name) values (1, 'role1');
insert into role (id, name) values (2, 'role2');

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (2, 2);
insert into user_role (user_id, role_id) values (2, 1);
