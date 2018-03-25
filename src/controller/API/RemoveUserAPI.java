package controller.API;

import controller.Util.CannotRemoveItemException;
import controller.Util.SessionChecker;
import model.DAO.UserDAO;
import model.beans.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * <p>
 * Supprimes un utilisateur si il n'a aucun emprunt en cours
 * </p>
 * URL : "/remove_user"
 * Permissions d'accès : Administrateur
 */

public class RemoveUserAPI extends HttpServlet {
    /**
     * Supprimes un utilisateur si il n'a aucun emprunt en cours
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
            //Si l'utilisateur n'est pas administrateur et connecté on définit le code d'erreur
            error_code = -3;
        } else {
            //Sinon on supprime l'utilisateur
            try {

                //On récupère l'utilisateur
                UserDAO userDAO = new UserDAO();
                User user = userDAO.getUser(Integer.parseInt(request.getParameter("userid")));

                try {
                    //On le supprimes des données
                    userDAO.removeUser(user);
                } catch(CannotRemoveItemException e) {
                    //Si il a des emprunts en cours on définie le code d'erreur
                    e.printStackTrace();
                    error_code = -2;
                }

            } catch (Exception e) {
                //Si une erreur inattendue se produit on définie le code d'erreur
                error_code = -1;
                e.printStackTrace();
            }
        }

        //On renvoie le code d'erreur
        PrintWriter out = response.getWriter();
        out.println("{\"error_code\": " + error_code + "}");
    }
}
