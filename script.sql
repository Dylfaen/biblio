CREATE TABLE administrateur
(
  ref_admin INT NOT NULL
    PRIMARY KEY
)
  ENGINE = InnoDB;

CREATE TABLE auteur
(
  ref_auteur  INT AUTO_INCREMENT
    PRIMARY KEY,
  nom         VARCHAR(45) NULL,
  prenom      VARCHAR(45) NULL,
  naissance   DATE        NULL,
  nationalite VARCHAR(45) NULL
)
  ENGINE = InnoDB;

CREATE TABLE client
(
  ref_client INT NOT NULL
    PRIMARY KEY
)
  ENGINE = InnoDB;

CREATE TABLE emprunt
(
  id_user       INT  NOT NULL,
  id_exemplaire INT  NOT NULL,
  dateEmprunt   DATE NOT NULL,
  PRIMARY KEY (id_user, id_exemplaire, dateEmprunt)
)
  ENGINE = InnoDB;

CREATE INDEX exemplaire___fk
  ON emprunt (id_exemplaire);

CREATE TABLE exemplaire
(
  id_exemplaire INT AUTO_INCREMENT
    PRIMARY KEY,
  id_oeuvre     INT NULL
)
  ENGINE = InnoDB;

CREATE INDEX oeuvre___fk
  ON exemplaire (id_oeuvre);

ALTER TABLE emprunt
  ADD CONSTRAINT exemplaire___fk
FOREIGN KEY (id_exemplaire) REFERENCES exemplaire (id_exemplaire);

CREATE TABLE livre
(
  ref_livre INT         NOT NULL
    PRIMARY KEY,
  Editeur   VARCHAR(45) NULL
)
  ENGINE = InnoDB;

CREATE TABLE oeuvre
(
  ref_oeuvre INT AUTO_INCREMENT
    PRIMARY KEY,
  ref_auteur INT         NULL,
  titre      VARCHAR(45) NULL,
  CONSTRAINT auteur___fk
  FOREIGN KEY (ref_auteur) REFERENCES auteur (ref_auteur)
)
  ENGINE = InnoDB;

CREATE INDEX auteur___fk
  ON oeuvre (ref_auteur);

ALTER TABLE exemplaire
  ADD CONSTRAINT oeuvre___fk
FOREIGN KEY (id_oeuvre) REFERENCES oeuvre (ref_oeuvre);

ALTER TABLE livre
  ADD CONSTRAINT livre_oeuvre_ref_oeuvre_fk
FOREIGN KEY (ref_livre) REFERENCES oeuvre (ref_oeuvre);

CREATE TABLE user
(
  idUser      INT AUTO_INCREMENT
    PRIMARY KEY,
  identifiant VARCHAR(45) NOT NULL,
  password    VARCHAR(45) NOT NULL,
  nom         VARCHAR(45) NULL,
  prenom      VARCHAR(45) NULL,
  naissance   VARCHAR(45) NULL,
  adresse     VARCHAR(90) NULL
)
  ENGINE = InnoDB;

ALTER TABLE administrateur
  ADD CONSTRAINT administrateur_user_idUser_fk
FOREIGN KEY (ref_admin) REFERENCES user (idUser);

ALTER TABLE client
  ADD CONSTRAINT client_user_idUser_fk
FOREIGN KEY (ref_client) REFERENCES user (idUser);

ALTER TABLE emprunt
  ADD CONSTRAINT user___fk
FOREIGN KEY (id_user) REFERENCES user (idUser);


