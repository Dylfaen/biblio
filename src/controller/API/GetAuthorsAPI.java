package controller.API;

import controller.Util.SessionChecker;
import model.DAO.AuthorDAO;
import model.beans.Author;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GetAuthorsAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int error_code = 0;
        String responseStr = "";
        SessionChecker sessionChecker = new SessionChecker(request);
        if(!sessionChecker.isConnected()) {
            error_code = -1;
            responseStr = "{\"error_code\": " + error_code + "}";
        } else {
            AuthorDAO authorDAO = new AuthorDAO();
            ArrayList<Author> authors = authorDAO.getAuthors();

            JsonArrayBuilder authorsBuilder = Json.createArrayBuilder();

            for(Author author : authors) {
                authorsBuilder.add(author.toJson());
            }

            JsonObject json = Json.createObjectBuilder().add("authors", authorsBuilder).build();

            responseStr = json.toString();
        }




        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();


        out.print(responseStr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
