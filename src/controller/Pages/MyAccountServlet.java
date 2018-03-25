package controller.Pages;

import controller.Util.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * Affiche la page de profil utilisateur
 * </p>
 * URL : "/my_account"
 * Permissions d'accès : Connecté
 */
public class MyAccountServlet extends HttpServlet {


    /**
     * Affiche la page de profile utilisateur
     *
     * @param request  L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //On réupère l'objet de vérification de session
        SessionChecker sessionChecker = new SessionChecker(request);
        if (!sessionChecker.isConnected()) {
            //Si l'utilisateur n'est pas connecté on le redirige ver la page d'erreur
            response.sendRedirect("/permission_error");
        } else {
            //Sinon
            //On définit le titre
            request.setAttribute("pageTitle", "Mon compte");
            //Et on affiche la pge
            this.getServletContext().getRequestDispatcher("/WEB-INF/view/my_account.jsp").forward(request, response);
        }

    }
}
