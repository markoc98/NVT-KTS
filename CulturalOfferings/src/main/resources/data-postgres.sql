

INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_USER');
--password admin
INSERT INTO user_table (email, username, password, is_enabled) VALUES ('adminadminovic@gmail.com', 'admin' ,'$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK', true);
--INSERT INTO user_table (email, username, password, is_enabled) VALUES ('userrrrrrr@gmail.com', 'user', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq', true);
INSERT INTO user_table (email, username, password, is_enabled) VALUES ('userrrrrrr@gmail.com', 'user', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq', true );

insert into user_authority (user_id, authority_id) values (1, 1); -- admin has ROLE_ADMIN
insert into user_authority (user_id, authority_id) values (2, 2); -- user has ROLE_USER

INSERT INTO category_table (name) VALUES ('Events');
INSERT INTO category_table (name) VALUES ('Cultural goods');
INSERT INTO category_table (name) VALUES ('Institutions');

INSERT INTO category_type_table (name, category_id) VALUES ('Festivals', '1');
INSERT INTO category_type_table (name, category_id) VALUES ('Monuments', '2');
INSERT INTO category_type_table (name, category_id) VALUES ('Museums', '3');
INSERT INTO category_type_table (name, category_id) VALUES ('Theaters', '3');

INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Exit festival', 'Najbolji exit festival', 1,'1',19.8603308,45.252251,'1');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Starinar', 'Nacionalni arheološki institut i muzej', 5,'3',19.8493044,45.2591691,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Samarija', 'Suveniri', 3,'3',19.8456967,45.2568468,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Museum of Vojvodina', 'Muzej Vojvodine', 3,'3',19.8495375,45.2564123,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Taktilna mapa Novog Sada', 'Muzej na otvorenom', 2.4,'3',19.8433196,45.2553372,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Spomenik Peri Dobrinoviću', 'Spomenik', 5,'2',19.8412736,45.2549681,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Bista Mike Antica', 'Bista Miroslava Mike Antica', 4.5,'2',19.8470468,45.2552157,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Spomenik Mihajlu Pupinu', 'Spomenik', 3.5,'2',19.8451824,45.2539107,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Spomenik Tarasu Ševčenku', 'Spomenik', 3.5,'2',19.8435285,45.2518928,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Monument to the Victims of the Raid', 'Spomenik', 3.5,'2',19.853812,45.2522972,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Spomenik Janike Balaša', 'Spomenik', 3.5,'2',19.8519948,45.2540273,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Bista Branka Radičevića', 'Spomenik', 3.5,'2',19.8489649,45.256212,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Новосадско Позориште', 'Pozoriste', 3.5,'3',19.8396023,45.2577829,'4');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Pozorište Promena', 'Pozoriste', 3.5,'3',19.8414954,45.2586126,'4');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Kulturni centar Vojvodine "Miloš Crnjanski"', 'Pozoriste', 3.5,'3',19.8509021,45.2553612,'4');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Pozorište mladih Novi Sad', 'Pozoriste', 3.5,'3',19.8469346,45.2553273,'4');

INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Народни музеј', 'Велики музеј са славном збирком италијанске уметности, древним артефактима и другим историјским предметима.', 4.7,'3',20.4603766,44.8163324,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Muzej Zepter', 'У овом музеју, смештеном у некадашњој банци, изложена су српска уметничка дела од средине 20. века до данас.', 3.5,'3',19.8469346,45.2553273,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Музеј илузија Београд', 'Pozoriste', 3.5,'3',20.4576921,44.8159822);
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Јеврејски историјски музеј', 'Зграда у којој се налази Јеврејски историјски музеј подигнута је 1928. године за потребе Јеврејске сефардске општине у Београду.', 3.5,'3',20.458071,44.8180941,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Музеј науке и технике', 'На сталној поставци изложено је преко четири стотине предмета из фонда Музеја науке и технике.', 3.5,'3',20.458071,44.8180941,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Позориште на Теразијама', 'Позориште са добрим репертоаром за мјузикле', 3.5,'3',20.4609505,44.8128011,'4');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Атеље 212', 'Pozoriste', 3.5,'3',20.46418,44.8131127,'4');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Народно позориште', 'Pozoriste', 3.5,'3',20.4603842,44.8153844,'4');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Петар Петровић Његош', 'Spomenik', 3.5,'2',20.4533183,44.8144723,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Споменик кнезу Михаилу', 'Spomenik', 3.5,'2',20.4533183,44.8144723,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Monument Borislav Pekic', 'Spomenik', 3.5,'2',20.4636798,44.8033659,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Споменик Милораду Павићу', 'Spomenik', 3.5,'2',20.4542756,44.7994017,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'CLOUD Festivals', 'Festival', 3.5,'1',20.4393988,44.8124358,'1');

INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Subotica', 'Spomenik žrtvama fašizma Subotica', 'Spomenik, namenjen žrtvama fašizma za vreme drugog svetskog rata', 3.5,'2',19.665248,46.0978166,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Subotica', 'Гробница Беле Фаркаша', 'Spomenik', 3.5,'2',19.8469346,45.2553273,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Subotica', 'Spomenik Palim Borcima Drugog svetskog rata', 'Spomenik', 3.5,'2',19.6266713,46.0887827,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Subotica', 'Позориште "Деже Костолањи"', 'Pozoriste', 3.5,'3',19.6642871,46.0982311,'4');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Subotica', 'Narodno pozoriste Subotica', 'Pozoriste', 3.5,'3',19.6642871,46.0982311,'4');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Subotica', 'Međunarodni Oldtajmer Klub "Subotica"', 'Muzej', 3.5,'3',19.6610076,46.0998525,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Subotica', 'Градски музеј', 'Muzej', 3.5,'3',19.6610076,46.0998525,'3');


--INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (1,1);
INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (2,1);
INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (2,2);
INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (2,3);
INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (2,4);
INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (2,5);
INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (2,6);
INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (2,7);
INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (2,8);
INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (2,9);

insert into newsletter (title, content,date) values ('Test-Title1', 'Test-Title1', '2020-05-06');