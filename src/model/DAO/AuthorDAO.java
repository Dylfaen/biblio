package model.DAO;

import model.Data;
import model.beans.Author;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class AuthorDAO {


    public Author getAuthor(int id) {
        Data data = Data.getInstance();
        ArrayList<Author> users = data.getAuthors();

        Author author = null;
        Boolean found = false;
        Iterator it = users.listIterator();
        while (it.hasNext() && !found) {
            Author temp_book = (Author) it.next();
            if (author.getId() == (id)) {
                author = temp_book;
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
