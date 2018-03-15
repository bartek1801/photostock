DROP DATABASE IF EXISTS library;
CREATE DATABASE library CHARACTER SET utf8 COLLATE utf8_unicode_ci;

USE library;

CREATE TABLE authors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    nationality CHAR(2),
    date_of_birth DATE NOT NULL,
    date_of_death DATE
);

INSERT INTO authors (firstname, lastname, nationality, date_of_birth, date_of_death)
VALUES ('Adam', 'Mickiewicz', 'PL', '1798-12-24', '1855-11-26');

INSERT INTO authors (firstname, lastname, nationality, date_of_birth, date_of_death)
VALUES ('Juliusz', 'Slowacki', 'PL', '1809-09-04','1849-04-03');
INSERT INTO authors (firstname, lastname, nationality, date_of_birth, date_of_death)
VALUES ('Boleslaw', 'Prus', 'PL', '1847-08-20','1912-05-19');
INSERT INTO authors (firstname, lastname, nationality, date_of_birth, date_of_death)
VALUES ('Zygmunt', 'Krasinski', 'PL', '1812-02-19','1859-02-23');
INSERT INTO authors (firstname, lastname, nationality, date_of_birth, date_of_death)
VALUES ('Henryk', 'Sienkiewicz', 'PL', '1846-05-05','1916-11-15');
INSERT INTO authors (firstname, lastname, nationality, date_of_birth, date_of_death)
VALUES ('Andrzej', 'Sapkowski', 'PL', '1948-06-21',null);
INSERT INTO authors (firstname, lastname, nationality, date_of_birth, date_of_death)
VALUES ('Adam', 'Asnyk', 'PL', '1948-06-21',null);


INSERT INTO authors (firstname, lastname, nationality, date_of_birth, date_of_death)
VALUES ('George', 'Byron', 'GB', '1948-06-21',null);
INSERT INTO authors (firstname, lastname, nationality, date_of_birth, date_of_death)
VALUES ('Honire', 'De Balsac', 'FR', '1948-06-21',null);
INSERT INTO authors (firstname, lastname, nationality, date_of_birth, date_of_death)
VALUES ('Alexander', 'Dumas', 'FR', '1948-06-21',null);

SELECT * FROM authors WHERE firstname LIKE 'Adam';
SELECT * FROM authors WHERE nationality LIKE 'PL';
SELECT * FROM authors WHERE date_of_death IS NULL;
SELECT * FROM authors WHERE date_of_death IS NOT NULL;

SELECT nationality, firstname, min(id), max(id), count(*)
FROM authors
WHERE nationality != 'FR'
GROUP BY nationality, firstname
HAVING count(*) > 0
ORDER BY count(*)
;

SELECT * FROM authors
WHERE date_of_death IS NULL AND DATEDIFF(CURRENT_DATE, date_of_birth) > 50 * 365;

SELECT * FROM authors
WHERE date_of_death IS NOT NULL AND ABS(DATEDIFF(date_of_birth, date_of_death)) < 40 * 365;

CREATE TABLE addresses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(255),
    house_number VARCHAR(20) NOT NULL,
    flat_number VARCHAR(20),
    postal_code CHAR(6) NOT NULL,
    city VARCHAR(50) NOT NULL
);

CREATE TABLE clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    document_number VARCHAR(30) NOT NULL,
    pesel CHAR(11) NOT NULL,
    address_id INT NOT NULL,
    FOREIGN KEY (address_id) REFERENCES addresses(id)
);


INSERT INTO addresses (street, house_number, flat_number, postal_code, city)
VALUES ('ul. Polnocna', '10', '10', '20-100', 'Lublin');

INSERT INTO clients (firstname, lastname, document_number, pesel, address_id)
VALUES ('Jan', 'Nowak', '123456', '123456', 1);
INSERT INTO clients (firstname, lastname, document_number, pesel, address_id)
VALUES ('Janina', 'Nowak', '123456', '123456', 1);

CREATE TABLE genres (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50) NOT NULL);

INSERT INTO genres (name) VALUES ('audiobooki'), ('biografie'),
('biznes, ekonomia, marketing'), ('dla dzieci'), ('dla młodzieży'),
('fantastyka, horror'), ('historia'), ('informatyka'), ('komiks'), ('kryminał, sensacja, thriller'), ('książka regionalna'), ('kuchnia i diety'), ('lektury, pomoce szkolne'), ('literatura faktu, reportaż'), ('literatura obyczajowa'), ('literatura piękna obca'), ('literatura piękna polska'), ('nauka języków'), ('nauki społeczne i humanistyczne'), ('nauki ścisłe, medycyna'), ('podręczniki szkolne, edukacja'), ('poezja, aforyzm, dramat'), ('poradniki'), ('prawo'), ('religie i wyznania'), ('rozwój osobisty'), ('sport i wypoczynek'), ('sztuka'), ('turystyka i podróże'), ('zdrowie, rodzina, związki');

CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(50) NOT NULL,
    published_at DATE NOT NULL,
    genre_id INT NOT NULL,
    FOREIGN KEY (genre_id) REFERENCES genres(id)
);

CREATE TABLE books_authors (
    book_id INT NOT NULL,
    author_id INT NOT NULL,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (author_id) REFERENCES authors(id)
);

INSERT INTO books(title,isbn,published_at,genre_id)
VALUES ('Norweski dziennik', '123456789', '2005-10-09', 6);

INSERT INTO books(title,isbn,published_at,genre_id)
VALUES ('Oko jelenia', '125555559', '2006-12-09', 6);

INSERT INTO books VALUES ( NULL,'W pustyni i w puszczy' , '1234','1850-12-12',7);

INSERT INTO books
(title, isbn, published_at, genre_id)
VALUES
('The C++ Programming Language, 4th Edition', '978-83-246-8533-2', '2014-07-14', 12),
('C++. 50 efektywnych sposobów na udoskonalenie', '83-7361-345-5', '2003-11-27', 12),
('Wzorce projektowe. Leksykon kieszonkowy', '978-83-283-3999-6', '2017-09-18', 12),
('Thinking in C++. Edycja polska. Tom 2', '83-7361-409-5', '2004-03-30', 12);

INSERT INTO books VALUES (NULL, 'Opowieści dziwnej treści.','30-231-32-34112', '2017-11-11', 2),
(NULL , 'klsdoasaja askdkapda - język Fiński dla opornych','ds0-231-32-34112', '2432-11-11', 4),
(NULL , 'Straszne historie','30-2das31-32-34112', '1002-11-11', 12),
(NULL, 'Dla każdego coś miłego','30-231-32-34112', '2017-11-11', 15),
(NULL , 'Bardzo trudna książka o niczym','ds0-231-32-34112', '2432-11-11', 7),
(NULL , 'Poezja na trzeźwo!!!','30-2das31-32-34112', '1002-11-11', 1);

insert into books_authors values (1, 1), (1, 2), (2, 2), (3, 3), (4, 4), (5, 3), (6, 3), (7, 2), (8, 4), (8, 5), (8, 1), (9, 2), (10, 4), (11, 4), (12, 2), (13, 1);

--a) wszystkie książki z autorami i z gatunkiem literackim

SELECT books.title, CONCAT(authors.firstname, ' ', authors.lastname) as autor, genres.name
FROM books
JOIN genres ON genres.id = books.genre_id
JOIN books_authors ON books_authors.book_id = books.id
JOIN authors ON books_authors.author_id = authors.id;

--b) autorów i ich wsystkie książki, ale tylko tych, których książki są w bibliotece

SELECT CONCAT(a.firstname, ' ', a.lastname) AS 'author', b.title
FROM authors a
JOIN books_authors ba ON ba.author_id = a.id
JOIN books b ON b.id = ba.book_id
ORDER BY a.lastname;

--c) autorów i ich wsystkie książki, łącznie z tymi, których książek nie ma w bibliotece

SELECT CONCAT(a.firstname, ' ', a.lastname) AS 'author', b.title
FROM authors a
LEFT JOIN books_authors ba ON ba.author_id = a.id
LEFT JOIN books b ON b.id = ba.book_id
ORDER BY a.lastname;

--d) autorów, których co najmniej jedna książka znajduje się w bibliotece

SELECT DISTINCT CONCAT(a.firstname, ' ', a.lastname) AS 'author'
FROM authors a
JOIN books_authors ba ON ba.author_id = a.id;

--e) autorów, których książek nie ma w bibliotece

SELECT DISTINCT CONCAT(a.firstname, ' ', a.lastname) AS 'author'
FROM authors a
LEFT JOIN books_authors ba ON ba.author_id = a.id
WHERE books_authors.book_id IS NULL;

--f) autorów, którzy napisali chociaż jeden kryminał (bez powtórzeń jeśli napisali więcej niż jeden)
SELECT DISTINCT CONCAT(a.firstname, ' ', a.lastname) AS 'author'
FROM authors a
JOIN books_authors ba ON ba.author_id = a.id
JOIN books b ON b.id = ba.book_id
JOIN genres g ON g.id = b.genre_id
WHERE g.name LIKE 'kuchnia%';

DROP TABLE IF EXISTS specimens;

CREATE TABLE specimens (
  id INT AUTO_INCREMENT,
  book_id INT NOT NULL,
  code VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (book_id) REFERENCES books(id)
);

DROP TABLE IF EXISTS lendings;

CREATE TABLE lendings (
  id INT AUTO_INCREMENT,
  client_id INT NOT NULL,
  specimen_id INT NOT NULL,
  lending_date DATETIME NOT NULL DEFAULT NOW(),
  return_date DATETIME DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (client_id) REFERENCES clients(id),
  FOREIGN KEY (specimen_id) REFERENCES specimens(id)
);

SELECT count(distinct a.id)
FROM authors a
JOIN books_authors ba ON ba.author_id = a.id;

--c) książki, których co najmniej jeden egzemplarz jest dostępny do wypożycznia

INSERT INTO specimens VALUES (null, 1, '11');
INSERT INTO specimens VALUES (null, 1, '12');
INSERT INTO specimens VALUES (null, 2, '21');
INSERT INTO specimens VALUES (null, 3, '31');

INSERT INTO lendings VALUES (null, 1, 4, now(), null);
INSERT INTO lendings VALUES (null, 1, 1, now(), null);
INSERT INTO lendings VALUES (null, 1, 3, now(), now());

SELECT DISTINCT(b.title)
FROM books b
JOIN specimens s ON b.id = s.book_id
LEFT JOIN lendings l ON l.specimen_id = s.id
WHERE l.id IS NULL OR ((SELECT count(*) FROM lendings ll WHERE ll.return_date IS NULL AND ll.specimen_id = s.id) = 0)
;

SELECT genres.name, count(books.id)
FROM genres
LEFT JOIN books ON books.genre_id = genres.id
GROUP BY genres.name
ORDER BY name
;

SELECT genres.name, count(books.id) AS books_count
FROM genres
LEFT JOIN books ON books.genre_id = genres.id
GROUP BY genres.name
HAVING books_count >= 1
ORDER BY books_count DESC
;

SELECT concat(firstname, ' ', lastname) AS author_name, genres.name, count(books_authors.author_id)
FROM genres
CROSS JOIN authors
LEFT JOIN books_authors ON books_authors.author_id = authors.id
LEFT JOIN books ON books.id = books_authors.book_id
GROUP BY author_name, genres.name
;

SELECT concat(firstname, ' ', lastname) AS author_name, genres.name, count(books.id)
FROM genres
CROSS JOIN authors
LEFT JOIN books_authors ON books_authors.author_id = authors.id
LEFT JOIN books ON books.id = books_authors.book_id
WHERE books.genre_id = genres.id OR books.id IS NULL
GROUP BY author_name, genres.name
ORDER BY count(books.id) DESC
;

SELECT genres.name, (SELECT count(*) FROM books WHERE genres.id = books.genre_id) AS books_count
FROM genres
ORDER BY books_count DESC
;

SELECT genres.name
FROM genres
WHERE (SELECT count(*) FROM books WHERE genres.id = books.genre_id) > 0
;

UPDATE lendings
SET return_date = now()
WHERE id = 2;