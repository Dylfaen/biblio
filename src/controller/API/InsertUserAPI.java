package controller.API;

import controller.Util.FormValidation;
import controller.Util.Security;
import controller.Util.SessionChecker;
import controller.Util.UsernameTakenException;
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
import java.text.Normalizer;
import java.text.SimpleDateFormat;
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

                String birthdateStr = request.getParameter("user[birthdate]");

                boolean isDateOk = false;
                String format1 = "yyyy-MM-dd";
                String format2 = "dd/MM/yyyy";

                Date birthdate = null;
                if(FormValidation.checkDate(birthdateStr, format1)) {
                    birthdate = new SimpleDateFormat(format1).parse(birthdateStr);
                    isDateOk = true;
                } else if(FormValidation.checkDate(birthdateStr, format2)) {
                    birthdate = new SimpleDateFormat(format2).parse(birthdateStr);
                    isDateOk = true;
                }

                if (isDateOk) {

                    String address = request.getParameter("user[address]");

                    System.out.println("adresse : " + address);
                    boolean isAdmin = Boolean.valueOf(request.getParameter("user[isAdmin]"));
                    User user = new User(username,password, lastname, firstname, birthdate, address, isAdmin);

                    try {
                        userDAO.createUser(user);
                    } catch (UsernameTakenException e) {
                        error_code = -4;
                        e.printStackTrace();
                    }
                } else {
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
