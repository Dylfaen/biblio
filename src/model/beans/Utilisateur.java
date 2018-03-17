package model.beans;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.util.Date;

public class Utilisateur {
    private int id;
    private String identifiant;
    private String nom;
    private String prenom;
    private Date naissance;
    private String adresse;
    private boolean isAdmin;

    public Utilisateur(int id, String identifiant, String nom, String prenom, Date naissance, String adresse, boolean isAdmin) {
        this.id = id;
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.adresse = adresse;
        this.isAdmin = isAdmin;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public JsonObjectBuilder toJson() {

        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("identifiant", this.identifiant)
                .add("nom", this.nom)
                .add("prenom", this.prenom)
                .add("naissance", this.naissance.toString())
                .add("adresse", this.adresse)
                .add("isAdmin", this.isAdmin);
    }
}
