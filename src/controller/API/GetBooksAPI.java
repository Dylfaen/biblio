package controller.API;

import controller.Util.SessionChecker;
import model.DAO.BookDAO;
import model.beans.Book;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * <p>
 * Renvoie toutes les oeuvres en JSON
 * </p>
 * URL : "/get_all_books"
 * Permissions d'accès : Connecté
 */
public class GetBooksAPI extends HttpServlet {

    /**
     * Renvoie toutes les oeuvres en JSON
     *
     * @param request  L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Le code d'erreur
        int error_code = 0; //Aucune erreur

        //On initialise la réponse
        String responseStr = "";


        SessionChecker sessionChecker = new SessionChecker(request);
        if (!sessionChecker.isConnected()) {
            //Si l'utilisateur n'est pas connecté
            error_code = -1;
            responseStr = "{\"error_code\": " + error_code + "}";
        } else {
            //Sinon

            //On récupère les oeuvres
            BookDAO bookDAO = new BookDAO();
            ArrayList<Book> books = bookDAO.getBooks();

            //On les formatte en JSON
            JsonArrayBuilder booksBuilder = Json.createArrayBuilder();
            for (Book book : books) {
                JsonObjectBuilder builder = Json.createObjectBuilder().add("book", book.toJson()).add("is_available", bookDAO.findAvailable(book) != null);
                booksBuilder.add(builder);
            }
            JsonObject json = Json.createObjectBuilder().add("books", booksBuilder).build();

            //On ajoute le json à la réponse
            responseStr = json.toString();
        }

        //On envoie la réponse en UTF-8
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(responseStr);

    }
}
