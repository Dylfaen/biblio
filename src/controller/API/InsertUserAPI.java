package controller.API;

import controller.Util.Security;
import controller.Util.SessionChecker;
import model.DAO.AuthorDAO;
import model.DAO.UserDAO;
import model.beans.Author;
import model.beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

public class InsertUserAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int error_code = 0;

        SessionChecker sessionChecker = new SessionChecker(request);

        if(!sessionChecker.isAdmin()) {
            error_code = -3;
        } else {
            try {

                UserDAO userDAO = new UserDAO();

                Calendar calendar = Calendar.getInstance();


                String username = request.getParameter("user[username]");
                String password = request.getParameter("user[password]");
                String firstname = request.getParameter("user[firstname]");
                String lastname = request.getParameter("user[lastname]");

                password = Security.get_SHA_512_SecurePassword(password, "");

                int year = Integer.parseInt(request.getParameter("user[birthdate][year]"));
                int month = Integer.parseInt(request.getParameter("user[birthdate][month]")) - 1;
                int day = Integer.parseInt(request.getParameter("user[birthdate][day]"));

                if (day > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    error_code = -2;
                }
                calendar.set(
                        Integer.parseInt(request.getParameter("user[birthdate][year]")),
                        month,
                        Integer.parseInt(request.getParameter("user[birthdate][day]"))
                );

                Date birthdate = calendar.getTime();
                String address = request.getParameter("user[address]");

                System.out.println("adresse : " + address);
                boolean isAdmin = Boolean.valueOf(request.getParameter("user[isAdmin]"));
                User user = new User(username,password, lastname, firstname, birthdate, address, isAdmin);
                UserDAO.createUser(user);
            } catch (Exception e) {
                error_code = -1;

            }
        }
        System.out.println(error_code);
        PrintWriter out = response.getWriter();
        out.println("{\"error_code\": " + error_code + "}");

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
