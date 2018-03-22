package controller.Util;

import model.beans.User;

import javax.servlet.http.HttpServletRequest;

public class SessionChecker {

    HttpServletRequest request;

    public SessionChecker(HttpServletRequest request) {
        this.request = request;
    }

    public boolean isConnected() {
        return this.request.getSession().getAttribute("user") != null;
    }

    public boolean isAdmin() {
        boolean isAdmin = false;

        if(isConnected()) {
            if (((User)this.request.getSession().getAttribute("user")).isAdmin()) {
                isAdmin = true;
            }
        }
        return isAdmin;
    }
}
