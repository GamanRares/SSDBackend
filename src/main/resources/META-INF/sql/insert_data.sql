INSERT INTO Role (Name) VALUES ('ADMINISTRATOR');
INSERT INTO Role (Name) VALUES ('GAMER');

INSERT INTO User (Username, Active, Email, FirstName, LastName, Password, Role) VALUES ('rares',TRUE,'rares_gamanrares@yahoo.com','rares','gaman','1234','ADMINISTRATOR');
INSERT INTO User (Username, Active, Email, FirstName, LastName, Password, Role) VALUES ('rares23',FALSE,'rares_gamanrares@yahoo.com','rares23','1234','gaman','GAMER');
INSERT INTO User (Username, Active, Email, FirstName, LastName, Password, Role) VALUES ('rares45',TRUE,'rares_gamanrares@yahoo.com','rares45','1234','gaman','GAMER');
INSERT INTO User (Username, Active, Email, FirstName, LastName, Password, Role) VALUES ('rares765',FALSE,'rares_gamanrares@yahoo.com','rares765','1234','gaman','ADMINISTRATOR');

INSERT INTO Game (Name) VALUES ('snake');
INSERT INTO Game (Name) VALUES ('tic-tac-toe');
INSERT INTO Game (Name) VALUES ('shooter');

INSERT INTO UserGame (Score, GAME_Name, USER_Username) VALUES (1000, 'snake', 'rares');
INSERT INTO UserGame (Score, GAME_NAME, USER_USERNAME) VALUES (3333, 'shooter', 'rares');
INSERT INTO UserGame (Score, GAME_NAME, USER_USERNAME) VALUES (10030, 'snake', 'rares765');
INSERT INTO UserGame (Score, GAME_NAME, USER_USERNAME) VALUES (454, 'tic-tac-toe', 'rares23');
