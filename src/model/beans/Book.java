package model.beans;

import model.Data;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;

public class Book {
    private long id;

    private Author author;
    private String title;
    private ArrayList<Copy> copies;

    public Book(Author author, String title, ArrayList<Copy> copies) {
        this.id = Data.getInstance().getBooksSerialVersionUID();
        Data.getInstance().incrementBooksSerialVersionUID();

        this.author = author;
        this.title = title;
        this.copies = copies;
    }

    public Book(Author author, String title) {
        this.id = Data.getInstance().getBooksSerialVersionUID();
        Data.getInstance().incrementBooksSerialVersionUID();

        this.author = author;
        this.title = title;
        this.copies = new ArrayList<>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        if(id < Data.getInstance().getBooksSerialVersionUID()) {
            this.id = id;
        } else {
            Data.getInstance().setBooksSerialVersionUID(id+1);
            this.id = id;
        }
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Copy> getCopies() {
        return copies;
    }

    public void setCopies(ArrayList<Copy> copies) {
        this.copies = copies;
    }

    public JsonObjectBuilder toJson() {

        JsonArrayBuilder exemplaireBuilder = Json.createArrayBuilder();
        for(Copy copy : this.copies) {
            exemplaireBuilder.add(copy.toJson());
        }

        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("author", this.author.toJson())
                .add("title", this.title)
                .add("copies", exemplaireBuilder);
    }

}
