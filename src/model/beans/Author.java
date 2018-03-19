package model.beans;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.util.Date;


public class Author {
    private int idAuteur;
    private String nom;
    private String prenom;
    private Date naissance;
    private String nationalite;

    public Author(int idAuteur, String nom, String prenom, Date naissance, String nationalite) {
        this.idAuteur = idAuteur;
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.nationalite = nationalite;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }

    public String getNom() {
        return this.nom;
    }
    public String getPrenom(){
        return this.prenom;
    }
    public Date getNaissance(){
        return this.naissance;
    }
    public String getNationalite(){
        return this.nationalite;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }
    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public JsonObjectBuilder toJson() {
        String naissance = "";
        if(this.naissance != null) {
            naissance = this.naissance.toString();
        }

        return Json.createObjectBuilder()
                .add("idAuteur", this.idAuteur)
                .add("nom", this.nom)
                .add("prenom", this.prenom)
                .add("naissance", naissance)
                .add("nationalite", this.nationalite);
    }
}
