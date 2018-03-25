package controller.API;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * Déconnecte l'utilisateur
 * </p>
 * URL : "/logout"
 * Permissions d'accès : Tous
 */
public class LogoutAPI extends HttpServlet {
    /**
     * Déconnecte l'utilisateur
     *
     * @param request  L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //On vide la session
        request.getSession().removeAttribute("user");
        //On redirige vers l'accueil
        response.sendRedirect("/");
    }
}
