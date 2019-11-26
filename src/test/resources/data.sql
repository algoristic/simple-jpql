DELETE FROM Book;
DELETE FROM Author;

INSERT INTO Author(id, name) VALUES (1, 'H. P. Lovecraft');
INSERT INTO Author(id, name) VALUES (2, 'Kentaro Miura');
INSERT INTO Author(id, name) VALUES (3, 'Frank Schätzing');

INSERT INTO Book(id, title, year, author_id) VALUES (1, 'At The Mountains Of Madness', 1936, 1);
INSERT INTO Book(id, title, year, author_id) VALUES (2, 'The Shadow Over Innsmouth', 1936, 1);
INSERT INTO Book(id, title, year, author_id) VALUES (3, 'Berserk Vol. 1', 1990, 2);
INSERT INTO Book(id, title, year, author_id) VALUES (4, 'Berserk Vol. 3', 1991, 2);
INSERT INTO Book(id, title, year, author_id) VALUES (5, 'The Dunwich Horror', 1929, 1);
INSERT INTO Book(id, title, year, author_id) VALUES (6, 'The Call Of Cthulhu', 1928, 1);
INSERT INTO Book(id, title, year, author_id) VALUES (7, 'Berserk Vol. 4', null, 2);
INSERT INTO Book(id, title, year, author_id) VALUES (8, 'Berserk Vol. 5', 1993, 2);
INSERT INTO Book(id, title, year, author_id) VALUES (9, 'Berserk Vol. 2', 1991, 2);
INSERT INTO Book(id, title, year, author_id) VALUES (10, 'The Swarm', 2004, 3);