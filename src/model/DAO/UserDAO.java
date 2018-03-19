package model.DAO;

import model.Data;
import model.beans.User;
import model.requests.Connection;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class UserDAO {

    public static User getUser(String username, String hashed_password) {
        Data data = Data.getInstance();
        ArrayList<User> users = data.getUsers();

        User user = null;
        Boolean found = false;
        Iterator it = users.listIterator();
        while(it.hasNext() && !found) {
            User temp_user = (User) it.next();
            if(temp_user.getUsername().equals(username)
                    && temp_user.getPassword().equals(hashed_password)) {
                user = temp_user;
                found = true;
            }
        }
        return user;
    }

    public static void createUser(String username, String password, String firstname, String lastname, Date birthdate, String address, Boolean isAdmin) throws FileNotFoundException {

        User user = new User(username, password, lastname, firstname, birthdate, address, isAdmin);
        Data data = Data.getInstance();
        data.getUsers().add(user);
        data.saveInstance();
    }



}
