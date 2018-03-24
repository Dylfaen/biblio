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
        User user = new UserDAO().getUser(
                ((User)this.request.getSession().getAttribute("user")).getId()
        );
        return user != null;
    }

    public boolean isAdmin() {
        boolean isAdmin = false;

        User user = new UserDAO().getUser(
                ((User)this.request.getSession().getAttribute("user")).getId()
        );

        if(isConnected()) {
            if (user.isAdmin()) {
                isAdmin = true;
            }
        }
        return isAdmin;
    }
}
