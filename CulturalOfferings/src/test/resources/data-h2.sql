

INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_USER');
--password admin
--INSERT INTO user_table (email, username, password, is_enabled) VALUES ('user@gmail.com', 'user', 'nijedobrasifara/D1w26G1.fWsi.aK', true);
INSERT INTO user_table (email, username, password, is_enabled) VALUES ('bazapera@gmail.com', 'admin' ,'$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK', true);
INSERT INTO user_table (email, username, password, is_enabled) VALUES ('user@gmail.com', 'user' ,'$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq', true);
INSERT INTO user_table (email, username, password, is_enabled) VALUES ('user2@gmail.com', 'user2' ,'$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK', true);
INSERT INTO user_table (email, username, password, is_enabled) VALUES ('user3@gmail.com', 'user3' ,'$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK', true);
INSERT INTO user_table (email, username, password, is_enabled) VALUES ('admin2@gmail.com', 'admin2' ,'$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK', true);
INSERT INTO user_table (email, username, password, is_enabled) VALUES ('userDELETE@gmail.com', 'userDELETE' ,'$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK', true);


insert into user_authority (user_id, authority_id) values (1, 1); -- admin has ROLE_ADMIN
insert into user_authority (user_id, authority_id) values (2, 2); -- user has ROLE_USER


INSERT INTO category_table (name) VALUES ('Events');
INSERT INTO category_table (name) VALUES ('Cultural goods');
INSERT INTO category_table (name) VALUES ('Institutions');
INSERT INTO category_table (name) VALUES ('Category delete');

INSERT INTO category_type_table (name, category_id) VALUES ('Festivals', '1');
INSERT INTO category_type_table (name, category_id) VALUES ('Monuments', '2');
INSERT INTO category_type_table (name, category_id) VALUES ('Category type delete', '2');


INSERT INTO cultural_offerings (location, name, description, rating) VALUES ('Beograd', 'Exit festival', 'Najbolji exit festival', -11.0);

insert into newsletter (title, content,date) values ('Test-Title1', 'Test-Title1', '2020-05-06');
insert into reviews (comment,rating) values ('test123' , 5);
insert into reviews (comment,rating) values ('NewTestComment' , 2.5);

