
INSERT INTO MOVIES (title, runtime, genre, imdb_score, rating)
VALUES ('Howard the Duck', 110, 'Sci-Fi', 4.6, 'PG'),
('Lavalantula', 83, 'Horror', 4.7, 'TV-14'),
('Starship Troopers', 129, 'Sci-Fi', 7.2, 'PG-13'),
('Waltz With Bashir', 90, 'Documentary', 8.0, 'R'),
('Spaceballs', 96, 'Comedy', 7.1, 'PG'),
('Monsters Inc.', 92, 'Animation', 8.1, 'G'),
('Spirited Away', 125, 'Animation', 8.6, 'G'),
('Reservoir Dogs', 96, 'Drama', 8.3, 'R'),
('Mean Girls', 97, 'Comedy', 7.0, 'PG-13');

SELECT *
    FROM MOVIES
    WHERE genre = 'Sci-Fi';
SELECT *
    FROM MOVIES
    WHERE imdb_score = 6.5;
SELECT *
    FROM MOVIES
    WHERE rating = 'G' OR 'PG'
    HAVING runtime < 100;
--where rating = 'G' OR 'PG' AND runtime < 100;
UPDATE MOVIES
    SET id = 3, title = 'Starship Troopers', runtime = 129, genre = 'Sci-Fi', imdb_score = 7.2, rating = 'R'
    WHERE title = 'Starship Troopers';
SELECT id, rating
    FROM MOVIES
    WHERE genre = 'Horror' OR genre = 'Documentary';
SELECT rating AVG(imdb_score), MIN(imdb_score), MAX(imdb_score)
    FROM MOVIES
    GROUP BY rating;
SELECT *
    FROM MOVIES
    HAVING COUNT(rating) > 1;
DELETE FROM MOVIES
    WHERE rating = 'R';

INSERT INTO PERSON (FIRST_NAME, LAST_NAME, MOBILE, BIRTHDAY)
VALUES ('Jen', 'Giroso', '302', null),
('Jeremy', 'Douglas', '302', null),
('James', 'Douglas', '302', null);
