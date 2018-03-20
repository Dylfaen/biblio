package model.DAO;

import model.Data;
import model.beans.*;

import java.io.FileNotFoundException;
import java.io.IOException;
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


    public void createBook(Author author, String title, ArrayList<Copy> copies) throws FileNotFoundException {

        Book book = new Book(author, title, copies);
        Data data = Data.getInstance();
        data.getBooks().add(book);
        try {
            data.saveInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createBook(Book book) throws FileNotFoundException {
        Data data = Data.getInstance();
        data.getBooks().add(book);
        try {
            data.saveInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Copy findAvailable(Book book) {
        ArrayList<Loan> loans = Data.getInstance().getLoans();
        ArrayList<Copy> copies = book.getCopies();
        Iterator itLoans = loans.iterator();
        Iterator itCopy = loans.iterator();
        Copy foundCopy = null;
        boolean availableFound = false;
        while(itCopy.hasNext() && !availableFound) {
            Copy copy = (Copy) itCopy.next();

            while(itLoans.hasNext() && !availableFound) {
                Loan loan = (Loan)itLoans.next();

                if(copy.equals(loan.getCopy())) {
                    availableFound = true;
                    foundCopy = copy;
                }
            }
        }
        return foundCopy;
    }
}
