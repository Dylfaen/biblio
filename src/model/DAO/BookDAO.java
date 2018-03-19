package model.DAO;

import model.Data;
import model.beans.Author;
import model.beans.Book;
import model.beans.Copy;
import model.beans.User;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class BookDAO {

    public Book getBook(int id) {
        Data data = Data.getInstance();
        ArrayList<Book> users = data.getBooks();

        Book book = null;
        Boolean found = false;
        Iterator it = users.listIterator();
        while(it.hasNext() && !found) {
            Book temp_book = (Book) it.next();
            if(book.getId() == (id)) {
                book = temp_book;
                found = true;
            }
        }
        return book;
    }

    public ArrayList<Book> getBooks() {
        return Data.getInstance().getBooks();
    }

    public static void createBook(Author author, String title, ArrayList<Copy> copies) throws FileNotFoundException {

        Book book = new Book(author, title, copies);
        Data data = Data.getInstance();
        data.getBooks().add(book);
        data.saveInstance();
    }
}
