package controller.API;

import controller.Util.SessionChecker;
import model.DAO.BookDAO;
import model.DAO.LoanDAO;
import model.beans.Book;
import model.beans.Copy;
import model.beans.Loan;
import model.beans.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * <p>
 * Emprunte une oeuvre pour l'utilisateur connecté
 * </p>
 * URL : "/loan_for_connected_user"
 * Permissions d'accès : Connecté
 */
public class LoanForConnectedUserAPI extends HttpServlet {
    /**
     * Emprunte une oeuvre pour l'utilisateur connecté
     *
     * @param request  L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //On initialise le code d'erreur
        int error_code = 0; //Aucune erreur

        SessionChecker sessionChecker = new SessionChecker(request);
        if (!sessionChecker.isConnected()) {
            //Si l'utilisateur n'est pas connecté on définit le code d'erreur
            error_code = -3;
        } else {
            //Sinon on emprunte l'oeuvre
            try {

                LoanDAO loanDAO = new LoanDAO();
                BookDAO bookDAO = new BookDAO();

                //On récupère l'utilisateur en session
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");

                //On récupère le livre à emprunter
                Book book = bookDAO.getBook(Integer.parseInt(request.getParameter("bookid")));

                //On cherche un exemplaire disponible
                Copy copy = new BookDAO().findAvailable(book);
                if (copy != null) {
                    //Si l'exemplaire est disponible on crée l'emprunt
                    Loan loan = new Loan(copy, new Date(), user);
                    //Et on l'ajoute aux données
                    loanDAO.createLoan(loan);
                } else {
                    //Sinon on définie le code d'erreur
                    error_code = -2;
                }

            } catch (Exception e) {
                //Si une erreur inattendue se produit on définit le code d'erreur
                error_code = -1;
                e.printStackTrace();
            }
        }
        //On envoie la réponse
        PrintWriter out = response.getWriter();
        out.println("{\"error_code\": " + error_code + "}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
