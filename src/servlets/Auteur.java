package servlets;
import java.util.Date;


public class Auteur {
    private String nom;
    private String prenom;
    private Date naissance;
    private String nationalite;

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
}
