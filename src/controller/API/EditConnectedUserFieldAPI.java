package controller.API;

import controller.Util.FormValidation;
import controller.Util.Security;
import controller.Util.SessionChecker;
import controller.Util.UsernameTakenException;
import model.DAO.UserDAO;
import model.beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * Edite les champs du profile de l'utilisateur connecté
 * </p>
 * URL : "/edit_user_field"
 * Permissions d'accès : Connecté
 */
public class EditConnectedUserFieldAPI extends HttpServlet {

    /**
     * Edite les champs du profil de l'utilisateur connecté
     *
     * @param request  L'objet de requête HTTP
     * @param response L'objet de réponse HTTP
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Initialisation du code d'erreur
        int error_code = 0; // Aucune erreur


        SessionChecker sessionChecker = new SessionChecker(request);
        if (!sessionChecker.isConnected()) {
            //Si l'utilisateur n'est pas connecté on renvoie le code d'erreur -3
            error_code = -3;
        } else {
            //Sinon on continue
            try {

                UserDAO userDAO = new UserDAO();

                // On récupère les paramètres envoyés
                String password = request.getParameter("password");
                String username = request.getParameter("username");
                String address = request.getParameter("address");

                String birthdateStr = request.getParameter("birthdate");


                /*
                On vérifie que la date est valide et au bon format
                 */
                boolean isDateOk = false;

                // On accepte deux format
                String format1 = "yyyy-MM-dd";
                String format2 = "dd/MM/yyyy";

                Date birthdate = null;
                if (birthdateStr != null && !birthdateStr.equals("")) {
                    //Si le paramètre birthdate n'est pas vide
                    if (FormValidation.checkDate(birthdateStr, format1)) {
                        //On tente de parser avec le premier format
                        try {
                            birthdate = new SimpleDateFormat(format1).parse(birthdateStr);
                            isDateOk = true;

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else if (FormValidation.checkDate(birthdateStr, format2)) {
                        //Sinon on tente le second format
                        try {
                            birthdate = new SimpleDateFormat(format2).parse(birthdateStr);
                            isDateOk = true;
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }


                /*
                 * On vérifie quel champ a été modifié et on modifie ce champ pour l'utilisateur connecté
                 */
                if (password != null) {
                    userDAO.editUserPassword(((User) request.getSession().getAttribute("user")).getId(), Security.get_SHA_512_SecurePassword(password, ""));
                } else if (username != null) {
                    try {
                        userDAO.editUserUsername(((User) request.getSession().getAttribute("user")).getId(), username);

                    } catch (UsernameTakenException e) {
                        error_code = -4;
                    }
                } else if (address != null) {
                    userDAO.editUserAddress(((User) request.getSession().getAttribute("user")).getId(), address);
                } else if (birthdate != null) {
                    if (isDateOk) {
                        userDAO.editUserBirthdate(((User) request.getSession().getAttribute("user")).getId(), birthdate);
                    } else {
                        //Si aucun des deux format n'a été accepté ou que la date est invalide on retourne une erreur
                        error_code = -5;
                    }
                } else {
                    //Si aucun champ n'a été trouvé à modifier on renvoie une erreur
                    error_code = -2;
                }


            } catch (Exception e) {
                // Si une erreur inattendue s'est produite (Ex : Impossible d'écrire dans le fichier)
                error_code = -1;
                e.printStackTrace();

            }
        }

        // On renvoie le code d'erreur dans un objet JSON
        PrintWriter out = response.getWriter();
        out.println("{\"error_code\": " + error_code + "}");

    }
}
