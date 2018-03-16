package model.beans;

import java.io.IOException;
import java.util.Date;

public class Utilisateur {
    private int id;
    private String identifiant;
    private String nom;
    private String prenom;
    private Date naissance;
    private String adresse;

    public Utilisateur(int id, String identifiant, String nom, String prenom, Date naissance, String adresse) {
        this.id = id;
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public Date getNaissance() {
        return naissance;
    }
    public String getAdresse() {
        return adresse;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }
}
