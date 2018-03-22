package controller.API;

import controller.Util.SessionChecker;
import model.DAO.BookDAO;
import model.beans.Book;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GetBooksAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int error_code = 0;
        String responseStr = "";
        SessionChecker sessionChecker = new SessionChecker(request);

        if(!sessionChecker.isConnected()) {
            error_code = -1;
            responseStr = "{\"error_code\": " + error_code + "}";
        } else {

            BookDAO bookDAO = new BookDAO();
            ArrayList<Book> books = bookDAO.getBooks();

            JsonArrayBuilder oeuvresBuilder = Json.createArrayBuilder();

            for (Book book : books) {
                JsonObjectBuilder builder = Json.createObjectBuilder().add("book", book.toJson()).add("is_available", bookDAO.findAvailable(book) != null);
                oeuvresBuilder.add(builder);
            }

            JsonObject json = Json.createObjectBuilder().add("books", oeuvresBuilder).build();


            responseStr = json.toString();
        }

        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.print(responseStr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
