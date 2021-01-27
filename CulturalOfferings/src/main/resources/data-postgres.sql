

INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_USER');
--password admin
INSERT INTO user_table (email, username, password, is_enabled) VALUES ('adminadminovic@gmail.com', 'admin' ,'$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK', true);
--INSERT INTO user_table (email, username, password, is_enabled) VALUES ('userrrrrrr@gmail.com', 'user', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq', true);
INSERT INTO user_table (email, username, password, is_enabled) VALUES ('noteventryingyo@gmail.com', 'user', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq', true );
INSERT INTO user_table (email, username, password, is_enabled) VALUES ('drugiuserovic@gmail.com', 'miha', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq', true );
INSERT INTO user_table (email, username, password, is_enabled) VALUES ('dobarjakogmail@gmail.com', 'user2', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq', true );



insert into user_authority (user_id, authority_id) values (1, 1); -- admin has ROLE_ADMIN
insert into user_authority (user_id, authority_id) values (2, 2); -- user has ROLE_USER
insert into user_authority (user_id, authority_id) values (3, 2); -- user has ROLE_USER
insert into user_authority (user_id, authority_id) values (4, 2); -- user has ROLE_USER

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
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Novosadsko Pozorište', 'Pozoriste', 3.5,'3',19.8396023,45.2577829,'4');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Pozorište Promena', 'Pozoriste', 3.5,'3',19.8414954,45.2586126,'4');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Kulturni centar Vojvodine "Miloš Crnjanski"', 'Pozoriste', 3.5,'3',19.8509021,45.2553612,'4');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Novi Sad', 'Pozorište mladih Novi Sad', 'Pozoriste', 3.5,'3',19.8469346,45.2553273,'4');

INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Narodni muzej', 'Veliki muzej sa slavnom zbirkom italijanske umetnosti, drevnim artefaktima i drugim istorijskim predmetima.', 4.7,'3',20.4603766,44.8163324,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Muzej Zepter', 'U ovom muzeju, smeštenom u nekadašnjoj banci, izložena su srpska umetnička dela od sredine 20. veka do danas.', 3.5,'3',19.8469346,45.2553273,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Muzej iluzija Beograd', 'Pozoriste', 3.5,'3',20.4576921,44.8159822,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Jevrejski istorijski muzej', 'Zgrada u kojoj se nalazi Jevrejski istorijski muzej podignuta je 1928. godine za potrebe Jevrejske sefardske opštine u Beogradu.', 3.5,'3',20.458071,44.8180941,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Muzej nauke i tehnike', 'Na stalnoj postavci izloženo je preko četiri stotine predmeta iz fonda Muzeja nauke i tehnike.', 3.5,'3',20.458071,44.8180941,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Pozorište na Terazijama', 'Pozorište sa dobrim repertoarom za mjuzikle', 3.5,'3',20.4609505,44.8128011,'4');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Atelje 212', 'Pozoriste', 3.5,'3',20.46418,44.8131127,'4');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Narodno pozorište', 'Pozoriste', 3.5,'3',20.4603842,44.8153844,'4');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Petar Petrović Njegoš', 'Spomenik', 3.5,'2',20.4533183,44.8144723,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Spomenik knezu Mihailu', 'Spomenik', 3.5,'2',20.4533183,44.8144723,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Monument Borislav Pekic', 'Spomenik', 3.5,'2',20.4636798,44.8033659,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'Spomenik Miloradu Paviću', 'Spomenik', 3.5,'2',20.4542756,44.7994017,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Beograd', 'CLOUD Festivals', 'Festival', 3.5,'1',20.4393988,44.8124358,'1');

INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Subotica', 'Spomenik žrtvama fašizma Subotica', 'Spomenik, namenjen žrtvama fašizma za vreme drugog svetskog rata', 3.5,'2',19.665248,46.0978166,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Subotica', 'Grobnica Bele Farkaša', 'Spomenik', 3.5,'2',19.8469346,45.2553273,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Subotica', 'Spomenik Palim Borcima Drugog svetskog rata', 'Spomenik', 3.5,'2',19.6266713,46.0887827,'2');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Subotica', 'Pozorište "Deže Kostolanji"', 'Pozoriste', 3.5,'3',19.6642871,46.0982311,'4');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Subotica', 'Narodno pozoriste Subotica', 'Pozoriste', 3.5,'3',19.6642871,46.0982311,'4');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Subotica', 'Međunarodni Oldtajmer Klub "Subotica"', 'Muzej', 3.5,'3',19.6610076,46.0998525,'3');
INSERT INTO cultural_offerings (location, name, description, rating,category_id,latitude,longitude,category_type_id) VALUES ('Subotica', 'Gradski muzej', 'Muzej', 3.5,'3',19.6610076,46.0998525,'3');


--INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (1,1);
INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (2,2);
INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (2,3);
INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (2,5);
INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (2,6);
INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (2,7);
INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (2,8);
INSERT INTO user_cultural_offering (user_id, cultural_offering_id) VALUES (2,9);

insert into newsletter (id, title, content, date, cultural_offering_cultural_offering_id) values (93,'Novi muzej u novom sadu', 'Lorem Ipsook a galley ope specimen book. It unchanged. It was popularised in the 1960s with the release ofker including versions of Lorem Ipsum.', '01.01.2021.','1');
insert into newsletter (id, title, content, date, cultural_offering_cultural_offering_id) values (94,'Izlozbe ', 'Lorem Ipsook a galley ope specimen book. It unchanged. It was popularised in the 1960s with the release ofker including versions of Lorem Ipsum.', '01.01.2021.','1');
insert into newsletter (id, title, content, date, cultural_offering_cultural_offering_id) values (95,'EXIT NAJBOLJI', 'Lorem Ipsook a galley ope specimen book. It unchanged. It was popularised in the 1960s with the release ofker including versions of Lorem Ipsum.', '01.01.2021.','1');
insert into newsletter (id, title, content, date, cultural_offering_cultural_offering_id) values (96,'Dodjite kod nas!!!', 'Lorem Ipsook a galley ope specimen book. It unchanged. It was popularised in the 1960s with the release ofker including versions of Lorem Ipsum.', '01.01.2021.','1');
insert into newsletter (id, title, content, date, cultural_offering_cultural_offering_id) values (97,'Zvonko Bogdan potvrdio dolazak', 'Lorem Ipsook a galley ope specimen book. It unchanged. It was popularised in the 1960s with the release ofker including versions of Lorem Ipsum.', '01.01.2021.','1');

insert into newsletter (id, title, content, date, cultural_offering_cultural_offering_id) values (98,'Prava kulturna dobra', 'Lorem Ipsook a galley ope specimen book. It unchanged. It was popularised in the 1960s with the release ofker including versions of Lorem Ipsum.', '01.01.2021.','1');
insert into newsletter (id, title, content, date, cultural_offering_cultural_offering_id) values (99, 'Exit otkazan ove godine svi placu', 'Lorem Ipsook a galley ope specimen book. It unchanged. It was popularised in the 1960s with the release ofker including versions of Lorem Ipsum.', '05.12.2020.','1');