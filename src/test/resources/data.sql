DELETE FROM Book;
INSERT INTO Book(id, title, year, author) VALUES (1, 'At The Mountains Of Madness', 1936, 'H. P. Lovecraft');
INSERT INTO Book(id, title, year, author) VALUES (2, 'The Shadow Over Innsmouth', 1936, 'H. P. Lovecraft');
INSERT INTO Book(id, title, year, author) VALUES (3, 'Berserk Vol. 1', 1990, 'Kentaro Miura');
INSERT INTO Book(id, title, year, author) VALUES (4, 'Berserk Vol. 3', 1991, 'Kentaro Miura');
INSERT INTO Book(id, title, year, author) VALUES (5, 'The Dunwich Horror', 1929, 'H. P. Lovecraft');
INSERT INTO Book(id, title, year, author) VALUES (6, 'The Call Of Cthulhu', 1928, 'H. P. Lovecraft');
INSERT INTO Book(id, title, year, author) VALUES (7, 'Berserk Vol. 4', 1992, 'Kentaro Miura');
INSERT INTO Book(id, title, year, author) VALUES (8, 'Berserk Vol. 5', 1993, 'Kentaro Miura');
INSERT INTO Book(id, title, year, author) VALUES (9, 'Berserk Vol. 2', 1991, 'Kentaro Miura');
INSERT INTO Book(id, title, year, author) VALUES (10, 'The Swarm', 2004, 'Frank Schätzing');

DELETE FROM Author;
INSERT INTO Author(id, name) VALUES (1, 'H. P. Lovecraft');
INSERT INTO Author(id, name) VALUES (2, 'Kentaro Miura');
INSERT INTO Author(id, name) VALUES (3, 'Frank Schätzing');
