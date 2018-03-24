package controller.API;

import model.beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetConnectedUserAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String responseStr = "";
        int error_code = 0;

        User user = (User) request.getSession().getAttribute("user");

        if(user == null) {
            error_code = -1;
            responseStr = "{\'error_code\' : " + error_code + "}";
        } else {
            responseStr = user.toJson().build().toString();
        }

        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(responseStr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
