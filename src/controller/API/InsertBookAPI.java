package controller.API;

import controller.Util.SessionChecker;
import model.DAO.AuthorDAO;
import model.DAO.BookDAO;
import model.beans.Author;
import model.beans.Book;
import model.beans.Copy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class InsertBookAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int error_code = 0;


        SessionChecker sessionChecker = new SessionChecker(request);

        if(!sessionChecker.isAdmin()) {
            error_code = -3;
        } else {

            try {

                BookDAO bookDAO = new BookDAO();
                AuthorDAO authorDAO = new AuthorDAO();

                String title = request.getParameter("book[title]");
                System.out.println("title " + title);

                long authorId = Integer.parseInt(request.getParameter("book[authorid]"));
                System.out.println("authorID " + authorId);

                int nbCopies = Integer.parseInt(request.getParameter("book[copies]"));
                if (nbCopies > 0) {
                    Author author = authorDAO.getAuthor(authorId);
                    ArrayList<Copy> copies = new ArrayList<>();

                    Book book = new Book(author, title);

                    for (int i = 0; i < nbCopies; i++) {
                        copies.add(new Copy(book.getId()));
                    }

                    book.setCopies(copies);

                    bookDAO.createBook(book);
                } else {
                    error_code = -2;
                }

            } catch (Exception e) {
                error_code = -1;
                e.printStackTrace();
            }
        }
        PrintWriter out = response.getWriter();
        out.println("{\"error_code\": " + error_code + "}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
