package controller.Pages;

import controller.Util.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * Affiche la page d'administration des utilisateurs
 * </p>
 * URL : "/admin" <br>
 * Permissions d'accès : Administrateur
 */
public class AdminServlet extends HttpServlet {
    /**
     * Affiche la page d'administration
     *
     * @param request  L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //On récupère l'objet de vérification de session
        SessionChecker sessionChecker = new SessionChecker(request);
        if (!sessionChecker.isAdmin()) {
            //Si l'utilisateur n'est pas administrateur (et connecté) on le redirigie vers la page d'erreur
            response.sendRedirect("/permission_error");
        } else {
            //Sinon
            //On définit le titre
            request.setAttribute("pageTitle", "Administration");
            //Et on affiche la page
            this.getServletContext().getRequestDispatcher("/WEB-INF/view/users_list.jsp").forward(request, response);
        }

    }
}
