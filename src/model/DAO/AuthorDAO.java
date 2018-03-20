package model.DAO;

import model.Data;
import model.beans.Author;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class AuthorDAO {


    public Author getAuthor(long id) {
        Data data = Data.getInstance();
        ArrayList<Author> authors = data.getAuthors();

        Author author = null;
        Boolean found = false;
        Iterator it = authors.listIterator();
        while (it.hasNext() && !found) {
            Author temp_author = (Author) it.next();
            if (temp_author.getId() == (id)) {
                author = temp_author;
                found = true;
            }
        }
        return author;
    }

    public ArrayList<Author> getAuthors() {
        return Data.getInstance().getAuthors();
    }

    public void createAuthor(Author author) throws IOException {
        Data data = Data.getInstance();
        data.getAuthors().add(author);
        data.saveInstance();
    }
}
