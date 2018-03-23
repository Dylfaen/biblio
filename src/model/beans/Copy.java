package model.beans;


import model.DAO.BookDAO;
import model.Data;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class Copy {
    private long id;
    private long bookId;

    public Copy(long bookId) {
        this.id = Data.getInstance().getCopiesSerialVersionUID();
        Data.getInstance().incrementCopiesSerialVersionUID();
        this.bookId = bookId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if(id < Data.getInstance().getCopiesSerialVersionUID()) {
            this.id = id;
        } else {
            Data.getInstance().setCopiesSerialVersionUID(id+1);
            this.id = id;
        }
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public JsonObjectBuilder toJson() {

        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("bookId", this.bookId);
    }

    public JsonObjectBuilder toJsonFull() {

        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.getBook(this.bookId);

        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("book", book.toJson());
    }
}
