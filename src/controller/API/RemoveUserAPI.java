package controller.API;

import controller.Util.CannotRemoveItemException;
import controller.Util.SessionChecker;
import model.DAO.BookDAO;
import model.DAO.UserDAO;
import model.beans.Book;
import model.beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class RemoveUserAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int error_code = 0;

        SessionChecker sessionChecker = new SessionChecker(request);

        if(!sessionChecker.isAdmin()) {
            error_code = -3;
        } else {

            try {
                UserDAO userDAO = new UserDAO();

                HttpSession session = request.getSession();

                User user = userDAO.getUser(Integer.parseInt(request.getParameter("userid")));

                try {
                    userDAO.removeUser(user);
                } catch(CannotRemoveItemException e) {
                    e.printStackTrace();
                    error_code = -2;
                }



            } catch (Exception e) {
                error_code = -1;
                e.printStackTrace();
            }
        }
        System.out.println(error_code);
        PrintWriter out = response.getWriter();
        out.println("{\"error_code\": " + error_code + "}");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
