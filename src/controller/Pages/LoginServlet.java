package controller.Pages;
import controller.Util.Security;
import model.DAO.UserDAO;
import model.Data;
import model.beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginServlet extends javax.servlet.http.HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageTitle", "Authentification");

        String username = request.getParameter("username");
        String password = Security.get_SHA_512_SecurePassword(request.getParameter("password"), "");

        User user = new UserDAO().getUser(username, password);

        System.out.println("username : " + username);
        System.out.println("password : " + password);

        if(user == null) {
            request.setAttribute("erreur", "Identifiant ou mot de passe incorrecte");
            request.setAttribute("username", username);
            this.getServletContext().getRequestDispatcher( "/WEB-INF/view/login.jsp" ).forward( request, response );
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("/");
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageTitle", "Authentification");
        request.setAttribute("erreur", null);
        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/login.jsp" ).forward( request, response );
    }
}
