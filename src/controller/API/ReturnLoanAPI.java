package controller.API;

import controller.Util.SessionChecker;
import model.DAO.BookDAO;
import model.DAO.LoanDAO;
import model.beans.Book;
import model.beans.Copy;
import model.beans.Loan;
import model.beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class ReturnLoanAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int error_code = 0;

        SessionChecker sessionChecker = new SessionChecker(request);

        Loan loan = null;


        if(!sessionChecker.isConnected()) {
            error_code = -3;
        } else {

            try {

                LoanDAO loanDAO = new LoanDAO();

                loan = loanDAO.getLoan(Integer.parseInt(request.getParameter("loanid")));

                if(loan.getUser().equals((request.getSession().getAttribute("user")))) {
                    loanDAO.returnLoan(loan.getId());
                } else {
                    error_code = -2;
                }


            } catch (Exception e) {
                error_code = -1;
                e.printStackTrace();
            }
        }
        System.out.println(error_code);
        PrintWriter out = response.getWriter();
        out.println("{\"error_code\": " + error_code + "}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
