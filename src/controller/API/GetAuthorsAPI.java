package controller.API;

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

        AuthorDAO authorDAO = new AuthorDAO();
        ArrayList<Author> authors = authorDAO.getAuthors();

        JsonArrayBuilder oeuvresBuilder = Json.createArrayBuilder();

        for(Author author : authors) {
            oeuvresBuilder.add(author.toJson());
        }

        JsonObject json = Json.createObjectBuilder().add("authors", oeuvresBuilder).build();

        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println(json.toString());

        out.print(json.toString());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
