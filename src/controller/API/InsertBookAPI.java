package controller.API;

import controller.Util.SessionChecker;
import model.DAO.AuthorDAO;
import model.DAO.BookDAO;
import model.beans.Author;
import model.beans.Book;
import model.beans.Copy;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * <p>
 * Insère une nouvelle oeuvre
 * </p>
 * URL : "/insert_book"
 * Permissions d'accès : Administrateur
 */
public class InsertBookAPI extends HttpServlet {
    /**
     * Insère une nouvelle oeuvre
     *
     * @param request  L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //On initialise le code d'erreur
        int error_code = 0; //Aucune erreur

        SessionChecker sessionChecker = new SessionChecker(request);
        if (!sessionChecker.isAdmin()) {
            //Si l'utilisateur n'est pas administrateur et connecté on définit le code d'erreur
            error_code = -3;
        } else {
            //Sinon on ajoute l'oeuvre
            try {

                BookDAO bookDAO = new BookDAO();
                AuthorDAO authorDAO = new AuthorDAO();

                //On récupère les paramètres
                String title = request.getParameter("book[title]");
                long authorId = Integer.parseInt(request.getParameter("book[authorid]"));
                int nbCopies = Integer.parseInt(request.getParameter("book[copies]"));

                if (nbCopies > 0) {
                    //Si le nombre d'exemplaires est valide

                    //On récupère l'auteur
                    Author author = authorDAO.getAuthor(authorId);
                    //On initialise la liste d'exemplaires
                    ArrayList<Copy> copies = new ArrayList<>();

                    //On crée le livre (sans les exemplaires)
                    Book book = new Book(author, title);

                    //On ajoute les exemplaires à la liste d'exemplaires
                    for (int i = 0; i < nbCopies; i++) {
                        copies.add(new Copy(book.getId()));
                    }
                    //On ajoute les copies à l'oeuvre
                    book.setCopies(copies);
                    //On ajoute le livre aux données
                    bookDAO.createBook(book);
                } else {
                    //Sinon on définit le code d'erreur
                    error_code = -2;
                }

            } catch (Exception e) {
                //Si une erreur inattendue s'est produite on définit le code d'erreur
                error_code = -1;
                e.printStackTrace();
            }
        }
        //On retourne le code d'erreur
        PrintWriter out = response.getWriter();
        out.println("{\"error_code\": " + error_code + "}");
    }
}
