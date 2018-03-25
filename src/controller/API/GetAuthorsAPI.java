package controller.API;

import controller.Util.SessionChecker;
import model.DAO.AuthorDAO;
import model.beans.Author;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * <p>
 * Renvoie tous les auteurs en JSON
 * </p>
 * URL : "/get_all_authors"
 * Permissions d'accès : Connecté
 */

public class GetAuthorsAPI extends HttpServlet {
    /**
     * Renvoie tous les auteurs en JSON
     *
     * @param request  L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

            //On récupère tous les auteurs
            AuthorDAO authorDAO = new AuthorDAO();
            ArrayList<Author> authors = authorDAO.getAuthors();


            //On les formatte en JSON
            JsonArrayBuilder authorsBuilder = Json.createArrayBuilder();
            for (Author author : authors) {
                authorsBuilder.add(author.toJson());
            }
            JsonObject json = Json.createObjectBuilder().add("authors", authorsBuilder).build();

            //On ajoute le json à la réponse
            responseStr = json.toString();
        }

        //On envoie la réponse en UTF-8
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(responseStr);

    }
}
