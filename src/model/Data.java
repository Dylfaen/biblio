package model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import model.beans.Author;
import model.beans.Book;
import model.beans.Loan;
import model.beans.User;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;

/**
 * (Singleton)
 * Data contient toutes les données persistantes de l'application. Elle stocke ces données du déploiement de l'application à la fermeture de l'application.
 * Elle met à disposition des DAO une instance unique de ses données et une méthode pour les sauvegarder dans un fichier XML.
 * Il revient aux DAO de sauvegarder l'application à chaque modification des données.
 * Data se charge de charger les données du fichier data.xml à l'ouverture de l'application si ce fichier est présent, et de la générer avec les données minimales dans le cas contraire.
 **/
public class Data {
    /**
     * L'instance unique des données
     */
    private static Data ourInstance;

    /**
     * Le valeur de l'id de la prochaine oeuvre à créer, permet de s'assurer de l'unicité de l'id
     */
    private long booksSerialVersionUID;
    /**
     * La liste des oeuvres
     */
    private ArrayList<Book> books;
    /**
     * Le valeur de l'id du prochain utilisateur à créer, permet de s'assurer de l'unicité de l'id
     */
    private long usersSerialVersionUID;
    /**
     * La liste des utilisateurs
     */
    private ArrayList<User> users;
    /**
     * Le valeur de l'id du prochain auteur à créer, permet de s'assurer de l'unicité de l'id
     */
    private long authorsSerialVersionUID;
    /**
     * La liste des auteurs
     */
    private ArrayList<Author> authors;
    /**
     * Le valeur de l'id du prochain exemplaire à créer, permet de s'assurer de l'unicité de l'id
     */
    private long copiesSerialVersionUID;
    /**
     * Le valeur de l'id du prochain emprunt à créer, permet de s'assurer de l'unicité de l'id
     */
    private long loansSerialVersionUID;
    /**
     * La liste des emprunts
     */
    private ArrayList<Loan> loans;


    /**
     * Construit l'objet Data. Initialise les données à partir du data.xml s'il existe, initialise les données par défaut et crée le fichier sinon.
     * Utilise XStream
     *
     * @throws IOException Si une erreur est survenue lors de la lecture ou l'écrture du fichier XML
     */
    private Data() throws IOException {

        //On initialise le fichier
        File file = new File(System.getProperty("user.dir") + "/data.xml");
        //On initialise XStream avec un driver
        XStream xStream = new XStream(new StaxDriver());

        if (file.exists()) {
            //Si le fichier existe on le lit
            FileInputStream f = new FileInputStream(file);
            Data tempData = (Data) xStream.fromXML(f);

            //On copie les données lues dans le fichier vers Data
            this.users = tempData.getUsers();
            this.usersSerialVersionUID = tempData.getUsersSerialVersionUID();

            this.books = tempData.getBooks();
            this.booksSerialVersionUID = tempData.getBooksSerialVersionUID();

            this.authors = tempData.getAuthors();
            this.authorsSerialVersionUID = tempData.getAuthorsSerialVersionUID();

            this.copiesSerialVersionUID = tempData.getCopiesSerialVersionUID();

            this.loans = tempData.getLoans();
            this.loansSerialVersionUID = tempData.getLoansSerialVersionUID();

            //On associe la nouvelle instance de Data à la classe static Data (Singleton)
            Data.ourInstance = this;
        } else {
            //Sinon
            //On instancie les données par défaut
            this.books = new ArrayList<>();
            this.users = new ArrayList<>();
            this.authors = new ArrayList<>();
            this.loans = new ArrayList<>();
            this.booksSerialVersionUID = 1L;
            this.usersSerialVersionUID = 1L;
            this.authorsSerialVersionUID = 1L;
            this.copiesSerialVersionUID = 1L;
            this.loansSerialVersionUID = 1L;

            //On crée un utilisateur (administrateur par défaut)
            User default_admin = new User(
                    this.usersSerialVersionUID,
                    "admin",
                    "c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec",
                    "Admin",
                    "Admin",
                    new Date(),
                    "",
                    true);
            this.incrementUsersSerialVersionUID();

            //On ajoute l'utilisateur
            this.users.add(default_admin);

            //On associe l'instance des données à la classe statique Data
            Data.ourInstance = this;

            //Et on sauvegarde l'instance
            saveInstance();

        }
    }

    /**
     * Renvoie l'instance unique des données
     *
     * @return l'instance unique des données
     */

    public static Data getInstance() {
        if (Data.ourInstance == null) {
            //Si les données n'existent pas on les crée
            try {
                Data.ourInstance = new Data();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //On retourne l'instance
        return Data.ourInstance;
    }

    /**
     * Sauvegarde l'instance des données dans le fichier XML grâce à la bibliothèque XStream
     *
     * @throws IOException Si une erreru survient lors de la lecture du fichier XML
     */
    public void saveInstance() throws IOException {
        //On crée le fichier
        File file = new File(System.getProperty("user.dir") + "/data.xml");

        //On initialise les parsers
        XStream xstream = new XStream(new StaxDriver());
        FileOutputStream f = new FileOutputStream(file);
        Writer writer = new OutputStreamWriter(f, Charset.forName("UTF-8"));

        //On écrit l'instance unique dans le fichier data.xml
        xstream.toXML(Data.getInstance(), writer);
    }

    /**
     * Renvoie la liste des oeuvres
     *
     * @return la liste des oeuvres
     */
    public ArrayList<Book> getBooks() {
        return this.books;
    }


    /**
     * Renvoie la liste des utilisateurs
     * @return la liste des utilisateurs
     */
    public ArrayList<User> getUsers() {
        return this.users;
    }


    /**
     * Renvoie la liste des emprunts
     * @return la liste des emprunts
     */
    public ArrayList<Loan> getLoans() {
        return loans;
    }


    /**
     * Incrémente le SerialVersionUID des utilisateurs
     */
    public void incrementUsersSerialVersionUID() {
        this.usersSerialVersionUID++;
    }


    /**
     * Renvoie la liste des auteurs
     * @return la liste des auteurs
     */
    public ArrayList<Author> getAuthors() {
        return authors;
    }

    /**
     * Incrémente le SerialVersionUID des auteurs
     */
    public void incrementAuthorsSerialVersionUID() {
        this.authorsSerialVersionUID++;
    }

    /**
     * Renvoie la liste des livres
     * @return la liste des livres
     */
    public long getBooksSerialVersionUID() {
        return booksSerialVersionUID;
    }

    /**
     * Définit le SerialVersionUID des oeuvres
     *
     * @param booksSerialVersionUID le SerialVersionUID des oeuvres
     */
    public void setBooksSerialVersionUID(long booksSerialVersionUID) {
        this.booksSerialVersionUID = booksSerialVersionUID;
    }

    /**
     * Incrémente le SerialVersionUID des oeuvres
     */
    public void incrementBooksSerialVersionUID() {
        this.booksSerialVersionUID++;
    }

    /**
     * Renvoie la liste des utilisateurs
     * @return la liste des utilisateurs
     */
    public long getUsersSerialVersionUID() {
        return usersSerialVersionUID;
    }

    /**
     * Définit le SerialVersionUID des utilisateurs
     * @param usersSerialVersionUID le SerialVersionUID des utilisateurs
     */
    public void setUsersSerialVersionUID(long usersSerialVersionUID) {
        this.usersSerialVersionUID = usersSerialVersionUID;
    }

    /**
     * Renvoie la liste des utilisateurs
     * @return la liste des utilisateurs
     */
    public long getAuthorsSerialVersionUID() {
        return authorsSerialVersionUID;
    }


    /**
     * Définit le SerialVersionUID des auteurs
     * @param authorsSerialVersionUID le SerialVersionUID des auteurs
     */
    public void setAuthorsSerialVersionUID(long authorsSerialVersionUID) {
        this.authorsSerialVersionUID = authorsSerialVersionUID;
    }

    /**
     * Renvoie la liste des exemplaires
     * @return la liste des exemplaires
     */
    public long getCopiesSerialVersionUID() {
        return copiesSerialVersionUID;
    }

    /**
     * Définit le SerialVersionUID des exemplaires
     *
     * @param copiesSerialVersionUID le SerialVersionUID des exemplaires
     */
    public void setCopiesSerialVersionUID(long copiesSerialVersionUID) {
        this.copiesSerialVersionUID = copiesSerialVersionUID;
    }

    /**
     * Incrémente le SerialVersionUID des exemplaires
     */
    public void incrementCopiesSerialVersionUID() {
        this.copiesSerialVersionUID++;
    }

    /**
     * Renvoie la liste des emprunts
     * @return la liste des emprunts
     */
    public long getLoansSerialVersionUID() {
        return loansSerialVersionUID;
    }

    /**
     * Définit le SerialVersionUID des emprunts
     * @param loansSerialVersionUID le SerialVersionUID des emprunts
     */
    public void setLoansSerialVersionUID(long loansSerialVersionUID) {
        this.loansSerialVersionUID = loansSerialVersionUID;
    }

    /**
     * Incrémente le SerialVersionUID des emprunts
     */
    public void incrementLoansSerialVersionUID() {
        this.loansSerialVersionUID++;
    }
}
