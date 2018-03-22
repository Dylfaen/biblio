package controller.Pages;

import controller.Util.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageTitle", "Emprunter");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionChecker sessionChecker = new SessionChecker(request);
        if(!sessionChecker.isConnected()) {
            response.sendRedirect("/permission_error");
        } else {
            request.setAttribute("pageTitle", "Emprunter");

            this.getServletContext().getRequestDispatcher( "/WEB-INF/view/books_list.jsp" ).forward( request, response );
        }
    }
}
