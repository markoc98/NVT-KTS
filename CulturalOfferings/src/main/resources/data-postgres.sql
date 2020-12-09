
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_USER');

INSERT INTO user_table (email, username, password, is_enabled) VALUES ('adminadminovic@gmail.com', 'admin' ,'$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK', true);
INSERT INTO user_table (email, username, password, is_enabled) VALUES ('userrrrrrr@gmail.com', 'user', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq', true);


insert into user_authority (user_id, authority_id) values (1, 1); -- admin has ROLE_ADMIN
insert into user_authority (user_id, authority_id) values (2, 2); -- user has ROLE_USER

INSERT INTO category_table (name) VALUES ('Events');
INSERT INTO category_table (name) VALUES ('Cultural goods');
INSERT INTO category_table (name) VALUES ('Institutions');

INSERT INTO category_type_table (name, category_id) VALUES ('Festivals', '1');
INSERT INTO category_type_table (name, category_id) VALUES ('Monuments', '2');
INSERT INTO category_type_table (name, category_id) VALUES ('Museums', '3');

INSERT INTO cultural_offerings (location, name, description, rating) VALUES ('Beograd', 'Exit festival', 'Najbolji exit festival', -11.0);