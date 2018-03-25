package controller.Pages;

import controller.Util.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * Page d'emprunt, elle affiche la liste des oeuvres et permet de les emprunter et de les gérer (ajout, suppression)
 * </p>
 * URL : "/loan"
 * Permissions d'accès : Connecté
 */
public class LoanServlet extends HttpServlet {


    /**
     * Affiche la page des emprunts
     *
     * @param request  L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //On récupère la classe de vérification de permission
        SessionChecker sessionChecker = new SessionChecker(request);
        if (!sessionChecker.isConnected()) {
            // Si l'utilisateur n'est pas connecté on affiche la page d'erreur
            response.sendRedirect("/permission_error");
        } else {
            //Si l'utilisateur est connecté on affiche la page


            request.setAttribute("pageTitle", "Emprunter"); //On définit la titre de la page
            // On affaiche la page
            this.getServletContext().getRequestDispatcher("/WEB-INF/view/books_list.jsp").forward(request, response);
        }
    }
}
