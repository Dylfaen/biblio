package controller.Util;

import model.DAO.UserDAO;
import model.beans.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Cette classe sert à déterminer l'état de variables de session importantes (notamment concernant l'utilisateur connecté)
 */
public class SessionChecker {

    /**
     * L'objet de Requête HTTP contennt les variables de session
     */
    private HttpServletRequest request;

    /**
     * Construit le SessionChecker
     *
     * @param request L'objet de requête HTTP contenant les variables de ssession;
     */
    public SessionChecker(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Vérifie que l'utilisateur est bien connecté
     * @return True si l'utilisateur est connecté, False sinon
     */
    public boolean isConnected() {
        //On récupère l'utilisateur en session
        User sessionUser = (User)this.request.getSession().getAttribute("user");
        User user = null;
        if(sessionUser != null) {
            //Si l'utilisateur est connecté
            //On vérifie qu'il existe toujours dans les données
            user = new UserDAO().getUser(sessionUser.getId());
        }

        return user != null;
    }

    /**
     * Vérifie que l'utilisateur est un administrateur connecté
     * @return True si l'utilisateur est connecté et administrateur, False sinon
     */
    public boolean isAdmin() {
        boolean isAdmin = false;

        //On récupère l'utilisateur en session
        User sessionUser = (User)this.request.getSession().getAttribute("user");
        User user = null;
        if(sessionUser != null) {
            //On vérifie qu'il existe toujours
            user = new UserDAO().getUser(sessionUser.getId());
            if(isConnected()) {
                if (user.isAdmin()) {
                    //Si il est connecté et administrateur alors on retourne vrai
                    isAdmin = true;
                }
            }
        }


        return isAdmin;
    }
}
