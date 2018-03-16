package model.beans;

import java.util.Date;

public class Administrateur extends Utilisateur {

    public Administrateur(int id, String identifiant, String nom, String prenom, Date naissance, String adresse) {
        super(id, identifiant, nom, prenom, naissance, adresse);
    }
}
