package controller.API;

import controller.Util.FormValidation;
import controller.Util.Security;
import controller.Util.SessionChecker;
import controller.Util.UsernameTakenException;
import model.DAO.UserDAO;
import model.beans.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * Insère un nouvel utilisateur
 * </p>
 * URL : "/insert_book"
 * Permissions d'accès : Administrateur
 */
public class InsertUserAPI extends HttpServlet {
    /**
     * Insère un nouvel utilisateur
     *
     * @param request  L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //On initialise le code d'erreur
        int error_code = 0; //Aucune erreur

        SessionChecker sessionChecker = new SessionChecker(request);
        if(!sessionChecker.isAdmin()) {
            //Si l'utilisateur n'est pas administarteur et connecté on définit le code d'erreur
            error_code = -3;
        } else {
            //Sinnon on insére l'utilisateur
            try {

                UserDAO userDAO = new UserDAO();

                //On récupère les paramètres de la requète
                String username = request.getParameter("user[username]");
                String password = request.getParameter("user[password]");
                String firstname = request.getParameter("user[firstname]");
                String lastname = request.getParameter("user[lastname]");
                String birthdateStr = request.getParameter("user[birthdate]");
                String address = request.getParameter("user[address]");
                boolean isAdmin = Boolean.valueOf(request.getParameter("user[isAdmin]"));

                //On hash le mot de passe
                password = Security.get_SHA_512_SecurePassword(password, "");

                //On accepte deux formats de date
                boolean isDateOk = false;
                String format1 = "yyyy-MM-dd";
                String format2 = "dd/MM/yyyy";

                Date birthdate = null;
                if(FormValidation.checkDate(birthdateStr, format1)) {
                    //Si la date correspond au premier format, on la parse à ce format
                    birthdate = new SimpleDateFormat(format1).parse(birthdateStr);
                    isDateOk = true;
                } else if(FormValidation.checkDate(birthdateStr, format2)) {
                    //Si la date correpsond au second format, on la parse à ce format
                    birthdate = new SimpleDateFormat(format2).parse(birthdateStr);
                    isDateOk = true;
                }

                if (isDateOk) {
                    //Si la date est valide et au bon format on crée l'utilisateur
                    User user = new User(username,password, lastname, firstname, birthdate, address, isAdmin);

                    try {
                        //On l'insère dans les données
                        userDAO.createUser(user);
                    } catch (UsernameTakenException e) {
                        //Si il y a une erreur à l'insertion on définit le code d'erreur
                        error_code = -4;
                        e.printStackTrace();
                    }
                } else {
                    //Sinon on définit le code d'erreur
                    error_code = -2;
                }

            } catch (Exception e) {
                //Si il y a une erreur inattendue on définit le code d'erreur
                error_code = -1;
                e.printStackTrace();
            }
        }
        //On retourne le code d'erreur
        PrintWriter out = response.getWriter();
        out.println("{\"error_code\": " + error_code + "}");

    }
}
