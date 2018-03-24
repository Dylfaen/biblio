package controller.API;

import controller.Util.Security;
import controller.Util.SessionChecker;
import model.DAO.UserDAO;
import model.beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
                String yearStr = request.getParameter("birthdate[year]");
                String monthStr = request.getParameter("birthdate[month]");
                String dayStr = request.getParameter("birthdate[day]");

                Integer year = null;
                Integer month = null;
                Integer day = null;
                Calendar calendar = Calendar.getInstance();
                if(yearStr != null && monthStr != null && dayStr != null) {
                    year = Integer.parseInt(yearStr);
                    month = Integer.parseInt(monthStr);
                    day = Integer.parseInt(dayStr);
                    calendar.set(year, month-1, day);
                }



                if(password != null) {
                    userDAO.editUserPassword(((User) request.getSession().getAttribute("user")).getId(), Security.get_SHA_512_SecurePassword(password, ""));
                } else if(username != null) {
                    userDAO.editUserUsername(((User) request.getSession().getAttribute("user")).getId(),username);
                } else if(address != null) {
                    userDAO.editUserAddress(((User) request.getSession().getAttribute("user")).getId(),address);
                } else if(year != null && month != null && day != null) {
                    userDAO.editUserBirthdate(((User) request.getSession().getAttribute("user")).getId(),calendar.getTime());
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
