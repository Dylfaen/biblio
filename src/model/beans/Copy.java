package model.beans;


import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class Copy {
    private int idExemplaire;
    private int idOeuvre;

    public Copy(int idExemplaire, int idOeuvre) {
        this.idExemplaire = idExemplaire;
        this.idOeuvre = idOeuvre;
    }

    public int getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(int idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(int idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public JsonObjectBuilder toJson() {

        return Json.createObjectBuilder()
                .add("idExemplaire", this.idExemplaire)
                .add("idOeuvre", this.idOeuvre);
    }
}
