package controller.API;

import controller.Util.SessionChecker;
import model.DAO.LoanDAO;
import model.beans.Loan;
import model.beans.User;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * <p>
 * Renvoie tous les emprunts de l'utilisateur en JSON
 * </p>
 * URL : "/get_loans_for_user"
 * Permissions d'accès : Connecté
 */
public class GetLoansForUserAPI extends HttpServlet {

    /**
     * Renvoie tous les emprunts de l'utilisateur en JSON
     *
     * @param request  L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //On initialise le code d'erreur
        int error_code = 0; //Aucune erreur
        //On initialise la réponse
        String responseStr = "";


        SessionChecker sessionChecker = new SessionChecker(request);
        if (!sessionChecker.isConnected()) {
            //Si l'utilisateur n'est pas connecté on retourne le code d'erreur
            error_code = -1;
            responseStr = "{\"error_code\": " + error_code + "}";
        } else {

            //Sinon
            //On récupère les emprunts de l'utilisateur
            LoanDAO loanDAO = new LoanDAO();
            ArrayList<Loan> loans = loanDAO.getLoans((User) request.getSession().getAttribute("user"));

            //On les formatte en JSON
            JsonArrayBuilder loansBuilder = Json.createArrayBuilder();
            System.out.println("size: " + loans.size());
            for (Loan loan : loans) {
                loansBuilder.add(loan.toJson());
            }
            JsonObject json = Json.createObjectBuilder().add("loans", loansBuilder).build();

            //On ajoute le json à la réponse
            responseStr = json.toString();
        }


        //On envoie la réponse en UTF-8
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println(responseStr);
        out.print(responseStr);

    }
}
