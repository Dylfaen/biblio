INSERT INTO biblio.auteur (ref_auteur, nom, prenom, naissance, nationalite) VALUES (1, 'Tolkien', 'J.R.R', null, 'Britannique');
INSERT INTO biblio.auteur (ref_auteur, nom, prenom, naissance, nationalite) VALUES (2, 'Rowling', 'J.K.', null, 'Britannique');
INSERT INTO biblio.auteur (ref_auteur, nom, prenom, naissance, nationalite) VALUES (3, 'Hugo', 'Victor', null, 'Française');
INSERT INTO biblio.auteur (ref_auteur, nom, prenom, naissance, nationalite) VALUES (4, 'Camus', 'Albert', null, 'Française');
INSERT INTO biblio.client (ref_client) VALUES (1);
INSERT INTO biblio.emprunt (id_user, id_exemplaire, dateEmprunt) VALUES (1, 3, '2018-03-12');
INSERT INTO biblio.emprunt (id_user, id_exemplaire, dateEmprunt) VALUES (3, 10, '2018-03-10');
INSERT INTO biblio.emprunt (id_user, id_exemplaire, dateEmprunt) VALUES (1, 12, '2018-03-01');
INSERT INTO biblio.exemplaire (id_exemplaire, id_oeuvre) VALUES (1, 2);
INSERT INTO biblio.exemplaire (id_exemplaire, id_oeuvre) VALUES (2, 2);
INSERT INTO biblio.exemplaire (id_exemplaire, id_oeuvre) VALUES (3, 3);
INSERT INTO biblio.exemplaire (id_exemplaire, id_oeuvre) VALUES (4, 4);
INSERT INTO biblio.exemplaire (id_exemplaire, id_oeuvre) VALUES (5, 4);
INSERT INTO biblio.exemplaire (id_exemplaire, id_oeuvre) VALUES (6, 4);
INSERT INTO biblio.exemplaire (id_exemplaire, id_oeuvre) VALUES (7, 5);
INSERT INTO biblio.exemplaire (id_exemplaire, id_oeuvre) VALUES (8, 6);
INSERT INTO biblio.exemplaire (id_exemplaire, id_oeuvre) VALUES (9, 7);
INSERT INTO biblio.exemplaire (id_exemplaire, id_oeuvre) VALUES (10, 8);
INSERT INTO biblio.exemplaire (id_exemplaire, id_oeuvre) VALUES (11, 9);
INSERT INTO biblio.exemplaire (id_exemplaire, id_oeuvre) VALUES (12, 9);
INSERT INTO biblio.livre (ref_livre, Editeur) VALUES (3, 'Les jeunes picsous');
INSERT INTO biblio.oeuvre (ref_oeuvre, ref_auteur, titre) VALUES (2, 1, 'LSDA');
INSERT INTO biblio.oeuvre (ref_oeuvre, ref_auteur, titre) VALUES (3, 2, 'HP');
INSERT INTO biblio.oeuvre (ref_oeuvre, ref_auteur, titre) VALUES (4, 3, 'Les Fleurs du Mal');
INSERT INTO biblio.oeuvre (ref_oeuvre, ref_auteur, titre) VALUES (5, 4, 'L''Etranger');
INSERT INTO biblio.oeuvre (ref_oeuvre, ref_auteur, titre) VALUES (6, 3, 'Le bossu de Notre Dame');
INSERT INTO biblio.oeuvre (ref_oeuvre, ref_auteur, titre) VALUES (7, 2, 'L''Appel du Coucou');
INSERT INTO biblio.oeuvre (ref_oeuvre, ref_auteur, titre) VALUES (8, 4, 'Le Mythe de Sisyphe');
INSERT INTO biblio.oeuvre (ref_oeuvre, ref_auteur, titre) VALUES (9, 3, 'Les Miserables');
INSERT INTO biblio.user (idUser, identifiant, password, nom, prenom, naissance, adresse) VALUES (1, 'CCC', 'alexlafripouille', 'De carvalho', 'Alexandre', null, null);
INSERT INTO biblio.user (idUser, identifiant, password, nom, prenom, naissance, adresse) VALUES (3, 'AAA', 'cesarlerigolo', 'Fraisseix', 'Cesar', '22/04/1996', '30 rue de la paquerette');
INSERT INTO biblio.user (idUser, identifiant, password, nom, prenom, naissance, adresse) VALUES (4, 'BBB', 'gwenlebarbu', 'Rio', 'Gwenael', '01/01/1900', '1 rue du démon');