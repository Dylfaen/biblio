package controller.API;

import controller.Util.FormValidation;
import controller.Util.SessionChecker;
import model.DAO.AuthorDAO;
import model.beans.Author;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

public class InsertAuthorAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int error_code = 0;

        SessionChecker sessionChecker = new SessionChecker(request);

        if(!sessionChecker.isAdmin()) {
            error_code = -3;
        } else {

            try {

                AuthorDAO authorDAO = new AuthorDAO();

                Calendar calendar = Calendar.getInstance();
                calendar.setLenient(false);


                String firstname = request.getParameter("author[firstname]");
                String lastname = request.getParameter("author[lastname]");
                String birthdateStr = request.getParameter("author[birthdate]");

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
                    String nationality = request.getParameter("author[nationality]");
                    Author author = new Author(lastname, firstname, birthdate, nationality);
                    authorDAO.createAuthor(author);
                } else {
                    error_code = -2;
                }


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
