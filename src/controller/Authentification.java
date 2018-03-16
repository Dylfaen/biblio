package controller;
import model.DAO.ClientDAO;
import model.beans.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authentification extends javax.servlet.http.HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageTitle", "Authentification");

        String username = request.getParameter("username");
        String password = this.get_SHA_512_SecurePassword(request.getParameter("password"), "");

        Client client = new ClientDAO()
                .getClient(username, password);


        System.out.println("username : " + username);
        System.out.println("password : " + password);

        if(client == null) {
            request.setAttribute("erreur", "Identifiant ou mot de passe incorrecte");
            request.setAttribute("username", username);
            this.getServletContext().getRequestDispatcher( "/WEB-INF/view/authentification.jsp" ).forward( request, response );
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", client);
            response.sendRedirect("/");
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageTitle", "Authentification");
        request.setAttribute("erreur", null);
        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/authentification.jsp" ).forward( request, response );
    }

    private String get_SHA_512_SecurePassword(String passwordToHash, String salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
