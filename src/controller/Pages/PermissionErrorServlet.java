package controller.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * On affiche cette servlet lorsque l'utilisateur accès à une page non autorisée.
 * Ex : L'utilisateur essaye de voir la list des emprunts sans être connecté ou la page d'administration sans être administrateur.
 * </p>
 * URL : "/permission_error" <br>
 * Permission d'accès : Tous
 */
public class PermissionErrorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //On définit le titre de la page
        request.setAttribute("pageTitle", "Erreur");
        //On affiche la page
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/permission_error.jsp").forward(request, response);
    }
}
