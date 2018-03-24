package controller.Pages;

import controller.Util.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyAccountServlet")
public class MyAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SessionChecker sessionChecker = new SessionChecker(request);
        if(!sessionChecker.isConnected()) {
            response.sendRedirect("/permission_error");
        } else {
            request.setAttribute("pageTitle", "Mon compte");
            this.getServletContext().getRequestDispatcher( "/WEB-INF/view/my_account.jsp" ).forward( request, response );
        }

    }
}
