package model.beans;

import model.Data;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Représente l'utilisateur de l'application
 */
public class User implements Serializable {

    /**
     * L'id unique de l'utilisateur
     */
    private long id;
    /**
     * L'identifiant / nom d'utilisateur
     */
    private String username;
    /**
     * Son mot de passe
     */
    private String password;
    /**
     * Son nom
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
     * Son adresse
     */
    private String address;
    /**
     * Le statut d'administrateur
     */
    private boolean isAdmin;

    /**
     * Construit un utilisateur
     *
     * @param username  Son nom d'utilisateur
     * @param password  Son mot de passe
     * @param lastname  Son nom
     * @param firstname Son prénom
     * @param birthdate Sa date de naissance
     * @param address   Son adresse
     * @param isAdmin   Son statut d'administrateur
     */
    public User(String username, String password, String lastname, String firstname, Date birthdate, String address, boolean isAdmin) {
        this.id = Data.getInstance().getUsersSerialVersionUID(); //On définit l'id unique
        Data.getInstance().incrementUsersSerialVersionUID(); //On incrémente le SerialVersionUID associé

        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthdate = birthdate;
        this.address = address;
        this.isAdmin = isAdmin;
    }

    /**
     * Construit un utilisateur en définissant un SerialVersionUID en paramètre.
     * Est utile dans le constructeur de Data pour initialiser un administrateur par défaut.
     * Ce constructeur ne garantie pas la construction d'un utilisateur à l'id unique. Toutes les vérification doivent être faites en amont
     * @see model.Data
     * @param serial Le serial à utiliser
     * @param username Son nom d'utilisateur
     * @param password Son mot de passe
     * @param lastname Son nom
     * @param firstname Son prénom
     * @param birthdate Sa date de naissance
     * @param address Son adresse
     * @param isAdmin Son statut d'administrateur
     */
    public User(long serial, String username, String password, String lastname, String firstname, Date birthdate, String address, boolean isAdmin) {
        this.id = serial;

        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthdate = birthdate;
        this.address = address;
        this.isAdmin = isAdmin;
    }

    /**
     * Renvoi l'id unique de l'utilisateur
     * @return l'id unique de l'utilisateur
     */
    public long getId() {
        return id;
    }

    /**
     * Renvoie le nom d'utilisateur
     * @return le nom d'utilisateur
     */
    public String getUsername() {
        return username;
    }

    /**
     * Définit le nom de l'utilisateur
     * @param username le nouveau nom d'utilisateur
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Renvoie le mot de passe
     * @return le mot de passe
     */
    public String getPassword() {
        return password;
    }

    /**
     * Définit le mot de passe
     * @param password le nouveau mot de passe
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Renvoie la date de naissance
     * @return la date de naissance
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * Définit la date de naissance
     *
     * @param birthdate la nouvealle date de naissance
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Revoie l'adresse
     * @return l'adresse
     */
    public String getAddress() {
        return address;
    }

    /**
     * Définit l'adresse
     *
     * @param address la nouvelle adresse
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Renvoie le nom
     * @return le nom
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Définit le nom
     *
     * @param lastname le nouveau nom
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Renvoie le prénom
     * @return le prénom
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Définit le prénom
     * @param firstname le nouveau prénom
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @return
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public JsonObjectBuilder toJson() {
        String birthdateStr = "";
        if (this.birthdate != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
            birthdateStr = formatter.format(this.birthdate);
        }
        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("username", this.username)
                .add("lastname", this.lastname)
                .add("firstname", this.firstname)
                .add("birthdate", birthdateStr)
                .add("address", this.address)
                .add("isAdmin", this.isAdmin);
    }
}
