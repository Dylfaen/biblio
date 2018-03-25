package model.beans;


import model.DAO.BookDAO;
import model.Data;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 * Représente un exemplaire d'une oeuvre
 */
public class Copy {
    /**
     * L'id unique de l'exemplaire
     */
    private long id;
    /**
     * L'id de l'oeuvre
     */
    private long bookId;

    /**
     * Construit un exemplaire avec un id unique
     *
     * @param bookId
     */
    public Copy(long bookId) {
        this.id = Data.getInstance().getCopiesSerialVersionUID(); //Définit l'id de l'exemplaire
        Data.getInstance().incrementCopiesSerialVersionUID(); //Incrémente le SerialVersionUID associé
        this.bookId = bookId;
    }

    /**
     * Renvoie l'id de l'exemplaire
     *
     * @return l'id de l'exemplaire
     */
    public long getId() {
        return id;
    }

    /**
     * Renvoie l'id de l'oeuvre
     *
     * @return l'id de l'oeuvre
     */
    public long getBookId() {
        return bookId;
    }


    /**
     * Définit l'id de l'oeuvre
     *
     * @param bookId l'id de l'oeuvre
     */
    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    /**
     * Formatte l'exemplaire en JSON sans récupérer l'oeuvre entière
     *
     * @return Le JsonObjectBuilder de l'exemplaire
     */
    public JsonObjectBuilder toJson() {

        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("bookId", this.bookId);
    }

    /**
     * Formatte l'exemplaire en JSON en récupérant l'oeuvre entière
     *
     * @return Le JsonOBjectBuilder de l'exemplaire
     */
    public JsonObjectBuilder toJsonFull() {

        //On récupère l'oeuvre
        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.getBook(this.bookId);

        //On renvoie le builder
        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("book", book.toJson());
    }
}
