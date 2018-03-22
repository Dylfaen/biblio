package controller.API;

import model.DAO.AuthorDAO;
import model.DAO.BookDAO;
import model.DAO.LoanDAO;
import model.beans.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

public class LoanForConnectedUserAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int error_code = 0;

        try {

            LoanDAO loanDAO = new LoanDAO();
            BookDAO bookDAO = new BookDAO();

            HttpSession session = request.getSession();

            User user = (User) session.getAttribute("user");
            Book book = bookDAO.getBook(Integer.parseInt(request.getParameter("bookid")));
            Copy copy = new BookDAO().findAvailable(book);
            if(copy != null) {
                Loan loan = new Loan(copy, new Date(), user);
                loanDAO.createLoan(loan);
            } else {
                error_code = -2;
            }



        } catch (Exception e) {
            error_code = -1;
            e.printStackTrace();
        }
        System.out.println(error_code);
        PrintWriter out = response.getWriter();
        out.println("{\"error_code\": " + error_code + "}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
