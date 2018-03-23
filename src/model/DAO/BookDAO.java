package model.DAO;

import model.Data;
import model.beans.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class BookDAO {

    public Book getBook(long id) {
        Data data = Data.getInstance();
        ArrayList<Book> users = data.getBooks();

        Book book = null;
        Boolean found = false;
        Iterator it = users.listIterator();
        while (it.hasNext() && !found) {
            Book temp_book = (Book) it.next();
            if (temp_book.getId() == (id)) {
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

    public boolean isAvailable(Copy copy) {
        ArrayList<Loan> loans = Data.getInstance().getLoans();
        Iterator itLoans = loans.listIterator();

        boolean isCopyAvailable = true;
        while (itLoans.hasNext() && isCopyAvailable) {
            Loan loan = (Loan) itLoans.next();

            if (copy.equals(loan.getCopy()) && !loan.isReturned()) {
                isCopyAvailable = false;
            }
        }
        return isCopyAvailable;
    }

    public void removeBook(Book book) throws IOException {
        Data data = Data.getInstance();
        LoanDAO loanDAO = new LoanDAO();
        ArrayList<Loan> loansToRemove = loanDAO.getLoans(book);

        loanDAO.removeLoans(loansToRemove);
        data.getBooks().remove(book);


        data.saveInstance();
    }

    public Copy findAvailable(Book book) {
        ArrayList<Loan> loans = Data.getInstance().getLoans();
        ArrayList<Copy> copies = book.getCopies();
        Iterator itLoans = loans.listIterator();
        Iterator itCopies = copies.listIterator();
        Copy foundCopy = null;
        boolean availableFound = false;
        while (itCopies.hasNext() && !availableFound) {
            Copy copy = (Copy) itCopies.next();

            if (isAvailable(copy)) {
                availableFound = true;
                foundCopy = copy;
            }
        }
        return foundCopy;
    }
}
