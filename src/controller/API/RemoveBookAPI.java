package controller.API;

import controller.Util.CannotRemoveItemException;
import controller.Util.SessionChecker;
import model.DAO.BookDAO;
import model.beans.Book;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * Supprimes une oeuvre si aucun exemplaire n'est emprunté
 * </p>
 * URL : "/remove_book"
 * Permissions d'accès : Administrateur
 */
public class RemoveBookAPI extends HttpServlet {
    /**
     * Supprimes une oeuvre si aucun exemplaire n'est emprunté
     *
     * @param request  L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //On initialise le code d'erreur
        int error_code = 0; //Aucune erreur

        SessionChecker sessionChecker = new SessionChecker(request);
        if(!sessionChecker.isAdmin()) {
            //Si l'utilisateur n'est pas administrateur et connecté on définie le code d'erreur
            error_code = -3;
        } else {
            //Sinon on supprime l'oeuvre
            try {

                //On récupère le livre
                BookDAO bookDAO = new BookDAO();
                Book book = bookDAO.getBook(Integer.parseInt(request.getParameter("bookid")));
                //On le supprime des données
                bookDAO.removeBook(book);


            } catch (CannotRemoveItemException e) {
                //Si des exemplaires sont empruntés on définie le code d'erreur
                error_code = -2;
                e.printStackTrace();
            } catch (IOException e) {
                //Si on a pas pu supprimer l'oeuvre pour une autre raison on définie le code d'erreur
                error_code = -3;
                e.printStackTrace();
            }
        }
        //On envoie la réponse
        PrintWriter out = response.getWriter();
        out.println("{\"error_code\": " + error_code + "}");
    }
}
