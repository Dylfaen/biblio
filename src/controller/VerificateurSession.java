package controller;

import model.beans.User;

import javax.servlet.http.HttpServletRequest;

public class VerificateurSession {

    HttpServletRequest request;

    public VerificateurSession(HttpServletRequest request) {
        this.request = request;
    }

    public boolean isConnected() {
        return this.request.getSession().getAttribute("user") == null;
    }

    public boolean isAdmin() {
        return ((User)this.request.getSession().getAttribute("user")).isAdmin();
    }
}
