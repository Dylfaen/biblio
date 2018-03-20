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

public class Data {
    private static Data ourInstance;

    private long booksSerialVersionUID;
    private ArrayList<Book> books;
    private long usersSerialVersionUID;
    private ArrayList<User> users;
    private long authorsSerialVersionUID;
    private ArrayList<Author> authors;
    private long copiesSerialVersionUID;
    private long loansSerialVersionUID;
    private ArrayList<Loan> loans;




    public static Data getInstance() {
        if (Data.ourInstance == null) {
            try {
                Data.ourInstance = new Data();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Data.ourInstance;
    }

    private void writeToXml() throws IOException {
        File file = new File(System.getProperty("user.dir") + "/data.xml");

        XStream xstream = new XStream(new StaxDriver());
        FileOutputStream f = new FileOutputStream(file);

        Writer writer = new OutputStreamWriter(f, Charset.forName("UTF-8"));

        xstream.toXML(Data.getInstance(), writer);
    }

    public void saveInstance() throws IOException {
        writeToXml();
    }

    private Data() throws IOException {
        File file = new File(System.getProperty("user.dir") + "/data.xml");
        XStream xStream = new XStream(new StaxDriver());

        System.out.println(file.getAbsolutePath());

        if (file.exists()) {
            FileInputStream f = new FileInputStream(file);
            Data tempData = (Data) xStream.fromXML(f);
            System.out.println(tempData.getAuthors().get(0));

            this.users = tempData.getUsers();
            this.usersSerialVersionUID = tempData.getUsersSerialVersionUID();

            this.books = tempData.getBooks();
            this.booksSerialVersionUID = tempData.getBooksSerialVersionUID();

            this.authors = tempData.getAuthors();
            this.authorsSerialVersionUID = tempData.getAuthorsSerialVersionUID();

            this.copiesSerialVersionUID = tempData.getCopiesSerialVersionUID();

            Data.ourInstance = this;
        } else {
            this.books = new ArrayList<>();
            this.users = new ArrayList<>();
            this.authors = new ArrayList<>();
            this.loans = new ArrayList<>();
            this.booksSerialVersionUID = 1L;
            this.usersSerialVersionUID = 1L;
            this.authorsSerialVersionUID = 1L;
            this.copiesSerialVersionUID = 1L;
            this.loansSerialVersionUID = 1L;

            User default_admin = new User(
                    this.usersSerialVersionUID,
                    "admin",
                    "c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec",
                    "Admin",
                    "Admin",
                    null,
                    null,
                    true);
            this.incrementUsersSerialVersionUID();

            this.users.add(default_admin);

            Data.ourInstance = this;

           saveInstance();

        }
    }

    public ArrayList<Book> getBooks() {
        return this.books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }


    public void incrementUsersSerialVersionUID() {
        this.usersSerialVersionUID++;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void incrementAuthorsSerialVersionUID() {
        this.authorsSerialVersionUID++;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public long getBooksSerialVersionUID() {
        return booksSerialVersionUID;
    }

    public void incrementBooksSerialVersionUID() {
        this.booksSerialVersionUID++;
    }

    public void setBooksSerialVersionUID(long booksSerialVersionUID) {
        this.booksSerialVersionUID = booksSerialVersionUID;
    }

    public long getUsersSerialVersionUID() {
        return usersSerialVersionUID;
    }

    public void setUsersSerialVersionUID(long usersSerialVersionUID) {
        this.usersSerialVersionUID = usersSerialVersionUID;
    }

    public long getAuthorsSerialVersionUID() {
        return authorsSerialVersionUID;
    }

    public void setAuthorsSerialVersionUID(long authorsSerialVersionUID) {
        this.authorsSerialVersionUID = authorsSerialVersionUID;
    }

    public long getCopiesSerialVersionUID() {
        return copiesSerialVersionUID;
    }

    public void incrementCopiesSerialVersionUID() {
        this.copiesSerialVersionUID++;
    }

    public void setCopiesSerialVersionUID(long copiesSerialVersionUID) {
        this.copiesSerialVersionUID = copiesSerialVersionUID;
    }

    public long getLoansSerialVersionUID() {
        return copiesSerialVersionUID;
    }

    public void incrementLoansSerialVersionUID() {
        this.copiesSerialVersionUID++;
    }

    public void setLoansSerialVersionUID(long copiesSerialVersionUID) {
        this.copiesSerialVersionUID = copiesSerialVersionUID;
    }
}
