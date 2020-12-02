

INSERT INTO user_table (email,password,username,role) VALUES ('adminovac@gmail.com','admin','admin','ADMIN');
INSERT INTO user_table (email,password,username,role) VALUES ('mile_moler@gmail.com','miletovasifra','mile','CUSTOMER');
INSERT INTO user_table (email,password,username,role) VALUES ('rade_pekar@gmail.com','123','rade','CUSTOMER');

INSERT INTO category_table (name) VALUES ('Events');
INSERT INTO category_table (name) VALUES ('Cultural goods');
INSERT INTO category_table (name) VALUES ('Institutions');

INSERT INTO category_type_table (name, category_id) VALUES ('Festivals', '1');
INSERT INTO category_type_table (name, category_id) VALUES ('Monuments', '2');
INSERT INTO category_type_table (name, category_id) VALUES ('Museums', '3');