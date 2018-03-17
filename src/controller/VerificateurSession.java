package controller;

import model.beans.Utilisateur;

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
        return ((Utilisateur)this.request.getSession().getAttribute("user")).isAdmin();
    }
}
