package model.DAO;

import model.beans.Administrateur;
import model.beans.Client;
import model.beans.Utilisateur;
import model.requests.Connection;

import java.sql.*;

public class UserDAO {
    private java.sql.Connection connection;

    public UserDAO() {
        connection = Connection.getInstance();
    }

    public Utilisateur getUser(String email, String hashed_password) {
        Utilisateur user = null;
        try {
            PreparedStatement statement = connection.prepareStatement( "SELECT * FROM user where identifiant=? and password=?;");

            statement.setString(1, email);
            statement.setString(2, hashed_password);

            ResultSet resultat = statement.executeQuery();


            if(resultat.next()) {



                int id = resultat.getInt("idUser");
                String identifiant = resultat.getString("identifiant");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                Date naissance =  resultat.getDate("naissance");
                String adresse = resultat.getString("adresse");

                statement = connection.prepareStatement( "SELECT * FROM administrateur where ref_admin=?;");

                statement.setInt(1, id);

                resultat = statement.executeQuery();

                if(resultat.next()) {
                    user = new Utilisateur(id, identifiant, nom, prenom, naissance, adresse, true);
                } else {
                    user = new Utilisateur(id, identifiant, nom, prenom, naissance, adresse, false);
                }
            } else {
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
