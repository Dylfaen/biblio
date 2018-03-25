package controller.Util;

import model.DAO.UserDAO;
import model.beans.User;

import javax.servlet.http.HttpServletRequest;

public class SessionChecker {

    private HttpServletRequest request;

    public SessionChecker(HttpServletRequest request) {
        this.request = request;
    }

    public boolean isConnected() {
        User sessionUser = (User)this.request.getSession().getAttribute("user");
        User user = null;
        if(sessionUser != null) {
            user = new UserDAO().getUser(sessionUser.getId());
        }

        return user != null;
    }

    public boolean isAdmin() {
        boolean isAdmin = false;

        User sessionUser = (User)this.request.getSession().getAttribute("user");
        User user = null;
        if(sessionUser != null) {
            user = new UserDAO().getUser(sessionUser.getId());
            if(isConnected()) {
                if (user.isAdmin()) {
                    isAdmin = true;
                }
            }
        }


        return isAdmin;
    }
}
