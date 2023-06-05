/*--Diseño de las tabla de la BBDD*/

/*Comentarios multilinea
  se ponen asi
 */


INSERT INTO record_company (name) VALUES ('Universal Music Group');
INSERT INTO record_company (name) VALUES ('Sony Music Entertainment');
INSERT INTO record_company (name) VALUES ('Warner Music Group');
INSERT INTO record_company (name) VALUES ('BMG Rights Management');
INSERT INTO record_company (name) VALUES ('Capitol Music Group');
INSERT INTO record_company (name) VALUES ('Atlantic Records');
INSERT INTO record_company (name) VALUES ('RCA Records');
INSERT INTO record_company (name) VALUES ('Sub Pop Records');
INSERT INTO record_company (name) VALUES ('Def Jam Recordings');
INSERT INTO record_company (name) VALUES ('Virgin Records');

INSERT INTO magazine_publisher (name) VALUES ('Prisa Revistas');
INSERT INTO magazine_publisher (name) VALUES ('Hearst España');
INSERT INTO magazine_publisher (name) VALUES ('RBA Revistas');
INSERT INTO magazine_publisher (name) VALUES ('Grupo Zeta');
INSERT INTO magazine_publisher (name) VALUES ('G+J España');

INSERT INTO language (name) VALUES ('Español');
INSERT INTO language (name) VALUES ('Catalán');
INSERT INTO language (name) VALUES ('Inglés');
INSERT INTO language (name) VALUES ('Francés');
INSERT INTO language (name) VALUES ('Italiano');
INSERT INTO language (name) VALUES ('Portugués');
INSERT INTO language (name) VALUES ('Alemán');
INSERT INTO language (name) VALUES ('Chino');
INSERT INTO language (name) VALUES ('Japonés');
INSERT INTO language (name) VALUES ('Ruso');

INSERT INTO literary_genre (name) VALUES ('Thriller');
INSERT INTO literary_genre (name) VALUES ('Histórico');
INSERT INTO literary_genre (name) VALUES ('Terror');
INSERT INTO literary_genre (name) VALUES ('Poesía');
INSERT INTO literary_genre (name) VALUES ('Novela');
INSERT INTO literary_genre (name) VALUES ('Cuento');
INSERT INTO literary_genre (name) VALUES ('Biografía');
INSERT INTO literary_genre (name) VALUES ('Ensayo');
INSERT INTO literary_genre (name) VALUES ('Informática');
INSERT INTO literary_genre (name) VALUES ('Ciencia Ficción');
INSERT INTO literary_genre (name) VALUES ('Infantil');
INSERT INTO literary_genre (name) VALUES ('Ciencia');
INSERT INTO literary_genre (name) VALUES ('Teatro');
INSERT INTO literary_genre (name) VALUES ('Deporte');
INSERT INTO literary_genre (name) VALUES ('Juegos');
INSERT INTO literary_genre (name) VALUES ('Arte');
INSERT INTO literary_genre (name) VALUES ('Comedia');
INSERT INTO literary_genre (name) VALUES ('Romance');
INSERT INTO literary_genre (name) VALUES ('Aventuras');
INSERT INTO literary_genre (name) VALUES ('Ajedrez');


INSERT INTO music_genre (name) VALUES ('Rock');
INSERT INTO music_genre (name) VALUES ('Pop');
INSERT INTO music_genre (name) VALUES ('Rap');
INSERT INTO music_genre (name) VALUES ('Electrónica');
INSERT INTO music_genre (name) VALUES ('Reggae');
INSERT INTO music_genre (name) VALUES ('Jazz');
INSERT INTO music_genre (name) VALUES ('Blues');
INSERT INTO music_genre (name) VALUES ('Clásica');
INSERT INTO music_genre (name) VALUES ('Folk');
INSERT INTO music_genre (name) VALUES ('Metal');
INSERT INTO music_genre (name) VALUES ('Country');

INSERT INTO movie_genre (name) VALUES ('Acción');
INSERT INTO movie_genre (name) VALUES ('Comedia');
INSERT INTO movie_genre (name) VALUES ('Drama');
INSERT INTO movie_genre (name) VALUES ('Bélica');
INSERT INTO movie_genre (name) VALUES ('Western');
INSERT INTO movie_genre (name) VALUES ('Terror');
INSERT INTO movie_genre (name) VALUES ('Romántica');
INSERT INTO movie_genre (name) VALUES ('Ciencia Ficción');
INSERT INTO movie_genre (name) VALUES ('Animación');
INSERT INTO movie_genre (name) VALUES ('Aventuras');
INSERT INTO movie_genre (name) VALUES ('Thriller');
INSERT INTO movie_genre (name) VALUES ('Infantil');
INSERT INTO movie_genre (name) VALUES ('Documental');

INSERT INTO magazine_subject (name) VALUES ('Informática');
INSERT INTO magazine_subject (name) VALUES ('Literatura');
INSERT INTO magazine_subject (name) VALUES ('Moda');
INSERT INTO magazine_subject (name) VALUES ('Cocina');
INSERT INTO magazine_subject (name) VALUES ('Ciencia');
INSERT INTO magazine_subject (name) VALUES ('Cultura');
INSERT INTO magazine_subject (name) VALUES ('Economía');
INSERT INTO magazine_subject (name) VALUES ('Música');
INSERT INTO magazine_subject (name) VALUES ('Educación');
INSERT INTO magazine_subject (name) VALUES ('Viajes');
INSERT INTO magazine_subject (name) VALUES ('Deportes');
INSERT INTO magazine_subject (name) VALUES ('Arte');
INSERT INTO magazine_subject (name) VALUES ('Política');
INSERT INTO magazine_subject (name) VALUES ('Aficiones');
INSERT INTO magazine_subject (name) VALUES ('Tecnología');
INSERT INTO magazine_subject (name) VALUES ('Juegos de Mesa');

INSERT INTO book_publisher (name) VALUES ('Planeta');
INSERT INTO book_publisher (name) VALUES ('Grupo Anaya');
INSERT INTO book_publisher (name) VALUES ('Grupo Santillana');
INSERT INTO book_publisher (name) VALUES ('Ediciones SM');
INSERT INTO book_publisher (name) VALUES ('Editorial Crítica');

INSERT INTO role (type) VALUES ('Writer');
INSERT INTO role (type) VALUES ('Actor');
INSERT INTO role (type) VALUES ('Musician');
INSERT INTO role (type) VALUES ('Director');

INSERT INTO month (name) VALUES ('Enero');
INSERT INTO month (name) VALUES ('Febrero');
INSERT INTO month (name) VALUES ('Marzo');
INSERT INTO month (name) VALUES ('Abril');
INSERT INTO month (name) VALUES ('Mayo');
INSERT INTO month (name) VALUES ('Junio');
INSERT INTO month (name) VALUES ('Julio');
INSERT INTO month (name) VALUES ('Agosto');
INSERT INTO month (name) VALUES ('Septiembre');
INSERT INTO month (name) VALUES ('Octubre');
INSERT INTO month (name) VALUES ('Noviembre');
INSERT INTO month (name) VALUES ('Diciembre');

INSERT INTO author (name, surnames, role) VALUES ('Gabriel', 'Garcia Marquez', 1);
INSERT INTO author (name, surnames, role) VALUES ('Mario', 'Vargas Llosa', 1);
INSERT INTO author (name, surnames, role) VALUES ('Isabel', 'Allende', 1);
INSERT INTO author (name, surnames, role) VALUES ('Pablo', 'Neruda', 1);
INSERT INTO author (name, surnames, role) VALUES ('Octavio', 'Paz', 1);

INSERT INTO author (name, surnames, role) VALUES ('Diane', 'Keaton', 2);
INSERT INTO author (name, surnames, role) VALUES ('Harrison', 'Ford', 2);
INSERT INTO author (name, surnames, role) VALUES ('Al', 'Pacino', 2);
INSERT INTO author (name, surnames, role) VALUES ('Marlon', 'Brando', 2);
INSERT INTO author (name, surnames, role) VALUES ('Sean', 'Connery', 2);

INSERT INTO author (name, surnames, role) VALUES ('Rosalia', '', 3);
INSERT INTO author (name, surnames, role) VALUES ('Bad Bunny', '', 3);
INSERT INTO author (name, surnames, role) VALUES ('The Beatles', '', 3);
INSERT INTO author (name, surnames, role) VALUES ('Metallica', '', 3);
INSERT INTO author (name, surnames, role) VALUES ('Billie Eilish', '', 3);

INSERT INTO author (name, surnames, role) VALUES ('Steven', 'Spielberg', 4);
INSERT INTO author (name, surnames, role) VALUES ('Guillermo', 'del Toro', 4);
INSERT INTO author (name, surnames, role) VALUES ('Martin', 'Scorsese', 4);
INSERT INTO author (name, surnames, role) VALUES ('Quentin', 'Tarantino', 4);
INSERT INTO author (name, surnames, role) VALUES ('Francis Ford', 'Coppola', 4);

INSERT INTO location (name,address,phone) VALUES ('Don Quijote','Diputación 54', 934567835);
INSERT INTO location (name,address,phone) VALUES ('Sherlock Holmes','Diagonal 661', 934823147);
INSERT INTO location (name,address,phone) VALUES ('Hamlet','Balmes 39', 934424423);
INSERT INTO location (name,address,phone) VALUES ('Robinson Crusoe','Gran Via 234', 934467259);
INSERT INTO location (name,address,phone) VALUES ('Oliver Twist','Sardenya 229', 934122012);

INSERT INTO status_copy (type) VALUES ('Disponible');
INSERT INTO status_copy (type) VALUES ('Reservado');
INSERT INTO status_copy (type) VALUES ('Prestado');
INSERT INTO status_copy (type) VALUES ('Perdido');
INSERT INTO status_copy (type) VALUES ('Baja');

INSERT INTO resource (published_year,title) VALUES (1970,'Cien años de soledad');
INSERT INTO book (id,isbn,pages,id_genre,id_idioma,id_publisher) VALUES (1,18298192,200,5,1,1);
INSERT INTO book_writer (id_writer,id_book) VALUES (1,1);
INSERT INTO resource (published_year,title) VALUES (1986,'Master of Puppets');
INSERT INTO album (duration,id_resource,id_genre,id_musician,id_record_company) VALUES (75,2,1,14,2);
INSERT INTO resource (published_year,title) VALUES (1989,'Indiana Jones y la Última Cruzada');
INSERT INTO movie (id,duration,id_director,id_genre,id_language) VALUES (3,127,16,10,1);
INSERT INTO movie_actor (id_movie,id_actor) VALUES (3,7);
INSERT INTO movie_actor (id_movie,id_actor) VALUES (3,10);
INSERT INTO resource (published_year,title) VALUES (2018,'Muy Interesante');
INSERT INTO magazine (id,number,pages,id_language,id_month,id_publisher,id_subject) VALUES (4,310,120,1,6,5,5);


INSERT INTO copy (registration_date,id_location,id_resource,id_status) VALUES ('2022-04-30',1,1,1);
INSERT INTO copy (registration_date,id_location,id_resource,id_status) VALUES ('2021-03-10',3,2,1);
INSERT INTO copy (registration_date,id_location,id_resource,id_status) VALUES ('2022-04-30',1,3,1);
INSERT INTO copy (registration_date,id_location,id_resource,id_status) VALUES ('2021-03-10',2,4,1);



INSERT INTO user_role (type) VALUES ('Admin');
INSERT INTO user_role (type) VALUES ('Librarian');
INSERT INTO user_role (type) VALUES ('User');

INSERT INTO user (address,email,name,surnames,nif,phone,registration_date,username,password,id_user_role)
VALUES ('a','admin@mail.com','Paco','Jones','11111111A',1,'2023-01-01','admin','GS2eDNcu0Ag8YZnctqI2JXkpej1DyS/VS/d7nR725YQ+DZ0W36KHS2iBtQFKRjzV',1);
INSERT INTO user (address,email,name,surnames,nif,phone,registration_date,username,password,id_user_role)
VALUES ('a','admin@mail.com','Jose','Alcaide','22222222B',1,'2023-01-01','librarian','GS2eDNcu0Ag8YZnctqI2JXkpej1DyS/VS/d7nR725YQ+DZ0W36KHS2iBtQFKRjzV',2);
INSERT INTO user (address,email,name,surnames,nif,phone,registration_date,username,password,id_user_role)
VALUES ('a','sergeitorres@gmail.com','Juan','Garcia','33333333C',1,'2023-01-01','jgarcia','GS2eDNcu0Ag8YZnctqI2JXkpej1DyS/VS/d7nR725YQ+DZ0W36KHS2iBtQFKRjzV',3);
INSERT INTO user (address,email,name,surnames,nif,phone,registration_date,username,password,id_user_role)
VALUES ('a','sergeitorres@gmail.com','Pedro','Ramirez','44444444D',1,'2023-01-01','pramirez','GS2eDNcu0Ag8YZnctqI2JXkpej1DyS/VS/d7nR725YQ+DZ0W36KHS2iBtQFKRjzV',3);
INSERT INTO user (address,email,name,surnames,nif,phone,registration_date,username,password,id_user_role)
VALUES ('a','sergeitorres@gmail.com','Sergi','Torres','40300550R',1,'2023-01-01','storres','GS2eDNcu0Ag8YZnctqI2JXkpej1DyS/VS/d7nR725YQ+DZ0W36KHS2iBtQFKRjzV',3);
INSERT INTO user (address,email,name,surnames,nif,phone,registration_date,username,password,id_user_role)
VALUES ('a','sergeitorres@gmail.com','Ana','Gonzalez','42321324L',1,'2023-01-01','agonzalez','GS2eDNcu0Ag8YZnctqI2JXkpej1DyS/VS/d7nR725YQ+DZ0W36KHS2iBtQFKRjzV',3);

/*Datos Préstamos Recursos */
INSERT INTO transaction_status (type) VALUES ("Prestamo en curso");
INSERT INTO transaction_status (type) VALUES ("Prestamo finalizado");
INSERT INTO transaction_status (type) VALUES ("Prestamo vencido");
INSERT INTO transaction_status (type) VALUES ("Reserva en curso");
INSERT INTO transaction_status (type) VALUES ("Reserva finalizada");

INSERT INTO transaction (type,start_date,end_date,id_copy,id_status,id_user) VALUES (2,'2023-05-23','2023-06-05',1,1,3);



