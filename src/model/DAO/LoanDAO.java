package model.DAO;

import controller.Util.CannotRemoveItemException;
import model.Data;
import model.beans.Book;
import model.beans.Copy;
import model.beans.Loan;
import model.beans.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Le DAO des emprunts. Il compile un ensemble de méthodes pour manipuler les emprunts de Data
 *
 * @see model.Data
 */
public class LoanDAO {

    /**
     * Renvoie la liste des emprunts d'un utilisateur
     * @param user l'utilisateur emprunteur
     * @return La liste des emprunts de l'utilisateur
     */
    public ArrayList<Loan> getLoans(User user) {
        Data data = Data.getInstance();
        ArrayList<Loan> loans = data.getLoans();

        ArrayList<Loan> myLoans = new ArrayList<>();

        for (Loan temp_loan : loans) {
            User temp_user = temp_loan.getUser();
            if (temp_user.equals(user)) {
                myLoans.add(temp_loan);
            }
        }
        return myLoans;
    }

    /**
     * Renvoie la liste des emprunts d'une oeuvres
     * @param book l'oeuvre liée aux exemplaires empruntés
     * @return la liste des emprunts de l'oeuvre en paramètre
     */
    public ArrayList<Loan> getLoans(Book book) {
        ArrayList<Loan> loans = new ArrayList<>();

        for (Copy copy : book.getCopies()) {
            loans.addAll(getLoans(copy));
        }

        return loans;
    }

    /**
     * Renvoie l'emprunt possédant l'id en paramètre
     * @param id l'id de l'emprunt à trouver
     * @return l'emprunt possédant l'id en paramètre ou null s'il n'existe pas
     */
    public Loan getLoan(long id) {
        Data data = Data.getInstance();
        ArrayList<Loan> loans = data.getLoans();

        Loan loan = null;

        boolean found = false;

        Iterator it = loans.listIterator();


        while (it.hasNext() && !found) {
            Loan temp_loan = (Loan) it.next();
            if (id == temp_loan.getId()) {
                found = true;
                loan = temp_loan;
            }
        }
        return loan;
    }

    /**
     * Renvoie les emprunts liés à l'exemplaire en paramètre
     * @param copy l'exemplaire à trouver
     * @return la liste des emprunts liés à l'exemplaire en paramètre
     */
    public ArrayList<Loan> getLoans(Copy copy) {
        Data data = Data.getInstance();
        ArrayList<Loan> loans = data.getLoans();

        ArrayList<Loan> copyLoans = new ArrayList<>();
        Boolean found = false;
        Iterator it = loans.listIterator();
        while (it.hasNext()) {
            Loan temp_loan = (Loan) it.next();
            Copy temp_copy = temp_loan.getCopy();
            if (temp_copy.equals(copy)) {
                copyLoans.add(temp_loan);
            }
        }
        return copyLoans;
    }

    /**
     * Ajoute un emprunt aux données
     *
     * @param loan L'emprunt à ajouter
     * @throws IOException Si la sauvegarde des données a échoué
     */
    public void createLoan(Loan loan) throws IOException {
        Data data = Data.getInstance();
        data.getLoans().add(loan);
        data.saveInstance();
    }

    /**
     * Modifie le statut de l'emprunt de emprunté à retourné
     * @param id l'id de l'emprunt à retourner
     * @throws IOException Si la sauvegarde des données à échoué
     */
    public void returnLoan(long id) throws IOException {
        getLoan(id).setReturned(true);
        Data.getInstance().saveInstance();

    }

    /**
     * Supprime un emprunt des données
     * @param loan L'emprunt à supprimer
     * @throws CannotRemoveItemException Si l'emprunt est encore en cours
     * @throws IOException Si la sauvegarde à échoué
     */
    public void removeLoan(Loan loan) throws CannotRemoveItemException, IOException {
        if(loan.isReturned()) {
            Data.getInstance().getLoans().remove(loan);
            Data.getInstance().saveInstance();
        } else {
            throw new CannotRemoveItemException("Can't remove unreturned loans");
        }

    }

    /**
     * Supprime une liste d'emprunts des données
     * @param loans La liste d'emprunts à supprimer
     * @throws IOException Si la sauvegarde a échoué
     * @throws CannotRemoveItemException Si l'un des emprunts est en cours
     */
    public void removeLoans(ArrayList<Loan> loans) throws IOException, CannotRemoveItemException {

        for (Loan loan : loans) {
            removeLoan(loan);
        }
        Data.getInstance().saveInstance();

    }


}
