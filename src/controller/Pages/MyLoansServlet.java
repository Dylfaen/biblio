package controller.Pages;

import controller.Util.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * Affiche la page des emprunts de l'utilisateur
 * </p>
 * URL : "/my_loans" <br>
 * Permissions d'accès : Connecté
 */
public class MyLoansServlet extends HttpServlet {

    /**
     * Affiche la page des emprunts de l'utilisateur
     * @param request L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //On récupère l'objet de vérification de session
        SessionChecker sessionChecker = new SessionChecker(request);
        if (!sessionChecker.isConnected()) {
            //Si l'utilisateur n'est pas connecté, on redirige vers la page d'erreur
            response.sendRedirect("/permission_error");
        } else {
            //Sinon
            //On définie le titre
            request.setAttribute("pageTitle", "Mes emprunts");

            // Et on affiche la page
            this.getServletContext().getRequestDispatcher("/WEB-INF/view/my_loans_list.jsp").forward(request, response);
        }
    }
}
