-- ---------------------------------------------------------------------------------------------------
-- - Reconstruction de la base de données
-- ---------------------------------------------------------------------------------------------------

DROP DATABASE IF EXISTS EcomFormation;
CREATE DATABASE EcomFormation;
USE EcomFormation;

-- ---------------------------------------------------------------------------------------------------
-- - Construction des formations en vente
-- ---------------------------------------------------------------------------------------------------

CREATE TABLE T_Formations (
	IdFormation 	INT(4)			PRIMARY KEY	AUTO_INCREMENT,
	Name			VARCHAR(50) 	NOT NULL,
	Description		VARCHAR(100)	NOT NULL,
	Duration		INT(3)			NOT NULL,
	IsRemoteWork	BOOLEAN			NOT NULL DEFAULT false,
	Price			FLOAT(8)		NOT NULL DEFAULT 0
) ENGINE = InnoDB;

-- ---------------------------------------------------------------------------------------------------
-- - Construction des catégories
-- ---------------------------------------------------------------------------------------------------

CREATE TABLE T_Categories (
	IdCategory 		INT(4)		PRIMARY KEY	AUTO_INCREMENT,
	Title			VARCHAR(30)	NOT NULL
) ENGINE = InnoDB;

-- ---------------------------------------------------------------------------------------------------
-- - Relation formation/catégorie
-- ---------------------------------------------------------------------------------------------------

ALTER TABLE T_Formations ADD COLUMN IdCategory INT(4);
ALTER TABLE T_Formations ADD FOREIGN KEY(IdCategory) REFERENCES T_Categories(IdCategory);

-- ---------------------------------------------------------------------------------------------------
-- - Construction des utilisateurs
-- ---------------------------------------------------------------------------------------------------

CREATE TABLE T_Users (
	IdUser			INT(4)		PRIMARY KEY AUTO_INCREMENT,
	LoginUser		VARCHAR(20)	NOT NULL UNIQUE,
	Password		VARCHAR(20)	NOT NULL,
	IsAdmin			BOOLEAN 	NOT NULL DEFAULT false
) ENGINE = InnoDB;

-- ---------------------------------------------------------------------------------------------------
-- - Construction des clients
-- ---------------------------------------------------------------------------------------------------

CREATE TABLE T_Customers (
	IdCustomer		INT(4)	PRIMARY KEY AUTO_INCREMENT,
	LastName		VARCHAR(20)	NOT NULL,
	FirstName		VARCHAR(20)	NOT NULL,
	Email			VARCHAR(50)	NOT NULL,
	Address			VARCHAR(50)	NOT NULL,
	Phone			VARCHAR(10)	NOT NULL
) ENGINE = InnoDB;

-- ---------------------------------------------------------------------------------------------------
-- - Relation client/utilisateur
-- ---------------------------------------------------------------------------------------------------

ALTER TABLE T_Customers ADD COLUMN IdUser INT(4);
ALTER TABLE T_Customers ADD FOREIGN KEY(IdUser) REFERENCES T_Users(IdUser);

-- ---------------------------------------------------------------------------------------------------
-- - Construction des commandes
-- ---------------------------------------------------------------------------------------------------

CREATE TABLE T_Orders (
	IdOrder			INT(4)		PRIMARY KEY AUTO_INCREMENT,
	Date			DATE		NOT NULL DEFAULT CURRENT_DATE,
	TotalPrice		FLOAT(8)	NOT NULL DEFAULT 0,
	PaymentOK		BOOLEAN		DEFAULT false
) ENGINE = InnoDB;

-- ---------------------------------------------------------------------------------------------------
-- - Relation commande/client
-- ---------------------------------------------------------------------------------------------------

ALTER TABLE T_Orders ADD COLUMN IdCustomer INT(4);
ALTER TABLE T_Orders ADD FOREIGN KEY(IdCustomer) REFERENCES T_Customers(IdCustomer);

-- ---------------------------------------------------------------------------------------------------
-- - Construction de la table de jointure commande/formation
-- ---------------------------------------------------------------------------------------------------

CREATE TABLE T_FormationOrders (
	IdFormationOrder	INT(4)		PRIMARY KEY AUTO_INCREMENT,
	IdFormation			INT(4)		NOT NULL,
	IdOrder				INT(4)		NOT NULL,
	Quantity			INT(2) 		NOT NULL DEFAULT 1
) ENGINE = InnoDB;

-- ---------------------------------------------------------------------------------------------------
-- - Remplissage des tables
-- ---------------------------------------------------------------------------------------------------

INSERT INTO T_Categories(Title) VALUES('Programmation');
INSERT INTO T_Categories(Title) VALUES('CMS');
INSERT INTO T_Categories(Title) VALUES('Web');
INSERT INTO T_Categories(Title) VALUES('Bureautique');
INSERT INTO T_Categories(Title) VALUES('IA');
INSERT INTO T_Categories(Title) VALUES('Cybersécurité');
INSERT INTO T_Categories(Title) VALUES('Design & Photo');
INSERT INTO T_Categories(Title) VALUES('Conception');

INSERT INTO T_Formations(Name, Description, Duration, IsRemoteWork, Price, IdCategory) VALUES('Apprendre Photoshop de -1', 'Vous allez maîtriser la retouche photo mieux que Midjourney', 30, true, 399, 7);
INSERT INTO T_Formations(Name, Description, Duration, IsRemoteWork, Price, IdCategory) VALUES('Java adapté pour les accros au café', 'Vous pourrez programmer votre cafetière pour qu''elle vous l''apporte toute seule', 200, false, 1299, 1);
INSERT INTO T_Formations(Name, Description, Duration, IsRemoteWork, Price, IdCategory) VALUES('Changer les couches d''une app est un jeu d''enfant', 'Vous serez le papa du dev', 45, true, 749, 8);
INSERT INTO T_Formations(Name, Description, Duration, IsRemoteWork, Price, IdCategory) VALUES('Maîtriser l''IA comme elle-même en 2050', 'Vous surpasserez le monde', 30, false, 7499, 5);
INSERT INTO T_Formations(Name, Description, Duration, IsRemoteWork, Price, IdCategory) VALUES('UML pour Une Majorité Larguée', 'Vous retrouverez une clarté mentale avec classe', 60, true, 79, 8);
INSERT INTO T_Formations(Name, Description, Duration, IsRemoteWork, Price, IdCategory) VALUES('Word de père office', 'Vous pourrez transmettre facilement vos compétences en rédaction CV', 7, true, 279, 4);
INSERT INTO T_Formations(Name, Description, Duration, IsRemoteWork, Price, IdCategory) VALUES('Votre pierre angular', 'Cette formation sera votre bijou précieux dans votre quête', 120, false, 39, 3);

INSERT INTO T_Users(LoginUser, Password) VALUES('agoncalves', 'peugeot206');


-- ---------------------------------------------------------------------------------------------------
-- - Affichage pour vérification
-- ---------------------------------------------------------------------------------------------------

SHOW TABLES;
DESCRIBE T_Formations;
SELECT * FROM T_Formations;
DESCRIBE T_Categories;
SELECT * FROM T_Categories;
DESCRIBE T_Users;
SELECT * FROM T_Users;
DESCRIBE T_Customers;
SELECT * FROM T_Customers;
DESCRIBE T_Orders;
SELECT * FROM T_Orders;
DESCRIBE T_FormationOrders;
SELECT * FROM T_FormationOrders;

------------------------------------------------------------------------------------------------------
-- - Création de l'utilisateur responsable de notre BDD et attribution permissions
-- ---------------------------------------------------------------------------------------------------

CREATE USER 'ecom_master'@'localhost' IDENTIFIED BY 'form999';
GRANT ALL PRIVILEGES ON EcomFormation.* TO 'antho'@'localhost';
FLUSH PRIVILEGES;