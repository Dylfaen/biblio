import java.util.Date;

public class Utilisateur {
    private String nom;
    private String prenom;
    private Date naissance;
    private String adresse;





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
