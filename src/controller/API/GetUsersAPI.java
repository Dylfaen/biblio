package controller.API;

import controller.Util.SessionChecker;
import model.DAO.UserDAO;
import model.beans.User;

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
 * Renvoie tous les utilisateurs en JSON
 * </p>
 * URL : "/get_all_users"
 * Permissions d'accès : Administrateur
 */
public class GetUsersAPI extends HttpServlet {
    /**
     * Renvoie tous les utilisateurs en JSON
     *
     * @param request  L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //On initialise le code d'erreur
        int error_code = 0; //Aucune erreur
        //On initialise la réponse
        String responseStr = "";

        SessionChecker sessionChecker = new SessionChecker(request);
        if (!sessionChecker.isAdmin()) {
            //Si l(utilisateur n'est pas administrateur et connecté on ajoute l'erreur à la réponse
            error_code = -1;
            responseStr = "{\"error_code\": " + error_code + "}";
        } else {
            //Sinon

            //On récupère la liste des utilisateurs
            UserDAO userDAO = new UserDAO();
            ArrayList<User> users = userDAO.getUsers();

            //On formatte la liste en JSON
            JsonArrayBuilder usersBuilder = Json.createArrayBuilder();
            for (User user : users) {
                JsonObjectBuilder builder = Json.createObjectBuilder().add("user", user.toJson());
                usersBuilder.add(builder);
            }
            JsonObject json = Json.createObjectBuilder().add("users", usersBuilder).build();


            //On ajoute le json à la réponse
            responseStr = json.toString();
        }

        //On envoie la réponse en UTF-8
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(responseStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
