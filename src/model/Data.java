package model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import model.beans.Book;
import model.beans.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Data {
    private static Data ourInstance;

    private ArrayList<Book> books;
    private ArrayList<User> users;

    public static Data getInstance() {
        if(Data.ourInstance == null) {
            try {
                Data.ourInstance = new Data();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Data.ourInstance;
    }

    public void saveInstance() throws FileNotFoundException {
        File file = new File("data.xml");
        XStream xStream = new XStream(new StaxDriver());
        if(file.exists()) {
            xStream.toXML(this);
        } else {
            throw new FileNotFoundException();
        }

    }

    private Data() throws IOException {
        File file = new File(System.getProperty("user.dir") + "/data.xml");
        XStream xStream = new XStream(new StaxDriver());

        System.out.println(file.getAbsolutePath());

        if(file.exists()) {
            FileInputStream f = new FileInputStream(file);
            Data tempData = (Data)xStream.fromXML(f);
            this.users = tempData.getUsers();
            this.books = tempData.getBooks();
            Data.ourInstance = this;
            System.out.println(Data.ourInstance.users.get(0).getUsername());
            System.out.println(Data.ourInstance.users.get(0).getPassword());
        } else {
            this.books = new ArrayList<>();
            this.users = new ArrayList<>();
            User default_admin = new User(
                    "admin",
                    "c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec",
                    "Admin",
                    "Admin",
                    null,
                    null,
                    true);
            this.users.add(default_admin);
            Data.ourInstance = this;
            String xml = xStream.toXML(Data.getInstance());
            FileOutputStream f = new FileOutputStream(file);
            f.write(xml.getBytes());
            f.flush();
            f.close();
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

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
