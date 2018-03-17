package model.beans;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;

public class Oeuvre {
    private int idOeuvre;
    private Auteur auteur;
    private String titre;
    private ArrayList<Exemplaire> exemplaires;

    public Oeuvre(int idOeuvre, Auteur auteur, String titre, ArrayList<Exemplaire> exemplaires) {
        this.idOeuvre = idOeuvre;
        this.auteur = auteur;
        this.titre = titre;
        this.exemplaires = exemplaires;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(int idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public ArrayList<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(ArrayList<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }

    public JsonObjectBuilder toJson() {

        JsonArrayBuilder exemplaireBuilder = Json.createArrayBuilder();
        for(Exemplaire exemplaire : this.exemplaires) {
            exemplaireBuilder.add(exemplaire.toJson());
        }

        return Json.createObjectBuilder()
                .add("idOeuvre", this.idOeuvre)
                .add("auteur", this.auteur.toJson())
                .add("titre", this.titre)
                .add("exemplaires", exemplaireBuilder);
    }

}
