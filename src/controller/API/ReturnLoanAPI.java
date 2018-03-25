package controller.API;

import controller.Util.SessionChecker;
import model.DAO.LoanDAO;
import model.beans.Loan;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * Retourne un emprunt
 * </p>
 * URL : "/return_copy"
 * Permissions d'accès : Connecté
 */
public class ReturnLoanAPI extends HttpServlet {
    /**
     * Retourne un emprunt
     *
     * @param request  L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //On initialise le code d'erreur
        int error_code = 0; //Aucune erreur

        SessionChecker sessionChecker = new SessionChecker(request);
        if(!sessionChecker.isConnected()) {
            //Si l'utilisateur n'est pas connecté on définit le code d'erreur
            error_code = -3;
        } else {
            //Sinon on retourne l'emprunt
            try {

                //On récupère l'emprunt
                LoanDAO loanDAO = new LoanDAO();
                Loan loan = loanDAO.getLoan(Integer.parseInt(request.getParameter("loanid")));

                if(loan.getUser().equals((request.getSession().getAttribute("user")))) {
                    //Si l'emprunt appartient à l'utilisateur connecté on le retourne
                    loanDAO.returnLoan(loan.getId());
                } else {
                    //Sinon on définit le code d'erreur
                    error_code = -2;
                }


            } catch (Exception e) {
                //Si une erreur inattendue survient on définit le code d'erreur
                error_code = -1;
                e.printStackTrace();
            }
        }

        //On envoie le code d'erreur
        PrintWriter out = response.getWriter();
        out.println("{\"error_code\": " + error_code + "}");
    }
}
