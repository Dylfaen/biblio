package controller.Pages;

import controller.Util.FormValidation;
import controller.Util.Security;
import controller.Util.UsernameTakenException;
import model.DAO.UserDAO;
import model.beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * <p>
 * Permet à l'utilisateur de s'inscrire
 * </p>
 * URL : "/signup" <br>
 * Permission d'accès : Tous
 */
public class SignupServlet extends HttpServlet {

    /**
     * Traite l'inscription de l'utilisateur
     * @param request L'objet requête HTPP
     * @param response L'objet réponse HTTP
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Définition du titre de la page
        request.setAttribute("pageTitle", "Inscription");


        //On récupère les paramètres du formulaire
        String id = request.getParameter("identifiant-input");
        String password = request.getParameter("password-input");
        String lastname = request.getParameter("nom-input");
        String firstname = request.getParameter("prenom-input");
        String birthdate = request.getParameter("birthdate-input");
        String address = request.getParameter("address-input");
        Boolean isAdmin = false;


        //On formatte la date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = formatter.parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // On vérifie que l'identifiant et le mot de passe sont bien renseignés
        try {
            FormValidation.validationIdentifiant(id);
            FormValidation.validationPassword(password);
        } catch (Exception e) {
            request.setAttribute("erreur", e);
        }

        //On initialise le UserDAO
        UserDAO userDAO = new UserDAO();

        //On ajoute l'utilisateur à Data
        try {
            User user = new User(id, Security.get_SHA_512_SecurePassword(password, ""), lastname, firstname, date, address, isAdmin);
            userDAO.createUser(user);
        } catch (UsernameTakenException e) {
            //createUser peut retourner une Exception si l'identifiant existe déjà
            request.setAttribute("erreur", "Ce nom d'utilisateur est déjà pris");
        }

        if (request.getAttribute("erreur") == null) {
            //Si aucune erreur n'est détectée on redirige vers la HomeServlet
            response.sendRedirect("/");
        } else {
            //Sinon on affiche à nouveau la page d'inscription avec les erreurs
            this.getServletContext().getRequestDispatcher("/WEB-INF/view/nv_membre.jsp").forward(request, response);

        }


    }

    /**
     * Affiche la page d'inscription
     *
     * @param request  L'objet requête HTPP
     * @param response L'objet réponse HTTP
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //On définit le titre de la page
        request.setAttribute("pageTitle", "Administration");
        //On affiche la page
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/nv_membre.jsp").forward(request, response);

    }
}

