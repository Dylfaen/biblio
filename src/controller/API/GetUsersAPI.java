package controller.API;

import controller.Util.SessionChecker;
import model.DAO.UserDAO;
import model.beans.User;

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

public class GetUsersAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int error_code = 0;
        String responseStr = "";
        SessionChecker sessionChecker = new SessionChecker(request);

        if (!sessionChecker.isConnected()) {
            error_code = -1;
            responseStr = "{\"error_code\": " + error_code + "}";
        } else {

            UserDAO userDAO = new UserDAO();
            ArrayList<User> users = userDAO.getUsers();

            JsonArrayBuilder usersBuilder = Json.createArrayBuilder();

            for (User user : users) {
                JsonObjectBuilder builder = Json.createObjectBuilder().add("user", user.toJson());
                usersBuilder.add(builder);
            }

            JsonObject json = Json.createObjectBuilder().add("users", usersBuilder).build();


            responseStr = json.toString();
        }

        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.print(responseStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
