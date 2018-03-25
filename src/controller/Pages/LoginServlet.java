package controller.Pages;

import controller.Util.Security;
import model.DAO.UserDAO;
import model.beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <p>
 * Page de connexion, elle redirige vers la Servlet d'accueil (HomeServlet) une fois connecté
 * </p>
 * URL : "/login"
 * Permissions d'accès : Tous
 */
public class LoginServlet extends javax.servlet.http.HttpServlet {
    /**
     * Traite la connexion de l'utilisateur
     *
     * @param request  L'objet requête HTPP
     * @param response L'objet réponse HTTP
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //On définit le titre de la page
        request.setAttribute("pageTitle", "Authentification");

        //On récupère les paramètres du formulaires (identifiant et mot de passe)
        String username = request.getParameter("username");
        String password = Security.get_SHA_512_SecurePassword(request.getParameter("password"), "");

        //On récupère l'utilisateur
        User user = new UserDAO().getUser(username, password);


        if (user == null) {
            //Si l'utilisateur n'existe pas, on défini le message d'erreur et on retourne sur la page de connexion
            request.setAttribute("erreur", "Identifiant ou mot de passe incorrecte");
            request.setAttribute("username", username);
            this.getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        } else {
            //Sinon, l'utilisateur peut accéder à son espace. On le redirige vers la HomeServlet
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("/");
        }

    }

    /**
     * Affiche la page de connexion
     *
     * @param request  L'objet requête HTPP
     * @param response L'objet réponse HTTP
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //On définit le titre de la page
        request.setAttribute("pageTitle", "Authentification");
        request.setAttribute("erreur", null);
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }
}
