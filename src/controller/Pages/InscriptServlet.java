package controller.Pages;

import controller.Util.CannotRemoveItemException;
import controller.Util.Security;
import controller.Util.SessionChecker;
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


public class InscriptServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageTitle", "Inscription");

        String id = request.getParameter("identifiant-input");
        String password = request.getParameter("identifiant-input");
        String lastname = request.getParameter("nom-input");
        String firstname = request.getParameter("prenom-input");

        String birthdate = request.getParameter("birthdate-input");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = formatter.parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String address = request.getParameter("address-input");
        Boolean isAdmin = false;
        try {
            validationIdentifiant(id);
            validationPassword(password);
        } catch (Exception e) {
            request.setAttribute("erreur", e);
        }
        UserDAO userDAO = new UserDAO();

        try {
            User user = new User(id, Security.get_SHA_512_SecurePassword(password, ""), lastname, firstname, date, address, isAdmin);
            userDAO.createUser(user);
        } catch (UsernameTakenException e) {
            request.setAttribute("erreur", "Ce nom d'utilisateur est déjà pris");
        }

        if (request.getAttribute("erreur") == null) {
            request.setAttribute("erreur", "Pas d'erreurs detectés");
            response.sendRedirect("/");
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/view/nv_membre.jsp").forward(request, response);

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageTitle", "Administration");
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/nv_membre.jsp").forward(request, response);

    }

    private void validationIdentifiant(String id) throws Exception {
        if (id == null) {
            throw new Exception("Identifiant obligatoire");
        }
    }

    private void validationPassword(String password) throws Exception {
        if (password == null) {
            throw new Exception("Mot de Passe obligatoire");
        }
    }
}

