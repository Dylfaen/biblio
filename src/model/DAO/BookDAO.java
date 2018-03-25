package model.DAO;

import controller.Util.CannotRemoveItemException;
import model.Data;
import model.beans.Book;
import model.beans.Copy;
import model.beans.Loan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Le DAO des oeuvres. Il compile un ensemble de méthodes pour manipuler les Oeuvres de Data
 *
 * @see model.Data
 */
public class BookDAO {

    /**
     * Récupère une oeuvre en fonction de l'id en paramètre
     * @param id l'id à chercher
     * @return L'oeuvre à l'id passé en paramètre ou null si aucun ne correspond
     */
    public Book getBook(long id) {
        //on récupère la liste des oeuvres
        Data data = Data.getInstance();
        ArrayList<Book> users = data.getBooks();

        //On parcoure la liste jusqu'à trouver l'oeuvre ou avoir parcouru toute la liste
        Book book = null;
        Boolean found = false;
        Iterator it = users.listIterator();
        while (it.hasNext() && !found) {
            Book temp_book = (Book) it.next();
            if (temp_book.getId() == (id)) {
                //Si l'oeuvre parcourue à l'id recherché alors on a trouvé l'oeuvre
                book = temp_book;
                found = true;
            }
        }
        return book;
    }

    /**
     * Renvoie la liste des oeuvres de Data
     * @see Data
     * @return la liste des oeuvres de Data
     */
    public ArrayList<Book> getBooks() {
        return Data.getInstance().getBooks();
    }


    /**
     * Ajoute une oeuvre à Data
     *
     * @param book Le livre à ajouter
     * @see Data
     */
    public void createBook(Book book) throws IOException {
        //On récupère les oeuvres et on y ajoute la nouvelle oeuvre
        Data data = Data.getInstance();
        data.getBooks().add(book);

        //On sauvegarde les données
        data.saveInstance();
    }


    /**
     * Vérifie si un exemplaire est disponible
     * @param copy l'exemplaire à vérifier
     * @return True si l'exemplaire est disponible, False sinon
     */
    public boolean isAvailable(Copy copy) {
        // On récupère la liste des emprunts
        ArrayList<Loan> loans = Data.getInstance().getLoans();

        //On parcoure la liste des emprunts jusqu'à trouver l'exemplaire indisponible
        Iterator itLoans = loans.listIterator();
        boolean isCopyAvailable = true;
        while (itLoans.hasNext() && isCopyAvailable) {

            Loan loan = (Loan) itLoans.next();
            if (copy.equals(loan.getCopy()) && !loan.isReturned()) {
                //Si l'exemplaire de l'emprunt parcouru corrospond à l'exemplaire recherché et qu'il n'est pas retourné
                //Alors on s'est que l'exemplaire n'est pas disponible
                isCopyAvailable = false;
            }
        }
        //Si on a trouvé l'exemplaire indisponible alors renvoie False, True sinon
        return isCopyAvailable;
    }

    /**
     * Supprime un livre de la liste des oeuvres
     * @see LoanDAO
     * @param book L'oeuvre à supprimer
     * @throws IOException Si on n'a pas réussi à sauvegarder les données
     * @throws CannotRemoveItemException Si l'oeuvre a des exemplaires empruntés
     */
    public void removeBook(Book book) throws IOException, CannotRemoveItemException {

        //On récupère la liste des emprunts liés à l'oeuvre
        Data data = Data.getInstance();
        LoanDAO loanDAO = new LoanDAO();
        ArrayList<Loan> loansToRemove = loanDAO.getLoans(book);

        // On supprime tous les emprunts liés à l'oeuvre
        loanDAO.removeLoans(loansToRemove);
        data.getBooks().remove(book);


        //On sauvegarde l'instance des données
        data.saveInstance();
    }

    /**
     * Renvoie un exemplaire disponible de l'oeuvre en paramètre
     * @param book L'oeuvre à vérifier
     * @return L'exemplaire disponible, null sinon
     */
    public Copy findAvailable(Book book) {
        //On récupère la liste des emprunts
        ArrayList<Loan> loans = Data.getInstance().getLoans();
        //On reécupère les liste des exemplaires de l'oeuvre
        ArrayList<Copy> copies = book.getCopies();

        //On parcoure le liste des exemplaires jusqu'à trouver un exemplaire disponible
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
