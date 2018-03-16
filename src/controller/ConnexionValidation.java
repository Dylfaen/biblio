package controller;

import model.DAO.ClientDAO;
import model.beans.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ConnexionValidation")
public class ConnexionValidation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        Client client = new ClientDAO()
                .getClient(request.getParameter("email"), request.getParameter("password"));

        if(client == null) {
            out.println("{data: -1}");

        } else {
            out.println("{data: " + client.toString() + "}");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
