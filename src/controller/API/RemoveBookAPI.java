package controller.API;

import controller.Util.CannotRemoveItemException;
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

public class RemoveBookAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int error_code = 0;

        SessionChecker sessionChecker = new SessionChecker(request);

        if(!sessionChecker.isAdmin()) {
            error_code = -3;
        } else {

            try {
                BookDAO bookDAO = new BookDAO();

                HttpSession session = request.getSession();

                Book book = bookDAO.getBook(Integer.parseInt(request.getParameter("bookid")));

                bookDAO.removeBook(book);


            } catch (CannotRemoveItemException e) {
                error_code = -2;
                e.printStackTrace();
            } catch (IOException e) {
                error_code = -3;
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
