package controller.API;

import controller.Util.FormValidation;
import controller.Util.SessionChecker;
import model.DAO.AuthorDAO;
import model.beans.Author;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * Insère un nouvel auteur
 * </p>
 * URL : "/insert_author"
 * Permissions d'accès : Administrateur
 */
public class InsertAuthorAPI extends HttpServlet {

    /**
     * Insère un nouvel auteur
     *
     * @param request  L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //On initialise le code d'erreur
        int error_code = 0; //Aucune erreur

        SessionChecker sessionChecker = new SessionChecker(request);
        if (!sessionChecker.isAdmin()) {
            //Si l'utilisateur n'est pas administrateur on défini le code d'erreur
            error_code = -3;
        } else {
            //Sinon on continue
            try {
                AuthorDAO authorDAO = new AuthorDAO();

                //On récupère les paramètres de la requète
                String firstname = request.getParameter("author[firstname]");
                String lastname = request.getParameter("author[lastname]");
                String birthdateStr = request.getParameter("author[birthdate]");

                //On accepte deux format de date
                boolean isDateOk = false;
                String format1 = "yyyy-MM-dd";
                String format2 = "dd/MM/yyyy";

                Date birthdate = null;
                if (FormValidation.checkDate(birthdateStr, format1)) {
                    //Si la date est au premier format et qu'elle est valide alors on la parse
                    birthdate = new SimpleDateFormat(format1).parse(birthdateStr);
                    isDateOk = true;
                } else if (FormValidation.checkDate(birthdateStr, format2)) {
                    //Si la date est au second format et qu'elle est valide alors on la parse
                    birthdate = new SimpleDateFormat(format2).parse(birthdateStr);
                    isDateOk = true;
                }

                if (isDateOk) {
                    //Si la date est valide et formatté correctement on crée l'auteur
                    String nationality = request.getParameter("author[nationality]");
                    Author author = new Author(lastname, firstname, birthdate, nationality);
                    //Et on l'ajoute aux données
                    authorDAO.createAuthor(author);
                } else {
                    //Sinon on définie le code d'erreur
                    error_code = -2;
                }
            } catch (Exception e) {
                //Si une erreur inattendue s'est produite
                error_code = -1;
            }
        }

        //On renvoie le code d'erreur
        PrintWriter out = response.getWriter();
        out.println("{\"error_code\": " + error_code + "}");
    }
}
