package model.DAO;

import controller.Util.CannotRemoveItemException;
import controller.Util.UsernameTakenException;
import model.Data;
import model.beans.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Le DAO des utilisateurs. Il compile un ensemble de méthodes pour manipuler les utilisateurs de Data
 *
 * @see model.Data
 */
public class UserDAO {

    /**
     * Renvoie l'utilisateur en fonction d'un nom d'utilisateur et d'un mot de passe
     * @param username Le nom d'utilisateur
     * @param hashed_password Le mot de passe
     * @return l'utilisateur possédant le nom d'utilisateur et le mot de passe correspondant si il existe, null sinon
     */
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

    /**
     * Renvoie l'utilisateur poossédant l'id en paramètre
     * @param id l'id à chercher
     * @return l'utilisateur possédant l'id en paramètre, null s'il n'existe pas
     */
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

    /**
     * Renvoie la liste des utilisateurs
     * @return la liste des utilisateurs
     */
    public ArrayList<User> getUsers() {
        return Data.getInstance().getUsers();
    }

    /**
     * Ajoute un nouvel utilisateur aux données
     *
     * @param user Le nouvel utilisateur
     * @throws UsernameTakenException Si le nom d'utilisateur est déjà pris
     * @throws IOException            Si la sauvegarde a échoué
     */
    public void createUser(User user) throws UsernameTakenException, IOException {

        if (isAvailable(user.getUsername())) {
            Data data = Data.getInstance();
            data.getUsers().add(user);
            data.saveInstance();
        } else {
            throw new UsernameTakenException("This username is already taken");
        }

    }

    /**
     * Vérifie que le nom d'utilisateur en paramètre est disponible
     * @param username le nom d'utilisateur à vérifier
     * @return True s'il est disponible, False sinon
     */
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

    /**
     * Modifie la date de naissance de l'utilisateur
     * @param id L'id de l'utilisateur
     * @param birthdate La nouvelle date de naissance
     * @throws IOException Si la sauvegarde a échoué
     */
    public void editUserBirthdate(long id, Date birthdate) throws IOException {
        this.getUser(id).setBirthdate(birthdate);
        Data.getInstance().saveInstance();
    }

    /**
     * Modifie l'adresse de l'utilisateur
     * @param id L'id de l'utilisateur
     * @param address La nouvelle adresse
     * @throws IOException Si la sauvegarde a échoué
     */
    public void editUserAddress(long id, String address) throws IOException {
        this.getUser(id).setAddress(address);
        Data.getInstance().saveInstance();
    }

    /**
     * Modifie le nom d'utilisateur de l'utilisateur
     * @param id L'id de l'utilisateur
     * @param username Le nouveau nom d'utilisateur
     * @throws IOException Si la sauvegarde a échoué
     * @throws UsernameTakenException Si le nom d'utilisateur n'est pas disponible
     */
    public void editUserUsername(long id, String username) throws IOException, UsernameTakenException {
        if(isAvailable(username)) {
            this.getUser(id).setUsername(username);
            Data.getInstance().saveInstance();
        } else {
            throw new UsernameTakenException("This username is already taken");
        }

    }


    /**
     * Modifie le mot de passe de l'utilisateur
     * @param id L'id de l'utilisateur
     * @param hashed_password Le nouveau mot de passe (hashé)
     * @throws IOException Si la sauvegarde a échoué
     */
    public void editUserPassword(long id, String hashed_password) throws IOException {
        this.getUser(id).setPassword(hashed_password);
        Data.getInstance().saveInstance();
    }


    /**
     * Supprime un utilisateur
     * @param user L'utilisateur a supprimer
     * @throws IOException Si la sauvegarde a échoué
     * @throws CannotRemoveItemException Si l'utilisateur à des emprunts en cours
     */
    public void removeUser(User user) throws IOException, CannotRemoveItemException {
        Data data = Data.getInstance();
        LoanDAO loanDAO = new LoanDAO();
        loanDAO.removeLoans(loanDAO.getLoans(user));
        data.getUsers().remove(user);

        data.saveInstance();
    }
}
