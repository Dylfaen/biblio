package controller.Pages;

import controller.Util.SessionChecker;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * Servlet d'entrée, elle redirige vers d'autres pages en fonction de l'état de connexion de l'utilisateur en session
 * </p>
 * URL : "/" <br>
 * Permission d'accès: Tous
 */
public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Titre de la page
        request.setAttribute("pageTitle", "Accueil");

        if (new SessionChecker(request).isConnected()) {
            //Si l'utilisateur est conncecté on affiche la page "Emprunter"
            response.sendRedirect("/loan");
        } else {
            //Sinon on afficjhe la page de connexion
            response.sendRedirect("/login");
        }
    }
}

