package controller.API;

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


                String firstname = request.getParameter("author[firstname]");
                String lastname = request.getParameter("author[lastname]");
                int year = Integer.parseInt(request.getParameter("author[birthdate][year]"));
                int month = Integer.parseInt(request.getParameter("author[birthdate][month]")) - 1;
                int day = Integer.parseInt(request.getParameter("author[birthdate][day]"));
                if (day > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    error_code = -2;
                }
                calendar.set(
                        Integer.parseInt(request.getParameter("author[birthdate][year]")),
                        month,
                        Integer.parseInt(request.getParameter("author[birthdate][day]"))
                );

                Date birthdate = calendar.getTime();
                String nationality = request.getParameter("author[nationality]");
                Author author = new Author(lastname, firstname, birthdate, nationality);
                authorDAO.createAuthor(author);
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
