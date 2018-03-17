package model.beans;


public class Exemplaire {
    private int idExemplaire;
    private int idOeuvre;

    public Exemplaire(int idExemplaire, int idOeuvre) {
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

    public String toJSON() {
        return null;
    }
}
