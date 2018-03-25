package model.beans;

import model.Data;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Le bean Author représente l'auteur d'une oeuvre
 */
public class Author {

    /**
     * L'id unique de l'auteur
     */
    private long id;

    /**
     * Son nom de famille
     */
    private String lastname;
    /**
     * Son prénom
     */
    private String firstname;
    /**
     * Sa date de naissance
     */
    private Date birthdate;
    /**
     * Sa nationalité
     */
    private String nationality;

    /**
     * Construit un auteur avec un id unique
     *
     * @param lastname    Son nom
     * @param firstname   Son prénom
     * @param birthdate   Sa date de naissance
     * @param nationality Sa nationalité
     */
    public Author(String lastname, String firstname, Date birthdate, String nationality) {
        this.id = Data.getInstance().getAuthorsSerialVersionUID(); //On définit l'id;
        Data.getInstance().incrementAuthorsSerialVersionUID(); //On incrémente le SerialVersionUID associé
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthdate = birthdate;
        this.nationality = nationality;
    }

    /**
     * Renvoie l'id
     * @return l'id
     */
    public long getId() {
        return id;
    }

    /**
     * Renvoie le nom
     * @return le nom
     */
    public String getLastname() {
        return this.lastname;
    }

    /**
     * Définit le nom
     * @param lastname le nouveau nom
     */
    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    /**
     * Renvoie le prénom
     *
     * @return le prénom
     */
    public String getFirstname() {
        return this.firstname;
    }

    /**
     * Définit le prénom
     * @param firstname le nouveau prénom
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Renvoie la date de naissance
     *
     * @return la date de naissance
     */
    public Date getBirthdate() {
        return this.birthdate;
    }

    /**
     * Définit la date de naissance
     * @param birthdate la nouvelle date de naissance
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Renvoie la nationalité
     *
     * @return la nationalité
     */
    public String getNationality() {
        return this.nationality;
    }

    /**
     * Définit la nationalité
     * @param nationality la nouvelle nationalité
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Formatte l'objet en JSON
     * @return le JsonObjectBuilder de l'auteur
     */
    public JsonObjectBuilder toJson() {

        //On formatte la date
        String birthdateStr = "";
        if(this.birthdate != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
            birthdateStr = formatter.format(this.birthdate);
        }

        // On construit l'objet et on le retourne
        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("lastname", this.lastname)
                .add("firstname", this.firstname)
                .add("birthdate", birthdateStr)
                .add("nationality", this.nationality);
    }
}
