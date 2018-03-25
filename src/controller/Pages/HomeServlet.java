package controller.Pages;

import controller.Util.Security;
import controller.Util.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageTitle", "Accueil");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("pageTitle", "Accueil");

        if(new SessionChecker(request).isConnected()) {
            response.sendRedirect("/loan");
        } else {
            response.sendRedirect("/login");
        }
    }
}

