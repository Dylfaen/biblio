package controller.API;

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

        BookDAO bookDAO = new BookDAO();
        ArrayList<Book> books = bookDAO.getBooks();

        JsonArrayBuilder oeuvresBuilder = Json.createArrayBuilder();

        for(Book book : books) {
            JsonObjectBuilder builder = Json.createObjectBuilder().add("book", book.toJson()).add("is_available", bookDAO.findAvailable(book) != null);
            oeuvresBuilder.add(builder);
        }

        JsonObject json = Json.createObjectBuilder().add("books", oeuvresBuilder).build();

        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        System.out.println(json.toString());

        out.print(json.toString());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
