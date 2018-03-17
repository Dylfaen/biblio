package controller;
import model.DAO.OeuvreDAO;
import model.beans.Oeuvre;
import model.requests.Connexion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "EmpruntServlet")
public class EmpruntServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageTitle", "Emprunt");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageTitle", "Emprunt");

        OeuvreDAO oeuvreDAO = new OeuvreDAO(Connexion.connect());

        request.setAttribute("oeuvres", oeuvreDAO.getOeuvres());

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/liste_oeuvres.jsp" ).forward( request, response );
    }
}
