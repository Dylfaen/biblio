package controller.API;

import controller.Util.SessionChecker;
import model.DAO.BookDAO;
import model.DAO.UserDAO;
import model.beans.Book;
import model.beans.User;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UsersAPI {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int error_code = 0;
        String responseStr = "";
        SessionChecker sessionChecker = new SessionChecker(request);

        UserDAO userDAO = new UserDAO();
        ArrayList<User> users = UserDAO.getUsers();

        JsonArrayBuilder userBuilder = Json.createArrayBuilder();

        for(User user : users) {
            userBuilder.add(user.toJson());
        }

        JsonObject json = Json.createObjectBuilder().add("users", userBuilder).build();

        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        System.out.println(json.toString());

        out.print(json.toString());

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
