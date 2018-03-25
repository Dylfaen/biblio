package model.DAO;

import controller.Util.CannotRemoveItemException;
import controller.Util.UsernameTakenException;
import model.Data;
import model.beans.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class UserDAO {

    public User getUser(String username, String hashed_password) {
        Data data = Data.getInstance();
        ArrayList<User> users = data.getUsers();

        User user = null;
        Boolean found = false;
        Iterator it = users.listIterator();
        while (it.hasNext() && !found) {
            User temp_user = (User) it.next();
            if (temp_user.getUsername().equals(username)
                    && temp_user.getPassword().equals(hashed_password)) {
                user = temp_user;
                found = true;
            }
        }
        return user;
    }

    public User getUser(long id) {
        Data data = Data.getInstance();
        ArrayList<User> users = data.getUsers();

        User user = null;
        Boolean found = false;
        Iterator it = users.listIterator();
        while (it.hasNext() && !found) {
            User temp_user = (User) it.next();
            if (temp_user.getId() == id) {
                user = temp_user;
                found = true;
            }
        }
        return user;
    }

    public ArrayList<User> getUsers() {
        return Data.getInstance().getUsers();
    }

    public void createUser(String username, String password, String firstname, String lastname, Date birthdate, String address, Boolean isAdmin) throws UsernameTakenException {

        if (!isAvailable(username)) {
            User user = new User(username, password, lastname, firstname, birthdate, address, isAdmin);
            Data data = Data.getInstance();
            data.getUsers().add(user);
            try {
                data.saveInstance();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new UsernameTakenException("This username is already taken");
        }
    }

    public void createUser(User user) throws UsernameTakenException {

        if (isAvailable(user.getUsername())) {
            Data data = Data.getInstance();
            data.getUsers().add(user);
            try {
                data.saveInstance();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new UsernameTakenException("This username is already taken");
        }

    }

    public boolean isAvailable(String username) {
        ArrayList<User> users = this.getUsers();
        Iterator iterator = users.listIterator();
        boolean found = false;
        while (iterator.hasNext() && !found) {
            User current_user = (User) iterator.next();
            if (current_user.getUsername().equals(username)) {
                found = true;
            }
        }
        return !found;
    }

    public void editUserBirthdate(long id, Date birthdate) throws IOException {
        this.getUser(id).setBirthdate(birthdate);
        Data.getInstance().saveInstance();
    }

    public void editUserAddress(long id, String address) throws IOException {
        this.getUser(id).setAddress(address);
        Data.getInstance().saveInstance();
    }

    public void editUserUsername(long id, String username) throws IOException, UsernameTakenException {
        if(isAvailable(username)) {
            this.getUser(id).setUsername(username);
            Data.getInstance().saveInstance();
        } else {
            throw new UsernameTakenException("This username is already taken");
        }

    }


    public void editUserPassword(long id, String hashed_password) throws IOException {
        this.getUser(id).setPassword(hashed_password);
        Data.getInstance().saveInstance();
    }

    public void removeUser(User user) throws IOException, CannotRemoveItemException {
        Data data = Data.getInstance();
        LoanDAO loanDAO = new LoanDAO();
        loanDAO.removeLoans(loanDAO.getLoans(user));
        data.getUsers().remove(user);

        data.saveInstance();
    }
}
