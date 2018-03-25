package model.beans;

import model.Data;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;

/**
 * Représente une oeuvre
 */
public class Book {

    /**
     * L'id unique de l'oeuvre
     */
    private long id;

    /**
     * L'auteur de l'oeuvre
     */
    private Author author;
    /**
     * Le titre
     */
    private String title;
    /**
     * Les exemplaires de l'oeuvre
     */
    private ArrayList<Copy> copies;

    /**
     * Construit une oeuvre avec un id unique
     *
     * @param author L'auteur de l'oeuvre
     * @param title  Le title
     * @param copies Les exemplaires
     */
    public Book(Author author, String title, ArrayList<Copy> copies) {
        this.id = Data.getInstance().getBooksSerialVersionUID(); //On définit l'id
        Data.getInstance().incrementBooksSerialVersionUID(); //On incrément le SerialVersionUID associé

        this.author = author;
        this.title = title;
        this.copies = copies;
    }

    /**
     * Construit une oeuvre sans exemplaire avec un id unique
     *
     * @param author
     * @param title
     */
    public Book(Author author, String title) {
        this.id = Data.getInstance().getBooksSerialVersionUID();
        Data.getInstance().incrementBooksSerialVersionUID();

        this.author = author;
        this.title = title;
        this.copies = new ArrayList<>();
    }


    /**
     * Renvoie l'id
     *
     * @return l'id
     */
    public long getId() {
        return id;
    }

    /**
     * Renvoir l'auteur
     *
     * @return l'auteur
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Définit l'auteur
     *
     * @param author le nouvel auteur
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * Renvoie le titre
     *
     * @return le titre
     */
    public String getTitle() {
        return title;
    }

    /**
     * Définit le titre
     *
     * @param title le nouveau titre
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Renvoie les exemplaires
     *
     * @return les exemplaires
     */
    public ArrayList<Copy> getCopies() {
        return copies;
    }

    /**
     * Définit les exemplaires
     *
     * @param copies les nouveaux exemplaires
     */
    public void setCopies(ArrayList<Copy> copies) {
        this.copies = copies;
    }

    /**
     * Formatte l'oeuvre en JSON
     *
     * @return le JsonObjectBuilder de l'oeuvre
     */
    public JsonObjectBuilder toJson() {

        //On formate la liste des exemplaires
        JsonArrayBuilder exemplaireBuilder = Json.createArrayBuilder();
        for (Copy copy : this.copies) {
            exemplaireBuilder.add(copy.toJson());
        }

        //On formatte l'oeuvre
        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("author", this.author.toJson())
                .add("title", this.title)
                .add("copies", exemplaireBuilder);
    }

}
