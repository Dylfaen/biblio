package controller.API;

import controller.Util.FormValidation;
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
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditConnectedUserFieldAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int error_code = 0;

        SessionChecker sessionChecker = new SessionChecker(request);

        if(!sessionChecker.isConnected()) {
            error_code = -3;
        } else {
            try {

                UserDAO userDAO = new UserDAO();

                String password = request.getParameter("password");
                String username = request.getParameter("username");
                String address = request.getParameter("address");

                String birthdateStr = request.getParameter("birthdate");



                boolean isDateOk = false;
                String format1 = "yyyy-MM-dd";
                String format2 = "dd/MM/yyyy";

                Date birthdate = null;
                if(birthdateStr != null && !birthdateStr.equals("")) {
                    if(FormValidation.checkDate(birthdateStr, format1)) {
                        try {
                            birthdate = new SimpleDateFormat(format1).parse(birthdateStr);
                            isDateOk = true;

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else if(FormValidation.checkDate(birthdateStr, format2)) {
                        try {
                            birthdate = new SimpleDateFormat(format2).parse(birthdateStr);
                            isDateOk = true;
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }



                if(password != null) {
                    userDAO.editUserPassword(((User) request.getSession().getAttribute("user")).getId(), Security.get_SHA_512_SecurePassword(password, ""));
                } else if(username != null) {
                    try {
                        userDAO.editUserUsername(((User) request.getSession().getAttribute("user")).getId(),username);

                    } catch (UsernameTakenException e) {
                        error_code = -4;
                    }
                } else if(address != null) {
                    userDAO.editUserAddress(((User) request.getSession().getAttribute("user")).getId(),address);
                } else if(birthdate != null) {
                    if(isDateOk) {
                        userDAO.editUserBirthdate(((User) request.getSession().getAttribute("user")).getId(),birthdate);
                    } else {
                        error_code = -5;
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
