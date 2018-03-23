package controller.API;

import controller.Util.SessionChecker;
import model.DAO.AuthorDAO;
import model.DAO.LoanDAO;
import model.beans.Author;
import model.beans.Loan;
import model.beans.User;

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

public class GetLoansForUserAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("test");
        int error_code = 0;
        String responseStr = "";
        SessionChecker sessionChecker = new SessionChecker(request);

        if(!sessionChecker.isConnected()) {
            error_code = -1;
            responseStr = "{\"error_code\": " + error_code + "}";
            System.out.println("size: " + responseStr);
        } else {
            LoanDAO loanDAO = new LoanDAO();

            ArrayList<Loan> loans = loanDAO.getLoans((User) request.getSession().getAttribute("user"));

            JsonArrayBuilder loansBuilder = Json.createArrayBuilder();

            System.out.println("size: " + loans.size());

            for(Loan loan : loans) {
                loansBuilder.add(loan.toJson());
            }

            JsonObject json = Json.createObjectBuilder().add("loans", loansBuilder).build();
            responseStr = json.toString();
        }




        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();


        System.out.println(responseStr);

        out.print(responseStr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
