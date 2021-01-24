
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_USER');
--password admin
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
INSERT INTO category_type_table (name, category_id) VALUES ('Theaters', '3');

INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Novi Sad', 'Exit festival', 'Najbolji exit festival', 1,'1');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Beograd', 'Exit festival2', 'Najbolji exit festival', 0,'1');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Beograd', 'Exit festival3', 'Najbolji exit festival', 0,'1');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Beograd', 'Exit festival4', 'Najbolji exit festival', 0,'1');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Beograd', 'Exit festival5', 'Najbolji exit festival', 0,'1');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Beograd', 'Exit festival6', 'Najbolji exit festival', 0,'1');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Beograd', 'Exit festival7', 'Najbolji exit festival', 0,'1');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Novi Sad', 'Starinar', 'Nacionalni arheološki institut i muzej', 5,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Novi Sad', 'Samarija', 'Suveniri', 3,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Novi Sad', 'Taktilna mapa Novog Sada', 'Muzej na otvorenom', 2.4,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Novi Sad', 'Spomenik Peri Dobrinoviću', 'Spomenik', 5,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Novi Sad', 'Bista Mike Antica', 'Bista Miroslava Mike Antica', 4.5,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Novi Sad', 'Spomenik Mihajlu Pupinu', 'Spomenik', 3.5,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Novi Sad', 'Spomenik Tarasu Ševčenku', 'Spomenik', 3.5,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Novi Sad', 'Monument to the Victims of the Raid', 'Spomenik', 3.5,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Novi Sad', 'Spomenik Janike Balaša', 'Spomenik', 3.5,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Novi Sad', 'Bista Branka Radičevića', 'Spomenik', 3.5,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Novi Sad', 'Новосадско Позориште', 'Pozoriste', 3.5,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Novi Sad', 'Pozorište Promena', 'Pozoriste', 3.5,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Novi Sad', 'Kulturni centar Vojvodine "Miloš Crnjanski"', 'Pozoriste', 3.5,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id) VALUES ('Novi Sad', 'Pozorište mladih Novi Sad', 'Pozoriste', 3.5,'3');






insert into newsletter (title, content,date) values ('Test-Title1', 'Test-Title1', '2020-05-06');