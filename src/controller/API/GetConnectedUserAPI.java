package controller.API;

import controller.Util.SessionChecker;
import model.beans.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * Renvoie l'utilisateur connecté en JSON
 * </p>
 * URL : "/get_connected_user"
 * Permissions d'accès : Connecté
 */
public class GetConnectedUserAPI extends HttpServlet {
    /**
     * Renvoie l'utilisateur connecté en JSON
     *
     * @param request  L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Initialise la reponse
        String responseStr;
        //Initialise le code d'erreur
        int error_code; //Aucune erreur;


        SessionChecker sessionChecker = new SessionChecker(request);
        if (sessionChecker.isConnected()) {
            //Si l'utilisateur est connecté on le formatte en json et on l'ajoute à la réponse
            User user = (User) request.getSession().getAttribute("user");
            responseStr = user.toJson().build().toString();

        } else {
            //Sinon on défini le code d'erreur
            error_code = -1;
            responseStr = "{\'error_code\' : " + error_code + "}";
        }

        //On envoie la réponse en UTF-8
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(responseStr);

    }
}
