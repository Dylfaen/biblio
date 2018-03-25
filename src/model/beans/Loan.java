package model.beans;

import model.Data;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Représente un emprunt
 */
public class Loan {

    /**
     * L'id unique de l'emprunt
     */
    private long id;
    /**
     * L'exemplaire emprunté
     */
    private Copy copy;
    /**
     * La date d'emprunt
     */
    private Date startDate;
    /**
     * L'emprunteur
     */
    private User user;
    /**
     * Vrai si l'exemplaire à été retourné
     */
    private boolean isReturned;


    /**
     * Constuit un emprunt
     *
     * @param copy      L'exemplaire emprunté
     * @param startDate La date d'emprunt
     * @param user      L'emprunteur
     */
    public Loan(Copy copy, Date startDate, User user) {
        this.id = Data.getInstance().getLoansSerialVersionUID(); //On définit l'id
        Data.getInstance().incrementLoansSerialVersionUID(); //On incrémente le SerialVersionUID associé
        this.copy = copy;
        this.startDate = startDate;
        this.user = user;
        this.isReturned = false;
    }

    /**
     * Renvoie l'id unique de l'emprunt
     *
     * @return l'id unique de l'emprunt
     */
    public long getId() {
        return id;
    }


    /**
     * Renvoie l'exemplaire emprunté
     *
     * @return l'exemplaire emprunté
     */
    public Copy getCopy() {
        return copy;
    }


    /**
     * Définit l'exemplaire emprunté
     *
     * @param copy le nouvel exemplaire
     */
    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    /**
     * Renvoie la date d'emprunt
     *
     * @return la date d'emrunt
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Définit la date d'emprunt
     *
     * @param startDate la nouvelle date d'emprunt
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Renvoie l'emprunteur
     *
     * @return l'emprunteur
     */
    public User getUser() {
        return user;
    }

    /**
     * Définit l'emprunteur
     *
     * @param user le nouvel emprunteur
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Renvoie true si l'exemplaire a été retourné
     *
     * @return Le statut du retour de l'emprunt
     */
    public boolean isReturned() {
        return isReturned;
    }

    /**
     * Définit si l'exemplaire a été retourné
     *
     * @param returned La nouvelle valeur du statut de retour de l'emprunt
     */
    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    /**
     * Formatte l'emprunt en JSON
     *
     * @return Le JsonObjectBuilder de l'emprunt
     */
    public JsonObjectBuilder toJson() {

        String startDateStr = "";

        //On formatte la date
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
        startDateStr = formatter.format(this.startDate);

        //On retourne le JsonObjectBuilder
        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("copy", this.copy.toJsonFull())
                .add("date", startDateStr)
                .add("user", this.user.toJson())
                .add("isReturned", this.isReturned);
    }
}
