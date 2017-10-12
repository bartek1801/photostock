 CREATE TABLE authors (
      id INT AUTO_INCREMENT PRIMARY KEY,
      firstname VARCHAR(20) NOT NULL,
      lastname VARCHAR(50)NOT NULL,
      nationality CHAR(2),
      date_of_birth DATE NOT NULL,
      date_of_death DATE
);

INSERT INTO authors (firstname, lastname, nationality, date_of_birth, date_of_death)
VALUES ('Adam', 'Mickiewicz', 'PL', '1798-12-24', '1855-12-26');

INSERT INTO authors (firstname, lastname, nationality, date_of_birth, date_of_death) VALUES ('Juliusz', 'Słowacki', 'PL', '1809-09-04', '1849-04-03' );
INSERT INTO authors (firstname, lastname, nationality, date_of_birth, date_of_death) VALUES ('Bolesław', 'Prus', 'PL', '1847-08-20', '1912-05-19');
INSERT INTO authors (firstname, lastname, nationality, date_of_birth, date_of_death) VALUES ('Zygmunt', 'Krasiński', 'PL', '1812-02-19', '1859-02-23');
INSERT INTO authors (firstname, lastname, nationality, date_of_birth, date_of_death) VALUES ('Henryk', 'Skienkiewicz', 'PL', '1846-05-05', '1916-11-15' );
INSERT INTO authors (firstname, lastname, nationality, date_of_birth) VALUES ('Andrzej', 'Sapkowski', 'PL', '1948-06-21');
INSERT INTO authors (firstname, lastname, nationality, date_of_birth) VALUES ('Andrzej', 'Pilipiuk', 'PL', '1974-03-20');
INSERT INTO authors (firstname, lastname, nationality, date_of_birth) VALUES ('George', 'Martin', 'US', '1974-00-00');

SELECT * FROM authors WHERE firstname = 'Andrzej';
SELECT * FROM authors WHERE nationality = 'PL';
SELECT * FROM authors WHERE lastname LIKE 'Kra%';
SELECT * FROM authors WHERE lastname LIKE '%a%';
SELECT * FROM authors WHERE nationality IN ('PL', 'US');
SELECT * FROM authors WHERE date_of_death IS NULL;
SELECT * FROM authors WHERE date_of_death IS NOT NULL;
SELECT id, CONCAT(firstname, ' ', lastname) AS name FROM authors;

-- 3. Napisz zapytana do tabeli autorów wyświetlające:
    -- a) wszystkich autorów
    SELECT * FROM authors;
 --   b) autorów o imieniu Adam
    SELECT * FROM authors WHERE firstname LIKE 'Adam';
    SELECT * FROM authors WHERE firstname = 'Adam';
 --   c) autorów narodowści polskiej
    SELECT * FROM authors WHERE nationality = 'PL';
  --  d) żyjących autorów
    SELECT * FROM authors WHERE date_of_death IS NOT NULL;
 --   e) nieżyjących autorów
    SELECT * FROM authors WHERE date_of_death IS NULL:
   -- f) autorów żyjących w wieku powyżej 50 lat
    SELECT * FROM authors WHERE date_of_death IS NULL AND DATEDIFF(CURDATE(), date_of_birth) > 50 * 365 ;
    SELECT * FROM authors WHERE date_of_death IS NULL AND DATEDIFF(CURDATE(), > 50 * 365 ;
  --  g) autorów nieżyjących, którzy zmarli w wieku poniżej 40 lat
    SELECT * FROM authors WHERE date_of_death IS NOT NULL AND SUBSTRING(date_of_death,1,4) - SUBSTRING(date_of_birth,1,4) > 40;
    SELECT * FROM authors WHERE (date_of_death IS NOT NULL) AND (DATEDIFF(date_of_death,date_of_birth) > 40 * 365);
    SELECT *,FLOOR(DATEDIFF(date_of_death, date_of_birth)/365) AS długość_życia FROM authors
    WHERE (date_of_death IS NOT NULL) AND (DATEDIFF(date_of_death,date_of_birth) > 40 * 365);
   -- h) wszystkich autorów sortowanych po nazwisku rosnąco
    SELECT * FROM authors ORDER BY lastname
   -- i) wszystkich autorów sortowanych po nazwisku i imieniu rosnąco
    SELECT * FROM authors ORDER BY lastname, firstname;
   -- j) wszystkich autorów sortowanych po dacie śmierci malejąco, imieniu i nazwisku rosnąco
    SELECT * FROM authors ORDER BY date_of_death DESC AND lastname, firstname ASC;
   -- k) tak jak w h ale tylko 2 pierwszych
    SELECT * FROM authors ORDER BY lastname LIMIT 2 ;
  --  l) tak jak w h ale tylko drugą stronę przy założeniu że mamy 2 autorów na stronę
    SELECT * FROM authors ORDER BY lastname LIMIT 2 OFFSET 2 ;

-- 4. Stwórz tabelę przechowującą adresy
 -- identyfikator
 --ulica
 -- nr domu
 -- nr mieszkania
 -- kod pocztowy
 -- miasto
CREATE TABLE addresses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    house_number VARCHAR(20) NOT NULL,
    flat_number INT,
    postal_code CHAR(6) NOT NULL,
    city VARCHAR(50) NOT NULL
);